package com.binktec.auth;

import com.binktec.auth.model.UserApi;
import com.binktec.auth.model.Users;
import com.binktec.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class RestApi {
    @Autowired
    UserRepository userRepository;
    @RequestMapping("/user")
    public UserApi user(Authentication authentication) {
        Optional<Users> usersOptional = userRepository.findByUsername(authentication.getName());
        if (usersOptional.isPresent()) {
            System.out.println(authentication);
            Users users = usersOptional.get();
            return new UserApi(users);
        }
        return null;
    }

}
