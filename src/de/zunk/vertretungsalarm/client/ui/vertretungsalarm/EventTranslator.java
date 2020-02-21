package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class EventTranslator {

	String result = "";

	public String translateEvent(VertretungsEvent e) {

		// ArrayList<String> lessons = new ArrayList<String>();
		// lessons.add("1");
		// lessons.add("2");
		//
		// ArrayList<String> classes = new ArrayList<String>();
		// classes.add("11B");
		//
		// VertretungsEvent event = new VertretungsEvent(classes, lessons, "ZUNK",
		// "---", "P11", "MA", "---",
		// new VertretungsDate(10, 3, 2019));

		if (e.getActualSubject().contains("---") && e.getActualTeacher().contains("---")) {

			return e.getLessonsAsString() + " Stunde: " + e.getPlannedSubject() + " bei " + e.getPlannedTeacher()
					+ " entfällt";

		} else if (e.getPlannedSubject().contains(e.getActualSubject())
				&& e.getPlannedTeacher().contains(e.getActualTeacher())) {
			return e.getLessonsAsString() + " Stunde: " + e.getActualSubject() + " bei " + e.getActualTeacher() + " in "
					+ e.getActualRoom();
		} else {
		}
		return e.getLessonsAsString() + " Stunde: " + e.getActualSubject() + " statt " + e.getPlannedSubject() + " bei "
				+ e.getActualTeacher();
	}

}
