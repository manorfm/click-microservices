package br.com.commons.rest.components.converters;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class StringToLocalDateTimeConverter extends BidirectionalConverter<String, LocalDateTime> {

	/*private static final String DD_MM_YYYY = "dd/MM/yyyy";
	private static final String YYYY_MM_DD = "yyyy/MM/dd";*/

	@Override
	public String convertFrom(LocalDateTime source, Type<String> dataType, MappingContext contex) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
		return source.format(formatter);
	}

	@Override
	public LocalDateTime convertTo(String source, Type<LocalDateTime> dataType, MappingContext contex) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(getPattern(source));
		return LocalDateTime.parse(source, formatter);
	}
	
	private String getPattern(String date) {
		/*if (date.matches("\\d{4}(\\/\\d+)+")) {
			return YYYY_MM_DD;
		} else if(date.matches("(\\d+\\/)+\\d{4}")) {
			return DD_MM_YYYY;
		} else*/
		if (date.matches("\\d+(-\\d+)+T\\d+(:\\d+)+")) {
			return "yyyy-MM-dd'T'HH:mm:ss"; 
		} else if (date.matches("\\d+(-\\d+)+T\\d+(:\\d+)+(\\-|\\+)\\d+")) {
			return "yyyy-MM-dd'T'HH:mm:ssZ";
		}
		
		throw new IllegalArgumentException(String.format("the %s does´t match with any of StringToLocalDateTimeConverter.class patterns", date));
	}
}
