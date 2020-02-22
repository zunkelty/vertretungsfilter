package de.zunk.vertretungsalarm.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	private VertretungsalarmService vertretungsalarmService;

	public GreetingServiceImpl() {
		vertretungsalarmService = new VertretungsalarmService();
	}

	@Override
	public Vertretungsplan getVertretungsplan() throws IllegalArgumentException {
		return vertretungsalarmService.getVertretungsplan();
	}

	@Override
	public Boolean reloadVertretungsplan() throws IllegalArgumentException {
		VertretungsalarmService.loadVertretungsplan();
		return true;
	}
}
