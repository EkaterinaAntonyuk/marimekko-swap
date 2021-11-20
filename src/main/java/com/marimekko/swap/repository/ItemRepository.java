package com.marimekko.swap.repository;

import com.marimekko.swap.model.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
