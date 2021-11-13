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
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class User extends BaseEntity implements UserDetails, CredentialsContainer {

    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;

    @Builder.Default
    private boolean isPg = false;

    @ManyToMany(mappedBy = "actionOwners")
    private Set<Action> actions = new HashSet<>();

    @ManyToMany(mappedBy = "users")
    private Set<Supplier> suppliers = new HashSet<>();


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
