/**
 * JwtAuthResponse class is used to represent the response after successful authentication.
 * It contains the JWT token, the username of the authenticated user, and the authorities granted to the user.
 *
 * @author Tejas_Medade
 */
package com.synchrony.configurations.JWT.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * The JwtAuthResponse class contains the token, username, and authorities of the authenticated user.
 * This class is used to transfer information back to the client after successful authentication.
 * The JWT token is either sent via a cookie or in the JSON response body.
 */
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthResponse {

	/**
	 * The JWT token generated after successful authentication.
	 * This will be sent either through a cookie or JSON body, based on the configuration.
	 * If the token is to be sent through a cookie, remove this field from the JSON body response.
	 */
	private String Token;

	/**
	 * The username of the authenticated user.
	 * This represents the identity of the user in the system.
	 */
	private String username;

	/**
	 * The authorities granted to the authenticated user.
	 * This is a collection of roles or permissions assigned to the user.
	 */
	private Collection<? extends GrantedAuthority> authorities;

	public String getToken() {
		return Token;
	}

	public void setToken(String token) {
		Token = token;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	public JwtAuthResponse(String token, String username, Collection<? extends GrantedAuthority> authorities) {
		Token = token;
		this.username = username;
		this.authorities = authorities;
	}
}
