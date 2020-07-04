package ru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.project.model.Role;
import ru.project.model.User;
import ru.project.service.UserUserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserUserServiceImpl userUserService;

    @Autowired
    public UserController(UserUserServiceImpl userUserService) {
        this.userUserService = userUserService;
    }

    @GetMapping("/me")
    public String hello(ModelMap modelMap, HttpServletRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//
        User user = (User) authentication.getPrincipal();
        Set<Role> set = user.getRoles();
        String text = set.toString().replaceAll("(?u)[^\\pL ]", "");
        modelMap.addAttribute("email", user.getEmail());
        modelMap.addAttribute("roles", text);
        modelMap.addAttribute("user",user);
        return "user";
    }
}
