package org.example.controller;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // List
    @GetMapping
    public String list(Model model) {
        model.addAttribute("users", service.getAllUsers());
        return "users";        // -> /WEB-INF/views/users.jsp
    }

    // Show add form
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("user", new User());
        return "add-user";     // -> /WEB-INF/views/add-user.jsp
    }

    // Save new
    @PostMapping("/save")
    public String save(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/users";
    }

    // Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        model.addAttribute("user", service.getUserById(id));
        return "edit-user";    // -> /WEB-INF/views/edit-user.jsp
    }

    // Update existing
    @PostMapping("/update")
    public String update(@ModelAttribute User user) {
        service.saveUser(user);
        return "redirect:/users";
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteUser(id);
        return "redirect:/users";
    }
}