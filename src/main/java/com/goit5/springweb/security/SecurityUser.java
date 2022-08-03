package com.goit5.springweb.security;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.goit5.springweb.feature.role.Role;
import com.goit5.springweb.feature.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Data
@AllArgsConstructor
public class SecurityUser implements UserDetails {

    private String email;
    @JsonIgnore
    private String password;

//    @Transient
//    private String passwordConfirm;
    private String firstName;
    private String lastName;

      private List<SimpleGrantedAuthority> authorities ;

    @Override
    public String toString() {
        return super.toString();
    }


      @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities
                ;
    }

    @Override
    public String getUsername() {
        return getEmail();
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

    public static UserDetails fromUser(User user){
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
    }


}