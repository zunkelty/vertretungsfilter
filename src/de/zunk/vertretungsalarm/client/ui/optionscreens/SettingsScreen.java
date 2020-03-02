package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.client.GreetingServiceAsync;
import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;
import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;

public class SettingsScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	Header header;
	BottomBar bottom;

	AbsolutePanel settingsView;
	VertretungsalarmBox changeClassBox;
	VertretungsalarmBox resetAllBox;

	VertretungsalarmButton changeClass;
	VertretungsalarmButton resetAll;

	public SettingsScreen() {

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				RootPanel.get().remove(SettingsScreen.this.asWidget());
				RootPanel.get().add(new SettingsScreen());
			}
		});

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

		changeClassBox = new VertretungsalarmBox(
				"<b>Ändere die Schulklasse, für die dir der Vertretungsfilter den Vertretungsplan anzeigen soll.</b><br>");
		changeClassBox.getElement().getStyle().setProperty("padding", "0px 15px");
		changeClass = new VertretungsalarmButton("Klasse ändern");
		changeClass.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Vertretungsalarm.getClientStorage().removeItem("schoolClass");
				Location.replace(Location.createUrlBuilder().removeParameter("page").buildString());
			}
		});

		changeClassBox.add(changeClass);

		resetAllBox = new VertretungsalarmBox(
				"<b>Lösche deine Zugangsberechtigung und alle anderen Daten, die im Zusammenhang mit dem Vertretungsfilter auf deinem Gerät stehen.</b><br><br>Achtung: Dies bedeutet, dass du keinen Zugriff mehr auf den Vertretungsfilter haben wirst!");
		resetAllBox.getElement().getStyle().setProperty("padding", "0px 15px");
		resetAll = new VertretungsalarmButton("Zurücksetzen");
		resetAll.addClickHandler(e -> RootPanel.get()
				.add(new Message("Willst du wirklich fortfahren?",
						"Dies bedeutet, dass du keinen Zugriff mehr auf den Vertretungsfilter haben wirst!",
						ButtonLayoutOption.YES_NO, CloseAction.RESET_EVERYTHING)));
		resetAllBox.add(resetAll);

		settingsView = new AbsolutePanel();
		settingsView.getElement().getStyle().setProperty("marginBottom", "20px");
		settingsView.getElement().getStyle().setProperty("flexShrink", "0");
		settingsView.getElement().getStyle().setProperty("padding", " 30px 25px 12px 25px");
		settingsView.getElement().getStyle().setProperty("paddingRight", "25px");
		settingsView.add(changeClassBox);
		settingsView.add(resetAllBox);

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