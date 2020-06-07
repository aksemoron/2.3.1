package project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.model.User;
import project.service.ServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class UsersController {

    @Autowired
    public ServiceInterface serviceInterface;

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public String printAllUsers(ModelMap model) {
        model.addAttribute("users", serviceInterface.getAll());
        return "users";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String addUser(ModelMap model, @RequestParam Map<String, String> params) {
        serviceInterface.add(new User(params.get("name"), params.get("family"), Long.parseLong(params.get("balans"))));
        model.addAttribute("users", serviceInterface.getAll());
        return "users";
    }

    @RequestMapping(value = "delete", method = RequestMethod.GET)
    public String deleteUser(ModelMap model, @RequestParam Map<String, String> params) {
        serviceInterface.remove(new User(Long.parseLong(params.get("idToDelete"))));
        model.addAttribute("users", serviceInterface.getAll());
        return "users";
    }

    @RequestMapping(value = "update", method = RequestMethod.GET)
    public String goToUpdateUser(ModelMap model, @RequestParam Map<String, String> params) {
        model.addAttribute("user", serviceInterface.getById(Long.parseLong(params.get("idToUpdate"))));
        return "update";
    }

    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String updateUser(ModelMap model, @RequestParam Map<String, String> params) {
        serviceInterface.update(new User(Long.parseLong(params.get("id")),params.get("name"),
                params.get("family"), Long.parseLong(params.get("balans"))));
        model.addAttribute("users", serviceInterface.getAll());
        return "users";
    }

}