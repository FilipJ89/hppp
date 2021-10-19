package com.pg.hppp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Material extends BaseEntity{

    private Integer materialCode;
    private String materialName;
}
