package com.pg.hppp.services;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.Risk;
import com.pg.hppp.model.enums.RiskLevel;
import com.pg.hppp.repositories.LineRepository;
import com.pg.hppp.repositories.RiskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@AllArgsConstructor
@Service
public class RiskService {

    private final RiskRepository riskRepository;
    private final LineRepository lineRepository;

    public void updateRisks(Set<Line> lines, MaterialFormFilter formData) {
        if (formData.getIsRisk()) {
            Risk risk = new Risk();
            risk.setRiskLevel(RiskLevel.getEnumByLabel(formData.getRiskLevel()));
            risk.setRiskDescription(formData.getRiskDescription());
            risk.setRiskStartDate(formData.getRiskStartDate());
            risk.setRiskEndDate(formData.getRiskEndDate());
            risk.setLines(lines);
            riskRepository.save(risk);

            lines.forEach(line -> {
                line.setIsRisk(true);
                line.setRisk(risk);
            });

        } else {
            lines.forEach(line -> {
                line.setIsRisk(false);
                line.setRisk(null);
            });
        }
        lineRepository.saveAll(lines);
        // todo delete risks that are not linked with any line
    }
}
