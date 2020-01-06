package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.OptionsBar;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.TopBar;

public class SettingsScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private TopBar topBar;
	private OptionsBar options;
	private AbsolutePanel info;

	private Label instructionLabel;

	Button selectSchoolClass;
	Button reset;

	public SettingsScreen() {

		// Generelles für den Container

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#ECE9FC");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "center");

		// TopBar

		topBar = new TopBar();

		// info

		info = new AbsolutePanel();
		info.getElement().getStyle().setProperty("background", "#AFE09C");
		info.getElement().getStyle().setBorderWidth(3, Unit.PX);
		info.getElement().getStyle().setBorderColor("#000000");
		info.getElement().getStyle().setProperty("display", "flex");
		info.getElement().getStyle().setProperty("flexDirection", "column");
		info.getElement().getStyle().setProperty("background", "#AFE09C");
		info.getElement().getStyle().setProperty("alignItems", "center");
		info.getElement().getStyle().setProperty("justifyContent", "flex-start");
		info.getElement().getStyle().setProperty("flex", "1 1 auto");
		info.getElement().getStyle().setProperty("padding", "20px");

		// Instructions

		instructionLabel = new Label("Was möchtest du tun?");
		instructionLabel.getElement().getStyle().setProperty("textAlign", "center");
		instructionLabel.getElement().getStyle().setProperty("fontSize", "2.5vh");
		instructionLabel.getElement().getStyle().setProperty("textAlign", "center");
		instructionLabel.getElement().getStyle().setProperty("margin", "20px 0px");
		info.add(instructionLabel);

		// selectSchoolClass Button
		selectSchoolClass = new Button("Meine Schulklasse ändern");
		selectSchoolClass.getElement().getStyle().setProperty("background", "#ECE9FC");
		selectSchoolClass.getElement().getStyle().setProperty("border", "1px solid rgba(0,0,0,0.3)");
		selectSchoolClass.getElement().getStyle().setProperty("fontSize", "2.5vh");
		selectSchoolClass.getElement().getStyle().setProperty("textAlign", "center");
		selectSchoolClass.getElement().getStyle().setProperty("marginBottom", "10px");
		selectSchoolClass.getElement().getStyle().setProperty("padding", "5px 20px");
		selectSchoolClass.getElement().getStyle().setProperty("borderRadius", "5px");

		selectSchoolClass.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Vertretungsalarm.getClientStorage().removeItem("schoolClass");
				UrlBuilder builder = Location.createUrlBuilder();
				builder.removeParameter("page");
				builder.setParameter("step", "2");
				Location.replace(builder.buildString());
			}
		});
		info.add(selectSchoolClass);

		// Reset Button
		reset = new Button("Den Vertretungsalarm zurücksetzen");
		reset.getElement().getStyle().setProperty("background", "#ECE9FC");
		reset.getElement().getStyle().setProperty("border", "1px solid rgba(0,0,0,0.3)");
		reset.getElement().getStyle().setProperty("fontSize", "2.5vh");
		reset.getElement().getStyle().setProperty("textAlign", "center");
		reset.getElement().getStyle().setProperty("marginBottom", "10px");
		reset.getElement().getStyle().setProperty("padding", "5px 20px");
		reset.getElement().getStyle().setProperty("borderRadius", "5px");

		reset.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Vertretungsalarm.getClientStorage().removeItem("schoolClass");
				Location.replace(Location.createUrlBuilder().removeParameter("page").buildString());
			}
		});
		info.add(reset);

		// Options Bar

		options = new OptionsBar();
		options.getElement().getStyle().setProperty("flex", "0.025 0 auto");

		topBar.setInfoText("Einstellungen");
		add(topBar);
		add(info);

		add(options);
		topBar.addPageBackOption();
	}
}