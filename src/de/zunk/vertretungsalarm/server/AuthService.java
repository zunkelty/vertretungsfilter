package de.zunk.vertretungsalarm.server;

import java.io.Serializable;

public class AuthService implements Serializable {

	private static final long serialVersionUID = 1L;

	public AuthService() {

	}

	public static boolean validateLogin(String username, String password) {
		// if (username.contains("***REMOVED***") && password.contains("***REMOVED***-han")) {
		// return true;
		// }
		if (username.contains("beta") && password.contains("vertretungsfilter2020")) {
			return true;
		}
		return false;
	}
}