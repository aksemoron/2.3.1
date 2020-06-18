package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.Service;
import project.model.User;

import java.util.Map;

@Controller
@RequestMapping("/")
public class RegistrationController {

    @Autowired
    private Service service;

    @GetMapping(value = "/registration")
    public String registrationGet() {
        return "registration";
    }

    @PostMapping(value = "/registration")
    public String registrationPost(@RequestParam Map<String, String> params) {
        User user = new User(params.get("login"), params.get("password"), params.get("name"), params.get("family"), Long.parseLong(params.get("balans")));
        service.save(user);
        return "registration";
    }

}
