package com.springweb.svcImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.springweb.daoImpl.UserRepository;
import com.springweb.model.LoginForm;
import com.springweb.model.User;
import com.springweb.svc.UserSvc;

@Service
public class UserSvcImpl implements UserSvc {

	@Autowired
	UserRepository userRepo;
	
	@Override
	public void addUser(User form) {
		form.setPassword(getHashPwd(form.getPassword()));
		userRepo.save(form);
		
	}

	@Override
	public void updateUser(User form) {
		userRepo.save(form);
	}

	@Override
	public void deleteUser(String userId) {
		userRepo.deleteById(userId);
	}

	@Override
	public User getUser(String userId) {
		return userRepo.findByUserId(userId);
	}
	
	@Override
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	public boolean validateUser(LoginForm login) {
		User userDetails = getUser(login.getUserId());
		if(userDetails==null) {
			return false;
		}
		
		boolean isValidUser = validatePwd(login.getPassword(), userDetails.getPassword());
		return isValidUser;
	}
	
	
	private String getHashPwd(String rawPwd) {
        String hashedPwd = BCrypt.hashpw(rawPwd, BCrypt.gensalt(12));
        return hashedPwd;
        
	}
	
	private boolean validatePwd(String rawPwd, String hashPwd) {
		boolean matched = BCrypt.checkpw(rawPwd, hashPwd);
		return matched;
        
	}

	
}
