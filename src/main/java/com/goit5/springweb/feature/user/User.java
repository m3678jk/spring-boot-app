package com.goit5.springweb.feature.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goit5.springweb.feature.role.Role;
import lombok.*;
import javax.persistence.*;
import java.util.*;


@Entity
@Data
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



//    public enum Role{
//        USER,
//        ADMIN
//
//    }


}
