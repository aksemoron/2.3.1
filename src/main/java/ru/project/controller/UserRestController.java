package ru.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.project.model.Role;
import ru.project.model.User;
import ru.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.Set;

@RestController
@RequestMapping("/user/rest")
public class UserRestController {


    private HttpSession httpSession;
    private UserService userService;

    @Autowired
    public UserRestController(HttpSession httpSession, UserService userService) {
        this.httpSession = httpSession;
        this.userService = userService;
    }

    @GetMapping("/getUser")
    public User getUser() {
        User user = (User) httpSession.getAttribute("user");
        return user;
    }

    @GetMapping("/emailOfUser")
    public ResponseEntity<String> getEmail() {
        User user = (User) httpSession.getAttribute("user");
        String text = user.getEmail();
        return new ResponseEntity<>(text, HttpStatus.OK);
    }


    @GetMapping("/RolesOfUser")
    public ResponseEntity<String> getRoles() {
        User user = (User) httpSession.getAttribute("user");
        Set<Role> set =user.getRoles();
        String text =set.toString().replaceAll("(?u)[^\\pL ]","");

        return new ResponseEntity<>(text, HttpStatus.OK);
    }
}
