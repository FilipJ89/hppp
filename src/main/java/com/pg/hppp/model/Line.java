package com.pg.hppp.model;

import com.pg.hppp.model.enums.Plant;
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
    private Material material;

    @NotNull
    @Enumerated(EnumType.ORDINAL)
    private Plant plant;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    private Risk risk;

    @OneToMany(mappedBy = "line")
    private Set<Action> actions = new HashSet<>();

    private User inputOriginator;
    private LocalDateTime inputTime;

}
