package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.datepicker.client.CalendarUtil;

import de.zunk.vertretungsalarm.shared.VertretungsDate;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class VertretungsplanViewExperimental extends AbsolutePanel {

	DayView today;
	DayView tomorrow;
	DayView afterTomorrow;
	DayView afterTomorrowDuplicate;

	Date dateToday;
	Date dateTomorrow;
	Date dateAfterTomorrow;

	DateTimeFormat dtf;

	ArrayList<VertretungsEvent> eventsForToday;
	ArrayList<VertretungsEvent> eventsForTomorrow;
	ArrayList<VertretungsEvent> eventsForAfterTomorrow;

	public VertretungsplanViewExperimental() {

		dateToday = new Date();
		dtf = DateTimeFormat.getFormat("dd.MM.yyyy");

		dateTomorrow = new Date();
		CalendarUtil.addDaysToDate(dateTomorrow, 1);

		dateAfterTomorrow = new Date();
		CalendarUtil.addDaysToDate(dateAfterTomorrow, 2);

		today = new DayView(dateToday);
		tomorrow = new DayView(dateTomorrow);
		afterTomorrow = new DayView(dateAfterTomorrow);

		eventsForToday = new ArrayList<VertretungsEvent>();
		eventsForTomorrow = new ArrayList<VertretungsEvent>();
		eventsForAfterTomorrow = new ArrayList<VertretungsEvent>();

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "auto");

		add(today);
		add(tomorrow);
		add(afterTomorrow);

	}

	protected void presentEvents(ArrayList<VertretungsEvent> userEvents) {

		ArrayList<VertretungsDate> eventDates = new ArrayList<VertretungsDate>();
		for (VertretungsEvent vE : userEvents) {

			eventDates.add(vE.getDate());

		}

		List<VertretungsDate> deduped = eventDates.stream().distinct().collect(Collectors.toList());

		for (VertretungsDate vD : deduped) {
			Window.alert(vD.toString());
		}

	}

}
