package com.enno.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

import com.enno.DAO.UserDaoImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    DataSource dataSource;
    @Autowired
    UserDaoImp userDaoImp;

    // @Bean
    // PasswordEncoder passwordEncoder() {
    // // Use BCryptPasswordEncoder for secure password hashing
    // return new BCryptPasswordEncoder();
    // }

    // @Bean
    // UserDetailsService userDetailsService() {

    // InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
    // String username = userDaoImp.getUserByName("kamal");
    // String password = userDaoImp.getUserB();
    // List<String> roles = userDaoImp.getUserRoles();
    // // Use BCryptPasswordEncoder to encode passwords
    // manager.createUser(
    // User.withUsername(username)
    // .password(passwordEncoder().encode(password))
    // .roles(roles.toArray(new String[0]))
    // .build());
    // return manager;
    // }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .and()
                //
                .authorizeRequests()
                .requestMatchers("/login").permitAll()
                .and()
                .authorizeRequests()
                .requestMatchers("/", "/login", "/showStudent").hasRole("USER")
                .requestMatchers("/saveStudent", "/edit/**", "/delete/**", "/User/**", "/User_Role/**").hasRole("ADMIN")
                .requestMatchers("/saveStudent", "/edit/**", "/delete/**", "/User/**", "/User_Role/**").authenticated()
                .and()
                //
                .formLogin()
                .and()

                //
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                //
                .exceptionHandling()
                .accessDeniedHandler((request, response, accessDeniedException) -> {
                    // Log details about the denied access
                    accessDeniedException.printStackTrace();
                    response.sendRedirect("/access-denied");
                });

        return http.build();
    }

}