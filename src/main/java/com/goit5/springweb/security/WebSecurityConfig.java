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
//    private final CustomAuthenticationProvider authenticationProvider;

    private final CustomUserDetailsService userDetailsService; //ok

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
//                .csrf().disable()
//                .cors().disable()
//                .authorizeRequests()
//                .antMatchers("/register", "/login").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin();


                .authorizeRequests()
//                .antMatchers("/register", "/login").permitAll()
                .antMatchers(HttpMethod.GET, "/dashboard").hasAnyAuthority("ADMIN", "USER")
                .antMatchers(HttpMethod.POST, "/user").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/user").hasAuthority("ADMIN")
                .antMatchers(HttpMethod.GET, "/superadmin").authenticated()

                .antMatchers("/register", "/login").permitAll()
                .and()
                .formLogin();
//                .formLogin(Customizer.withDefaults());
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