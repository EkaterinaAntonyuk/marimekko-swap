package com.marimekko.swap.service;

import com.marimekko.swap.dto.ItemAvailabilityDto;
import com.marimekko.swap.model.Item;
import com.marimekko.swap.model.ItemType;
import com.marimekko.swap.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;

    public ItemsService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<ItemAvailabilityDto> getAllItems(){
        return itemsRepository.getAllItems();
    }

    public List<ItemAvailabilityDto> getFilteredItems(ItemType itemType, List<String> sizes, Boolean isAvailable) {
        return itemsRepository.getFilteredItems(itemType,sizes, isAvailable);
    }
}
