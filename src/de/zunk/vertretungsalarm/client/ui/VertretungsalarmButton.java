package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.user.client.ui.Button;

public class VertretungsalarmButton extends Button {

	public VertretungsalarmButton(String text) {
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("border", "0");
		getElement().getStyle().setProperty("padding", "9px 10px");
		getElement().getStyle().setProperty("flexGrow", "0");

		getElement().getStyle().setProperty("borderRadius", "15px");

		getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203,203,203,0.4)");

		setText(text);

		getElement().getStyle().setProperty("font", "500 17px Ubuntu");
		getElement().getStyle().setProperty("background", "#F0C267");
	}

	public void disable() {
		setEnabled(false);
		getElement().getStyle().setProperty("background", "#cccccc");
	}

	public void enable() {
		setEnabled(true);
		getElement().getStyle().setProperty("background", "#F0C267");
	}

}
