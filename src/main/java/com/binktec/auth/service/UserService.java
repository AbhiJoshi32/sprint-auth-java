package com.binktec.auth.service;

import com.binktec.auth.Exception.EmailExistsException;
import com.binktec.auth.Exception.UsernameExistsException;
import com.binktec.auth.model.*;
import com.binktec.auth.repository.RoleRepository;
import com.binktec.auth.repository.UserRepository;
import com.binktec.auth.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService implements IUserService {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenRepository tokenRepository;

    @Override
    public Users registerNewUserAccount(RegisterUserApi registerUserApi)
            throws EmailExistsException, UsernameExistsException {

        if (emailExist(registerUserApi.getEmail())) {
            throw new EmailExistsException();
        }
        if (usernameExist(registerUserApi.getUsername())) {
            throw new UsernameExistsException();
        }
        Users user = new Users(registerUserApi);
        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByRoleName("ANONYMOUS"));
        user.setActive(1);
        user.setRoles(roles);
        return userRepository.save(user);
    }

    private boolean usernameExist(String username) {
        Users user = userRepository.findByEmail(username);
        return user != null;
    }

    private boolean emailExist(String email) {
        Users user = userRepository.findByEmail(email);
        return user != null;
    }

    @Override
    public Users getUser(String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

    @Override
    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    @Override
    public Users getUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Users> optional = userRepository.findByUsername(name);
        return optional.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public void saveRegisteredUser(Users user) {
        userRepository.save(user);
    }

    @Override
    public void createVerificationToken(Users user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        tokenRepository.save(myToken);
    }
}