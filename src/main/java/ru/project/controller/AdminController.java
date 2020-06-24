package ru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.project.model.User;
import ru.project.service.UserService;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping(value = "/all")
    public String printAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAll());
        return "admin";
    }

    @PostMapping(value = "/add")
    public String addUser(ModelMap model, @RequestParam Map<String, String> params) {
        userService.save(new User(params.get("name"), params.get("family"), Long.parseLong(params.get("balans"))));
        model.addAttribute("users", userService.getAll());
        return "allUsers";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(ModelMap model, @RequestParam Map<String, String> params) {
        userService.remove(new User(Long.parseLong(params.get("idToDelete"))));
        model.addAttribute("users", userService.getAll());
        return "allUsers";
    }

    @GetMapping(value = "/update")
    public String goToUpdateUser(ModelMap model, @RequestParam Map<String, String> params) {
        model.addAttribute("user", userService.getById(Long.parseLong(params.get("idToUpdate"))));
        return "update";
    }

    @PostMapping(value = "/update")
    public String updateUser(ModelMap model, @RequestParam Map<String, String> params) {
        userService.update(new User(Long.parseLong(params.get("id")),params.get("name"),
                params.get("family"), Long.parseLong(params.get("balans"))));
        model.addAttribute("users", userService.getAll());
        return "allUsers";
    }

}