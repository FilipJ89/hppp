package com.pg.hppp.model;

import com.pg.hppp.model.enums.Category;
import com.pg.hppp.model.enums.Plant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Material extends BaseEntity{

    @NotNull
    @Digits(integer = 8, fraction = 0)
    private Integer materialCode;

    @NotNull
    private String materialName;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.ORDINAL)
    private Set<Plant> plants = new HashSet<>();

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Category> categories = new HashSet<>();
}
