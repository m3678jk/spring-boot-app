package com.goit5.springweb.configuration;

import com.goit5.springweb.CustomAuthenticationProvider;
import com.goit5.springweb.feature.user.CustomUserDetailsService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfig {
    private final CustomAuthenticationProvider authenticationProvider;
    private final CustomUserDetailsService userDetailsService;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()
                .antMatchers("/register", "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin();


//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/auth").hasAnyRole()
//                .antMatchers(HttpMethod.GET, "/non-auth").permitAll()
//                .and()
//                .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Autowired
    public void injectCustomAuthProvider(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider);
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
//
//    @Bean
//    public DataSource dataSource() {
//        return new EmbeddedDatabaseBuilder()
//                .setType(EmbeddedDatabaseType.HSQL)
//                .addScript(JdbcDaoImpl.DEFAULT_USER_SCHEMA_DDL_LOCATION)
//                .build();
//    }
//

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(encoder());
        return authProvider;
    }

    @Bean
    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("ad")
//                .password("password")
//                .roles("admin")
//                .build();
//        List<UserDetails> user1 = List.of(user, admin);
//        InMemoryUserDetailsManager inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1
//        );
//        return inMemoryUserDetailsManager ;
//    }

//    protected void configure (AuthenticationManagerBuilder auth) throws Exception {
//            auth.inMemoryAuthentication()
//                    .withUser("user1").password("123").authorities("admin")
//                    .and()
//                    .withUser("user2").password("11").authorities("user")
//                    .and()
////                    .passwordEncoder(new BCryptPasswordEncoder());
//                    .passwordEncoder(NoOpPasswordEncoder.getInstance());

       // return auth.build();
    }
