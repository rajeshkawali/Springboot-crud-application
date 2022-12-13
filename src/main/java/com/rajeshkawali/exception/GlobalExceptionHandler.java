package com.rajeshkawali.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rajeshkawali.constant.UserConstants;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Rajesh_Kawali
 *
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
		List<FieldError> fieldError = ex.getBindingResult().getFieldErrors();
		List<String> errorFields = new ArrayList<>();
		for (FieldError error : fieldError) {
			errorFields.add(error.getField());
		}
		String errorMsg = UserConstants.ERROR_MESSAGE+errorFields.stream().sorted().collect(Collectors.toList()).toString();
		log.error("ErrorMessage : " + errorMsg);

		return new ResponseEntity<>(errorMsg, HttpStatus.BAD_REQUEST);
	}
}
