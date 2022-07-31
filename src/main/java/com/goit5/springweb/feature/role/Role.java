//package com.goit5.springweb.feature.role;
//
//import com.goit5.springweb.feature.user.User;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import java.util.Set;
//
//@Entity
//@Data
//@NoArgsConstructor
//@Table(name = "role")
//public class Role implements GrantedAuthority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//
//    @Override
//    public String getAuthority() {
//        return getName();
//    }
//}
