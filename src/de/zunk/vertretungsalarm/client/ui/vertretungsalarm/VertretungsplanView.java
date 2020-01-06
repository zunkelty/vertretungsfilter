package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class VertretungsplanView extends AbsolutePanel {

	DateTimeFormat dtf;

	Button noEventsImage;
	Label noEventsLabel;

	public VertretungsplanView() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "auto");

		Timer timer = new Timer() {
			@Override
			public void run() {
				Location.reload();
			}
		};
		timer.schedule(5 * 60 * 1000);

	}

	protected void presentEvents(ArrayList<VertretungsEvent> userEvents) {

		if (userEvents.size() > 0) {

			ArrayList<VertretungsEvent> dayEvents = new ArrayList<VertretungsEvent>();

			ArrayList<String> dates = new ArrayList<String>();
			for (VertretungsEvent vE : userEvents) {
				dates.add(vE.getDateAsText());
			}
			LinkedHashSet<String> hashSet = new LinkedHashSet<>(dates);
			dates.clear();
			dates = new ArrayList<>(hashSet);

			for (String date : dates) {
				DayView dV = new DayView();
				for (VertretungsEvent vE : userEvents) {
					if (vE.getDateAsText() == date) {
						dayEvents.add(vE);
						dV.setDate(vE.getDate());
					}
				}
				dV.presentDayEvents(dayEvents);
				add(dV);
				dayEvents.clear();
			}
		} else {
			noEventsImage = new Button("");
			noEventsImage.setHeight("200px");
			noEventsImage.getElement().getStyle().setProperty("outline", "0");
			noEventsImage.getElement().getStyle().setProperty("border", "0px");
			noEventsImage.getElement().getStyle().setProperty("background",
					"url(pictures/hasNoEvents.png) no-repeat center transparent");
			noEventsImage.getElement().getStyle().setProperty("backgroundSize", "contain");
			noEventsImage.getElement().getStyle().setProperty("margin", "30px 10px");
			add(noEventsImage);

			noEventsLabel = new Label(
					"Zurzeit steht für deine Klasse nichts auf dem Vertretungsplan. Der Unterricht findet wie geplant statt.");
			noEventsLabel.getElement().getStyle().setProperty("fontSize", "2.5vh");
			// noEventsLabel.getElement().getStyle().setProperty("fontWeight", "bold");
			noEventsLabel.getElement().getStyle().setProperty("textAlign", "center");
			noEventsLabel.getElement().getStyle().setProperty("margin", "0px 20px");
			// noEventsLabel.getElement().getStyle().setProperty("", "");
			// noEventsLabel.getElement().getStyle().setProperty("", "");
			add(noEventsLabel);
		}

	}

}
