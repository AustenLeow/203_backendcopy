package com.cs203g1t2.springjwt;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.net.URI;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.cs203g1t2.springjwt.models.User;
import com.cs203g1t2.springjwt.repository.RoleRepository;
import com.cs203g1t2.springjwt.repository.UserRepository;
import com.cs203g1t2.springjwt.security.jwt.JwtUtils;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class SpringBootSecurityJwtApplicationTests {


	@Test
	public void contextLoads() {
	}

}
