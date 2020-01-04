package de.zunk.vertretungsalarm.client.ui.registration;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.OptionsBar;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.TopBar;

public class RegistrationScreen extends Screen {

	private static final long serialVersionUID = 1L;

	// Deklarieren der Elemente

	// Auswahlen
	private Button student;
	private Button teacher;

	// Info & Optionen
	private AbsolutePanel options;
	private AbsolutePanel chooseSchoolClass;
	private schoolYearSelector chooseSchoolYear;
	private schoolClassLetterSelector chooseSchoolClassLetter;
	private TopBar topBar;

	private ArrayList<Button> schoolYearButtons;

	private String[] possibleClasses = { "5A", "5B", "5C", "5F", "6A", "6B", "6C", "6F", "7A", "7B", "7C", "7F", "8A",
			"8B", "8C", "8F", "9A", "9B", "9C", "9F", "10A", "10B", "10C", "10D", "10S", "11A", "11B", "11C", "11D" };

	public RegistrationScreen() {

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

		schoolYearButtons = new ArrayList<>();

		// *********************//
		// ****** Schritt1 *****//
		// *********************//

		// Student Button

		student = new Button("<p display:inline\">Schüler</p>");
		student.getElement().getStyle().setProperty("flex", "0.5 1 auto");

		student.getElement().getStyle().setProperty("background", "#ECE9FC");
		student.getElement().getStyle().setBorderWidth(1, Unit.PX);
		student.getElement().getStyle().setBorderColor("#000000");
		student.getElement().getStyle().setProperty("fontSize", "10vh");
		student.getElement().getStyle().setProperty("fontFamily", "Roboto");
		student.addClickHandler(
				event -> Location.replace(Location.createUrlBuilder().setParameter("step", "2").buildString()));

		// Teacher Button

		teacher = new Button("<p display:inline\">Lehrer</p>");
		teacher.getElement().getStyle().setProperty("flex", "0.5 1 auto");

		teacher.getElement().getStyle().setProperty("background", "#AFE09C");
		teacher.getElement().getStyle().setBorderWidth(1, Unit.PX);
		teacher.getElement().getStyle().setBorderColor("#000000");
		teacher.getElement().getStyle().setProperty("fontSize", "10vh");
		teacher.getElement().getStyle().setProperty("fontFamily", "Roboto");
		teacher.addClickHandler(event -> Window.alert(
				"Zurzeit funktioniert der " + Vertretungsalarm.getW***REMOVED***iteName() + " leider \n\nNUR FÜR SCHÜLER\n\nDer "
						+ Vertretungsalarm.getW***REMOVED***iteName() + " für Lehrer ist aber bereits in Planung."));

		// *********************//
		// ****** Schritt2 *****//
		// *********************//

		chooseSchoolYear = new schoolYearSelector();

		chooseSchoolClassLetter = new schoolClassLetterSelector();

		chooseSchoolClass = new AbsolutePanel();
		chooseSchoolClass.getElement().getStyle().setProperty("background", "#00FF00");
		chooseSchoolClass.getElement().getStyle().setBorderWidth(3, Unit.PX);
		chooseSchoolClass.getElement().getStyle().setBorderColor("#000000");
		chooseSchoolClass.getElement().getStyle().setProperty("display", "flex");
		chooseSchoolClass.getElement().getStyle().setProperty("flexDirection", "row");
		chooseSchoolClass.getElement().getStyle().setProperty("alignItems", "stretch");
		chooseSchoolClass.getElement().getStyle().setProperty("justifyContent", "center");
		chooseSchoolClass.getElement().getStyle().setProperty("flex", "1 1 auto");
		chooseSchoolClass.add(chooseSchoolYear);
		chooseSchoolClass.add(chooseSchoolClassLetter);

		// Hinzufügen der Elemente gemäß des aktuellen Schrittss

		if (Location.getParameter("step") == null) {
			topBar.setInfoText("Ich bin ...");
			add(topBar);
			add(student);
			add(teacher);
			add(options);
		} else if (Integer.parseInt(Location.getParameter("step")) <= 3
				&& Integer.parseInt(Location.getParameter("step")) > 0) {
			switch (Location.getParameter("step")) {

			case "1":
				topBar.setInfoText("Ich bin ...");
				add(topBar);
				add(student);
				add(teacher);
				add(options);
				break;
			case "2":
				topBar.setInfoText("Meine Klasse ist die ...");
				add(topBar);
				add(chooseSchoolClass);
				add(options);
				topBar.addPageBackOption();
				topBar.addPageForwardOption(event -> finishRegistration(
						chooseSchoolYear.getChoice() + chooseSchoolClassLetter.getChoice()));
				break;
			}
		} else {
			Location.replace(Location.createUrlBuilder().setParameter("step", "1").buildString());
		}

	}

	public void finishRegistration(String schoolClass) {
		if (Arrays.asList(possibleClasses).contains(schoolClass)) {
			try {
				Vertretungsalarm.getClientStorage().removeItem("schoolClass");
			} catch (Exception e) {

			}
			Vertretungsalarm.getClientStorage().setItem("schoolClass", schoolClass);
			Window.alert(
					"Wahnsinn! So schnell? Das wars auch schon.\n\nWir zeigen dir hier ab sofort das Wichtigste für die Klasse "
							+ Vertretungsalarm.getClientStorage().getItem("schoolClass"));
			Location.replace(Location.createUrlBuilder().removeParameter("step").buildString());
		} else {
			Window.alert("Huch? Die Klasse " + schoolClass
					+ " existiert gar nicht. Hast du dich vertippt?\n\nWenn du sicher bist, dass es die " + schoolClass
					+ " doch gibt, dann klicke bitte unten auf Hilfe und melde da den Fehler.");
		}
	}

}