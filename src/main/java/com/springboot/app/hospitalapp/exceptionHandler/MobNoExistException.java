package com.springboot.app.hospitalapp.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND, reason="Mob No already Exists")
public class MobNoExistException extends Exception {

	 public MobNoExistException(String msg) {
	        super(msg);
	    }
}
