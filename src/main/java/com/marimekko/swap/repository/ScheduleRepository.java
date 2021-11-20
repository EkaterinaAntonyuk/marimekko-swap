package com.marimekko.swap.repository;

import com.marimekko.swap.model.Item;
import com.marimekko.swap.model.Schedule;
import com.marimekko.swap.model.User;
import org.springframework.data.repository.CrudRepository;

import java.time.Instant;
import java.util.List;

public interface ScheduleRepository extends CrudRepository<Schedule, Long> {
    List<Schedule> findByUserAndMonthOfUsageAfter(User user, Instant after);
    List<Schedule> getAllByItemAndMonthOfUsageAfter(Item item, Instant after);
}
