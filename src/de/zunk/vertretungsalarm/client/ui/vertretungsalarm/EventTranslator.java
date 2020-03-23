package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class EventTranslator {

	public static String getEventTitle(VertretungsEvent e) {
		if (!e.getIsHappening()) {
			return matchAbbreviationWithSubject(e.getPlannedSubject()) + " bei " + e.getPlannedTeacher() + " entfällt";
		} else if (e.getPlannedRoom() != e.getActualRoom() && e.getPlannedTeacher() == e.getActualTeacher()) {
			return matchAbbreviationWithSubject(e.getActualSubject()) + " in Raum " + e.getActualRoom() + " statt "
					+ e.getPlannedRoom();
		} else if (e.getPlannedTeacher() != e.getActualTeacher()) {
			if (e.getPlannedSubject() != e.getActualSubject()) {
				return matchAbbreviationWithSubject(e.getActualSubject()) + " bei " + e.getActualTeacher() + " statt "
						+ e.getPlannedSubject();
			} else {
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
