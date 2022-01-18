package com.pg.hppp.services;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate riskStartDate = null;

    @Builder.Default
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate riskEndDate = null;

    @Builder.Default
    private String plant = "";

    @Builder.Default
    private String riskLevel = "";
}
