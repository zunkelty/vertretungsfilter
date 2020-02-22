package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.LinkedHashSet;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class VertretungsplanView extends AbsolutePanel {

	DateTimeFormat dtf;

	DayView dV;

	Button noEventsImage;
	Label noEventsLabel;

	public VertretungsplanView() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");

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
				dV = new DayView();
				for (VertretungsEvent vE : userEvents) {
					if (vE.getDateAsText() == date) {
						dayEvents.add(vE);
					}
				}
				dV.presentEvents(dayEvents);
				add(dV);
				dayEvents.clear();
			}

		} else {

			Label info;
			Label reload;

			info = new Label("Zurzeit steht für dich nichts auf dem Vertretungsplan");
			info.getElement().getStyle().setProperty("font", "18px Ubuntu");
			info.getElement().getStyle().setProperty("color", "#3E4158");
			info.getElement().getStyle().setProperty("textAlign", "center");
			info.getElement().getStyle().setProperty("padding", "20px");

			reload = new Label("Neu laden");
			reload.getElement().getStyle().setProperty("font", "10px Ubuntu:300");
			reload.getElement().getStyle().setProperty("fontStyle", "italic");
			reload.getElement().getStyle().setProperty("color", "#3E4158");
			reload.getElement().getStyle().setProperty("textAlign", "center");
			reload.getElement().getStyle().setProperty("padding", "10px");

			reload.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					Location.reload();
				}
			});

			add(info);
			add(reload);

		}

	}

}
