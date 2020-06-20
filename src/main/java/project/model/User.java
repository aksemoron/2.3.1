package project.model;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, String family, long balans) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.balans = balans;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "email")
    private String email;

    public User(String email, String password, String name, String family, long balans) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.family = family;
        this.balans = balans;
    }



    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "family")
    private String family;

    @Column(name = "balans")
    private long balans;


    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public long getBalans() {
        return balans;
    }

    public void setBalans(long balans) {
        this.balans = balans;
    }

    public User() {
    }

    public User(String name, String family, long balans) {
        this.name = name;
        this.family = family;
        this.balans = balans;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
