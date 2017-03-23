package com.gazatem.ekip.service;

import com.gazatem.ekip.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
