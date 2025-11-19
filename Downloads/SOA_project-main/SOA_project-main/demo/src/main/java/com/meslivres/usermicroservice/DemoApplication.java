package com.meslivres.usermicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import com.meslivres.usermicroservice.entities.User;
import com.meslivres.usermicroservice.entities.Role;
import com.meslivres.usermicroservice.repositories.UserRepository;
import com.meslivres.usermicroservice.repositories.RoleRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Collections;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Bean
	CommandLineRunner initUsers(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			// Add ROLE_ADMIN & ROLE_USER if not exist
			Role adminRole = roleRepository.findByName("ROLE_ADMIN")
					.orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_ADMIN").description("Administrateur").build()));
			Role userRole = roleRepository.findByName("ROLE_USER")
					.orElseGet(() -> roleRepository.save(Role.builder().name("ROLE_USER").description("Utilisateur standard").build()));

			// Add admin
			if (!userRepository.existsByUsername("admin")) {
				User admin = User.builder()
						.username("admin")
						.password(passwordEncoder.encode("123"))
						.email("admin@admin.com")
						.enabled(true)
						.roles(Collections.singletonList(adminRole))
						.build();
				userRepository.save(admin);
			}

			// Add user
			if (!userRepository.existsByUsername("user")) {
				User user = User.builder()
						.username("user")
						.password(passwordEncoder.encode("123"))
						.email("user@user.com")
						.enabled(true)
						.roles(Collections.singletonList(userRole))
						.build();
				userRepository.save(user);
			}
		};
	}
}
