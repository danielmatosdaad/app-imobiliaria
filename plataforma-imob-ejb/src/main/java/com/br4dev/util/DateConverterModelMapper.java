package com.br4dev.util;

import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.xml.datatype.XMLGregorianCalendar;

import org.modelmapper.MappingException;
import org.modelmapper.internal.Errors;
import org.modelmapper.spi.ConditionalConverter;
import org.modelmapper.spi.MappingContext;

public class DateConverterModelMapper implements ConditionalConverter<String, Date> {

	@Override
	public Date convert(MappingContext<String, Date> context) {

		Object source = context.getSource();
		if (source == null) {
			return null;
		}
		Class<?> destinationType = context.getDestinationType();

		return dateFor((String) source.toString(), destinationType);
	}

	@Override
	public org.modelmapper.spi.ConditionalConverter.MatchResult match(Class<?> sourceType, Class<?> destinationType) {
		// TODO Auto-generated method stub
		return null;
	}

	Date dateFor(String source, Class<?> destinationType) {

		String sourceStr = ((java.lang.String) source).trim();

		if (sourceStr.length() == 0) {
			throwException(source, destinationType);
		}

		if (destinationType.equals(Date.class)) {
			try {

				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");

				return sdf.parse(sourceStr);

			} catch (IllegalArgumentException | ParseException e) {
				throwException(source, destinationType,
						"String must be format [dd/MM/YYYY] to create a java.util.Date");
			}
		}

		if (destinationType.equals(java.sql.Date.class)) {
			try {
				return java.sql.Date.valueOf(sourceStr);

			} catch (IllegalArgumentException e) {
				throwException(source, destinationType,
						"String must be in JDBC format [yyyy-MM-dd] to create a java.sql.Date");
			}
		}

		if (destinationType.equals(Time.class)) {
			try {
				return Time.valueOf(sourceStr);

			} catch (IllegalArgumentException e) {
				throwException(source, destinationType,
						"String must be in JDBC format [HH:mm:ss] to create a java.sql.Time");
			}
		}

		if (destinationType.equals(Timestamp.class)) {
			try {
				return Timestamp.valueOf(sourceStr);

			} catch (IllegalArgumentException e) {
				throwException(source, destinationType,
						"String must be in JDBC format [yyyy-MM-dd HH:mm:ss.fffffffff] to create a java.sql.Timestamp");
			}
		}
		throwException(source, destinationType);
		return null;

	}

	private void throwException(String source, Class<?> destinationType, String message) {
		Errors erros = new Errors();
		erros.addMessage(message);
		MappingException ex = erros.errorMapping(source, destinationType).toMappingException();
		throw ex;

	}

	private void throwException(Object source, Class<?> destinationType) {
		Errors erros = new Errors();
		MappingException ex = erros.errorMapping(source, destinationType).toMappingException();
		throw ex;
	}
}
