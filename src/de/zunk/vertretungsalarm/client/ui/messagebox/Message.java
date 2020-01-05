package de.zunk.vertretungsalarm.client.ui.messagebox;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class Message extends AbsolutePanel {

	MessageBox box;

	public Message(String title, String content, boolean hasToBeAccepted) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "center");
		getElement().getStyle().setProperty("background", "rgba(0, 0, 0, .6)");
		// getElement().getStyle().setProperty("opacity", "0.6");

		setSize(Window.getClientWidth() + "px", Window.getClientHeight() + "px");

		box = new MessageBox(title, content, hasToBeAccepted);
		add(box);

	}

}
