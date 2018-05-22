package br.com.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="User already exists")
public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 5186765795932546148L;

	public UserAlreadyExistException(Long id) {
		super(String.format("User with id: %d already registred.", id));
	}
}
