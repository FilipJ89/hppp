package com.pg.hppp.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Category {
    FHC("Fabric & Home Care"),
    BC("Baby Care"),
    FC("Feminine Care"),
    OC("Oral Care"),
    HC("Health Care"),
    SC("Shave Care");

    private String catLabel;


}
