package de.zunk.vertretungsalarm.server;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import de.zunk.vertretungsalarm.shared.VertretungsDate;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class TestingClass implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	public static void main(String args[]) {

		Vertretungsplan vertretungsplan = new Vertretungsplan();
		ArrayList<VertretungsEvent> exampleEvents = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			ArrayList<String> schoolClasses = new ArrayList<String>();
			ArrayList<String> lessons = new ArrayList<String>();

			String plannedTeacher = "";
			String actualTeacher = "";
			String room = "";
			String plannedSubject = "";
			String actualSubject = "";

			VertretungsDate date = new VertretungsDate();

			schoolClasses.add(i + "A");

			lessons.add(i + "");

			plannedTeacher = i + ".PlannedTeacher";

			actualTeacher = i + ".ActualTeacher";

			room = i + ".Room";

			plannedSubject = i + ".PlannedSubject";

			actualSubject = i + ".ActualSubject";

			date = new VertretungsDate(1, 1, 2019);

			exampleEvents.add(new VertretungsEvent(schoolClasses, lessons, plannedTeacher, actualTeacher, room,
					plannedSubject, actualSubject, date));
		}

		vertretungsplan = new Vertretungsplan(exampleEvents);

		System.out.println(vertretungsplan.getTime().toString());

		// private static final DateTimeFormatter dtf =
		// DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

		// Date date = new Date();
		// System.out.println(sdf.format(date));
	}

}
