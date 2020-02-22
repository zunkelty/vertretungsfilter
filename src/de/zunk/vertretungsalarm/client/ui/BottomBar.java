package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

public class BottomBar extends AbsolutePanel {

	Label legal;
	Label about;
	Label settings;
	Label contact;

	public BottomBar() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "space-between");
		getElement().getStyle().setProperty("padding", "20px 15px");
		getElement().getStyle().setProperty("minHeight", "150px");
		getElement().getStyle().setProperty("background", "#F0C267");

		legal = new Label("Rechtliches");
		legal.getElement().getStyle().setProperty("font", "25px Ubuntu:300");
		legal.getElement().getStyle().setProperty("color", "#3E4158");
		legal.getElement().getStyle().setProperty("padding", "5px");

		about = new Label("Über");
		about.getElement().getStyle().setProperty("font", "25px Ubuntu:300");
		about.getElement().getStyle().setProperty("color", "#3E4158");
		about.getElement().getStyle().setProperty("padding", "5px");

		settings = new Label("Einstellungen");
		settings.getElement().getStyle().setProperty("font", "25px Ubuntu:300");
		settings.getElement().getStyle().setProperty("color", "#3E4158");
		settings.getElement().getStyle().setProperty("padding", "5px");

		contact = new Label("Kontakt");
		contact.getElement().getStyle().setProperty("font", "25px Ubuntu:300");
		contact.getElement().getStyle().setProperty("color", "#3E4158");
		contact.getElement().getStyle().setProperty("padding", "5px");

		add(about);
		add(settings);
		add(contact);
		add(legal);

	}

}
