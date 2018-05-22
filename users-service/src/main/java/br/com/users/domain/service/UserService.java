package br.com.users.domain.service;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.commons.security.KeySecurity;
import br.com.commons.security.KeyUtil;
import br.com.users.domain.User;
import br.com.users.domain.repository.UserRepository;
import br.com.users.exception.UserAlreadyExistException;
import br.com.users.exception.UserNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Transactional
	public void save(User user) {
		try {
			if (user.getId() == null || findOne(user.getId()) == null) {
				user.setPassword(KeyUtil.doHash(user.getPassword(), KeySecurity.KEY));
				userRepository.save(user);
			} else {
				throw new UserAlreadyExistException(user.getId());
			}
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	@Transactional
	public void update(User user) {
		userRepository.save(user);
	}
	
	private User findOne(Long id) {
		return userRepository.findOne(id);
	}

	@Transactional
    public void remove(User user) {
	    userRepository.delete(user);
    }

	@Transactional
    public void remove(Long id) {
		User user = get(id);
	    userRepository.delete(user);
    }
	
	@Transactional
	public User get(Long id) {
		User user = userRepository.findOne(id);
		if (user == null) {
			throw new UserNotFoundException(id);
		}
	    return user;
	}

	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Transactional
	public User login(String name, String password) {
		try {
			return userRepository.findOneByNameAndPassword(name, KeyUtil.doHash(password, KeySecurity.KEY));
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}
}