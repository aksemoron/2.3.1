package ru.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.project.dao.RoleDao;
import ru.project.model.Role;
import ru.project.model.User;
import ru.project.service.UserService;

import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {


    private UserService userService;
    private RoleDao roleDao;

    @Autowired
    public AdminController(UserService userService, RoleDao roleDao) {
        this.userService = userService;
        this.roleDao = roleDao;
    }

    @GetMapping(value = "/all")
    public String printAllUsers(ModelMap modelMap) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//
        User user = (User) authentication.getPrincipal();
        Set<Role> set = user.getRoles();
        String text = set.toString().replaceAll("(?u)[^\\pL ]", "");
        List<User> users = userService.getAll();
        modelMap.addAttribute("email", user.getEmail());
        modelMap.addAttribute("roles", text);
        modelMap.addAttribute("user",user);
        modelMap.addAttribute("allUsers",users);
        return "admin";
    }

    @PostMapping(value = "/add")
    public String addUser(ModelMap model, @RequestParam Map<String, String> params) {
        System.out.println(params.get("emailNewUser")+"----------------------------------------------------------");
        /*User user = new User(params.get("emailNewUser"),
                Long.parseLong(params.get("age")),params.get("password"), params.get("name"),
                params.get("family"));
        user.setRoles(new HashSet<Role>((Collection<? extends Role>) roleDao.getOne(Long.parseLong(params.get("roleNewUser")))));
        userService.save(user);*/
        model.addAttribute("users", userService.getAll());
        return "admin";
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