package de.zunk.vertretungsalarm.client.ui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTML;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.client.GreetingServiceAsync;
import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class VertretungsalarmScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	TopBar topBar;
	HTML info;

	public VertretungsalarmScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");

		ArrayList<VertretungsEvent> userEvents = new ArrayList<VertretungsEvent>();

		topBar = new TopBar();
		topBar.getElement().getStyle().setProperty("flex", "0.025 0 auto");

		info = new HTML("Hier sollte eigentlich was anderes stehen...");
		info.getElement().getStyle().setProperty("flex", "0.3 0 auto");
		info.getElement().getStyle().setProperty("textAlign", "center");
		info.getElement().getStyle().setProperty("fontFamily", "Roboto");
		info.getElement().getStyle().setProperty("fontSize", "4vh");
		info.getElement().getStyle().setProperty("margin", "40px");
		info.getElement().getStyle().setProperty("marginTop", "60px");
		// info.getElement().getStyle().setProperty("background", "#FF0000");

		greetingService.getVertretungsplan(new AsyncCallback<Vertretungsplan>() {

			@Override
			public void onSuccess(Vertretungsplan vertretungsplan) {

				ArrayList<VertretungsEvent> vertretungs_events = new ArrayList<>();
				vertretungs_events = vertretungsplan.getVertretungsEvents();

				for (VertretungsEvent event : vertretungs_events) {
					if (event.getSchoolClasses().contains(Vertretungsalarm.getClientStorage().getItem("schoolClass"))) {
						// userEvents.add(event);
						// Window.alert(event.toString());
					}
				}

				if (userEvents.size() > 0) {
					// HasEvents
					getElement().getStyle().setProperty("background",
							"url(pictures/hasEvents-full-screen.png) no-repeat center");
					getElement().getStyle().setProperty("backgroundSize", "cover");
					topBar.setMainColor("#d1ddf7");
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
					getElement().getStyle().setProperty("background",
							"url(pictures/hasNoEvents-full-screen.png) no-repeat center");
					getElement().getStyle().setProperty("backgroundSize", "cover");
					topBar.setMainColor("#d1ddf7");
					info.setHTML("Zurzeit steht auf dem <br> Vertretungsplan nichts <br>für die <b><font size=\"6vh\">"
							+ Vertretungsalarm.getClientStorage().getItem("schoolClass"));
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
				add(info);

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der Vertretungsplan kann im Moment nicht geladen werden! " + caught);
			}
		});

	}
}