package com.safelet.walletserver.service;

import com.safelet.walletserver.model.User;
import com.safelet.walletserver.repository.UserRepository;
import org.springframework.stereotype.Service;

/**
 * Main service class for Users.
 * @authors Andres Sanchez,
 */
@Service
public class UserService {

	private final UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public User getUserInfo(String username){
		return userRepository.findByName(username);
	}

	public User create(User user){ return userRepository.save(user); }
}
