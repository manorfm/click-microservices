package br.com.users.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.com.commons.domain.PersistenceObject;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user")
@AttributeOverride(name="id", column=@Column(name="usr_id"))
@EqualsAndHashCode(callSuper=false)
@Getter @Setter
public class User extends PersistenceObject {
	
	private static final long serialVersionUID = 4439931903656975507L;

	@Column(name = "usr_nm")
	private String name;
	
	@Column(name = "usr_pw")
	private String password;
}
