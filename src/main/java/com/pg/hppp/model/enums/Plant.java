package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Plant {
    EUSKIRCHEN(4830, Region.EU),
    TARGOWEK(9445, Region.EU),
    BELLVILLE(1111,Region.NA);

    private final Integer plantNumber;
    private final Region region;
}
