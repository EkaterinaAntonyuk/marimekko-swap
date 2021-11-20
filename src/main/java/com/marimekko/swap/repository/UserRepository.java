package com.marimekko.swap.repository;

import com.marimekko.swap.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

}
