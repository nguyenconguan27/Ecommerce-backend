package com.example.ecommerceproject.security;

import com.example.ecommerceproject.entity.User;
import com.example.ecommerceproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) {
        if(userRepository.existsByUsername(username)) {
            User user = userRepository.findByUsername(username).get();
            return CustomUserDetails.build(user);
        }
        throw new UsernameNotFoundException("Username is not found");
    }
}
