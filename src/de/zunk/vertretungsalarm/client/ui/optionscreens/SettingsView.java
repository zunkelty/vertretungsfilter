package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;
import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;

public class SettingsView extends AbsolutePanel {

	VertretungsalarmBox changeClassBox;
	VertretungsalarmBox resetAllBox;

	VertretungsalarmButton changeClass;
	VertretungsalarmButton resetAll;

	VertretungsalarmBox exceptionSettingsBox;
	VertretungsalarmBox exceptionSubjectView;
	VertretungsalarmBox exceptionTeacherView;

	VertretungsalarmButton editSubjectExceptions;
	VertretungsalarmButton editTeacherExceptions;

	public SettingsView() {

		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("flexShrink", "0");
		getElement().getStyle().setProperty("padding", " 30px 25px 12px 25px");
		getElement().getStyle().setProperty("paddingRight", "25px");

		editSubjectExceptions = new VertretungsalarmButton("Bearbeiten");
		editTeacherExceptions = new VertretungsalarmButton("Bearbeiten");

		exceptionSubjectView = new VertretungsalarmBox(
				Vertretungsalarm.getClientStorage().getItem("subjectExceptions") != null
						? ("Fächer: " + Vertretungsalarm.getClientStorage().getItem("subjectExceptions"))
						: ("Fächer:"));
		exceptionSubjectView.getElement().getStyle().setProperty("padding", "0px 15px");
		exceptionSubjectView.add(editSubjectExceptions);

		exceptionTeacherView = new VertretungsalarmBox(
				Vertretungsalarm.getClientStorage().getItem("teacherExceptions") != null
						? ("Lehrer: " + Vertretungsalarm.getClientStorage().getItem("teacherExceptions"))
						: ("Lehrer:"));
		exceptionTeacherView.getElement().getStyle().setProperty("padding", "0px 15px");
		exceptionTeacherView.add(editTeacherExceptions);

		exceptionSettingsBox = new VertretungsalarmBox(
				"<b>Füge Lehrer und Fächer hinzu, die du nicht hast, dann zeigt dir der Vertretungsfilter diese nicht mehr an.");
		exceptionSettingsBox.getElement().getStyle().setProperty("padding", "0px 15px");
		exceptionSettingsBox.add(exceptionSubjectView);
		exceptionSettingsBox.add(exceptionTeacherView);

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

		// add(exceptionSettingsBox);
		add(changeClassBox);
		add(resetAllBox);

	}

}
