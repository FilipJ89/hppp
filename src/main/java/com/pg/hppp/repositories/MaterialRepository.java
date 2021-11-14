package com.pg.hppp.repositories;

import com.pg.hppp.model.Material;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MaterialRepository extends CrudRepository<Material, Integer> {

    Optional<Material> findByMaterialCode(String materialCode);
}
