package ru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.project.service.UserService;
import ru.project.model.User;



@Controller
@RequestMapping("/")
public class RegistrationController {

@Autowired
    public RegistrationController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/registration")
    public String registrationGet() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationPost(@ModelAttribute("user") User user, ModelMap modelMap) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.save(user);
        return "redirect:/login";
    }

}
