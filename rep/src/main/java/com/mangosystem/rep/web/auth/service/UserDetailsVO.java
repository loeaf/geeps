package com.mangosystem.rep.web.auth.service;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@SuppressWarnings("serial")
public class UserDetailsVO implements UserDetails {
	
	private String username = null;
	
	private String password = null;
	
	private Collection<? extends GrantedAuthority> authorities = null;
	
	
	public UserDetailsVO() {
		/*
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		this.authorities = authorities;
		*/
	}
	
	public UserDetailsVO(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public UserDetailsVO(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.authorities = authorities;
	}
	
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//		return authorities;
//	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
