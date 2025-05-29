
package com.mantprev.exceptions;

import lombok.Getter;

@Getter
public class UserNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private final String errorMessage;
	
	
	public UserNotFoundException(final String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
