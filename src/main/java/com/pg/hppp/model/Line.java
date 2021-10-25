package com.pg.hppp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Line extends BaseEntity{

    @Builder.Default
    private Boolean isRisk = false;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "material_id")
    private Material material;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "risk_id")
    private Risk risk;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "line_action",
            joinColumns = {@JoinColumn(name = "line_id")},
            inverseJoinColumns = {@JoinColumn(name = "action_id")})
    private Set<Action> actions = new HashSet<>();

    private User inputOriginator;
    private LocalDateTime inputTime;

}
