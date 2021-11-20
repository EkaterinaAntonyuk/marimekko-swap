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
public class ItemsController {
    private final ItemsService itemsService;

    public ItemsController(ItemsService itemsService) {
        this.itemsService = itemsService;
    }

    @GetMapping("/explore")
    public String getFilteredItems(Model model, HttpSession httpSession, @RequestParam(required = false) ItemType itemType,
                                   @RequestParam(required = false) List<String> sizes,
                                   @RequestParam(defaultValue = "false") Boolean isAvailableNextMonth) {
        final FiltersDto filters = new FiltersDto(
                itemType,
                sizes,
                isAvailableNextMonth
        );
        model.addAttribute("filters", filters);
        if (itemType == null && sizes == null && !isAvailableNextMonth) {
            model.addAttribute("items", itemsService.getAllItems((Long) httpSession.getAttribute("user")));
        } else {
            model.addAttribute("items", itemsService.getFilteredItems(itemType, sizes, isAvailableNextMonth));
        }
        return "items";
    }

}
