package com.gazatem.ekip.service;

import com.gazatem.ekip.model.Role;
import com.gazatem.ekip.model.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
	public void createUser(User user);
	public List<User> findAll();
	public List<Role> findAllRoles();
}
