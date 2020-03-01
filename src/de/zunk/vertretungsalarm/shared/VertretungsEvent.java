package de.zunk.vertretungsalarm.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class VertretungsEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<String> schoolClasses;
	ArrayList<String> lessons;

	VERTRETUNGS_EVENT_TYPE type;
	String plannedTeacher;
	String actualTeacher;
	String plannedRoom;
	String actualRoom;
	String plannedSubject;
	String actualSubject;
	String additionalText;
	Boolean isHappening;

	VertretungsDate date;

	public VertretungsEvent() {
		super();
	}

	public VertretungsEvent(ArrayList<String> schoolClasses, ArrayList<String> lessons, VERTRETUNGS_EVENT_TYPE type,
			String plannedTeacher, String actualTeacher, String plannedRoom, String actualRoom, String plannedSubject,
			String actualSubject, String additionalText, VertretungsDate date, Boolean isHappening) {

		this.schoolClasses = schoolClasses;
		this.lessons = lessons;
		this.type = type;
		this.plannedTeacher = plannedTeacher;
		this.actualTeacher = actualTeacher;
		this.plannedRoom = plannedRoom;
		this.actualRoom = actualRoom;
		this.plannedSubject = plannedSubject;
		this.actualSubject = actualSubject;
		this.additionalText = additionalText;
		this.date = date;
		this.isHappening = isHappening;

	}

	@Override
	public String toString() {
		return ("----------------Neues Event:----------------" + "\nKlasse/-n / Jahrgang: " + getSchoolClassesAsString()
				+ "\nStunde: " + getLessonsAsString() + "\nTyp: " + getType() + "\nEigentlich Lehrer: "
				+ getPlannedTeacher() + "\nVertretungslehrer: " + getActualTeacher() + "\nEigentlich Raum: "
				+ getPlannedRoom() + "\nVertretungsraum: " + getActualRoom() + "\nEigentliches Fach: "
				+ getPlannedSubject() + "\nVertretungsfach: " + getActualSubject() + "\nZusatztext: "
				+ getAdditionalText() + "\nDatum: " + getDateAsText() + "\nisHappening: " + isHappening);

	}

	public String getSchoolClassesAsString() {
		String schoolClassesAsString = "";

		for (String schoolClass : schoolClasses) {
			schoolClassesAsString = schoolClassesAsString + schoolClass + " ";
		}

		return schoolClassesAsString;
	}

	public ArrayList<String> getSchoolClasses() {
		return schoolClasses;
	}

	public String getLessonsAsString() {

		String lessonsAsString = "";

		if (getLessons().size() == 1) {
			lessonsAsString = lessons.get(0) + ".";
		} else if (getLessons().size() >= 2) {
			for (String lesson : getLessons()) {
				lessonsAsString = lessonsAsString + lesson + ". / ";
			}
			lessonsAsString = lessonsAsString.substring(0, lessonsAsString.length() - 2);
		}

		return lessonsAsString;

	}

	public ArrayList<String> getLessons() {
		return lessons;
	}

	public VERTRETUNGS_EVENT_TYPE getType() {
		return type;
	}

	public String getPlannedTeacher() {
		return plannedTeacher;
	}

	public String getActualTeacher() {
		return actualTeacher;
	}

	public String getPlannedRoom() {
		return plannedRoom;
	}

	public String getActualRoom() {
		return actualRoom;
	}

	public String getPlannedSubject() {
		return plannedSubject;
	}

	public String getActualSubject() {
		return actualSubject;
	}

	public String getAdditionalText() {
		return additionalText;
	}

	public VertretungsDate getDate() {
		return date;
	}

	public String getDateAsText() {
		return date.toString();
	}

	public Boolean getIsHappening() {
		return isHappening;
	}

}
