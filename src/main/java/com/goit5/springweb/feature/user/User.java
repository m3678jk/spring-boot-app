package com.goit5.springweb.feature.user;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;


@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
public class User  {
    //implements UserDetails
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    @JsonIgnore
    private String password;

//    @Transient
//    private String passwordConfirm;
//
//    private String firstName;
//
//    private String lastName;

//    @ManyToMany(fetch = FetchType.EAGER)
//    private Set<Role> roles;
//
//    @Override
//    public String toString() {
//        return super.toString();
//    }
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return id == user.id && Objects.equals(email, user.email) && Objects.equals(password, user.password);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(id, email, password);
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return getRoles();
//    }
//
//    @Override
//    public String getUsername() {
//        return getEmail();
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }

}
