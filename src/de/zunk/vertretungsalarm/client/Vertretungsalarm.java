package de.zunk.vertretungsalarm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.ui.optionscreens.AboutScreen;
import de.zunk.vertretungsalarm.client.ui.optionscreens.ContactScreen;
import de.zunk.vertretungsalarm.client.ui.optionscreens.LegalScreen;
import de.zunk.vertretungsalarm.client.ui.optionscreens.SettingsScreen;
import de.zunk.vertretungsalarm.client.ui.registration.ImportantNoticeScreen;
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

		if (isDeviceAllowed()) {

			// Weiterleiten zur richtigen Stelle der W***REMOVED***ite

			if (client_storage.getItem("schoolClass") == null) {
				RegistrationScreen screen = new RegistrationScreen();
				RootPanel.get().add(screen, 0, 0);
			} else if (client_storage.getItem("hasAcceptedImportantNotice") == null) {
				ImportantNoticeScreen screen = new ImportantNoticeScreen();
				RootPanel.get().add(screen, 0, 0);
			} else if (Location.getParameter("page") == "about") {
				AboutScreen screen = new AboutScreen();
				RootPanel.get().add(screen, 0, 0);
			} else if (Location.getParameter("page") == "settings") {
				SettingsScreen screen = new SettingsScreen();
				RootPanel.get().add(screen, 0, 0);
			} else if (Location.getParameter("page") == "contact") {
				ContactScreen screen = new ContactScreen();
				RootPanel.get().add(screen, 0, 0);
			} else if (Location.getParameter("page") == "legal") {
				LegalScreen screen = new LegalScreen();
				RootPanel.get().add(screen, 0, 0);
			} else {

				VertretungsalarmScreen screen = new VertretungsalarmScreen();
				RootPanel.get().add(screen, 0, 0);
			}
		} else {
			RootPanel.get().getElement().getStyle().setProperty("background", "#F0C267");
			LockScreen screen = new LockScreen();
			RootPanel.get().add(screen);
		}
	}

	private boolean isDeviceAllowed() {
		return true;
	}

	public static String getW***REMOVED***iteName() {
		return name;
	}

	public static Storage getClientStorage() {
		return client_storage;
	}

}
