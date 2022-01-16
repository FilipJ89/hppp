package com.pg.hppp.services;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialFormFilter {

    @Builder.Default
    private String materialName = "";

    @Builder.Default
    private String materialCode = "";

    @Builder.Default
    private String materialFamily = "";

    @Builder.Default
    private Boolean isRisk = false;

    @Builder.Default
    private String riskDescription = "";

    @Builder.Default
    private LocalDate riskStartDate = LocalDate.now();

    @Builder.Default
    private LocalDate riskEndDate = LocalDate.now();

    @Builder.Default
    private String plant = "";

    @Builder.Default
    private String riskLevel = "";
}
