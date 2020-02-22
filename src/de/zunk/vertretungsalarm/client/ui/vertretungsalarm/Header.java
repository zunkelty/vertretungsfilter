package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;

public class Header extends AbsolutePanel {

	public Header() {
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");

		getElement().getStyle().setProperty("animationName", "fadeIn");
		getElement().getStyle().setProperty("animationDuration", "0.3s");

		getElement().getStyle().setProperty("background", "#F0C267");
		getElement().getStyle().setProperty("borderBottomLeftRadius", "49px");
		getElement().getStyle().setProperty("borderBottomRightRadius", "49px");
		getElement().getStyle().setProperty("minHeight", "115px");
		getElement().getStyle().setProperty("maxHeight", "157px");

		HTML title = new HTML("Dein<br><p style=\"font-family: Ubuntu:700\"><b>Vertretungsplan</b>");
		title.getElement().getStyle().setProperty("font", "33px Ubuntu");
		title.getElement().getStyle().setProperty("color", "#FFFFFF");
		title.getElement().getStyle().setProperty("paddingLeft", "25px");
		title.getElement().getStyle().setProperty("paddingTop", "15px");
		add(title);

	}

}
