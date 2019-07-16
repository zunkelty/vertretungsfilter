package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.client.ui.OptionsBar;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.TopBar;

public class ImpressumScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private TopBar topBar;
	private OptionsBar options;
	private AbsolutePanel info;

	public ImpressumScreen() {

		// Generelles für den Container

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#ECE9FC");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "center");

		// Generelle Elemente für alle Schritte

		// Info Button

		topBar = new TopBar();
		topBar.getElement().getStyle().setProperty("flex", "0.025 0 auto");

		// Options Button

		options = new OptionsBar();
		options.getElement().getStyle().setProperty("flex", "0.025 0 auto");

		info = new AbsolutePanel();
		info.getElement().getStyle().setProperty("background", "#AFE09C");
		info.getElement().getStyle().setBorderWidth(3, Unit.PX);
		info.getElement().getStyle().setBorderColor("#000000");
		info.getElement().getStyle().setProperty("display", "flex");
		info.getElement().getStyle().setProperty("flexDirection", "column");
		info.getElement().getStyle().setProperty("background", "#AFE09C");
		info.getElement().getStyle().setProperty("alignItems", "stretch");
		info.getElement().getStyle().setProperty("justifyContent", "center");
		info.getElement().getStyle().setProperty("flex", "1 1 auto");

		topBar.setInfoText("Impressum");
		add(topBar);
		add(info);
		add(options);
		topBar.addPageBackOption();
	}
}