package com.enno.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity

public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        // Use BCryptPasswordEncoder for secure password hashing
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        // Use BCryptPasswordEncoder to encode passwords
        manager.createUser(
                User.withUsername("enno123")
                        .password(passwordEncoder().encode("123123"))
                        .roles("ADMIN", "USER")
                        .build());
        manager.createUser(
                User.withUsername("kamal")
                        .password(passwordEncoder().encode("123123"))
                        .roles("USER")
                        .build()

        );

        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
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
                .requestMatchers("/saveStudent", "/edit/**", "/delete/**", "/User/**").hasRole("ADMIN")
                .requestMatchers("/saveStudent", "/edit/**", "/delete/**", "/User/**").authenticated()
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