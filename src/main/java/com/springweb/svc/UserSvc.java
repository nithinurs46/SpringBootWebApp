package com.springweb.svc;

import java.util.List;

import com.springweb.model.LoginForm;
import com.springweb.model.User;

public interface UserSvc {

	void addUser(User form);

	void updateUser(User form);

	void deleteUser(String userId);

	User getUser(String userId);

	List<User> getAllUsers();

	boolean validateUser(LoginForm login);

}
