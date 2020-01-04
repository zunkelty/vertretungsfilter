package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.user.client.ui.Label;

public class Event extends Label {

	public Event(String text) {

		getElement().getStyle().setProperty("paddingBottom", "10px");

		getElement().getStyle().setProperty("borderBottom", "1px dashed #000000");

		getElement().getStyle().setProperty("fontSize", "2vh");
		getElement().getStyle().setProperty("textAlign", "center");
		getElement().getStyle().setProperty("paddingTop", "10px");

		setText(text);

	}

	protected void present(String text) {
		setText(text);
	}

}
