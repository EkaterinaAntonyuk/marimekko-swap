package com.marimekko.swap.controller;

import com.marimekko.swap.dto.FiltersDto;
import com.marimekko.swap.model.ItemType;
import com.marimekko.swap.service.ItemsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class OwnItemsController {
    private final ItemsService itemsService;

    public OwnItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("/own-clothes")
    public String getFilteredItems(Model model, HttpSession session) {
        model.addAttribute("items", itemsService.getOwnItems((Long) session.getAttribute("user")));
        return "own-items";
    }

}
