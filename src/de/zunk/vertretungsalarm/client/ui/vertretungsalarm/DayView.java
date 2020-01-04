package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class DayView extends AbsolutePanel {

	DateLabel dateLabel;
	InfoView info;
	EventView events;

	ArrayList<VertretungsEvent> dayEvents;

	public DayView(Date date) {

		dateLabel = new DateLabel(date);
		info = new InfoView("");
		events = new EventView("");

		dayEvents = new ArrayList<VertretungsEvent>();

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("overflow", "auto");
		getElement().getStyle().setProperty("margin", "10px");

		add(dateLabel);
		add(info);
		add(events);

	}

	protected void presentDayEvents(ArrayList<VertretungsEvent> dayEvents) {
		events.presentEvents(dayEvents);
		info.presentDayInfo(dayEvents);
	}

}
