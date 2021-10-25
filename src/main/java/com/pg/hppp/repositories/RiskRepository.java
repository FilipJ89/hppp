package com.pg.hppp.repositories;

import com.pg.hppp.model.Risk;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RiskRepository extends CrudRepository<Risk, Integer> {

    Optional<Risk> findByRiskDescription(String riskDescription);

}
