package myy803.traineeship_app.controller;

import myy803.traineeship_app.dtos.UserDto;
import myy803.traineeship_app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
public class UserController {


    private static final String MESSAGE = "MESSAGE";

    @Autowired
    private UserService userService;

    @RequestMapping("/register")
    public String login(Model model) {
        model.addAttribute("user", new UserDto());
        return "auth/register";
    }

    @PostMapping("/save")
    public String addUser(@ModelAttribute("user") UserDto user, Model theModel) {

        if (userService.isUserPresent(user)){
            theModel.addAttribute(MESSAGE, "User "+ user.getUsername() +" is already registered");
            return "auth/login";
        }
        userService.saveUser(user);
        theModel.addAttribute(MESSAGE, "User "+ user.getUsername()+ "added successfully");
        return "auth/login";
    }
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
}

