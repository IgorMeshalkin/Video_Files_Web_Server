package com.igormeshalkin.testtask.videowebserver.fileupload;

import com.igormeshalkin.testtask.videowebserver.config.FilesStorage;
import com.igormeshalkin.testtask.videowebserver.dao.FileDAO;
import com.igormeshalkin.testtask.videowebserver.entity.File;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;


public class UploadAction implements Runnable {
    private final String username;
    private final MultipartFile file;
    private final FileDAO fileDAO;

    public UploadAction(String username, MultipartFile file, FileDAO fileDAO) {
        this.username = username;
        this.file = file;
        this.fileDAO = fileDAO;
    }

    @Override
    public void run() {
        //Получает трекер текущей загрузки
        UploadTracker thisTracker = FileUploadManager.getUploadTrackers()
                .stream()
                .filter(ut -> ut.getOwner().equals(username) && ut.getFileName().equals(file.getOriginalFilename()))
                .findFirst()
                .orElse(null);

        //Открывает потоки ввода-вывода для сохранения файла.
        try (BufferedInputStream inputStream = new BufferedInputStream(file.getInputStream());
             BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(FilesStorage.FILES_STORAGE_PATH + file.getOriginalFilename()))) {
            int i;
            int bitesCounter = 0;
            int percentsCounter = 0;
            while ((i = inputStream.read()) != -1) {
                ++bitesCounter;
                if (bitesCounter % (file.getSize() / 20) == 0) {

                    //Задержка для того что бы тестировать работу приложения локально, когда файлы сохраняются очень быстро.
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    //Когда счётчик процентов достигает 100, трекер удаляется из списка (перестаёт отображаться на дашборде админа)
                    percentsCounter += 5;
                    if (thisTracker != null) {
                        thisTracker.setProgress(percentsCounter);
                        if (percentsCounter == 100) {
                            FileUploadManager.removeTracker(thisTracker);
                        }
                    }
                }
                outputStream.write(i);
            }

            //Информация о загруженном файле и его собственнике сохраняется в БД.
            File newFile = new File(username, file.getOriginalFilename(), file.getSize(), FilesStorage.FILES_STORAGE_PATH);
            fileDAO.save(newFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
