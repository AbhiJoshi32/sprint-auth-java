package com.binktec.auth;

import com.binktec.auth.model.Users;
import com.binktec.auth.model.Role;
import com.binktec.auth.repository.RoleRepository;
import com.binktec.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;


@SpringBootApplication
public class AuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthApplication.class, args);
	}
}
