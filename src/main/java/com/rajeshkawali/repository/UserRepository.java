package com.rajeshkawali.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rajeshkawali.model.User;

import java.util.List;

/**
 * @author Rajesh_Kawali
 *
 */
@Repository
public interface UserRepository extends CrudRepository<User, Long> {

	@Query("select m from User m where m.firstName like %?1% or m.lastName like %?1%")
	List<User> findByUserName(String name);
}