package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.shared.DayInfo;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class VertretungsplanView extends AbsolutePanel {

	DateTimeFormat dtf;

	DayView dV;

	Button noEventsImage;
	Label noEventsLabel;

	Label timeLabel;

	public VertretungsplanView(ArrayList<VertretungsEvent> userEvents, ArrayList<DayInfo> dayInfos, String time) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("flexShrink", "0");

		ArrayList<String> dates = new ArrayList<String>();
		for (VertretungsEvent vE : userEvents) {
			// dates.add(vE.getDateAsText());
		}
		for (DayInfo dayInfo : dayInfos) {
			dates.add(dayInfo.getDate().toString());
		}

		LinkedHashSet<String> datesHashSet = new LinkedHashSet<>(dates);
		dates.clear();
		dates = new ArrayList<>(datesHashSet);

		ArrayList<VertretungsEvent> dayEvents = new ArrayList<VertretungsEvent>();

		for (String date : dates) {

			for (VertretungsEvent vE : userEvents) {
				if (vE.getDateAsText() == date) {
					dayEvents.add(vE);
				}
			}

			DayInfo dayInfo = new DayInfo();
			for (DayInfo info : dayInfos) {
				if (info.getDate().toString() == date) {
					dayInfo = info;
					break;
				}
			}

			dV = new DayView(dayEvents, dayInfo);
			add(dV);
			dayEvents.clear();
		}

		timeLabel = new Label("Daten von " + time);
		timeLabel.getElement().getStyle().setProperty("font", "300 13px Ubuntu");
		timeLabel.getElement().getStyle().setProperty("color", "#3E4158");
		timeLabel.getElement().getStyle().setProperty("textAlign", "center");
		add(timeLabel);

	}

}
