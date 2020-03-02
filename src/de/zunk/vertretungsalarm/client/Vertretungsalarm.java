package de.zunk.vertretungsalarm.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;
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

	final static GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public static long startTime;

	@Override
	public void onModuleLoad() {

		// Sicherstellen, dass das Gerät eine LocalStorage besitzt

		client_storage = Storage.getLocalStorageIfSupported();

		if (client_storage == null) {
			Window.alert("Leider ist dein Gerät nicht für den " + getW***REMOVED***iteName()
					+ " qualifiziert!\nSag doch einem Admin Bescheid. \n(Grund: Storage.isLocalStorageSupported == false)");
		}

		if (Location.getParameter("page") == "contact") {
			ContactScreen screen = new ContactScreen();
			RootPanel.get().add(screen, 0, 0);
		} else if (Location.getParameter("page") == "legal") {
			LegalScreen screen = new LegalScreen();
			RootPanel.get().add(screen, 0, 0);
		} else if (Location.getParameter("page") == "about") {
			AboutScreen screen = new AboutScreen();
			RootPanel.get().add(screen, 0, 0);
		} else if (Vertretungsalarm.getClientStorage().getItem("username") != null
				&& Vertretungsalarm.getClientStorage().getItem("password") != null) {

			Message msg = new Message("Gleich geht es weiter...",
					"Der Vertretungsfilter überprüft gerade, dass du berechtigt bist auf den Vertretungsfilter zuzugreifen.",
					ButtonLayoutOption.NONE, CloseAction.NONE);

			RootPanel.get().add(msg);

			greetingService.validateLogin(Vertretungsalarm.getClientStorage().getItem("username"),
					Vertretungsalarm.getClientStorage().getItem("password"), new AsyncCallback<Boolean>() {

						@Override
						public void onSuccess(Boolean success) {
							RootPanel.get().remove(msg);
							if (success) {

								// Weiterleiten zur richtigen Stelle der W***REMOVED***ite

								if (client_storage.getItem("schoolClass") == null) {
									RegistrationScreen screen = new RegistrationScreen();
									RootPanel.get().add(screen, 0, 0);
								} else if (client_storage.getItem("hasAcceptedImportantNotice") == null) {
									ImportantNoticeScreen screen = new ImportantNoticeScreen();
									RootPanel.get().add(screen, 0, 0);
								} else if (Location.getParameter("page") == "settings") {
									SettingsScreen screen = new SettingsScreen();
									RootPanel.get().add(screen, 0, 0);
								} else {

									VertretungsalarmScreen screen = new VertretungsalarmScreen();
									RootPanel.get().add(screen, 0, 0);
								}

							} else {
								// NO SUCCESS

								RootPanel.get().add(new Message("Falsche Logindaten", "Bitte melde Dich erneut an!",
										ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.RELOAD));
								Vertretungsalarm.getClientStorage().removeItem("username");
								Vertretungsalarm.getClientStorage().removeItem("password");
							}

						}

						@Override
						public void onFailure(Throwable caught) {
							RootPanel.get().remove(msg);
							RootPanel.get().add(new Message("Fehler",
									"Die Logindaten konnten nicht überprüft werden. Bitte versuche es später erneut."
											+ caught,
									ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.CLOSE));
							RootPanel.get().getElement().getStyle().setProperty("background", "#F0C267");
							LockScreen screen = new LockScreen();
							RootPanel.get().add(screen, 0, 0);
						}
					});

		} else {
			RootPanel.get().getElement().getStyle().setProperty("background", "#F0C267");
			LockScreen screen = new LockScreen();
			RootPanel.get().add(screen, 0, 0);
		}
	}

	public static String getW***REMOVED***iteName() {
		return name;
	}

	public static Storage getClientStorage() {
		return client_storage;
	}

}
