package com.marimekko.swap.dto;

import com.marimekko.swap.model.ItemType;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public class FiltersDto {
    private final ItemType itemType;
    private List<String> sizes;
    private final boolean isAvailableNextMonth;

    public List<String> getSizes() {
        if(sizes == null) {
            sizes = Collections.emptyList();
        }
        return sizes;
    }
}
