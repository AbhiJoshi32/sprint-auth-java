package com.binktec.auth.service;

import com.binktec.auth.model.CustomUserDetail;
import com.binktec.auth.model.Users;
import com.binktec.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public CustomerUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Users> optional = userRepository.findByUsername(username);
        Users userOptional= optional.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
//        System.out.print("Found User");
        return new CustomUserDetail(userOptional);
    }
}
