package de.zunk.vertretungsalarm.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.zunk.vertretungsalarm.shared.Vertretungsplan;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void getVertretungsplan(AsyncCallback<Vertretungsplan> callback);

	void reloadVertretungsplan(AsyncCallback<Boolean> callback);
}
