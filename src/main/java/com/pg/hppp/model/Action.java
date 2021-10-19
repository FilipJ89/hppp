package com.pg.hppp.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Action extends BaseEntity {


    private String actionDescription;

    @ManyToMany(mappedBy = "actions")
    private Set<User> actionOwners = new HashSet<>();
    private LocalDate actionDueDate;
    private Boolean isExecuted;

    private LocalDateTime actionCreationDateTime;

    @ManyToOne
    @JoinColumn(name = "line_id")
    private Line line;
}
