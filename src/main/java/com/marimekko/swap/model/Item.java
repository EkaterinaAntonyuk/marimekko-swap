package com.marimekko.swap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Item {
    private final String name;
    private final ItemType itemType;
    private final String itemSize;
}
