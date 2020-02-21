package de.zunk.vertretungsalarm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.ui.optionscreens.AboutScreen;
import de.zunk.vertretungsalarm.client.ui.optionscreens.ImpressumScreen;
import de.zunk.vertretungsalarm.client.ui.optionscreens.PrivacyScreen;
import de.zunk.vertretungsalarm.client.ui.optionscreens.SettingsScreen;
import de.zunk.vertretungsalarm.client.ui.registration.RegistrationScreen;
import de.zunk.vertretungsalarm.client.ui.vertretungsalarm.VertretungsalarmScreen;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Vertretungsalarm implements EntryPoint {

	public static String name = "Vertretungsalarm";

	static Storage client_storage;

	public static long startTime;

	@Override
	public void onModuleLoad() {

		// Sicherstellen, dass das Gerät eine LocalStorage besitzt

		client_storage = Storage.getLocalStorageIfSupported();

		if (client_storage == null) {
			Window.alert("Leider ist dein Gerät nicht für den " + getW***REMOVED***iteName()
					+ " qualifiziert!\nSag doch einem Admin Bescheid. \n(Grund: Storage.isLocalStorageSupported == false)");
		}

		// Weiterleiten zur richtigen Stelle der W***REMOVED***ite

		if (Location.getParameter("page") == "legal") {

			ImpressumScreen screen = new ImpressumScreen();
			RootPanel.get().add(screen, 0, 0);

		} else if (Location.getParameter("page") == "about") {

			AboutScreen screen = new AboutScreen();
			RootPanel.get().add(screen, 0, 0);

		} else if (Location.getParameter("page") == "privacy") {

			PrivacyScreen screen = new PrivacyScreen();
			RootPanel.get().add(screen, 0, 0);

		} else if (Location.getParameter("page") == "settings") {

			SettingsScreen screen = new SettingsScreen();
			RootPanel.get().add(screen, 0, 0);

			// } else if (Location.getParameter("page") == "fullscreenEventView") {
			//
			// DetailedEventViewScreen screen = new DetailedEventViewScreen();
			// RootPanel.get().add(screen, 0, 0);

		} else {

			if (client_storage.getItem("schoolClass") == null) {
				RegistrationScreen screen = new RegistrationScreen();
				RootPanel.get().add(screen, 0, 0);
			} else {
				VertretungsalarmScreen screen = new VertretungsalarmScreen();
				RootPanel.get().add(screen, 0, 0);
			}
		}
	}

	public static String getW***REMOVED***iteName() {
		return name;
	}

	public static Storage getClientStorage() {
		return client_storage;
	}

}
