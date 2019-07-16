package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextBox;

public class RegistrationScreenOld extends Screen {

	private static final long serialVersionUID = 1L;

	private AbsolutePanel selectTeachStud = new AbsolutePanel();
	private AbsolutePanel selectSchoolClass = new AbsolutePanel();
	private AbsolutePanel menu = new AbsolutePanel();

	private Button student;
	private Button teacher;
	private Button back;
	private Button forward;
	private Button info;

	private SimplePanel logo;

	private HTML explanationLabel;

	private String teachOrStud = "ZUNK";
	private String userSchoolClass = "ZUNK";

	private TextBox schoolClassTextBox;

	@SuppressWarnings("deprecation")
	public RegistrationScreenOld() {
		setPixelSize(width, height);

		logo = new SimplePanel();
		logo.setPixelSize(45, 45);
		logo.getElement().getStyle().setProperty("background", "url(logo-round.png) no-repeat center transparent");
		logo.getElement().getStyle().setProperty("backgroundSize", "cover");

		// Elemente für das Menü erstellen

		menu.setPixelSize(width, height);
		menu.getElement().getStyle().setProperty("background", "#FFcc00");

		back = new Button();
		back.setPixelSize(45, 45);
		back.getElement().getStyle().setProperty("background", "url(icons/back.png) no-repeat center transparent");
		back.getElement().getStyle().setProperty("backgroundSize", "cover");
		back.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showView("selectStudTeach");
			}
		});

		// Elemente für Schritt 1 erstellen

		student = new Button("<p style=\"font-size: 30px; display: inline\">Ich bin<br><b><p style=\"font-size:"
				+ width / 5 + "px; display:inline\">Schüler</p></b></p>");
		student.getElement().getStyle().setProperty("background", "#ECE9FC");
		student.getElement().getStyle().setBorderWidth(2, Unit.PX);
		student.getElement().getStyle().setBorderColor("#000000");
		student.getElement().getStyle().setProperty("fontFamily", "Roboto");
		student.setPixelSize(width + 4, height / 2 + 2);
		student.addClickHandler(e -> completeStep(1, "student"));

		teacher = new Button("<p style=\"font-size:30px; display: inline\">Ich bin<br><b><p style=\"font-size:"
				+ width / 5 + "px; display:inline\">Lehrer</p></b></p>");
		teacher.getElement().getStyle().setProperty("background", "#AFE09C");
		teacher.getElement().getStyle().setBorderWidth(2, Unit.PX);
		teacher.getElement().getStyle().setBorderColor("#000000");
		teacher.getElement().getStyle().setProperty("fontFamily", "Roboto");
		teacher.setPixelSize(width + 4, height / 2 + 2);
		teacher.addClickHandler(e -> completeStep(1, "teacher"));

		selectTeachStud.add(student, -2, -2);
		selectTeachStud.add(teacher, -2, height / 2);
		selectTeachStud.add(back, 10, 10);

		selectTeachStud.setPixelSize(width, height);

		// Elemente für Schritt 2 erstellen

		explanationLabel = new HTML("<b>In welcher Klasse bist du?</b>");
		explanationLabel.setWidth(width + "px");
		explanationLabel.getElement().getStyle().setProperty("color", "#000000");
		explanationLabel.getElement().getStyle().setProperty("textAlign", "center");
		explanationLabel.getElement().getStyle().setProperty("fontSize", "25px");
		explanationLabel.getElement().getStyle().setProperty("fontFamily", "Roboto");

		schoolClassTextBox = new TextBox();
		schoolClassTextBox.setPixelSize(width - 175, 35);
		schoolClassTextBox.getElement().getStyle().setProperty("backgroundColor", "#ECE9FC");
		schoolClassTextBox.getElement().getStyle().setProperty("color", "#000000");
		schoolClassTextBox.getElement().getStyle().setProperty("border", "0");
		schoolClassTextBox.getElement().getStyle().setProperty("textTransform", "uppercase");
		schoolClassTextBox.getElement().getStyle().setProperty("textAlign", "center");
		schoolClassTextBox.getElement().getStyle().setProperty("lineHeight", "30px");
		schoolClassTextBox.getElement().getStyle().setProperty("fontSize", "24px");
		schoolClassTextBox.getElement().getStyle().setProperty("fontFamily", "Roboto");
		schoolClassTextBox.getElement().getStyle().setProperty("padding", "4px");

		forward = new Button();
		forward.setPixelSize(45, 45);
		forward.getElement().getStyle().setProperty("background",
				"url(icons/forward.png) no-repeat center transparent");
		forward.getElement().getStyle().setProperty("backgroundSize", "cover");
		forward.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				showView("complete");
			}
		});

		selectSchoolClass.setPixelSize(width, height);
		selectSchoolClass.getElement().getStyle().setProperty("background", "#AFE09C");
		selectSchoolClass.add(explanationLabel, 0, 150);
		selectSchoolClass.add(schoolClassTextBox, ((width - (width - 200)) / 2) - 45 - 10, 200);
		selectSchoolClass.add(forward, ((width - (width - 200)) / 2) - 45 - 10 + (width - 200) + 50, 200);
		selectSchoolClass.add(back, 10, 10);

		// Generelle Vorbereitungen
		add(selectTeachStud, 0, 0);
		add(selectSchoolClass, 0, 0);
		add(menu, 0, 0);
		add(logo, (width - 45) / 2, 10);
		showView("selectStudTeach");

	}

	private void showView(String view) {
		if (view == "selectStudTeach") {
			selectTeachStud.setVisible(true);
			selectSchoolClass.setVisible(false);
			menu.setVisible(false);
		}
		if (view == "selectSchoolClass") {
			selectTeachStud.setVisible(false);
			selectSchoolClass.setVisible(true);
			menu.setVisible(false);
		}
		if (view == "menu") {
			selectTeachStud.setVisible(false);
			selectSchoolClass.setVisible(false);
			menu.setVisible(true);
		}
		if (view == "complete") {
			Window.confirm("Super, das wars schon!\n\nDu siehst ab sofort hier die Meldungen für die " + userSchoolClass
					+ "\n\nWenn du deine Einstellungen ändern willst klicke im Menü auf das Logo oben rechts.");
		}

	}

	private String getView() {
		if (selectTeachStud.isVisible()) {
			return "selectTeachStud";
		} else if (selectSchoolClass.isVisible()) {
			return "selectSchoolClass";
		} else if (menu.isVisible()) {
			return "menu";
		}

		return "selectTeachStud";

	}

	private void completeStep(int stepCompleted, String info) {
		if (stepCompleted == 1) {
			if (info == "teacher") {
				Window.alert(
						"Zurzeit funktioniert der Vertretungsfilter leider \n\nNUR FÜR SCHÜLER\n\nDiese Funktion ist aber bereits in Planung.");
			} else {
				teachOrStud = info;
				showView("selectSchoolClass");
			}

		}
		if (stepCompleted == 2)

		{

			{

			}

		}
	}
}