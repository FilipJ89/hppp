package com.pg.hppp.model;

import com.pg.hppp.model.enums.Category;
import com.pg.hppp.model.enums.Plant;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Material extends BaseEntity{

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private Integer materialCode;

    @NotNull
    private String materialName;

    private String materialFamily;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Plant> plants = new HashSet<>();

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Category> categories = new HashSet<>();

//    @OneToMany(mappedBy = "material")
//    private Set<Line> lines = new HashSet<>();
}
