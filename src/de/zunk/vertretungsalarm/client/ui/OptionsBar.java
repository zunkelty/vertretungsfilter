package de.zunk.vertretungsalarm.client.ui;

import java.io.Serializable;

import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;

public class OptionsBar extends AbsolutePanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	HTML about;
	HTML impressum;
	HTML datenschutz;
	HTML settings;

	public OptionsBar() {

		about = new HTML("<a><font color=\"#333333\">Über</a>");
		impressum = new HTML("<a><font color=\"#333333\">Impressum</a>");
		datenschutz = new HTML("<a><font color=\"#333333\">Datenschutz</a>");
		settings = new HTML("<a><font color=\"#333333\">Einstellungen</a>");

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "space-evenly");

		about.getElement().getStyle().setProperty("fontFamily", "Roboto");
		about.getElement().getStyle().setProperty("textAlign", "center");
		about.getElement().getStyle().setProperty("fontSize", "2vh");
		about.getElement().getStyle().setProperty("padding", "6px");
		about.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "about").buildString()));
		add(about);

		impressum.getElement().getStyle().setProperty("fontFamily", "Roboto");
		impressum.getElement().getStyle().setProperty("textAlign", "center");
		impressum.getElement().getStyle().setProperty("fontSize", "2vh");
		impressum.getElement().getStyle().setProperty("padding", "6px");
		impressum.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "legal").buildString()));
		add(impressum);

		datenschutz.getElement().getStyle().setProperty("fontFamily", "Roboto");
		datenschutz.getElement().getStyle().setProperty("textAlign", "center");
		datenschutz.getElement().getStyle().setProperty("fontSize", "2vh");
		datenschutz.getElement().getStyle().setProperty("padding", "6px");
		datenschutz.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "privacy").buildString()));
		add(datenschutz);

		settings.getElement().getStyle().setProperty("fontFamily", "Roboto");
		settings.getElement().getStyle().setProperty("textAlign", "center");
		settings.getElement().getStyle().setProperty("fontSize", "2vh");
		settings.getElement().getStyle().setProperty("padding", "6px");
		settings.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "settings").buildString()));

	}

	public void addSettingsOption() {
		add(settings);
	}

	public void removeSettingsOption() {
		remove(settings);
	}
}
