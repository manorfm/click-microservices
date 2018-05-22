package br.com.clicks.domain;

import java.time.LocalDateTime;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.LocalDateTimeConverter;

import br.com.commons.domain.PersistenceObject;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "click")
@AttributeOverride(name="id", column=@Column(name="clk_id"))
@EqualsAndHashCode(callSuper=false)
public @Data class Click extends PersistenceObject {

	private static final long serialVersionUID = -2943136365144245908L;
	
	public Click() { }
	
	public Click(Long userId, LocalDateTime timer) {
		this.timer = timer;
		this.userId = userId;
	}
	
	@NotNull(message = "Click cannot be saved without user id.")
	@Column(name = "usr_id")
	private Long userId;
	
	@Column(name = "clk_dh_timer")
	@Convert(converter = LocalDateTimeConverter.class)
	private LocalDateTime timer;
	
	
}