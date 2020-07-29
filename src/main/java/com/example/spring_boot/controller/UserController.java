package com.example.spring_boot.controller;

import com.example.spring_boot.entity.Role;
import com.example.spring_boot.entity.User;
import com.example.spring_boot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.DoubleToIntFunction;

@Controller
@RequestMapping("")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/admin")
    public String getAdminPage(Model model, Authentication authentication) {
        User userAuthority = userService.findByLogin(authentication.getName());
        List<User> users = userService.listUsers();
        model.addAttribute("userAuthority", userAuthority);
        model.addAttribute("users", users);
        model.addAttribute("newUser", (new User()));
        return "/admin";
    }

    @GetMapping("/user")
    public String getUserPage(Model model, Authentication authentication) {
        User userAuthority = userService.findByLogin(authentication.getName());
        model.addAttribute("userAuthority", userAuthority);
        return "/user";
    }

    @PostMapping("/admin/edit")
    public String editUser(@ModelAttribute("editUser") User editUser, Model model) {
        userService.edit(editUser);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(@RequestParam(value = "id") Long id, Model model) {
        model.addAttribute("id", id);
        userService.delete(id);
        return "redirect:/admin";
    }

    @PostMapping("/admin/save")
    public String saveUser(@ModelAttribute("newUser") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "/login";
    }



}
