package com.pg.hppp.services;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.Risk;
import com.pg.hppp.model.enums.RiskLevel;
import com.pg.hppp.repositories.LineRepository;
import com.pg.hppp.repositories.RiskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class RiskService {

    private final RiskRepository riskRepository;
    private final LineRepository lineRepository;

    public void updateRisks(Set<Line> lines, MaterialFormFilter formData) {
        List<Risk> risks = lines.stream().map(Line::getRisk).collect(Collectors.toList());
        if (formData.getIsRisk() == true) {
            risks.forEach(risk -> {
                risk.setRiskLevel(RiskLevel.getEnumByLabel(formData.getRiskLevel()));
                risk.setRiskDescription(formData.getRiskDescription());
                risk.setRiskStartDate(formData.getRiskStartDate());
                risk.setRiskEndDate(formData.getRiskEndDate());
            });
            riskRepository.saveAll(risks);
        } else {
            lines.forEach(line -> {
                line.setIsRisk(false);
                line.setRisk(null);
            });
            lineRepository.saveAll(lines); // todo delete risks that are not linked with any line
        }
    }
}
