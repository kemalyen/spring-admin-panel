package com.gazatem.ekip.controller;


import com.gazatem.ekip.model.User;
import com.gazatem.ekip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TaskController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/tasks/my-tasks", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("admin/tasks/my-tasks");
        return modelAndView;
    }

}
