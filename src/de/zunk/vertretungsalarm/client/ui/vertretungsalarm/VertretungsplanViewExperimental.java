package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class VertretungsplanViewExperimental extends AbsolutePanel {

	DateTimeFormat dtf;

	ArrayList<DayView> allDayViews;

	public VertretungsplanViewExperimental() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "auto");

	}

	protected void presentEvents(ArrayList<VertretungsEvent> userEvents) {

		ArrayList<VertretungsEvent> dayEvents = new ArrayList<VertretungsEvent>();

		for (VertretungsEvent vE : userEvents) {
			DayView dV = new DayView();
			dV.setDate(vE.getDate());
			dayEvents.add(vE);
			userEvents.remove(vE);
			for (VertretungsEvent otherVE : userEvents) {

				if (otherVE.getDateAsText().contains(dV.getDate().toString())) {
					dayEvents.add(otherVE);
					userEvents.remove(otherVE);
				}
			}
			dV.presentDayEvents(dayEvents);
			add(dV);
			dayEvents.clear();
		}

	}

}
