package com.pg.hppp.services;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.Supplier;
import com.pg.hppp.model.User;
import com.pg.hppp.repositories.LineRepository;
import com.pg.hppp.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class LineService {

    private final LineRepository lineRepository;
    private final SupplierRepository supplierRepository;

    public Set<Line> getAllLinesForAuthUser(User authUser) {
        if (authUser.isPg()) {
            List<Line> lines = (List<Line>) lineRepository.findAll();
            return lines.stream().collect(Collectors.toSet());
        }

        Set<User> authUsers = new HashSet<>();
        authUsers.add(authUser);
        Set<Supplier> suppliers = supplierRepository.findAllByUsersIn(authUsers);
        return lineRepository.findAllBySupplierIn(suppliers);
    }

    public Set<Line> getAllLinesForAuthUserIsRisk(Boolean isRisk, User authUser) {
        Set<Line> authorizedLines = getAllLinesForAuthUser(authUser);
        Set<Line> filteredLines;
        if (isRisk) {
            filteredLines = authorizedLines
                    .stream()
                    .filter(line -> line.getIsRisk())
                    .collect(Collectors.toSet());
        } else {
            filteredLines = authorizedLines
                    .stream()
                    .filter(line -> !line.getIsRisk())
                    .collect(Collectors.toSet());
        }
        return filteredLines;
    }

    public Set<Line> getAllLinesFiltered(Set<Line> startLines, MaterialFormFilter formData) {
        Set<Line> endLines = startLines
                .stream()
                .filter(line -> {
                    Boolean materialNameCondition = line.getMaterial().getMaterialName().contains(formData.getMaterialName());
                    Boolean materialCodeCondition = line.getMaterial().getMaterialCode().contains(formData.getMaterialCode());

                    Boolean riskDescriptionCondition = true;
                    if (!(line.getRisk() == null)) {
                        if (!formData.getRiskDescription().equals("")) {
                            riskDescriptionCondition = line.getRisk().getRiskDescription().contains(formData.getRiskDescription());
                        }
                    }

                    Boolean materialFamilyCondition = true;
                    if (!formData.getMaterialFamily().equals("")) {
                        materialFamilyCondition = formData.getMaterialFamily().contains(line.getMaterial().getMaterialFamily());
                    }

                    Boolean isRiskCondition = true;
                    if (formData.getIsRisk()) {
                        isRiskCondition = line.getIsRisk();
                    }

                    Boolean plantCondition = true;
                    if (!formData.getPlant().equals("")) {
                        plantCondition = formData.getPlant().contains(line.getMaterial().getPlant().getPlantLabel());
                    }

                    Boolean riskLevelCondition = true;
                    if (!formData.getRiskLevel().equals("")) {
                        if (line.getRisk() == null) {
                            riskLevelCondition = false;
                        } else {
                            riskLevelCondition = formData.getRiskLevel().contains(line.getRisk().getRiskLevel().getRiskLevelLabel());
                        }
                    }

                    return materialNameCondition && materialCodeCondition && riskDescriptionCondition &&
                            materialFamilyCondition && isRiskCondition && plantCondition && riskLevelCondition;
                })
                .collect(Collectors.toSet());

        return endLines;
    }
}
