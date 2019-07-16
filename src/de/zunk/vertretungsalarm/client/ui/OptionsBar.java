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

	public OptionsBar() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "space-evenly");

		HTML about = new HTML("<a><font color=\"#333333\">Über</a>");
		about.getElement().getStyle().setProperty("fontFamily", "Roboto");
		about.getElement().getStyle().setProperty("fontSize", "13px");
		about.getElement().getStyle().setProperty("textAlign", "center");
		about.getElement().getStyle().setProperty("fontSize", "2vh");
		about.getElement().getStyle().setProperty("padding", "3px");
		about.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "about").buildString()));
		add(about);

		HTML impressum = new HTML("<a><font color=\"#333333\">Impressum</a>");
		impressum.getElement().getStyle().setProperty("fontFamily", "Roboto");
		impressum.getElement().getStyle().setProperty("fontSize", "13px");
		impressum.getElement().getStyle().setProperty("textAlign", "center");
		impressum.getElement().getStyle().setProperty("fontSize", "2vh");
		impressum.getElement().getStyle().setProperty("padding", "3px");
		impressum.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "legal").buildString()));
		add(impressum);

		HTML datenschutz = new HTML("<a><font color=\"#333333\">Datenschutz</a>");
		datenschutz.getElement().getStyle().setProperty("fontFamily", "Roboto");
		datenschutz.getElement().getStyle().setProperty("fontSize", "13px");
		datenschutz.getElement().getStyle().setProperty("textAlign", "center");
		datenschutz.getElement().getStyle().setProperty("fontSize", "2vh");
		datenschutz.getElement().getStyle().setProperty("padding", "3px");
		datenschutz.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("page", "privacy").buildString()));
		add(datenschutz);

	}
}
