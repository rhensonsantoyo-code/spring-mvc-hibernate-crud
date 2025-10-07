package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "index";
    }

    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";
    }

    @GetMapping("/users/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";
    }

    @PostMapping("/users/save")
    public String save(@Valid @ModelAttribute("user") User user,
                       BindingResult result) {
        if (result.hasErrors()) {
            return "add-user"; // redisplay with error messages
        }
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit-user";
    }

    @PostMapping("/users/update")
    public String update(@Valid @ModelAttribute("user") User user,
                         BindingResult result) {
        if (result.hasErrors()) {
            return "edit-user";
        }
        service.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}