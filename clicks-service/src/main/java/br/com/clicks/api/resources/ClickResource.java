package br.com.clicks.api.resources;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.ToString;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ClickResource {

	private Long userId;
	private String timer;
	private UserResource user;
}
