package com.marimekko.swap.repository;

import com.marimekko.swap.dto.ItemAvailabilityDto;
import com.marimekko.swap.model.Item;
import com.marimekko.swap.model.ItemType;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemsRepository {
    public List<ItemAvailabilityDto> getAllItems() {
        List<ItemAvailabilityDto> items = new ArrayList<>();
        items.add(new ItemAvailabilityDto(new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2022-01-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Dress", ItemType.CLOTHING, "S"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Dress", ItemType.CLOTHING, "L"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Shoes", ItemType.ACCESSORIES, "37"),
                Instant.parse("2022-01-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Shoes", ItemType.ACCESSORIES, "38"),
                Instant.parse("2022-02-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Shoes", ItemType.ACCESSORIES, "38"),
                Instant.parse("2022-01-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Shoes", ItemType.ACCESSORIES, "39"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Bag", ItemType.BAGS, "One size"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Bag", ItemType.BAGS, "One size"),
                Instant.parse("2022-12-23T17:14:00.092812Z")));
        return items;
    }

    public List<ItemAvailabilityDto> getFilteredItems(ItemType itemType, List<String> sizes, Boolean isAvailable) {
        List<ItemAvailabilityDto> items = new ArrayList<>();
        items.add(new ItemAvailabilityDto(new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        items.add(new ItemAvailabilityDto(new Item("Dress", ItemType.CLOTHING, "M"),
                Instant.parse("2021-12-23T17:14:00.092812Z")));
        return items;
    }
}
