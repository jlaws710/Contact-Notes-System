package com.contact_notes.config;

import com.contact_notes.model.Users;
import com.contact_notes.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Fetch User from the DB

        Optional<Users> userRes = userRepository.findByUsername(username);
        // No user found
        if(userRes.isEmpty())
            throw new UsernameNotFoundException("Could not findUser with username = " + username);
        // Return a User Details object using the fetched User information
        Users user = userRes.get();
        return new org.springframework.security.core.userdetails.User(
                username,
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
