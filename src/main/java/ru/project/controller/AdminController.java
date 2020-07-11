package ru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.project.model.User;
import ru.project.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/all")
    public String printAllUsers(ModelMap model, HttpServletRequest httpSession) {
        model.addAttribute("user", httpSession.getSession().getAttribute("user"));
        return "admin";
    }

}