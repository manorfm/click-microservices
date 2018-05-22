package br.com.clicks.api.user.model;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(callSuper=false)
@Getter @Setter
public class User implements Serializable {

	private static final long serialVersionUID = -8439613328026008430L;

	private Long id;

	private String name;
	
}
