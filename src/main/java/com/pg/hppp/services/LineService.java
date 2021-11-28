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

    public Set<Line> getAllLinesForAuthUserFilterMaterials(User authUser, MaterialFormFilter formData) {
        Set<Line> authorizedLines = getAllLinesForAuthUser(authUser);
        Set<Line> filteredLines = authorizedLines
                .stream()
                .filter(line -> {
                    Boolean condition1 = line.getMaterial().getMaterialName().contains(formData.getMaterialName());
                    Boolean condition2 = line.getMaterial().getMaterialCode().contains(formData.getMaterialCode());

                    Boolean condition3 = true;
                    if (formData.getIsRisk()) {
                        condition3 = line.getIsRisk();
                    }

                    Boolean condition4 = true;
                    if (!formData.getRiskLevel().equals("Risk level")) { //todo export and link this with default choice = show all
                        if (line.getRisk() == null) {
                            condition4 = false;
                        } else {
                            condition4 = line.getRisk().getRiskLevel().getRiskLevelLabel().equals(formData.getRiskLevel());
                        }
                    }

                    return condition1 && condition2 && condition3 && condition4;
                })
                .collect(Collectors.toSet());

        return  filteredLines;
    }

    public Set<Line> getAllLinesWithRisksFilteredForAuthUser(User authUser) {
        Set<Line> authorizedLines = getAllLinesForAuthUser(authUser);
        Set<Line> filteredLines = authorizedLines
                .stream()
                .filter(line -> line.getIsRisk())
                .collect(Collectors.toSet());

        return filteredLines;
    }

    public Set<Line> getAllLinesForAuthUserFilterRisks(User authUser, MaterialFormFilter formData) {
        Set<Line> authorizedLines = getAllLinesWithRisksFilteredForAuthUser(authUser);
        Set<Line> filteredLines = authorizedLines
                .stream()
                .filter(line -> {
                    Boolean condition1 = line.getMaterial().getMaterialName().contains(formData.getMaterialName());
                    Boolean condition2 = line.getMaterial().getMaterialCode().contains(formData.getMaterialCode());
                    Boolean condition3 = line.getMaterial().getMaterialFamily().contains(formData.getMaterialFamily());

                    Boolean condition4 = true;
                    if (!formData.getRiskLevel().equals("Risk level")) { //todo export and link this with default choice = show all
                        if (line.getRisk() == null) {
                            condition4 = false;
                        } else {
                            condition4 = line.getRisk().getRiskLevel().getRiskLevelLabel().equals(formData.getRiskLevel());
                        }
                    }

                    return condition1 && condition2 && condition3 && condition4;
                })
                .collect(Collectors.toSet());

        return  filteredLines;
    }



}
