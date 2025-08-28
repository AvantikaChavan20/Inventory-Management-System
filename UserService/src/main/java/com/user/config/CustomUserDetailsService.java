package com.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.user.entity.Admin;
import com.user.entity.User;
import com.user.repository.AdminRepository;
import com.user.repository.UserRepository;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository repository;
    @Autowired
    private AdminRepository adminRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOpt = repository.findByUsername(username);
        if (userOpt.isPresent()) {
            return new CustomUserDetails(userOpt.get());
        }

        Optional<Admin> adminOpt = adminRepo.findByUsername(username);
        if (adminOpt.isPresent()) {
            return new CustomUserDetails(adminOpt.get());
        }

        throw new UsernameNotFoundException("User not found with username: " + username);
    }

    
}
