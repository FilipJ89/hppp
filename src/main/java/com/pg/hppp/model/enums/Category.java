package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

@Getter
@AllArgsConstructor
public enum Category {
    FHC("Fabric & Home Care", 0),
    BC("Baby Care", 1),
    FC("Feminine Care", 2),
    OC("Oral Care", 3),
    HC("Health Care", 4),
    SC("Shave Care", 5);

    private final String catLabel;
    private final Integer catOrder;

    public static Category getCategoryByOrderNumber(Integer number) {
        for (Category c : Category.values()) {
            if (c.getCatOrder().equals(number)) {
                return c;
            }
        }
        return null;
    }

    public static Set<Category> createRandomEnumSet() {
        Set<Category> categorySet = new HashSet<>();
        Random random = new Random();

        Integer enumSize = Category.values().length;
        Integer setSize = random.nextInt(enumSize) + 1; // at least one in set

        for (Integer n=0;n<setSize ;n++) {
            categorySet.add(getCategoryByOrderNumber(random.nextInt(enumSize)));
        }
        return categorySet;
    }


}
