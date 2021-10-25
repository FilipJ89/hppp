package com.pg.hppp.repositories;

import com.pg.hppp.model.Action;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ActionRepository extends CrudRepository<Action, Integer> {

    Optional<Action> findByActionDescription(String actionDescription);

}
