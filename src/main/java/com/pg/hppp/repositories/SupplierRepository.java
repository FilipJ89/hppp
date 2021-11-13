package com.pg.hppp.repositories;

import com.pg.hppp.model.Supplier;
import com.pg.hppp.model.User;
import org.springframework.data.repository.CrudRepository;
import java.util.Set;

public interface SupplierRepository extends CrudRepository<Supplier, Integer> {
    Set<Supplier> findAllByUsersIn(Set<User> users);
}
