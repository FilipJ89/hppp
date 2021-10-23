package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RiskLevel {
    HIGH("high"),
    MEDIUM("medium"),
    LOW("low");

    private final String riskLevelLabel;
}
