package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class InfoView extends AbsolutePanel {

	Label info;

	public InfoView(String date) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("overflow", "auto");

		getElement().getStyle().setProperty("padding", "10px");
		getElement().getStyle().setProperty("border", "2px solid #000");
		getElement().getStyle().setProperty("borderBottom", "0px");

		info = new Label("Die Informationen zum Tag können zurzeit nicht dargestellt werden");
		info.getElement().getStyle().setProperty("fontSize", "2vh");
		info.getElement().getStyle().setProperty("textAlign", "center");
		getElement().getStyle().setProperty("paddingTop", "10px");
		add(info);
	}

	public void presentDayInfo(ArrayList<VertretungsEvent> events) {
		boolean areInfosMatching = false;
		for (VertretungsEvent event : events) {

		}
	}

}
