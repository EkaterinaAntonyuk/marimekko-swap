package com.marimekko.swap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private ItemType itemType;
    private String itemSize;
    private String pictureUrl = "https://www.marimekko.com/media/catalog/product/0/6/065205-353_X192281_10_1631079127.jpg?width=924&height=1156&canvas=924:1156&quality=100&bg-color=255,255,255&fit=bounds";
    private Long ownerId;
    private Long demoUserId;
    @Embedded
    private Location location;

    public Item(String name, ItemType itemType, String itemSize, Long demoUserId, Long ownerId, String pictureUrl, Location location) {
        this.name = name;
        this.itemType = itemType;
        this.itemSize = itemSize;
        this.demoUserId = demoUserId;
        this.ownerId = ownerId;
        this.pictureUrl = pictureUrl;
        this.location = location;
    }
}
