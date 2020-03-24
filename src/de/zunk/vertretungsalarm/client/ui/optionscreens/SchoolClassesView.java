package de.zunk.vertretungsalarm.client.ui.optionscreens;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;

public class SchoolClassesView extends AbsolutePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SchoolClassBox newSchoolClass;

	public SchoolClassesView() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");

		getElement().getStyle().setProperty("marginTop", "15px");
		getElement().getStyle().setProperty("padding", "8px 19px 19px 19px");

		VertretungsalarmBox note = new VertretungsalarmBox(
				"<b>Du kannst hier deine Klasse ändern oder weitere Klassen hinzufügen, nach denen der Vertretungsfilter filtern soll.");
		note.getElement().getStyle().setProperty("order", "-2");
		add(note);

		ArrayList<String> userSchoolClasses = new ArrayList<String>();
		String userSchoolClass = Vertretungsalarm.getClientStorage().getItem("schoolClass");

		if (userSchoolClass.indexOf(",") <= 0) {
			userSchoolClasses.add(userSchoolClass.trim());
		} else {
			String[] parts = userSchoolClass.split(",");
			for (String schoolClass : parts) {
				userSchoolClasses.add(schoolClass);
			}
		}

		for (String schoolClass : userSchoolClasses) {
			add(new SchoolClassBox(schoolClass, false));
		}

		VertretungsalarmButton add = new VertretungsalarmButton("Hinzufügen");
		add.getElement().getStyle().setProperty("order", "9999999999999999999");
		add.getElement().getStyle().setProperty("alignSelf", "flex-end");
		add.getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203, 203, 203, 0.9)");
		add.getElement().getStyle().setProperty("background", "icons/add-24px.svg");

		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				String userSchoolClass = Vertretungsalarm.getClientStorage().getItem("schoolClass");

				userSchoolClasses.clear();

				if (userSchoolClass.indexOf(",") <= 0) {
					userSchoolClasses.add(userSchoolClass.trim());
				} else {
					String[] parts = userSchoolClass.split(",");
					for (String schoolClass : parts) {
						userSchoolClasses.add(schoolClass);
					}
				}

				int schoolClassBoxes = 0;
				for (int i = 0; i < getWidgetCount(); i++) {
					if (getWidget(i).getClass() == SchoolClassBox.class) {
						schoolClassBoxes++;
					}
				}
				if (userSchoolClasses.size() == schoolClassBoxes) {
					newSchoolClass = new SchoolClassBox("", true);
					newSchoolClass.getElement().getStyle().setProperty("order", "-1");
					add(newSchoolClass);
				}
			}
		});

		add(add);

	}

}
