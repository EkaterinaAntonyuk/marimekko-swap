package com.marimekko.swap.controller;

import com.marimekko.swap.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute(profileService.getProfile(1));
        return "profile";
    }
}
