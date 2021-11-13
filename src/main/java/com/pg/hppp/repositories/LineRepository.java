package com.pg.hppp.repositories;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.Supplier;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface LineRepository extends CrudRepository<Line, Integer> {
    Set<Line> findAllBySupplierIn(Set<Supplier> suppliers);
}
