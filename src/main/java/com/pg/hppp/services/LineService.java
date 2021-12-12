package com.pg.hppp.services;

import com.pg.hppp.model.Line;
import com.pg.hppp.model.Supplier;
import com.pg.hppp.model.User;
import com.pg.hppp.repositories.LineRepository;
import com.pg.hppp.repositories.SupplierRepository;
import lombok.AllArgsConstructor;
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

    public Set<Line> getAllLinesFiltered(Set<Line> startLines, MaterialFormFilter formData) {
        Set<Line> endLines = startLines
                .stream()
                .filter(line -> {
                    Boolean condition1 = line.getMaterial().getMaterialName().contains(formData.getMaterialName());
                    Boolean condition2 = line.getMaterial().getMaterialCode().contains(formData.getMaterialCode());
                    Boolean condition3 = line.getMaterial().getMaterialFamily().contains(formData.getMaterialFamily());

                    Boolean condition4 = true;
                    if (formData.getIsRisk()) {
                        condition4 = line.getIsRisk();
                    }

                    Boolean condition5 = true;
                    if (!formData.getPlant().equals("plant") && !formData.getPlant().equals("")) { //todo export and link this with default choice = show all
                        condition5 = line.getMaterial().getPlant().getPlantLabel().equals(formData.getPlant());
                    }

                    Boolean condition6 = true;
                    if (!formData.getRiskLevel().equals("risk level") && !formData.getRiskLevel().equals("")) { //todo export and link this with default choice = show all
                        if (line.getRisk() == null) {
                            condition6 = false;
                        } else {
                            condition6 = line.getRisk().getRiskLevel().getRiskLevelLabel().equals(formData.getRiskLevel());
                        }
                    }

                    return condition1 && condition2 && condition3 && condition4 && condition5 && condition6;
                })
                .collect(Collectors.toSet());

        return endLines;
    }

    public Set<Line> getAllLinesWithRisksFilteredForAuthUser(User authUser) {
        Set<Line> authorizedLines = getAllLinesForAuthUser(authUser);
        Set<Line> filteredLines = authorizedLines
                .stream()
                .filter(line -> line.getIsRisk())
                .collect(Collectors.toSet());

        return filteredLines;
    }


}
