/**
 * JwtAuthRequest class is used to handle the authentication request
 * containing the username and password for JWT generation.
 * This class serves as a data transfer object for the authentication process.
 *
 * @author Tejas_Medade
 */
package com.synchrony.configurations.JWT.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The JwtAuthRequest class contains two fields: username and password,
 * which are needed for the authentication process to generate a JWT token.
 * It uses Lombok annotations to generate the boilerplate code for getters,
 * setters, constructors, equals, hashCode, and toString methods.
 */
@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class JwtAuthRequest {

	/**
	 * The username of the user trying to authenticate.
	 * This will be used to identify the user in the system.
	 */
	private String username;

	/**
	 * The password of the user trying to authenticate.
	 * This will be used to verify the user's identity.
	 */
	private String password;

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
}
