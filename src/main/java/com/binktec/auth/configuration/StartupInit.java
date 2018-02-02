package com.binktec.auth.configuration;

import com.binktec.auth.model.Users;
import com.binktec.auth.repository.RoleRepository;
import com.binktec.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.binktec.auth.model.Role;
import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class StartupInit {
    private final
    RoleRepository roleRepository;
    private final
    UserRepository userRepository;

    @Autowired
    public StartupInit(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @PostConstruct
    public void init() {

		roleRepository.save(new Role("ADMIN"));
        roleRepository.save(new Role("USER"));
        roleRepository.save(new Role("ANONYMOUS"));

        Users user = new Users();
        user.setVerified(true);
        user.setActive(1);
        user.setUsername("admin");
        user.setName("admin");
        user.setPassword("123456");
        user.setVerified(false);
        user.setActive(1);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("ADMIN"));
        user.setActive(1);
        user.setRoles(roles);
        userRepository.save(user);
    }
}
