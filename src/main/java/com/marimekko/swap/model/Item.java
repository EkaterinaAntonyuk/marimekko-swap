package com.marimekko.swap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Item {
    private final String name;
    private final ItemType itemType;
    private final String itemSize;
    private final String pictureUrl = "https://www.marimekko.com/media/catalog/product/0/9/090273-325_X191655_10_1632898629.jpg?width=822&height=1028&canvas=822:1028&quality=100&bg-color=255,255,255&fit=bounds";
}
