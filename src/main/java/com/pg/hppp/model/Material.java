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
    private String materialCode;

    @NotNull
    private String materialName;

    private String materialFamily;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Plant plant;

    @NotNull
    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Category> categories = new HashSet<>();

}
