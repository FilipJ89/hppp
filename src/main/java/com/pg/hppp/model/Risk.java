package com.pg.hppp.model;

import com.pg.hppp.model.enums.RiskLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Risk extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    private String riskDescription;
    private LocalDate riskStartDate;
    private LocalDate riskEndDate;
}
