package com.johurulislam.controller;

import com.johurulislam.model.User;
import com.johurulislam.service.UserService;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MainController {
    public UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/home")
    public ModelAndView getHomePage(ModelAndView modelAndView) {
        modelAndView.setViewName("HomePage");
        modelAndView.addObject("name", "Johurul Islam");
        return modelAndView;
    }
    @PostMapping("/submit")
    public ModelAndView submitProfilePage(ModelAndView modelAndView, RedirectAttributes redirectAttributes, @ModelAttribute User user) {
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("success", "Data Saved");
        modelAndView.setViewName("redirect:/home");
        return modelAndView;
    }
    @GetMapping("/user-details")
    public ModelAndView viewProfilePage(ModelAndView modelAndView) {
        modelAndView.setViewName("UserDetails");

        modelAndView.addObject("userList",userService.getAllUsers());
        System.out.println(userService.getAllUsers());
        return modelAndView;
    }

}
