package com.igormeshalkin.testtask.videowebserver.fileupload;

import com.igormeshalkin.testtask.videowebserver.dao.FileDAO;
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

    private static final ExecutorService executorService = Executors.newFixedThreadPool(10);
    private static final Set<UploadTracker> uploadTrackers = new HashSet<>();

    public FileUploadManager(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    public void addUploadToPool(MultipartFile file) {
        String currentUserName = SecurityContextHolder.getContext().getAuthentication().getName();

        if (uploadTrackers.stream().filter(ut -> ut.getOwner().equals(currentUserName)).collect(Collectors.toSet()).size() < 2) {
            Future<?> future = executorService.submit(new UploadAction(currentUserName, file, fileDAO));
            UploadTracker uploadTracker = new UploadTracker(currentUserName, file.getOriginalFilename(), file.getSize());
            uploadTrackers.add(uploadTracker);
            try {
                future.get();
                uploadTrackers.remove(uploadTracker);
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("У этого юзера уже две задачи, пусть подождёт");
        }
    }

    public Set<UploadTracker> getUploadTrackers() {
        return uploadTrackers;
    }
}
