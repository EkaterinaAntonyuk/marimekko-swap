package com.marimekko.swap.repository;

import com.marimekko.swap.dto.ItemAvailabilityDto;
import com.marimekko.swap.model.Item;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ItemRepository extends CrudRepository<Item, Long> {
    @Query("SELECT new com.marimekko.swap.dto.ItemAvailabilityDto(i, s.monthOfUsage) " +
            "FROM Item i " +
            "LEFT JOIN Schedule s ON s.item.id = i.id " +
            "WHERE i.demoUserId = :demoUserId " +
            "AND (s is null OR s.id = (" +
            "SELECT ss.id from Schedule ss where ss.item.id = i.id "+
            "and ss.monthOfUsage = (select max(sss.monthOfUsage) from Schedule sss where sss.item.id = i.id AND sss.user.id <> :demoUserId)))")
    List<ItemAvailabilityDto> getAvailableItems(Long demoUserId);

    List<Item> findAllByOwnerId(Long ownerId);
}
