package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import project.model.User;
import project.service.Service;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private Service service;

    @GetMapping(value = "/all")
    public String printAllUsers(ModelMap model) {
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @PostMapping(value = "/add")
    public String addUser(ModelMap model, @RequestParam Map<String, String> params) {
        service.add(new User(params.get("name"), params.get("family"), Long.parseLong(params.get("balans"))));
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @GetMapping(value = "/delete")
    public String deleteUser(ModelMap model, @RequestParam Map<String, String> params) {
        service.remove(new User(Long.parseLong(params.get("idToDelete"))));
        model.addAttribute("users", service.getAll());
        return "users";
    }

    @GetMapping(value = "/update")
    public String goToUpdateUser(ModelMap model, @RequestParam Map<String, String> params) {
        model.addAttribute("user", service.getById(Long.parseLong(params.get("idToUpdate"))));
        return "update";
    }

    @PostMapping(value = "/update")
    public String updateUser(ModelMap model, @RequestParam Map<String, String> params) {
        service.update(new User(Long.parseLong(params.get("id")),params.get("name"),
                params.get("family"), Long.parseLong(params.get("balans"))));
        model.addAttribute("users", service.getAll());
        return "users";
    }

}