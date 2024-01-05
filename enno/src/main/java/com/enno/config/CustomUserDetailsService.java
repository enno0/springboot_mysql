package com.enno.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.enno.DAO.UserDaoImp;

@Service
@Configuration
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserDaoImp userDaoImp;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        username = userDaoImp.getUserByName(username);
        String password = userDaoImp.getUserB();
        if (password == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<String> roles = userDaoImp.getUserRoles();

        return User.withUsername(username)
                .password(passwordEncoder().encode(password))
                .roles(roles.toArray(new String[0]))
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
