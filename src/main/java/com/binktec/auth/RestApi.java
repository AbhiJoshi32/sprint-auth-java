package com.binktec.auth;

import com.binktec.auth.model.Role;
import com.binktec.auth.model.RegisterUserApi;
import com.binktec.auth.model.UserInfoApi;
import com.binktec.auth.model.Users;
import com.binktec.auth.repository.RoleRepository;
import com.binktec.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/")
public class RestApi {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @PreAuthorize("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
    @RequestMapping("/user")
    public UserInfoApi user(Authentication authentication) {
        System.out.println(authentication.getName() + authentication.getAuthorities());
        Optional<Users> usersOptional = userRepository.findByUsername(authentication.getName());
        return usersOptional.map(UserInfoApi::new).orElse(null);
    }

    @PreAuthorize("hasRole('ROLE_ANONYMOUS') or hasRole('ROLE_ADMIN')" )
    @RequestMapping(method = RequestMethod.POST,value = "/register")
    public ResponseEntity login(@RequestBody RegisterUserApi registerUserApi) {
        Users user = new Users(registerUserApi);
        user.setVerified(false);
        user.setActive(1);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("USER"));
        user.setActive(1);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}