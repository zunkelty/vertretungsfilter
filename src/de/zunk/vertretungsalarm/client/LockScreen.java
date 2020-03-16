package de.zunk.vertretungsalarm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;

public class LockScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	Header header;
	BottomBar bottom;

	LockView lockView;

	public LockScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("<b>Willkommen zum Vertretungsfilter<b>", false);
		lockView = new LockView();
		bottom = new BottomBar();

		add(header);
		add(lockView);
		add(bottom);

		resizeComponents();

	}

	public void resizeComponents() {
		lockView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}
}