package com.igormeshalkin.testtask.videowebserver.controller;

import com.igormeshalkin.testtask.videowebserver.exception.UploadVideoException;
import com.igormeshalkin.testtask.videowebserver.fileupload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/loading")
public class LoadingController {
    private final FileUploadManager fileUploadManager;

    public LoadingController(FileUploadManager fileUploadManager) {
        this.fileUploadManager = fileUploadManager;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String uploadSubmit() {
        return "upload";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadSubmit(@RequestParam("file") MultipartFile file, Model model) {
        try {
            fileUploadManager.addUploadToPool(file);
        } catch (UploadVideoException ex) {
            model.addAttribute("message", ex.getMessage());
            return "errorPage";
        }
        return "redirect:/";
    }

    @RequestMapping(value = "/download", method = RequestMethod.GET)
    public void downloadFile(@RequestParam(value = "filePath", required = false) String filePath,
                             @RequestParam(value = "fileName", required = false) String fileName,
                             HttpServletRequest request, HttpServletResponse response) {
        Path file = Paths.get(filePath, fileName);
        if (Files.exists(file)) {
            response.setContentType("video/" + fileName.split("\\.")[1]);
            response.addHeader("Content-Disposition", "attachment; filename=" + fileName);

            try {
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
