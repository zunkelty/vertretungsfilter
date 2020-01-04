package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class EventView extends AbsolutePanel {

	ArrayList<VertretungsEvent> eventsToPresent;

	EventTranslator translator = new EventTranslator();

	public EventView(String date) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("overflow", "auto");

		getElement().getStyle().setProperty("padding", "10px");
		getElement().getStyle().setProperty("border", "4px solid #000");

	}

	protected void presentEvents(ArrayList<VertretungsEvent> events) {
		for (VertretungsEvent vertretungsEvent : events) {
			Event e = new Event(translator.translatedEvent(vertretungsEvent));
			add(e);
			// Window.alert(vertretungsEvent.toString());
		}
		if (getWidgetCount() == 0) {
			Label info = new Label();
			info = new Label("---");
			info.getElement().getStyle().setProperty("fontSize", "2vh");
			info.getElement().getStyle().setProperty("textAlign", "center");
			getElement().getStyle().setProperty("paddingTop", "10px");
			add(info);
		}

	}

}
