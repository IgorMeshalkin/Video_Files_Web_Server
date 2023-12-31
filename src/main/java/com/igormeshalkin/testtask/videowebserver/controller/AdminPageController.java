package com.igormeshalkin.testtask.videowebserver.controller;

import com.igormeshalkin.testtask.videowebserver.fileupload.FileUploadManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminPageController {

    @RequestMapping(method = RequestMethod.GET)
    public String getAllUploadTrackers(Model model) {
        model.addAttribute("uploadingFiles", FileUploadManager.getUploadTrackers());
        return "adminsDashboard";
    }
}
