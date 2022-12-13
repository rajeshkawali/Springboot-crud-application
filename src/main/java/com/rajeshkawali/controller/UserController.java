package com.rajeshkawali.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.rajeshkawali.constant.UserConstants;
import com.rajeshkawali.model.User;
import com.rajeshkawali.service.UserService;
import com.rajeshkawali.util.UserUtil;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@RequestMapping("/api")
@RestController
public class UserController {

	public static final String CLASS_NAME = UserController.class.getName();

	@Autowired
	private UserService userService;

	@GetMapping("/v1/getAllUsers")
	@ApiOperation("Get all the Users")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "SuccessFul Retrieval of Users") })
	public List<User> getAllUsers() {
		String _function = ".getAllUsers";
		log.info(CLASS_NAME + _function + "::ENTER");
		List<User> usersList = new ArrayList<>();
		usersList = userService.getAllUsers();
		log.info(CLASS_NAME + _function + "::EXIT");
		return usersList;
	}

	@ApiOperation("Adds a new User.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "User Successfully added to the InMemory DB.") })
	@PostMapping("/v1/addUser")
	public ResponseEntity<?> addUser(@Valid @RequestBody User user) {
		String _function = ".addUser";
		log.info(CLASS_NAME + _function + "::ENTER");
		log.debug(CLASS_NAME + _function + "::Request to add a new User into the DB: {} ", user);
		User addedUser = userService.addUser(user);
		log.info(CLASS_NAME + _function + "::User SuccessFully added to the DB: {}", addedUser);
		return ResponseEntity.status(HttpStatus.CREATED).body(addedUser);

	}

	@ApiOperation("Get User using the User id.")
	@ApiResponses(value = { 
			@ApiResponse(code = 200, message = "User for the id"),
			@ApiResponse(code = 404, message = "No User found"), })
	@GetMapping("/v1/user/{id}")
	public ResponseEntity<?> userById(@PathVariable Long id) {
		String _function = ".userById";
		log.info(CLASS_NAME + _function + "::ENTER");
		log.debug(CLASS_NAME + _function + "::Requested User Id: {} ", id);
		User addedUser = userService.userById(id);
		if (addedUser != null) {
			log.info(CLASS_NAME + _function + "::EXIT");
			return ResponseEntity.status(HttpStatus.OK).body(addedUser);
		} else {
			log.error(CLASS_NAME + _function + "::User not available for given Id: {} ", id);
			throw idNotFound.apply(id);
		}
	}

	@ApiOperation("Get user by using the user name")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Returns the User using the FirstName or LastName of the User."),
			@ApiResponse(code = 404, message = "User not found for given name"), })
	@GetMapping("/v1/userName")
	public ResponseEntity<?> getUserByName(@RequestParam("userName") String name) {
		String _function = ".getUserByName";
		log.info(CLASS_NAME + _function + "::ENTER");
		log.debug(CLASS_NAME + _function + "::Requested User name: {} ", name);
		List<User> usersList = new ArrayList<>();
		usersList = userService.getUserByName(name);
		if (CollectionUtils.isEmpty(usersList)) {
			log.error(CLASS_NAME + _function + "::User not available for given name: {} ", name);
			throw nameNotFound.apply(name);
		} else {
			log.info(CLASS_NAME + _function + "::EXIT");
			return ResponseEntity.status(HttpStatus.OK).body(usersList);
		}
	}

	@ApiOperation("Update the User details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "User details are successfully updated to the DB."),
			@ApiResponse(code = 404, message = "User not found for the given id"), })
	@PutMapping("/v1/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
		String _function = ".updateUser";
		log.info(CLASS_NAME + _function + "::ENTER");
		log.debug(CLASS_NAME + _function + "::Requested User Id: {} ", id);
		log.debug(CLASS_NAME + _function + "::Requested User detail: {} ", user);
		User userToUpdate = userService.userById(id);
		if (userToUpdate != null) {
			UserUtil.createUserEntity(userToUpdate, user);
			User updatedUser = userService.addUser(userToUpdate);
			log.info(CLASS_NAME + _function + "::EXIT");
			return ResponseEntity.status(HttpStatus.OK).body(updatedUser);
		} else {
			log.error(CLASS_NAME + _function + "::User not available for given Id: {} ", id);
			throw idNotFound.apply(id);
		}
	}

	@ApiOperation("Delete User detail")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "User details are successfully deleted from the DB."),
			@ApiResponse(code = 404, message = "User not found for given Id"), })
	@DeleteMapping("/v1/user/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		String _function = ".deleteUser";
		log.info(CLASS_NAME + _function + "::ENTER");
		log.debug(CLASS_NAME + _function + "::Request to delete the user using Id: {} ", id);
		User user = userService.userById(id);
		if (user != null) {
			userService.deleteUser(id);
			log.info(CLASS_NAME + _function + "::User successfully deleted from the DB");
			return ResponseEntity.status(HttpStatus.OK).body(UserConstants.DELETE_MESSAGE);
		} else {
			log.error(CLASS_NAME + _function + "::User not available for given Id: {} ", id);
			throw idNotFound.apply(id);
		}

	}

	@ApiOperation("500 Error endpoint")
	@GetMapping("/v1/user/error")
	public ResponseEntity<?> errorEndpoint() {
		String _function = ".errorEndpoint";
		log.info(CLASS_NAME + _function + "::ENTER");
		throw serverError.get();
	}

	Function<Long, ResponseStatusException> idNotFound = (id) -> {
		return new ResponseStatusException(HttpStatus.NOT_FOUND, "No User Available with the given Id: " + id);
	};

	Function<String, ResponseStatusException> nameNotFound = (name) -> {
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No User Available for given name: " + name);
	};

	Supplier<ResponseStatusException> serverError = () -> {
		throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "RunTimeException from User Service");
	};

}
