package com.pg.hppp.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Action extends BaseEntity {


    private String actionDescription;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "action_user",
            joinColumns = {@JoinColumn(name = "action_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")})
    private Set<User> actionOwners = new HashSet<>();
    private LocalDate actionDueDate;
    private Boolean isExecuted;

    private LocalDateTime actionCreationDateTime;

    @ManyToMany(mappedBy = "actions")
    private Set<Line> lines = new HashSet<>();

    @Override
    public int hashCode() {
        return getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() == obj.getClass()) {
            Action other = (Action) obj;
            return getId() == other.getId();
        }
        return false;
    }
}
