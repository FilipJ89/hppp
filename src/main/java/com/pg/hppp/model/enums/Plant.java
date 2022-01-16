package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Random;

@Getter
@AllArgsConstructor
public enum Plant {

    BELLVILLE("BEL",1111,Region.NA, 0,"Bellville - 1111"),
    EUSKIRCHEN("EUS",4830, Region.EU, 1,"Euskirchen - 4830"),
    TARGOWEK("TAR",9445, Region.EU, 2,"Targowek - 9445");

    private final String plantShort;
    private final Integer plantNumber;
    private final Region region;
    private final Integer plantOrder;
    private final String plantLabel;

    public static Plant getPlantByOrderNumber(Integer number) {
        for (Plant p : Plant.values()) {
            if (p.getPlantOrder().equals(number)) {
                return p;
            }
        }
        return null;
    }

    public static Plant randomPlant() {
        Random random = new Random();
        Integer enumSize = Plant.values().length;

        return getPlantByOrderNumber(random.nextInt(enumSize));

    }
}
