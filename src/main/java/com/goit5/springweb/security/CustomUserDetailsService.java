package com.goit5.springweb.security;

import com.goit5.springweb.feature.user.User;
import com.goit5.springweb.feature.user.UserSecurity;
import com.goit5.springweb.feature.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(email).orElseThrow(()-> new UsernameNotFoundException("User not found"));
        return UserSecurity.fromUser(user);
    }


}

