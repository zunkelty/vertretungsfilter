package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class DayView extends AbsolutePanel {

	@SuppressWarnings("static-access")
	public void presentEvents(ArrayList<VertretungsEvent> dayEvents) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("paddingLeft", "25px");

		Label dateLabel = new Label();

		dateLabel = new Label(dayEvents.get(0).getDateAsText());
		dateLabel.getElement().getStyle().setProperty("font", "15px Ubuntu");
		dateLabel.getElement().getStyle().setProperty("fontWeight", "300");
		dateLabel.getElement().getStyle().setProperty("color", "#3E4158");
		dateLabel.getElement().getStyle().setProperty("textAlign", "start");
		dateLabel.getElement().getStyle().setProperty("paddingTop", "30px");
		dateLabel.getElement().getStyle().setProperty("marginRight", "25px");

		Date now = new Date();
		Date date;
		CalendarUtil c = new CalendarUtil();
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
		date = dateTimeFormat.parse(dateLabel.getText());

		switch (c.getDaysBetween(now, date)) {
		case -1: {
			dateLabel.setText("Gestern, " + dayEvents.get(0).getDate().getDay() + "."
					+ dayEvents.get(0).getDate().getMonth() + ".");
		}
			break;
		case 0: {
			dateLabel.setText("Heute, " + dayEvents.get(0).getDate().getDay() + "."
					+ dayEvents.get(0).getDate().getMonth() + ".");
		}

			break;
		case 1: {
			dateLabel.setText("Morgen, " + dayEvents.get(0).getDate().getDay() + "."
					+ dayEvents.get(0).getDate().getMonth() + ".");
		}

			break;
		case 2: {
			dateLabel.setText("Übermorgen, " + dayEvents.get(0).getDate().getDay() + "."
					+ dayEvents.get(0).getDate().getMonth() + ".");
		}

			break;

		default:
			break;
		}

		add(dateLabel);

	}

}
