package com.example.accessingdatamysql.controller;

import com.example.accessingdatamysql.model.User;
import com.example.accessingdatamysql.view.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
public class MainController {


    private final UserRepository userRepository;
    @Autowired
    public MainController(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @PostMapping("/add")
    public @ResponseBody String addNewUser
            (@RequestParam String name, @RequestParam String password) {

        User user = new User();
        user.setName(name);
        user.setPassword(password);

        userRepository.save(user);

        return "success";

    }

    @GetMapping("/get")
    public @ResponseBody String getUser (@RequestParam String name, @RequestParam String password) {
        User user = userRepository.findUserByNameAndPassword(name, password);
        if(user == null)
        {
            return "No such user";
        }
        else
        {
            return user.getName();
        }


    }

    @GetMapping("/all")
    public @ResponseBody Iterable<User> getAllUsers(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("success;");
        return userRepository.findAll();
    }

}
