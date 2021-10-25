package com.pg.hppp.model;

import com.pg.hppp.model.enums.RiskLevel;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Risk extends BaseEntity{

    @Enumerated(EnumType.STRING)
    private RiskLevel riskLevel;
    private String riskDescription;
    private LocalDate riskStartDate;
    private LocalDate riskEndDate;

    @OneToMany(mappedBy = "risk")
    private Set<Line> lines = new HashSet<>();
}
