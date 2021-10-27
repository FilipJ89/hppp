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
public class User extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Builder.Default
    private boolean isPg = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_action",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "action_id")})
    private Set<Action> actions = new HashSet<>();
}
