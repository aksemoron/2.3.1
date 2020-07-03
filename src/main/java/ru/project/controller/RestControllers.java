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
    private UserService userService;

    @Autowired
    public RestControllers(UserService userService) {
        this.userService = userService;
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
    public boolean updateUser(@RequestBody User user){
        return userService.update(user);
    }



    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable Long id){
        return (User) userService.getById(id);
    }

    @GetMapping("/deleteUser/{id}")
    public void deleteUserById(@PathVariable Long id){
        userService.remove(new User(id));
    }


}
