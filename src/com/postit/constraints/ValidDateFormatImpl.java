package com.postit.constraints;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidDateFormatImpl implements ConstraintValidator<ValidDateFormat, String> {

	@Override
	public void initialize(ValidDateFormat constraintAnnotation) {

	}

	@Override
	public boolean isValid(String dateString, ConstraintValidatorContext context) {

		SimpleDateFormat format = new SimpleDateFormat("dd-mm-yyyy");

		try {
			format.parse(dateString);
			return true;
		} catch (ParseException e) {
			return false;
		}

	}

}
