package com.marimekko.swap.service;

import com.marimekko.swap.dto.ItemAvailabilityDto;
import com.marimekko.swap.model.Item;
import com.marimekko.swap.model.ItemType;
import com.marimekko.swap.repository.ItemRepository;
import com.marimekko.swap.repository.ItemsRepository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.temporal.TemporalUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final ItemRepository itemRepository;

    public ItemsService(ItemsRepository itemsRepository, ItemRepository itemRepository) {
        this.itemsRepository = itemsRepository;
        this.itemRepository = itemRepository;
    }

    public List<ItemAvailabilityDto> getAllItems(Long demoUserId) {
        final List<ItemAvailabilityDto> availableItems = itemRepository.getAvailableItems(demoUserId);
        availableItems.forEach(item -> {
            if (item.getAvailableMonth() == null) {
                item.setAvailableMonth(Instant.now());
            } else {
                item.setAvailableMonth(item.getAvailableMonth().atZone(ZoneId.of("UTC")).plus(1, java.time.temporal.ChronoUnit.MONTHS).toInstant());
            }
        });
        return availableItems.stream()
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    public List<ItemAvailabilityDto> getFilteredItems(ItemType itemType, List<String> sizes, Boolean isAvailable) {
        return itemsRepository.getFilteredItems(itemType, sizes, isAvailable).stream()
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    public List<Item> getOwnItems(Long userId) {
        return itemRepository.findAllByOwnerId(userId);
    }
}
