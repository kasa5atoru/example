package com.onoazusa.service;

import java.util.List;

import com.onoazusa.model.User;

public interface UserService {
	public void addUser(User p);
	public void updateUser(User p);
	public List<User> listUsers();
	public User getUserById(int id);
	public void removeUser(int id);
}
