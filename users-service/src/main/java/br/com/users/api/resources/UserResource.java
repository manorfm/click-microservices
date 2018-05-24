package br.com.users.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false) // NOSONAR
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@JsonInclude(Include.NON_NULL)
public class UserResource {

	private Long id;
	
	private String name;
	
	private String password;
}
