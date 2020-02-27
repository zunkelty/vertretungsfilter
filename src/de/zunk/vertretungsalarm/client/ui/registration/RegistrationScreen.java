package de.zunk.vertretungsalarm.client.ui.registration;

import com.google.gwt.user.client.Window;

import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;

public class RegistrationScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	RegistrationView regView;
	BottomBar bottom;

	public RegistrationScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("Wilkommen beim<br><p style=\"font-family: Ubuntu:700\"><b>Vertretungsfilter</b>", false);
		regView = new RegistrationView();
		bottom = new BottomBar();

		add(header);
		add(regView);
		add(bottom);

		header.getElement().getStyle().setProperty("alignSelf", "stretch");
		regView.getElement().getStyle().setProperty("alignSelf", "stretch");
		bottom.getElement().getStyle().setProperty("alignSelf", "stretch");

		resizeComponents();

	}

	private void resizeComponents() {
		regView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}

}