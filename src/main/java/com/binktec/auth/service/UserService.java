package com.binktec.auth.service;

import com.binktec.auth.Exception.EmailExistsException;
import com.binktec.auth.Exception.UsernameExistsException;
import com.binktec.auth.model.*;
import com.binktec.auth.repository.RoleRepository;
import com.binktec.auth.repository.UserRepository;
import com.binktec.auth.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class UserService implements IUserService {
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public UserService(RoleRepository roleRepository, UserRepository userRepository, VerificationTokenRepository verificationTokenRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository  = roleRepository;
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

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
        user.setPassword(bCryptPasswordEncoder.encode(registerUserApi.getPassword()));
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
    public VerificationToken getVerificationToken(String VerificationToken) {
        return verificationTokenRepository.findByToken(VerificationToken);
    }

    @Override
    public Users getUserByUsername(String name) throws UsernameNotFoundException {
        Optional<Users> optional = userRepository.findByUsername(name);
        return optional.orElseThrow(()-> new UsernameNotFoundException("Username not found"));
    }

    @Override
    public Users verifyUser(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
        Users user = verificationToken.getUser();
        user.setVerified(true);
        Set<Role> roles = user.getRoles();
        roles.removeIf(role -> role.getRoleName().equals("ANONYMOUS"));
        roles.add(roleRepository.findByRoleName("USER"));
        user.setRoles(roles);
        userRepository.save(user);
        return user;
    }

    @Override
    public void createVerificationToken(Users user, String token) {
        VerificationToken myToken = new VerificationToken(token, user);
        verificationTokenRepository.save(myToken);
    }
}