package com.binktec.auth.repository;

import com.binktec.auth.model.Users;
import com.binktec.auth.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository
        extends JpaRepository<VerificationToken, Long> {

    VerificationToken findByToken(String token);

    VerificationToken findByUser(Users user);
}
