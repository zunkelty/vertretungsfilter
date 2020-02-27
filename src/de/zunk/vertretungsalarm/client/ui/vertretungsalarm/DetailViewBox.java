package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.user.client.ui.AbsolutePanel;

public class DetailViewBox extends AbsolutePanel {

	public DetailViewBox() {

		getElement().getStyle().setProperty("padding", "11px 17px 9px 17px");
		getElement().getStyle().setProperty("background", "#FDFCFE");
		getElement().getStyle().setProperty("borderRadius", "25px");
		getElement().getStyle().setProperty("boxShadow", "0px 4px 5px 0px rgba(203,203,203,0.4)");
		getElement().getStyle().setProperty("marginBottom", "7px");

	}

}
