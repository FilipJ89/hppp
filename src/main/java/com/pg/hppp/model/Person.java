package com.pg.hppp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person extends BaseEntity {

    private String firstName;
    private String lastName;
    private String userName;
    private String email;
}
