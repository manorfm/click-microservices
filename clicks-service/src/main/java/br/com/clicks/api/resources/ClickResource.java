package br.com.clicks.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@JsonInclude(Include.NON_NULL)
public class ClickResource {

	private Long id;
	private Long userId;
	private String timer;
	private UserResource user;
}
