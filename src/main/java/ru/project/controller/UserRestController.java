package ru.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.model.Role;
import ru.project.model.User;
import ru.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.lang.ref.PhantomReference;
import java.util.Set;

@RestController
@RequestMapping("/user/rest")
public class UserRestController {
    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public User getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//
        return (User) authentication.getPrincipal();
    }

    @GetMapping("/emailOfUser")
    public ResponseEntity<String> getEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        String text = user.getEmail();
        return new ResponseEntity<>(text, HttpStatus.OK);
    }


    @GetMapping("/RolesOfUser")
    public ResponseEntity<String> getRoles() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//
        User user = (User) authentication.getPrincipal();
        Set<Role> set =user.getRoles();
        String text =set.toString().replaceAll("(?u)[^\\pL ]","");

        return new ResponseEntity<>(text, HttpStatus.OK);
    }
}
