package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Pattern(regexp = "[A-Za-z]{2,15}", message = "Name should be between 2 and 15 latin characters")
    private String name;

    @Pattern(regexp = "[A-Za-z]{2,15}", message = "Name should be between 2 and 15 latin characters")
    private String surname;

    @Min(value = 0, message = "Age should be >= 0")
    @Max(value = 127, message = "Age should be < 128")
    private byte age;

    @Pattern(regexp = "[A-Za-z]{2,15}", message = "Name should be between 2 and 15 latin characters")
    private String job;

    @Pattern(regexp = "[A-Za-z]{2,15}", message = "Name should be between 2 and 15 latin characters")
    private String gender;

    @Pattern(regexp = "([A-z0-9_.-]+)@([A-z0-9_.-]+).([A-z]{2,8})", message = "Enter correct email")
    private String email;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 2, max = 15, message = "Name should be between 2 and 15 latin characters")
    @Column(unique = true)
    private String username;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 4, message = "Password should be greater then 4 symbols")
    private String password;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();


    public User() {
    }

    public User(String name, String surname, byte age,String job, String gender, String email, String username, String password, Set<Role> roles) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.job = job;
        this.gender = gender;
        this.email = email;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }
    public String getRolesAsString() {
        StringBuilder sb = new StringBuilder();
        for (Role role : roles) {
            sb.append(role.toString().substring(5));
            sb.append(" ");
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", job='" + job + '\'' +
                ", gender='" + gender + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }
    public String getStringRoles(){
        return roles.toString();
    }

    public void setRoles(Set<Role>roles) {
        this.roles = roles;
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
        return username;
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