package com.gazatem.ekip.controller;


import com.gazatem.ekip.model.Role;
import com.gazatem.ekip.model.User;
import com.gazatem.ekip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Controller
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin/users/list", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);
        modelAndView.setViewName("admin/users/list");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/users/teams", method = RequestMethod.GET)
    public ModelAndView teams() {
        ModelAndView modelAndView = new ModelAndView();
        /*Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage", "Content Available Only for Users with Admin Role");
        */
        modelAndView.setViewName("admin/users/list");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/users/create", method = RequestMethod.GET)
    public ModelAndView create() {
        ModelAndView modelAndView = new ModelAndView();
        List<Role> roles = userService.findAllRoles();
        modelAndView.addObject("roles", roles);
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("admin/users/create");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/users/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid User user, BindingResult bindingResult) {

        logger.info("This is a debug message");

        List<Role> roles = userService.findAllRoles();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("roles", roles);


        User userExists = userService.findUserByEmail(user.getEmail());
        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            logger.info("This is a error message"  + bindingResult.toString());
            modelAndView.setViewName("admin/users/create");
        } else {
            userService.createUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("admin/users/create");
        }
        return modelAndView;
    }

}
