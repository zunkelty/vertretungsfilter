package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.client.GreetingServiceAsync;
import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.OptionsBar;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.TopBar;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class VertretungsalarmScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	TopBar topBar;
	OptionsBar optionsBar;
	VertretungsplanView infoView;
	// VertretungsplanViewExperimental infoView;

	public VertretungsalarmScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflow", "auto");
		getElement().getStyle().setProperty("background", "#ECE9FC");

		ArrayList<VertretungsEvent> userEvents = new ArrayList<VertretungsEvent>();

		topBar = new TopBar();

		optionsBar = new OptionsBar();
		optionsBar.addSettingsOption();

		infoView = new VertretungsplanView();
		// infoView = new VertretungsplanViewExperimental();

		greetingService.getVertretungsplan(new AsyncCallback<Vertretungsplan>() {

			@Override
			public void onSuccess(Vertretungsplan vertretungsplan) {

				ArrayList<VertretungsEvent> vertretungs_events = new ArrayList<>();
				vertretungs_events = vertretungsplan.getVertretungsEvents();

				for (VertretungsEvent event : vertretungs_events) {
					if (event.getSchoolClasses().contains(Vertretungsalarm.getClientStorage().getItem("schoolClass"))) {
						userEvents.add(event);
					}
				}

				infoView.presentEvents(userEvents);

				if (userEvents.size() > 0) {
					// HasEvents
					int rand = Random.nextInt(3);
					switch (rand) {
					case 0: {
						topBar.setInfoText(
								"Das ist alles für die " + Vertretungsalarm.getClientStorage().getItem("schoolClass"));
					}
						break;
					case 1: {
						topBar.setInfoText(
								"Da gibts was für die " + Vertretungsalarm.getClientStorage().getItem("schoolClass"));
					}
						break;
					case 2: {
						topBar.setInfoText("Das haben wir für die "
								+ Vertretungsalarm.getClientStorage().getItem("schoolClass") + " gefunden");
					}
						break;
					}

				} else {
					// NoEvents

					int rand = Random.nextInt(3);
					switch (rand) {
					case 0: {
						topBar.setInfoText("Gähnende Leere auf dem Vertretungsplan");
					}
						break;
					case 1: {
						topBar.setInfoText("Nichts für dich auf dem Vertretungplan");
					}
						break;
					case 2: {
						topBar.setInfoText("Alles läuft nach Plan");
					}
						break;
					}
				}

				add(topBar);
				add(infoView);
				add(optionsBar);

				resizeComponents();

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der Vertretungsplan kann im Moment nicht geladen werden! " + caught);
			}
		});

	}

	private void resizeComponents() {
		infoView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - topBar.getOffsetHeight() - optionsBar.getOffsetHeight() + "px");
	}
}