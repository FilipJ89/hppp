package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum Plant {
    EUSKIRCHEN(4830, Region.EU, 0),
    TARGOWEK(9445, Region.EU, 1),
    BELLVILLE(1111,Region.NA, 2);

    private final Integer plantNumber;
    private final Region region;
    private final Integer plantOrder;

    public static Plant getPlantByOrderNumber(Integer number) {
        for (Plant p : Plant.values()) {
            if (p.getPlantOrder().equals(number)) {
                return p;
            }
        }
        return null;
    }

    public static Set<Plant> createRandomPlantSet() {
        Set<Plant> plantSet = new HashSet<>();
        Random random = new Random();

        Integer enumSize = Plant.values().length;
        Integer setSize = random.nextInt(enumSize) + 1; // at least one in set

        for (Integer n=0;n<setSize ;n++) {
            plantSet.add(getPlantByOrderNumber(random.nextInt(enumSize)));
        }
        return plantSet;
    }
}
