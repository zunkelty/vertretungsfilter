package de.zunk.vertretungsalarm.client.ui.registration;

import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.messagebox.MessageBox;

public class RegistrationScreen extends AbsolutePanel {

	public RegistrationScreen() {

		try {
			Vertretungsalarm.getClientStorage().removeItem("schoolClass");
			Vertretungsalarm.getClientStorage().setItem("schoolClass", "7B");
			Location.replace(Location.createUrlBuilder().setParameter("showGuide", "yes").buildString());
		} catch (Exception e) {
			RootPanel.get().add(new MessageBox("Local Storage Fehler",
					"Die Local Storage wurde nicht gefunden.\nBitte wende dich an den Entwickler (s. Kontakt)", true));
		}

	}
}
