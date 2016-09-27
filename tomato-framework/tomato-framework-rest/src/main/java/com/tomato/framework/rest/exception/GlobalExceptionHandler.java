package com.tomato.framework.rest.exception;

import javax.servlet.http.HttpServletRequest;

import lombok.extern.slf4j.Slf4j;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.tomato.framework.rest.helper.ViewModelHelper;
import com.tomato.framework.rest.result.ViewModelResult;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ViewModelResult<?> defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {

		if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
			throw e;
		}
		log.error("", e);
		return ViewModelHelper.NOViewModelResult(500, e.getMessage());
	}

}
