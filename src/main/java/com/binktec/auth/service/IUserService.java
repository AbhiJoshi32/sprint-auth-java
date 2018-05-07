package com.binktec.auth.service;

import com.binktec.auth.Exception.EmailExistsException;
import com.binktec.auth.Exception.UsernameExistsException;
import com.binktec.auth.model.RegisterUserApi;
import com.binktec.auth.model.Users;
import com.binktec.auth.model.VerificationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface IUserService {

    Users registerNewUserAccount(RegisterUserApi accountDto)
            throws EmailExistsException, UsernameExistsException;

    void createVerificationToken(Users user, String token);

    VerificationToken getVerificationToken(String VerificationToken);

    Users getUserByUsername(String name) throws UsernameNotFoundException;

    Users verifyUser(VerificationToken verificationToken);
}