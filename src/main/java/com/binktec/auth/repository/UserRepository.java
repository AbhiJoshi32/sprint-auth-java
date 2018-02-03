package com.binktec.auth.repository;

import com.binktec.auth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Users,Integer> {
    Optional<Users> findByUsername(String username);

    Users findByEmail(String email);
}
