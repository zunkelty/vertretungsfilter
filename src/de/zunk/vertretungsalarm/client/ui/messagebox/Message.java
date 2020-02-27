package de.zunk.vertretungsalarm.client.ui.messagebox;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.client.ui.CloseAction;

public class Message extends AbsolutePanel {

	MessageBox box;

	public Message(String title, String content, boolean hasToBeAccepted, CloseAction closeAction) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "center");
		getElement().getStyle().setProperty("background", "rgba(0, 0, 0, .3)");

		setSize(Window.getClientWidth() + "px", Window.getClientHeight() + "px");

		getElement().getStyle().setProperty("alignSelf", "center");
		getElement().getStyle().setProperty("justifySelf", "center");
		getElement().getStyle().setProperty("animation", "fade-in 0.2s");

		box = new MessageBox(title, content, hasToBeAccepted, closeAction);
		add(box);

	}

}
