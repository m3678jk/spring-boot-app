package com.goit5.springweb.feature.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goit5.springweb.feature.role.Role;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Getter
@Setter

@NoArgsConstructor
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @JsonIgnore
    private String password;
//    @Transient
//    private String passwordConfirm;

    private String firstName;
    private String lastName;
    @ManyToMany(fetch = FetchType.EAGER, mappedBy = "users")
    private Set<Role> roles = new HashSet<>() ;

    public void addRoles(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRoles(Role role) {
        roles.remove(role);
        role.getUsers().remove(this);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
    //    public enum Role{
//        USER,
//        ADMIN
//
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
