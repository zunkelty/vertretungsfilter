package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.shared.DayInfo;
import de.zunk.vertretungsalarm.shared.VertretungsDate;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class DayView extends AbsolutePanel {

	String dateAsString;
	private Label dateLabel;
	private VertretungsalarmBox dayInfoBox;

	public DayView(ArrayList<VertretungsEvent> dayEvents, DayInfo dayInfo) {

		getElement().getStyle().setProperty("display", "inline-block");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("paddingLeft", "25px");
		getElement().getStyle().setProperty("paddingRight", "25px");

		VertretungsDate dayDate = dayInfo != null ? dayInfo.getDate() : dayEvents.get(0).getDate();

		dateLabel = new Label(dayDate.toString());
		dateLabel.getElement().getStyle().setProperty("font", "15px Ubuntu");
		dateLabel.getElement().getStyle().setProperty("fontWeight", "300");
		dateLabel.getElement().getStyle().setProperty("color", "#3E4158");
		dateLabel.getElement().getStyle().setProperty("textAlign", "start");
		dateLabel.getElement().getStyle().setProperty("paddingTop", "30px");
		dateLabel.getElement().getStyle().setProperty("paddingBottom", "12px");

		Date now = new Date();
		Date date;
		DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("dd.MM.yyyy");
		date = dateTimeFormat.parse(dayDate.toString());

		switch (CalendarUtil.getDaysBetween(now, date)) {
		case -1: {
			dateLabel.setText("Gestern, " + dayDate.getDay() + "." + dayDate.getMonth() + ".");
		}
			break;
		case 0: {
			dateLabel.setText("Heute, " + dayDate.getDay() + "." + dayDate.getMonth() + ".");
		}

			break;
		case 1: {
			dateLabel.setText("Morgen, " + dayDate.getDay() + "." + dayDate.getMonth() + ".");
		}

			break;
		case 2: {
			dateLabel.setText("??bermorgen, " + dayDate.getDay() + "." + dayDate.getMonth() + ".");
		}

			break;

		default:
			;
		}

		add(dateLabel);

		if (dayInfo != null && dayInfo.getInfo().trim() != "") {
			dayInfoBox = new VertretungsalarmBox(dayInfo.getInfo());
			dayInfoBox.getWidget(0).getElement().getStyle().setProperty("font", "300 15px Ubuntu");
			dayInfoBox.getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203,203,203,0.5)");
			dayInfoBox.getElement().getStyle().setProperty("opacity", "0.8");
			dayInfoBox.getElement().getStyle().setProperty("filter", "brightness(87%)");
			dayInfoBox.getElement().getStyle().setProperty("marginBottom", "20px");
			add(dayInfoBox);
		}

		for (VertretungsEvent vertretungsEvent : dayEvents) {
			EventBox e = new EventBox(vertretungsEvent);
			add(e);
		}

		if (dayEvents.isEmpty()) {
			VertretungsalarmBox box = new VertretungsalarmBox("Keine Vertretungen f??r Dich");
			box.getWidget(0).getElement().getStyle().setProperty("fontWeight", "300");
			box.getElement().getStyle().setProperty("marginBottom", "20px");

			add(box);
		}

	}

}
