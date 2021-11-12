package com.pg.hppp.model;

import lombok.*;
import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class User extends Person implements UserDetails, CredentialsContainer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String password;

    @Builder.Default
    private boolean isPg = false;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_action",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "action_id")})
    private Set<Action> actions = new HashSet<>();

    @Builder.Default
    private Boolean accountNonExpired = true;

    @Builder.Default
    private Boolean accountNonLocked = true;

    @Builder.Default
    private Boolean credentialsNonExpired = true;

    @Builder.Default
    private Boolean enabled = true;



    //todo specify method
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {return this.accountNonExpired;}

    @Override
    public boolean isAccountNonLocked() {return this.accountNonLocked;}

    @Override
    public boolean isCredentialsNonExpired() {return this.credentialsNonExpired;}

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public void eraseCredentials() {this.password=null; }
}
