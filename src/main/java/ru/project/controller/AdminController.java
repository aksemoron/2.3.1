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
    public String addUser(@RequestParam Map<String, String> params) {
       User user = new User(params.get("emailNewUser"),
                Long.parseLong(params.get("age")),params.get("password"), params.get("name"),
                params.get("family"));
       Set<Role> roles= new HashSet<>();
       roles.add(roleDao.getOne(Long.parseLong(params.get("roleNewUser"))));
        user.setRoles(roles);
       userService.save(user);
        return "redirect:/admin/all";
    }

    @GetMapping(value = "/showDelete")
    public String goToDeleteUser(ModelMap modelMap, @RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//
        User user = (User) authentication.getPrincipal();
        User userToDelite = (User) userService.getById(Long.parseLong(params.get("deleteButton")));
        Set<Role> set = user.getRoles();
        String text = set.toString().replaceAll("(?u)[^\\pL ]", "");
        List<User> users = userService.getAll();
        modelMap.addAttribute("email2", user.getEmail());
        modelMap.addAttribute("roles2", text);
        modelMap.addAttribute("user2",user);
        modelMap.addAttribute("allUsers2",users);
        modelMap.addAttribute("userToDelite",userToDelite);
        return "delete";
    }

    @GetMapping(value = "/showUpdate")
    public String goToUpdateUser(ModelMap modelMap, @RequestParam Map<String, String> params) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        Set<Role> set = user.getRoles();
        User userToUpdate = (User) userService.getById(Long.parseLong(params.get("updateButton")));
        String text = set.toString().replaceAll("(?u)[^\\pL ]", "");
        List<User> users = userService.getAll();
        modelMap.addAttribute("email1", user.getEmail());
        modelMap.addAttribute("roles1", text);
        modelMap.addAttribute("user1",user);
        modelMap.addAttribute("allUsers1",users);
        modelMap.addAttribute("userToUpdate",userToUpdate);
        return "update";
    }

    @PostMapping  (value = "/updateUser")
    public String updateUser(ModelMap model, @RequestParam Map<String, String> params) {
        System.out.println(params.size()+"------------------------------");
        User user1 = new User(params.get("emailUpdate"),
                Long.parseLong(params.get("ageUpdate")),params.get("passwordUpdate"), params.get("nameUpdate"),
                params.get("familyUpdate"));
        user1.setId(Long.parseLong(params.get("idToUpdate")));
        Set<Role> roles= new HashSet<>();
        roles.add(roleDao.getOne(Long.parseLong(params.get("roleUpdate"))));
        user1.setRoles(roles);
        userService.update(user1);
        return "redirect:/admin/all";
    }

    @PostMapping(value = "/deleteUser")
    public String deleteUser(ModelMap model, @RequestParam Map<String, String> params) {
        userService.remove(new User(Long.parseLong(params.get("deleteUser"))));
        return "redirect:/admin/all";
    }

}