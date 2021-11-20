package com.marimekko.swap.service;

import com.marimekko.swap.dto.ItemAvailabilityDto;
import com.marimekko.swap.model.Item;
import com.marimekko.swap.model.ItemType;
import com.marimekko.swap.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<ItemAvailabilityDto> getAllItems() {
        return itemsRepository.getAllItems().stream()
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    public List<ItemAvailabilityDto> getFilteredItems(ItemType itemType, List<String> sizes, Boolean isAvailable) {
        return itemsRepository.getFilteredItems(itemType, sizes, isAvailable).stream()
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    public List<Item> getOwnItems(int userId) {
        return itemsRepository.getItemsByOwnerId(userId);
    }
}
