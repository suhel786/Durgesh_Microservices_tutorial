package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.User;

public interface UserService {

	// Create
	User saveUser(User user);

	// get all users
	List<User> getAllusers();

	// get single user of given userId
	User getUser(String userId);

	// delete user
	void deleteUser(String userId);

	// update user
	User updateuser(String userId, User updatedUser);
}
