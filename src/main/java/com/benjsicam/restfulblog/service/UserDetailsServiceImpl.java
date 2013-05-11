package com.benjsicam.restfulblog.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.benjsicam.restfulblog.domain.Author;

@SuppressWarnings("deprecation")
@Component("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AuthorService authorService;
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Author author = authorService.findByUsername(username);
		
		if(author == null)
			throw new UsernameNotFoundException("Username or password is invalid.");
		
		String userName = author.getUsername();
		String password = author.getPassword();
		
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new GrantedAuthorityImpl("User"));
		
		return new User(userName, password, true, true, true, true, authorities);
	}
}