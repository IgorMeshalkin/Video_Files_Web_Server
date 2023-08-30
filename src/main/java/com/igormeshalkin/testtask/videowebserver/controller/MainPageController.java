package com.igormeshalkin.testtask.videowebserver.controller;

import com.igormeshalkin.testtask.videowebserver.dao.FileDAO;
import com.igormeshalkin.testtask.videowebserver.entity.File;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainPageController {
    private final FileDAO fileDAO;

    public MainPageController(FileDAO fileDAO) {
        this.fileDAO = fileDAO;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String main(Model model) {
        List<File> myFiles = fileDAO.findAllByCurrentUser();
        model.addAttribute("actualFiles", myFiles);
        return "main";
    }
}
