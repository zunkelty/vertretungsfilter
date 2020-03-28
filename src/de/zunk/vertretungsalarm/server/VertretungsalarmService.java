package de.zunk.vertretungsalarm.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import de.zunk.vertretungsalarm.shared.DayInfo;
import de.zunk.vertretungsalarm.shared.VertretungsDate;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.VertretungsEventType;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class VertretungsalarmService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Vertretungsplan vertretungsplan;

	String scraped_content_example;

	private static VertretungsDate dateForEvents = new VertretungsDate(16, 10, 2002);
	private static String dayInfoForEvents = "";

	public static void main(String[] args) {
		loadVertretungsplan();
	}

	public VertretungsalarmService() {
		vertretungsplan = null;
		loadVertretungsplan();

		new Timer().scheduleAtFixedRate(new TimerTask() {

			@Override
			public void run() {
				try {
					loadVertretungsplan();
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		}, 0, 1 * 60 * 1000);
	}

	public Vertretungsplan getVertretungsplan() {
		return vertretungsplan;
	}

	public static void loadVertretungsplan() {

		String scraped_content = readVertretungsplan();

		System.out.println(scraped_content);

		ArrayList<VertretungsEvent> allVertretungsEvents = new ArrayList<>();
		ArrayList<DayInfo> allDayInfos = new ArrayList<>();

		// Aufbereitung des gescrapten Inhalts zur Weiterverarbeitung

		String[] snippets = scraped_content.split(" ");

		for (int i = 0; i < snippets.length; i++) {

			snippets[i] = snippets[i].replaceAll("[\\s\\,]", "");

		}

		for (String string : snippets) {
			System.out.println("Snippets:");
			System.out.println("-->" + string + "<--");
		}

		// Einzelne Schnipsel werden ausgelesen, verstanden und als VertretungsEvents
		// abgespeichert

		for (int i = 0; i < snippets.length; i++) {

			if (isVertretungsDate(snippets[i])) {

				String[] date_split = snippets[i].split("\\.", 3);

				int day = Integer.parseInt(date_split[0]);
				int month = Integer.parseInt(date_split[1]);
				int year = Integer.parseInt(date_split[2]);

				dateForEvents.setDate(day, month, year);

			} else if (snippets[i].contains("Nachrichten") && snippets[i + 1].contains("zum")
					&& snippets[i + 2].contains("Tag")) {

				i += 3;

				dayInfoForEvents = "";

				while (!snippets[i].contains("Vtr-Nr.") && !snippets[i].contains("Keine")
						&& !snippets[i + 1].contains("Vertretungen")) {
					dayInfoForEvents += " " + snippets[i];
					i++;
				}
				DayInfo dayInfo = new DayInfo(dayInfoForEvents,
						new VertretungsDate(dateForEvents.getDay(), dateForEvents.getMonth(), dateForEvents.getYear()));
				allDayInfos.add(dayInfo);

			} else if (isEventId(snippets[i])) {
				try {

					int j = i;

					ArrayList<String> schoolClasses = new ArrayList<String>();
					ArrayList<String> lessons = new ArrayList<String>();

					VertretungsEventType type = VertretungsEventType.UNDEFINED;
					String plannedTeacher = "ZUNK";
					String actualTeacher = "ZUNK";
					String plannedRoom = "ZUNK";
					String actualRoom = "ZUNK";
					String plannedSubject = "ZUNK";
					String actualSubject = "ZUNK";
					String additionalText = "";

					VertretungsDate date = new VertretungsDate();

					j++;

					String type_s = "";
					while (isEventType(snippets[j])) {
						if (type == VertretungsEventType.UNDEFINED) {
							type_s = snippets[j];
						} else {
							type_s = type_s + " " + snippets[j];
						}
						j++;
					}

					type = getEventTypeFromString(type_s);

					j++;

					// Speichern der genannten Schulstunde / -n

					if (isLessonName(snippets[j]) && snippets[j + 1] != "-" && !isLessonName(snippets[j + 2])) {
						lessons.add(snippets[j]);
						j++;
					} else if (isLessonName(snippets[j]) && isLessonName(snippets[j + 2])) {
						lessons.add(snippets[j]);
						j++;
						j++;
						lessons.add(snippets[j]);
						j++;
					}

					// Speichern der genannten Schulklasse / -n / des genannten Jahrgangs
					while (isSchoolClassName(snippets[j]) || isSchoolYearName(snippets[j])) {
						schoolClasses.add(snippets[j]);
						j++;
					}

					while (snippets[j].matches("-?\\d+(\\.\\d+)?")) {
						j++;
					}

					boolean isHappening = true;

					if (snippets[j + 3].contains("Freis.") || snippets[j + 3].contains("Entfall")
							|| type == VertretungsEventType.CANCELED || type == VertretungsEventType.FREE) {
						if (snippets[j].indexOf("?") <= 0 && snippets[j + 1].contains("---")) {
							isHappening = false;
						}
					}

					// Speichern der genannten Lehrer
					if (!isHappening) {
						plannedTeacher = snippets[j];
						actualTeacher = "---";
					} else {
						if (snippets[j].contains("?")) {
							String[] splitTeachers = snippets[j].split("\\?");
							plannedTeacher = splitTeachers[0];
							actualTeacher = splitTeachers[1];
						} else {
							plannedTeacher = snippets[j];
							actualTeacher = snippets[j];
						}
					}

					j++;

					// Speichern der genannten Räume

					if (!isHappening) {
						plannedRoom = snippets[j];
						actualRoom = "---";
					} else {
						if (snippets[j].contains("?")) {
							String[] splitRooms = snippets[j].split("\\?");
							plannedRoom = splitRooms[0];
							actualRoom = splitRooms[1];
						} else {
							plannedRoom = snippets[j];
							actualRoom = snippets[j];
						}
					}

					j++;

					// Speichern der genannten Fächer

					if (!isHappening) {
						plannedSubject = snippets[j];
						actualSubject = "---";
					} else {
						if (snippets[j].contains("?")) {
							String[] splitSubjects = snippets[j].split("\\?");
							plannedSubject = splitSubjects[0];
							actualSubject = splitSubjects[1];
						} else {
							plannedSubject = snippets[j];
							actualSubject = snippets[j];
						}
					}

					j++;

					// Speichern der Zusatzinformationen

					while (!isEventId(snippets[j]) && !snippets[j].contains("Untis") && !snippets[j].contains("x")
							&& j < snippets.length - 1) {
						additionalText = additionalText.trim() + " " + snippets[j].trim();
						j++;
					}
					if (additionalText.contains("DSB")) {
						additionalText = additionalText.replace("DSB", "");
					}

					date.setDate(dateForEvents.getDay(), dateForEvents.getMonth(), dateForEvents.getYear());

					if (!schoolClasses.isEmpty() && !lessons.isEmpty() && !plannedTeacher.contains("ZUNK")
							&& !actualTeacher.contains("ZUNK") && !plannedRoom.contains("ZUNK")
							&& !actualRoom.contains("ZUNK") && !plannedSubject.contains("ZUNK")
							&& !actualSubject.contains("ZUNK")) {

						VertretungsEvent e = new VertretungsEvent(schoolClasses, lessons, type, plannedTeacher,
								actualTeacher, plannedRoom, actualRoom, plannedSubject, actualSubject, additionalText,
								date, isHappening);
						allVertretungsEvents.add(e);

						System.out.println(e.toString());
					}

				} catch (Exception e) {
					// TODO: handle exception
				}

			}
		}

		Date now = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		vertretungsplan = new Vertretungsplan(allVertretungsEvents, allDayInfos, sdf.format(now));
	}

	public static boolean isEventType(String s) {
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetter(s.charAt(i)) && s.charAt(i) != '-' && s.charAt(i) != '.') {
				return false;
			}
		}
		return true;
	}

	public static boolean isEventId(String possibleId) {
		try {
			// See if possibleId can be converted to an int
			Integer.parseInt(possibleId);
			// See if possibleId is a number with four digits
			if (possibleId.matches("[0-9]+") && possibleId.length() == 4) {
				return true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	public static boolean isVertretungsDate(String possibleDateName) {
		try {
			String[] date_split = possibleDateName.split("\\.", 3);

			// Check that the split parts are parsable to int
			int day = Integer.parseInt(date_split[0]);
			int month = Integer.parseInt(date_split[1]);
			int year = Integer.parseInt(date_split[2]);

			// Check that the parts have their correct length (day=2 digits; year=4 digits)
			if (String.valueOf(day).length() == 1 && day < 10) {
			} else if (String.valueOf(day).length() == 2 && day >= 10 && day <= 31) {
			} else {
				return false;
			}
			if (String.valueOf(month).length() == 1 && month < 10) {
			} else if (String.valueOf(month).length() == 2 && month >= 10 && month <= 12) {
			} else {
				return false;
			}
			if (String.valueOf(year).length() == 4 && year >= 2019) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isLessonName(String possibleLessonName) {
		try {
			if (Integer.parseInt(possibleLessonName) <= 14) {
				return true;
			}
		} catch (Exception e) {

		}

		return false;
	}

	public static boolean isSchoolClassName(String possibleSchoolClassName) {
		try {
			List<String> output = new ArrayList<String>();
			Matcher match = Pattern.compile("[0-9]+|[a-z]+|[A-Z]+").matcher(possibleSchoolClassName);
			while (match.find()) {
				output.add(match.group());
			}
			if (output.size() == 2 && output.get(0).matches("[0-9]+") && output.get(0).length() <= 2
					&& Integer.parseInt(output.get(0)) <= 13 && output.get(1).matches("[a-zA-Z]+")
					&& output.get(1).length() == 1) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static boolean isSchoolYearName(String possibleSchoolYearName) {
		try {
			if (possibleSchoolYearName.contains("12") || possibleSchoolYearName.contains("13")) {
				if (possibleSchoolYearName.length() == 2) {
					return true;
				}
			}
			return false;
		} catch (Exception e) {
			return false;
		}

	}

	public static VertretungsEventType getEventTypeFromString(String type_s) {
		switch (type_s) {
		case "Entfall": {
			return VertretungsEventType.CANCELED;
		}
		case "Raum-Vtr.": {
			return VertretungsEventType.ROOM_CHANGE;
		}
		case "Statt-Vertretung": {
			return VertretungsEventType.INSTEAD_OF;
		}
		case "Trotz Absenz": {
			return VertretungsEventType.DESPITE_ABSENSE;
		}
		case "Verlegung": {
			return VertretungsEventType.MOVING;
		}
		case "Lehrertausch": {
			return VertretungsEventType.TEACHER_CHANGE;
		}
		case "Vertretung": {
			return VertretungsEventType.SUBSTITUTE;
		}
		case "Betreuung": {
			return VertretungsEventType.CARE;
		}
		case "Freisetzung": {
			return VertretungsEventType.FREE;
		}
		default:
			return VertretungsEventType.UNDEFINED;
		}
	}

	private static String readVertretungsplan() {

		try (final WebClient client = new WebClient(BrowserVersion.CHROME)) {
			System.out.println("Vertretungsplan auslesen...");

			HtmlPage page = null;

			page = client.getPage(
					"***REMOVED***");

			final List<FrameWindow> window = page.getFrames();
			final HtmlPage pageTwo = (HtmlPage) window.get(0).getEnclosedPage();

			client.waitForBackgroundJavaScriptStartingBefore(5000);

			// Get the form that we are dealing with and within that form,
			// find the submit button and the field that we want to change.
			final HtmlForm form = pageTwo.getFormByName("form1");

			final HtmlSubmitInput button = form.getInputByName("ctl02$btnLogin");
			final HtmlTextInput textFieldUser = form.getInputByName("ctl02$txtBenutzername");
			final HtmlPasswordInput textFieldPw = form.getInputByName("ctl02$txtPasswort");

			// Change the value of the text field
			textFieldUser.type("***REMOVED***");
			textFieldPw.type("***REMOVED***-han");

			// Now submit the form by clicking the button and get back the second page.
			final com.gargoylesoftware.htmlunit.html.HtmlPage pageThree = (com.gargoylesoftware.htmlunit.html.HtmlPage) button
					.click();

			// System.out.println("Ausgelesen! --> " + pageThree.asText() + " <--");

			return pageThree.asText();

		} catch (FailingHttpStatusCodeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "";

	}

}
