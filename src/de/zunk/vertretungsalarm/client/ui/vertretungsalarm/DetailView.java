package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class DetailView extends AbsolutePanel {

	public DetailView(VertretungsEvent e) {
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("background", "#F0C267");

		getElement().getStyle().setProperty("padding", "8px 19px 19px 19px");

		getElement().getStyle().setProperty("borderRadius", "35px 35px 0px 0px");

		Image icon = new Image("icons/expand_more-24px.svg");
		icon.getElement().getStyle().setProperty("transform", "scale(1.3)");
		icon.getElement().getStyle().setProperty("paddingBottom", "5px");
		add(icon);

		// Title Box

		DetailViewBox titleBox = new DetailViewBox();

		Label title = new Label(
				EventTranslator.getEventTitle(e) + " am " + e.getDate().getDay() + "." + e.getDate().getMonth() + ".");
		title.getElement().getStyle().setProperty("font", "bold 17px Ubuntu");
		title.getElement().getStyle().setProperty("color", "#3E4158");

		titleBox.add(title);
		add(titleBox);

		// Lessons Box

		DetailViewBox lessonsBox = new DetailViewBox();

		Label lessons = new Label(e.getLessonsAsString() + " Stunde");
		lessons.getElement().getStyle().setProperty("font", "500 16px Ubuntu");
		lessons.getElement().getStyle().setProperty("color", "#3E4158");

		lessonsBox.add(lessons);
		add(lessonsBox);

		// SchoolClasses Box

		DetailViewBox schoolClassesBox = new DetailViewBox();

		Label schoolClasses = new Label(e.getSchoolClassesAsString());
		schoolClasses.getElement().getStyle().setProperty("font", "500 16px Ubuntu");
		schoolClasses.getElement().getStyle().setProperty("color", "#3E4158");

		schoolClassesBox.add(schoolClasses);
		add(schoolClassesBox);

		// Planned Box

		DetailViewBox plannedBox = new DetailViewBox();

		Label plannedTitle = new Label("Geplant:");
		plannedTitle.getElement().getStyle().setProperty("font", "200 16px Ubuntu");
		plannedTitle.getElement().getStyle().setProperty("color", "#3E4158");

		Label plannedMainText = new Label(EventTranslator.getPlanned(e));
		plannedMainText.getElement().getStyle().setProperty("font", "500 16px Ubuntu");
		plannedMainText.getElement().getStyle().setProperty("color", "#3E4158");

		plannedBox.add(plannedTitle);
		plannedBox.add(new HorizontalLine(1));
		plannedBox.add(plannedMainText);

		add(plannedBox);

		// Actual Box

		DetailViewBox actualBox = new DetailViewBox();

		Label actualTitle = new Label("Jetzt:");
		actualTitle.getElement().getStyle().setProperty("font", "200 16px Ubuntu");
		actualTitle.getElement().getStyle().setProperty("color", "#3E4158");

		Label actualMainText = new Label(EventTranslator.getActual(e));
		actualMainText.getElement().getStyle().setProperty("font", "500 16px Ubuntu");
		actualMainText.getElement().getStyle().setProperty("color", "#3E4158");

		actualBox.add(actualTitle);
		actualBox.add(new HorizontalLine(1));
		actualBox.add(actualMainText);
		actualBox.add(new HorizontalLine(0.2));

		if (e.getAdditionalText() != "" && e.getAdditionalText() != null) {
			Label actualAdditionalText = new Label("Info: " + e.getAdditionalText());
			actualAdditionalText.getElement().getStyle().setProperty("font", "500 16px Ubuntu");
			actualAdditionalText.getElement().getStyle().setProperty("color", "#3E4158");
			actualBox.add(actualAdditionalText);
		}

		add(actualBox);

	}

}
