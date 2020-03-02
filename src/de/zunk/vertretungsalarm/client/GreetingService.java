package de.zunk.vertretungsalarm.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.zunk.vertretungsalarm.shared.Vertretungsplan;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	Vertretungsplan getVertretungsplan() throws IllegalArgumentException;

	Boolean reloadVertretungsplan() throws IllegalArgumentException;

	Boolean validateLogin(String username, String password) throws IllegalArgumentException;
}
