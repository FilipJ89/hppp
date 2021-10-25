package com.pg.hppp.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Supplier extends BaseEntity {

    private Integer supplierCode;
    private String supplierName;

    private HashSet<User> approvedUsers = new HashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<Line> lines = new HashSet<>();

}
