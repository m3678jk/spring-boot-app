package com.goit5.springweb.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final CustomUserDetailsService userDetailsService; //ok

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/app/products/list").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/app/products/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/app/products/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET, "/app/producers/list").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/app/producers/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/app/producers/**").hasAuthority("ADMIN")

                .antMatchers(HttpMethod.GET, "/app/roles/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/app/roles/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/app/users/**").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.POST, "/app/users/**").hasAuthority("ADMIN")

                .and()
                .formLogin();
        return http.build();
    }

    //OK
    @Bean
    @Primary
    public AuthenticationManagerBuilder injectCustomAuthProvider(AuthenticationManagerBuilder auth) throws Exception {
        return auth.authenticationProvider(authProvider());
    }


//    @Bean
//    public AuthenticationManager authenticationManager(
//            AuthenticationConfiguration authenticationConfiguration) throws Exception {
//        return authenticationConfiguration.getAuthenticationManager();
//    }

    //OK
    @Bean
    @Qualifier
    protected DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    //OK
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(12);
    }


}