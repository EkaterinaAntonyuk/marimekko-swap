package com.marimekko.swap.controller;

import com.marimekko.swap.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model, HttpSession session) {
        model.addAttribute("profile", profileService.getProfile((Long) session.getAttribute("user")));
        return "profile";
    }
}
