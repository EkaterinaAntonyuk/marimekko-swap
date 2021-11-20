package com.marimekko.swap.controller;

import com.marimekko.swap.dto.FiltersDto;
import com.marimekko.swap.model.ItemType;
import com.marimekko.swap.service.ItemsService;
import com.marimekko.swap.service.ProfileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ItemsController {
    private final ItemsService itemsService;
    private final ProfileService profileService;

    public ItemsController(ItemsService itemsService, ProfileService profileService) {
        this.itemsService = itemsService;
        this.profileService = profileService;
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
        final Long user = (Long) httpSession.getAttribute("user");
        if (itemType == null && sizes == null && !isAvailableNextMonth) {
            model.addAttribute("items", itemsService.getAllItems(user));
        } else {
            model.addAttribute("items", itemsService.getFilteredItems(user, itemType, sizes, isAvailableNextMonth));
        }
        model.addAttribute("user", profileService.getUser(user));
        return "items";
    }

    @PostMapping("/book/{itemId}")
    public String bookItem(@PathVariable Long itemId, HttpSession httpSession) {
        final Long user = (Long) httpSession.getAttribute("user");
        itemsService.bookItem(user, itemId);
        return "redirect:/explore";
    }

    @PostMapping("/get-back")
    public String getBack(HttpSession httpSession) {
        final Long user = (Long) httpSession.getAttribute("user");
        itemsService.getItemBack(user);
        return "redirect:/profile";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
