package br.com.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="User not found.")
public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 5186765795932546148L;

	public UserNotFoundException(Long pis) {
		super(String.format("User with id: %d not found", pis));
	}
}
