package com.app.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class FindAuthenticationDetails {
	
	public Long getUserId() {
		// Retrieve the authentication object from the SecurityContext
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated()) {
			// Get the principal (which should be UserDetails or a custom user type)
			Object credentials = authentication.getCredentials();
			

			// You can check the type of the principal and cast accordingly
			if (credentials instanceof Long) {
				Long userDetails = (Long) credentials;
				return userDetails; 
			}

		}

		return null;
	}
}
