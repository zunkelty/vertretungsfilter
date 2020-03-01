package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

public class BottomBar extends AbsolutePanel {

	Label legal;
	Label about;
	Label settings;
	Label contact;

	Label credits;

	public BottomBar() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "space-between");
		getElement().getStyle().setProperty("padding", "10px 0px");
		getElement().getStyle().setProperty("minHeight", "130px");
		getElement().getStyle().setProperty("flexShrink", "0");
		getElement().getStyle().setProperty("background", "#F0C267");

		credits = new Label("Vertretungsfilter von Sönke Peters".toUpperCase());
		credits.getElement().getStyle().setProperty("font", "500 15px Ubuntu");
		credits.getElement().getStyle().setProperty("color", "#F0C267");
		credits.getElement().getStyle().setProperty("background", "#FDFCFE");
		credits.getElement().getStyle().setProperty("borderRadius", "10px");
		credits.getElement().getStyle().setProperty("padding", "6px");
		credits.getElement().getStyle().setProperty("marginBottom", "10px");

		legal = new Label("Rechtliches");
		legal.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		legal.getElement().getStyle().setProperty("color", "#3E4158");
		legal.getElement().getStyle().setProperty("padding", "5px");
		legal.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "legal").buildString()));

		about = new Label("Über");
		about.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		about.getElement().getStyle().setProperty("color", "#3E4158");
		about.getElement().getStyle().setProperty("padding", "5px");
		about.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "about").buildString()));

		settings = new Label("Einstellungen");
		settings.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		settings.getElement().getStyle().setProperty("color", "#3E4158");
		settings.getElement().getStyle().setProperty("padding", "5px");
		settings.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "settings").buildString()));

		contact = new Label("Kontakt");
		contact.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		contact.getElement().getStyle().setProperty("color", "#3E4158");
		contact.getElement().getStyle().setProperty("padding", "5px");
		contact.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "contact").buildString()));

		add(credits);
		add(about);
		add(settings);
		add(contact);
		add(legal);

	}

}
