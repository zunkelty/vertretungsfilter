package de.zunk.vertretungsalarm.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class VertretungsEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	ArrayList<String> schoolClasses;
	ArrayList<String> lessons;

	String plannedTeacher;
	String actualTeacher;
	String room;
	String plannedSubject;
	String actualSubject;
	String info;

	VertretungsDate date;

	// VertretungsDate date = new VertretungsDate();

	public VertretungsEvent() {
		super();
	}

	public VertretungsEvent(ArrayList<String> schoolClasses, ArrayList<String> lessons, String plannedTeacher,
			String actualTeacher, String room, String plannedSubject, String actualSubject, String info,
			VertretungsDate date) {

		this.schoolClasses = schoolClasses;
		this.lessons = lessons;
		this.plannedTeacher = plannedTeacher;
		this.actualTeacher = actualTeacher;
		this.room = room;
		this.plannedSubject = plannedSubject;
		this.actualSubject = actualSubject;
		this.info = info;
		this.date = date;

		System.out.println("LessonsAsString: " + getLessonsAsString());

	}

	@Override
	public String toString() {
		return ("----------------Neues Event:----------------" + "\nKlasse/-n: " + getSchoolClassesAsString()
				+ "\nStunde: " + getLessonsAsString() + "\nEigentlich Lehrer: " + getPlannedTeacher()
				+ "\nVertretungslehrer: " + getActualTeacher() + "\nRaum: " + getRoom() + "\nEigentliches Fach: "
				+ getPlannedSubject() + "\nVertretungsfach: " + getActualSubject() + "\nInfo: " + getInfo()
				+ "\nDatum: " + getDateAsText());

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

	public String getPlannedTeacher() {
		return plannedTeacher;
	}

	public String getActualTeacher() {
		return actualTeacher;
	}

	public String getRoom() {
		return room;
	}

	public String getPlannedSubject() {
		return plannedSubject;
	}

	public String getActualSubject() {
		return actualSubject;
	}

	public String getInfo() {
		return info;
	}

	public VertretungsDate getDate() {
		return date;
	}

	public String getDateAsText() {
		return date.toString();
	}
}
