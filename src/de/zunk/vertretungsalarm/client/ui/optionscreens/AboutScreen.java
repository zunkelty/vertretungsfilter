package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;

public class AboutScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	BottomBar bottom;

	AbsolutePanel settingsView;
	VertretungsalarmBox aboutBox;
	VertretungsalarmBox specsBox;
	VertretungsalarmButton show;
	VertretungsalarmBox techStackBox;

	VertretungsalarmBox igBox;
	VertretungsalarmButton igButton;

	public AboutScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("<b>Über den Vertretungsfilter<b>", true);

		aboutBox = new VertretungsalarmBox(
				"<b>Wieso solltest du jeden Tag auf den Vertretungsplan schauen, wenn es den Vertretungsfilter gibt?</b><br><br>Jede Minute checkt der Vertretungsfilter den Vertretungsplan und sucht alles Wichtige raus. Am Ende siehst du nur noch was dich wirklich interessiert, nämlich alle Meldungen zu deiner Schulklasse.<br><br><b>Entwickelt von Sönke Peters");
		aboutBox.getElement().getStyle().setProperty("padding", "0px 15px");

		igBox = new VertretungsalarmBox("Social Media:");
		igBox.getElement().getStyle().setProperty("padding", "0px 15px");

		igButton = new VertretungsalarmButton("Sönke auf Instagram");
		igButton.getElement().getStyle().setProperty("justifySelf", "stretch");
		igButton.addClickHandler(e -> Window.open("https://www.instagram.com/zunk.p/", "Sönke auf Instagram", ""));

		igBox.add(igButton);

		specsBox = new VertretungsalarmBox("Wie viele Zeilen JAVA, HTML & CSS umfasst der Vertretungsfilter?");
		specsBox.getElement().getStyle().setProperty("padding", "0px 15px");

		show = new VertretungsalarmButton("Lösung zeigen");
		show.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				specsBox.setHTML("Wie viele Zeilen Programmcode umfasst der Vertretungsfilter?<br><br><b>>2400 Zeilen");
				specsBox.remove(show);
			}
		});
		specsBox.add(show);

		techStackBox = new VertretungsalarmBox(
				"<b>Was steckt im Vertretungsfilter?</b><br><br><br<br>GWT (Google Web Toolkit), <br>HTML Unit,<br> JAVA,<br> HTML,<br> CSS");
		techStackBox.getElement().getStyle().setProperty("padding", "0px 15px");

		settingsView = new AbsolutePanel();
		settingsView.getElement().getStyle().setProperty("marginBottom", "20px");
		settingsView.getElement().getStyle().setProperty("flexShrink", "0");
		settingsView.getElement().getStyle().setProperty("padding", " 30px 25px 12px 25px");
		settingsView.getElement().getStyle().setProperty("paddingRight", "25px");
		settingsView.add(aboutBox);
		settingsView.add(igBox);
		settingsView.add(specsBox);
		settingsView.add(techStackBox);

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