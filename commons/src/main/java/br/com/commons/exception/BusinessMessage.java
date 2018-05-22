package br.com.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BusinessMessage {

	private String title;
	private String message;
	
}
