package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.user.client.ui.AbsolutePanel;

public class HorizontalLine extends AbsolutePanel {

	public HorizontalLine(double thickness) {
		getElement().getStyle().setProperty("display", "block");
		getElement().getStyle().setProperty("height", +thickness + "px");
		getElement().getStyle().setProperty("border", "0");
		getElement().getStyle().setProperty("borderTop", thickness + "px solid #404359");
		getElement().getStyle().setProperty("margin", "5px auto");
		getElement().getStyle().setProperty("padding", "0");
	}

}
