package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // Root → redirect to index page
    @GetMapping("/")
    public String home() {
        return "index";   // -> src/main/resources/templates/index.html
    }

    // List
    @GetMapping("/users")
    public String list(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";   // -> src/main/resources/templates/users.html
    }

    // Show add form
    @GetMapping("/users/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";   // -> src/main/resources/templates/add-user.html
    }

    // Save new
    @PostMapping("/users/save")
    public String save(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/users";
    }

    // Show edit form
    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit-user";   // -> src/main/resources/templates/edit-user.html
    }

    // Update existing
    @PostMapping("/users/update")
    public String update(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/users";
    }

    // Delete
    @GetMapping("/users/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}