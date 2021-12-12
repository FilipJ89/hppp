package com.pg.hppp.services;

import com.pg.hppp.model.enums.Plant;
import lombok.*;

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
    private String plant = "";

    @Builder.Default
    private Boolean isRisk = false;

    @Builder.Default
    private String riskLevel = "";
}
