package com.rajeshkawali.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rajeshkawali.model.User;
import com.rajeshkawali.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

	public static final String CLASS_NAME = UserServiceImpl.class.getName();

	@Autowired
	private UserRepository userRepository;

	@Override
	public List<User> getAllUsers() {
		String _function = ".getAllUsers";
		log.info(CLASS_NAME + _function + "::ENTER");
		List<User> userList = new ArrayList<>();
		try {
			userRepository.findAll().forEach(user -> userList.add(user));
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred:" + e.getMessage());
		}
		return userList;
	}

	@Override
	public User addUser(User user) {
		String _function = ".addUser";
		log.info(CLASS_NAME + _function + "::ENTER");
		User addedUser = null;
		try {
			addedUser = userRepository.save(user);
		} catch (Exception e) {
			log.error(CLASS_NAME + _function + "::Exception occurred:" + e.getMessage());
		}
		return addedUser;
	}

	@Override
	public User userById(Long id) {
		String _function = ".userById";
		log.info(CLASS_NAME + _function + "::ENTER");
		Optional<User> userOptional = userRepository.findById(id);
		if (userOptional.isPresent()) {
			log.debug(CLASS_NAME + _function + "::Response is: {}", userOptional.get());
			return userOptional.get();
		} else {
			return null;
		}
	}

	@Override
	public List<User> getUserByName(String name) {
		String _function = ".getUserByName";
		log.info(CLASS_NAME + _function + "::ENTER");
		List<User> userOptional = userRepository.findByUserName(name);
		if (userOptional != null) {
			log.debug(CLASS_NAME + _function + "::Response is: {}", userOptional);
			return userOptional;
		} else {
			return null;
		}
	}

	@Override
	public void deleteUser(Long id) {
		String _function = ".deleteUser";
		log.info(CLASS_NAME + _function + "::ENTER");
		userRepository.deleteById(id);
		log.info(CLASS_NAME + _function + "::EXIT");
	}
}
