package com.marimekko.swap.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private final String pictureUrl = "https://www.marimekko.com/media/catalog/product/0/9/090273-325_X191655_10_1632898629.jpg?width=822&height=1028&canvas=822:1028&quality=100&bg-color=255,255,255&fit=bounds";
    private Long ownerId;
    private Long demoUserId;

    public Item(String name, ItemType itemType, String itemSize, Long demoUserId, Long ownerId) {
        this.name = name;
        this.itemType = itemType;
        this.itemSize = itemSize;
        this.demoUserId = demoUserId;
        this.ownerId = ownerId;
    }
}
