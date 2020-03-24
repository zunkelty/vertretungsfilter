package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class EventTranslator {

	public static String getEventTitle(VertretungsEvent e) {

		String title = getEventTitleBody(e);

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

		if (userSchoolClasses.size() > 1) {
			ArrayList<String> matchingSchoolClasses = new ArrayList<String>();
			for (String schoolClass : userSchoolClasses) {
				if (e.getSchoolClasses().contains(schoolClass.trim())) {
					matchingSchoolClasses.add(schoolClass.trim());
				}
			}
			String temp = title;
			title = "";
			for (String match : matchingSchoolClasses) {
				title += (title == "" ? "" : ", ") + match;
			}
			title += ": " + temp;
		}

		return title;

	}

	public static String getEventTitleBody(VertretungsEvent e) {

		if (!e.getIsHappening()) {
			return matchAbbreviationWithSubject(e.getPlannedSubject()) + " bei " + e.getPlannedTeacher() + " entfällt";
		} else if (e.getPlannedRoom() != e.getActualRoom() && e.getPlannedTeacher() == e.getActualTeacher()) {
			return matchAbbreviationWithSubject(e.getActualSubject()) + " in Raum " + e.getActualRoom() + " statt "
					+ e.getPlannedRoom();
		} else if (e.getPlannedTeacher() != e.getActualTeacher()) {
			if (e.getPlannedSubject() != e.getActualSubject()) {
				return matchAbbreviationWithSubject(e.getActualSubject()) + " bei " + e.getActualTeacher() + " statt "
						+ e.getPlannedSubject();
			} else if (e.getPlannedTeacher() != e.getActualTeacher()) {
				return matchAbbreviationWithSubject(e.getActualSubject()) + " bei " + e.getActualTeacher()
						+ " statt bei " + e.getPlannedTeacher();
			}
		}
		return matchAbbreviationWithSubject(e.getPlannedSubject()) + " bei " + e.getActualTeacher();

	}

	public static String getEventSubtitle(VertretungsEvent e) {
		if (!e.getIsHappening()) {
			return e.getLessonsAsString() + " Stunde";
		} else if (e.getPlannedRoom() != e.getActualRoom() && e.getPlannedTeacher() == e.getActualTeacher()) {
			return e.getLessonsAsString() + " Stunde<br>" + matchAbbreviationWithSubject(e.getActualSubject()) + " bei "
					+ e.getActualTeacher();
		} else if (e.getPlannedTeacher() != e.getActualTeacher() && e.getPlannedRoom() == e.getActualRoom()) {
			return e.getLessonsAsString() + " Stunde<br>" + matchAbbreviationWithSubject(e.getActualSubject())
					+ " in Raum " + e.getActualRoom();
		} else if (e.getPlannedTeacher() != e.getActualTeacher() && e.getPlannedRoom() != e.getActualRoom()) {
			return e.getLessonsAsString() + " Stunde<br>" + matchAbbreviationWithSubject(e.getActualSubject())
					+ " in Raum " + e.getActualRoom() + " statt in " + e.getPlannedRoom();
		} else if (e.getPlannedRoom() == e.getActualRoom() && e.getPlannedTeacher() == e.getActualTeacher()
				&& e.getPlannedSubject() == e.getActualSubject() && e.getAdditionalText() != "") {
			return e.getLessonsAsString() + " Stunde<br>Raum " + e.getActualRoom() + "<br>(s. Info)";
		}
		return e.getLessonsAsString() + " Stunde<br>Raum " + e.getActualRoom();
	}

	public static String getPlanned(VertretungsEvent e) {
		String response = "";
		if (e.getPlannedSubject() != "---") {
			response = EventTranslator.matchAbbreviationWithSubject(e.getPlannedSubject());
		}
		if (e.getPlannedTeacher() != "---") {
			response += " bei " + e.getPlannedTeacher();
		}
		if (e.getPlannedRoom() != "---") {
			response += " in " + e.getPlannedRoom();
		}
		return response;
	}

	public static String getActual(VertretungsEvent e) {
		return EventTranslator.matchAbbreviationWithSubject(e.getActualSubject()) + " bei " + e.getActualTeacher()
				+ " in " + e.getActualRoom();
	}

	private static String matchAbbreviationWithSubject(String abbr) {
		switch (abbr) {
		case "BI":
			return "Biologie";
		case "SP":
			return "Sport";
		case "IF":
			return "Informatik";
		case "PO":
			return "Politik";
		case "EK":
			return "Erdkunde";
		case "FR":
			return "Französisch";
		case "SN":
			return "Spanisch";
		case "MA":
			return "Mathe";
		case "WN":
			return "Werte u. Normen";
		case "CH":
			return "Chemie";
		case "MU":
			return "Musik";
		case "DE":
			return "Deutsch";
		case "EN":
			return "Englisch";
		case "PH":
			return "Physik";
		case "GE":
			return "Geschichte";
		case "RE":
			return "Religion";
		default:
			return abbr;
		}
	}

}
