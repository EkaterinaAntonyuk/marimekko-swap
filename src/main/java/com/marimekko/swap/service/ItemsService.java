package com.marimekko.swap.service;

import com.marimekko.swap.dto.ItemAvailabilityDto;
import com.marimekko.swap.model.Item;
import com.marimekko.swap.model.ItemType;
import com.marimekko.swap.repository.ItemRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.YearMonth;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static com.marimekko.swap.utils.MonthUtils.getMonthsBetweenInstants;
import static com.marimekko.swap.utils.MonthUtils.monthStart;

@Service
public class ItemsService {
    private final ItemRepository itemRepository;

    public ItemsService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public List<ItemAvailabilityDto> getAllItems(Long demoUserId) {
        final List<ItemAvailabilityDto> availableItems = getAvailableItmes(demoUserId);
        return availableItems.stream()
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    public List<ItemAvailabilityDto> getFilteredItems(Long demoUserId, ItemType itemType, List<String> sizes, Boolean isAvailable) {
        final List<ItemAvailabilityDto> availableItems = getAvailableItmes(demoUserId);
        return availableItems.stream()
                .filter(itemAvailabilityDto -> {
                    final Item item = itemAvailabilityDto.getItem();
                    var include = true;
                    if (itemType != null) {
                        include = item.getItemType().equals(itemType);
                    }
                    if (include && sizes != null && sizes.size() > 0) {
                        include = sizes.contains(item.getItemSize());
                    }
                    if(include && isAvailable) {
                        include = getMonthsBetweenInstants(itemAvailabilityDto.getAvailableMonth(), Instant.now()) == 1;
                    }
                    return include;
                })
                .sorted(Comparator.comparing(ItemAvailabilityDto::getAvailableMonth))
                .collect(Collectors.toList());
    }

    private List<ItemAvailabilityDto> getAvailableItmes(Long demoUserId) {
        final List<ItemAvailabilityDto> availableItems = itemRepository.getAvailableItems(demoUserId);
        availableItems.forEach(item -> {
            if (item.getAvailableMonth() == null) {
                item.setAvailableMonth(Instant.now());
            } else {
                item.setAvailableMonth(item.getAvailableMonth().atZone(ZoneId.of("UTC")).plus(1, java.time.temporal.ChronoUnit.MONTHS).toInstant());
            }
        });
        return availableItems;
    }

    public List<Item> getOwnItems(Long userId) {
        return itemRepository.findAllByOwnerId(userId);
    }
}
