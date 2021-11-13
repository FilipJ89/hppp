package com.pg.hppp.model;

import lombok.*;

import javax.persistence.*;
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

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "supplier_user",
            joinColumns = {@JoinColumn(name = "supplier_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "supplier")
    private Set<Line> lines = new HashSet<>();

}
