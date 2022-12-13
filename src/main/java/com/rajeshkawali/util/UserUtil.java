package com.rajeshkawali.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.rajeshkawali.model.User;
/**
 * @author Rajesh_Kawali
 *
 */
@Component
public class UserUtil {

	public static void createUserEntity(User userToUpdate, User updateUser) {
		if (checkEmptyNullString(updateUser.getFirstName())
				&& !updateUser.getFirstName().equals(userToUpdate.getFirstName())) {
			userToUpdate.setFirstName(updateUser.getFirstName());
		}
		if (checkEmptyNullString(updateUser.getLastName())
				&& !updateUser.getLastName().equals(userToUpdate.getLastName())) {
			userToUpdate.setLastName(updateUser.getLastName());
		}
		if (updateUser != null && updateUser.getAge() != userToUpdate.getAge()) {
			userToUpdate.setAge(updateUser.getAge());
		}
		if (checkEmptyNullString(updateUser.getGender())
				&& !updateUser.getGender().equals(userToUpdate.getGender())) {
			userToUpdate.setGender(updateUser.getGender());
		}
		if (checkEmptyNullString(updateUser.getRole())
				&& !updateUser.getRole().equals(userToUpdate.getRole())) {
			userToUpdate.setRole(updateUser.getRole());
		}
	}

	public static boolean checkEmptyNullString(String input) {
		return !StringUtils.isEmpty(input) && !StringUtils.isEmpty(input.trim());
	}
}
