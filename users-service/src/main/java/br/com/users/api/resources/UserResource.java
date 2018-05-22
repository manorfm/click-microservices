package br.com.users.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = false) // NOSONAR
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class UserResource {

	private Long id;
	
	private String name;
	
	@JsonIgnore
	private String password;
}
