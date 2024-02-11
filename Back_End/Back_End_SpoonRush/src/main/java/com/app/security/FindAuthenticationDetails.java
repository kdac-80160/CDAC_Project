package com.app.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class FindAuthenticationDetails {

	public Authentication getAuthenticationObject() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication != null && authentication.isAuthenticated())
			return authentication;
		return null;
	}

	public Long getUserId() {
		Authentication authentication = getAuthenticationObject();
		if(authentication!=null)
		{
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
	
	public String getAuthority()
	{
		Authentication authentication = getAuthenticationObject();
		if(authentication!=null)
		{
			 List<SimpleGrantedAuthority> list = (List<SimpleGrantedAuthority>) authentication.getAuthorities();
//			list.stream().map(a-> a.toString()).collect(Collectors.toList());
			 return list.get(0).toString();
		}
		return null;
	}
}
