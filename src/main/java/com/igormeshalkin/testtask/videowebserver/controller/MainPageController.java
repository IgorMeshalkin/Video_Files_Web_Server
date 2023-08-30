package com.igormeshalkin.testtask.videowebserver.controller;

import com.igormeshalkin.testtask.videowebserver.dao.FileDAO;
import com.igormeshalkin.testtask.videowebserver.entity.File;
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
import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {
    private final FileUploadManager fileUploadManager;
    private final FileDAO fileDAO;

    public MainPageController(FileUploadManager fileUploadManager, FileDAO fileDAO) {
        this.fileUploadManager = fileUploadManager;
        this.fileDAO = fileDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        List<File> myFiles = fileDAO.findAllByCurrentUser();
        model.addAttribute("actualFiles", myFiles);
        return "main";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public String uploadSubmit(@RequestParam("file") MultipartFile file) {
        fileUploadManager.addUploadToPool(file);
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
