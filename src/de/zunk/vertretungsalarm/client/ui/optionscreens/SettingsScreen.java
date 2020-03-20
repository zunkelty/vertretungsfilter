package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.client.GreetingServiceAsync;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;

public class SettingsScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	Header header;
	BottomBar bottom;

	VertretungsalarmBox changeClassBox;
	VertretungsalarmBox resetAllBox;

	VertretungsalarmButton changeClass;
	VertretungsalarmButton resetAll;

	SettingsView settingsView;

	public SettingsScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("<b>Einstellungen<b>", true);

		settingsView = new SettingsView();

		bottom = new BottomBar();

		add(header);
		add(settingsView);
		add(bottom);

		resizeComponents();

	}

	public void resizeComponents() {
		settingsView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}
}