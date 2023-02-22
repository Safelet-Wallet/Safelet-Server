package com.safelet.walletserver.service;

import com.safelet.walletserver.model.User;
import com.safelet.walletserver.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Main service class for Users.
 * @authors Andres Sanchez,
 */
@Service
public class UserService {

	private final UserRepository repository;

	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	public Optional<User> getById(Long id) {
		return repository.findById(id);
	}

	public Optional<User> getByUsername(String username){
		return repository.findByUsername(username);
	}

	public Optional<User> findByUsernameAndPassword(String username, String password){
		return repository.findByUsernameAndPassword(username, password);
	}

	public User create(User user){ return repository.save(user); }

    public User update(User user) {
        return repository.save(user);
    }

    public boolean delete(Long id) {
		if (repository.existsById(id)){
			repository.deleteById(id);
			return true;
		}
		return false;
	}
}
