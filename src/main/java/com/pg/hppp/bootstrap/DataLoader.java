package com.pg.hppp.bootstrap;

import com.pg.hppp.model.*;
import com.pg.hppp.model.enums.Category;
import com.pg.hppp.model.enums.Plant;
import com.pg.hppp.model.enums.RiskLevel;
import com.pg.hppp.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@AllArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final Integer MATERIAL_NUMBER_DATALOAD = 100;
    private final Integer SUPPLIER_NUMBER_DATALOAD = 5;
    private final Integer RISK_NUMBER_DATALOAD = 5;
    private final Integer ACTION_NUMBER_DATALOAD = 5;
    private final Integer FAMILY_NUMBER_DATALOAD = 5;
    private final Integer SAP_NUMBER_BASE = 10000000;
    private final String RISK_DESCRIPTION = "Risk description";
    private final String ACTION_DESCRIPTION = "Action description";
    private final Random random = new Random();

    private final MaterialRepository materialRepository;
    private final SupplierRepository supplierRepository;
    private final RiskRepository riskRepository;
    private final ActionRepository actionRepository;
    private final LineRepository lineRepository;

    @Override
    public void run(String... args) {
        loadMaterials();
        loadRisks();
        loadActions();
        loadSupplier();
        loadLines();
    }

    private void loadMaterials() {
        Integer counter = 0;

        while (counter < MATERIAL_NUMBER_DATALOAD) {
            materialRepository.save(Material.builder()
            .materialCode(SAP_NUMBER_BASE + counter)
            .materialName("Material"+(counter+1))
            .materialFamily("Family"+random.nextInt(FAMILY_NUMBER_DATALOAD)+1)
            .categories(Category.createRandomEnumSet())
            .plants(Plant.createRandomPlantSet())
            .build());

            counter++;
        }
    }

    private void loadRisks() {
        Integer counter = 0;

        while (counter<RISK_NUMBER_DATALOAD) {
            riskRepository.save(Risk.builder()
                    .riskDescription(RISK_DESCRIPTION+(counter+1))
                    .riskLevel(RiskLevel.getRandomRiskLevel())
                    .riskStartDate(LocalDate.of(2021,10,1))
                    .riskEndDate(LocalDate.now().plusMonths(counter))
                    .build());

            counter++;
        }
    }

    private void loadActions() {
        Integer actionNumber = ACTION_NUMBER_DATALOAD;
        Integer counter = 0;

        while (counter < ACTION_NUMBER_DATALOAD) {
            actionRepository.save(Action.builder()
                    .actionDescription(ACTION_DESCRIPTION+(counter+1))
                    .actionCreationDateTime(LocalDateTime.now())
                    .actionDueDate(LocalDate.now().minusWeeks(ACTION_NUMBER_DATALOAD/2).plusWeeks(counter))
                    .actionOwners(null)
                    .isExecuted(random.nextBoolean())
                    .build());

            counter++;
        }

    }

    private void loadSupplier() {
        Integer counter = 0;

        while(counter < SUPPLIER_NUMBER_DATALOAD) {
            supplierRepository.save(Supplier.builder()
                    .supplierCode(12345678+counter)
                    .supplierName("Supplier"+(counter+1))
                    .approvedUsers(null)
                    .lines(null)
                    .build());

            counter++;
        }
    }

    private void loadLines() {
        Integer counter = 0;
        while (counter < MATERIAL_NUMBER_DATALOAD) {
            Line line = new Line();
            line.setMaterial(materialRepository.findByMaterialCode(
                    SAP_NUMBER_BASE + counter).orElse(null));
            line.setRisk(riskRepository.findById(random.nextInt(2*RISK_NUMBER_DATALOAD)+1).orElse(null)); // around half will be true
            line.setIsRisk(line.getRisk()!= null);

            Set<Action> actionSet = new HashSet<>();
            if (line.getRisk() != null) {
                Integer numberActions = random.nextInt(ACTION_NUMBER_DATALOAD);
                Integer counter2 = 0;

                while (counter2<numberActions) {
                    Integer randomActionId = random.nextInt(ACTION_NUMBER_DATALOAD)+1;
                    actionSet.add(actionRepository.findById(randomActionId).orElse(null));
                    counter2++;
                }
            }

            line.setActions(actionSet);
            line.setSupplier(supplierRepository.findById(random.nextInt(SUPPLIER_NUMBER_DATALOAD)+1).orElse(null));
            line.setInputOriginator(null);
            line.setInputTime(LocalDateTime.now());

            lineRepository.save(line);
            counter++;
        }
    }
}