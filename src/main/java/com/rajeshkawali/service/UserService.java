package com.rajeshkawali.service;

import java.util.List;

import com.rajeshkawali.model.User;



/**
 * @author Rajesh_Kawali
 *
 */
public interface UserService {

	public List<User> getAllUsers();

	public User addUser(User user);

	public User userById(Long id);

	public List<User> getUserByName(String name);

	public void deleteUser(Long id);

}
