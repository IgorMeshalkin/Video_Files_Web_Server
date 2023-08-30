package com.igormeshalkin.testtask.videowebserver.fileupload;

import com.igormeshalkin.testtask.videowebserver.dao.FileDAO;
import com.igormeshalkin.testtask.videowebserver.exception.UploadVideoException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Component
public class FileUploadManager {
    private final FileDAO fileDAO;

    private final String MORE_THEN_TWO_UPLOADS_MESSAGE = "Вы не можете загружать больше двух файлов одновременно";
    private final String NOT_VIDEO_FILE_MESSAGE = "Вы можете загружать только видео файлы";

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private volatile static Set<UploadTracker> uploadTrackers = new HashSet<>();

    public FileUploadManager(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void addUploadToPool(MultipartFile file) throws UploadVideoException, ExecutionException, InterruptedException {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (!file.getContentType().split("/")[0].equals("video")) {
            throw new UploadVideoException(NOT_VIDEO_FILE_MESSAGE);
        }

        if (uploadTrackers.stream().filter(ut -> ut.getOwner().equals(currentUserName)).collect(Collectors.toSet()).size() < 2) {
            Future<?> future = executorService.submit(new UploadAction(currentUserName, file, fileDAO));
            UploadTracker uploadTracker = new UploadTracker(currentUserName, file.getOriginalFilename(), file.getSize());
            uploadTrackers.add(uploadTracker);
            future.get();
        } else {
            throw new UploadVideoException(MORE_THEN_TWO_UPLOADS_MESSAGE);
        }
    }

    public static Set<UploadTracker> getUploadTrackers() {
        return uploadTrackers;
    }

    public static synchronized void removeTracker(UploadTracker tracker) {
        uploadTrackers.remove(tracker);
    }
}
