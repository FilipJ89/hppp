package com.pg.hppp.repositories;

import com.pg.hppp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
