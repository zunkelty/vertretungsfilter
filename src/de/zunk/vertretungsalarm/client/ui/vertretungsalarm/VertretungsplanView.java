package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class VertretungsplanView extends AbsolutePanel {

	DateTimeFormat dtf;

	public VertretungsplanView() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "auto");

	}

	protected void presentEvents(ArrayList<VertretungsEvent> userEvents) {

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

	}

}
