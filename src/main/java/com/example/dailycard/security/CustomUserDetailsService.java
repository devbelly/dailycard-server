package com.example.dailycard.security;

import com.example.dailycard.model.User;
import com.example.dailycard.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user =
                userRepository
                        .findByUsernameOrEmail(username, username)
                        .orElseThrow(
                                () ->
                                        new UsernameNotFoundException(
                                                "User not found with username or email : "
                                                        + username));

        return UserPrincipal.create(user);
    }
}
