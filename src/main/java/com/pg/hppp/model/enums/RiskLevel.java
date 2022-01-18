package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum RiskLevel {
    HIGH("high", 0),
    MEDIUM("medium",1),
    LOW("low",2);

    private final String riskLevelLabel;
    private final Integer riskLevelOrder;

    public static RiskLevel getRandomRiskLevel() {
        Integer riskLevelSize = RiskLevel.values().length;
        Integer riskLevelOrder = new Random().nextInt(riskLevelSize);
        for (RiskLevel r : RiskLevel.values()) {
            if (r.getRiskLevelOrder().equals(riskLevelOrder)) {
                return r;
            }
        }
        return null;
    }

    public static RiskLevel getEnumByLabel(String label) {
        for (RiskLevel r : RiskLevel.values()) {
            if(r.riskLevelLabel.equals(label)) {
                return r;
            }
        }
        return null;
    }
}
