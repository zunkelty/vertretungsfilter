package de.zunk.vertretungsalarm.client.ui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.client.GreetingServiceAsync;
import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class VertretungsalarmScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	public VertretungsalarmScreen() {

		greetingService.getVertretungsplan(new AsyncCallback<Vertretungsplan>() {

			@Override
			public void onSuccess(Vertretungsplan vertretungsplan) {

				ArrayList<VertretungsEvent> vertretungs_events = new ArrayList<>();
				vertretungs_events = vertretungsplan.getVertretungsEvents();

				for (VertretungsEvent vE : vertretungs_events) {
					if (vE.getSchoolClasses().contains(Vertretungsalarm.getClientStorage().getItem("schoolClass"))) {
						Window.alert("Event: " + vE.toString());
					}
				}

			}

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Der Vertretungsplan kann im Moment nicht geladen werden! " + caught);
			}
		});

	}
}