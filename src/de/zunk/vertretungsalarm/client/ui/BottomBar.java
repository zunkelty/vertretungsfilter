package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;

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
		getElement().getStyle().setProperty("padding", "10px 0px");
		getElement().getStyle().setProperty("minHeight", "130px");
		getElement().getStyle().setProperty("flexShrink", "0");
		getElement().getStyle().setProperty("background", "#F0C267");

		legal = new Label("Rechtliches");
		legal.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		legal.getElement().getStyle().setProperty("color", "#3E4158");
		legal.getElement().getStyle().setProperty("padding", "4px");

		about = new Label("Über");
		about.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		about.getElement().getStyle().setProperty("color", "#3E4158");
		about.getElement().getStyle().setProperty("padding", "4px");

		settings = new Label("Einstellungen");
		settings.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		settings.getElement().getStyle().setProperty("color", "#3E4158");
		settings.getElement().getStyle().setProperty("padding", "4px");

		contact = new Label("Kontakt");
		contact.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		contact.getElement().getStyle().setProperty("color", "#3E4158");
		contact.getElement().getStyle().setProperty("padding", "4px");

		legal.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Vertretungsalarm.getClientStorage().removeItem("schoolClass");
				Location.reload();
			}
		});

		add(about);
		add(settings);
		add(contact);
		add(legal);

	}

}
