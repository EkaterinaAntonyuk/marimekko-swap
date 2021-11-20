package com.marimekko.swap.controller;

import com.marimekko.swap.service.ItemsService;
import com.marimekko.swap.service.ProfileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@Controller
public class OwnItemsController {
    private final ItemsService itemsService;
    private final ProfileService profileService;

    public OwnItemsController(ItemsService itemsService, ProfileService profileService) {
        this.itemsService = itemsService;
        this.profileService = profileService;
    }

    @GetMapping("/own-clothes")
    public String getFilteredItems(Model model, HttpSession session) {
        final Long userId = (Long) session.getAttribute("user");
        model.addAttribute("items", itemsService.getOwnItems(userId));
        model.addAttribute("user", profileService.getUser(userId));
        return "own-items";
    }

}
