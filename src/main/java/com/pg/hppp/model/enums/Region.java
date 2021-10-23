package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Region {
    EU("Europe"),
    NA("North America"),
    AS("Asia"),
    MEA("Middle East Africa"),
    LA("Latin America");

    private final String regionLabel;
}
