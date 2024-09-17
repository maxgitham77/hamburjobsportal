package com.jobportalhambrg.jobportalhamburg.services;

import com.jobportalhambrg.jobportalhamburg.entity.Users;
import com.jobportalhambrg.jobportalhamburg.repository.UserRepository;
import com.jobportalhambrg.jobportalhamburg.utils.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("Could not found user."));
        return new CustomUserDetails(user);
    }
}
