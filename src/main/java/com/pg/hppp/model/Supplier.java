package com.pg.hppp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Supplier extends BaseEntity {

    private Integer supplierCode;
    private String supplierName;

    private HashSet<User> approvedUsers = new HashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<Line> lines = new HashSet<>();

}
