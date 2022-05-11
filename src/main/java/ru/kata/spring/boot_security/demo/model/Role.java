package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority {
    @Id
    private Long id;
    @Column(name = "rolename", unique = true)
    private String name;
    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "roles")
private Set<User> users;
    public Role() {
    }

    public Role(Long id) {
        this.id = id;
    }
    public Role(String rolename){
        this.name = rolename;
    }

    public Role(Long id, String role) {
        this.id = id;
        this.name = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getAuthority() {
        return getName();
    }

    @Override
    public String toString() {
        return name.substring(5);
    }
}