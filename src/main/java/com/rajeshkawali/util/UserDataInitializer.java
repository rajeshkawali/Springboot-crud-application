package com.rajeshkawali.util;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.rajeshkawali.model.User;
import com.rajeshkawali.repository.UserRepository;

import java.util.Arrays;
import java.util.List;
/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@Component
public class UserDataInitializer implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {

		User user1 = new User(null, "Rajesh", "Kawali", 32, "male", "Senior Engineer");
		User user2 = new User(null, "Mahesh", "Koli", 30, "male", "Senior Engineer");
		User user3 = new User(null, "Keshav", "Koli", 22, "female", "Lead Engineer");
		User user4 = new User(null, "Kiran", "Kamble", 35, "female", "Manager");
		User user5 = new User(null, "Laxmi", "Rane", 25, "female", "Manager");
		List<User> userList = Arrays.asList(user1, user2, user3, user4, user5);
		userRepository.saveAll(userList);
		System.out.println("UserDataInitializer.run()");
		userRepository.findAll().forEach((user -> log.info("" + user)));

	}
}