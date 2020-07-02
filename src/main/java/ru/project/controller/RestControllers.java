package ru.project.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.project.model.Role;
import ru.project.model.User;
import ru.project.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/admin/rest")
public class RestControllers {
    private HttpSession httpSession;
    private UserService userService;

    @Autowired
    public RestControllers( HttpSession httpSession, UserService userService) {
        this.httpSession = httpSession;
        this.userService = userService;
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


    @GetMapping("/tableOfUsers")
    public List<User>  getTable() {
        List<User> list = userService.getAll();
         return list;
    }

    @PostMapping("/addNewUser")
    public void addNewUser( @RequestBody User user){
        userService.save(user);
    }

    @PostMapping("/updateUser")
    public void updateUser(@RequestBody User user){
        userService.update(user);
    }

    @GetMapping("/getUser/{id}")
    public User gettingUserById(@PathVariable Long id){
        return (User) userService.getById(id);
    }


}
