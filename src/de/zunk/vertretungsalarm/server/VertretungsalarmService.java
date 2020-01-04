package de.zunk.vertretungsalarm.server;

import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.FrameWindow;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlPasswordInput;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;

import de.zunk.vertretungsalarm.shared.VertretungsDate;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class VertretungsalarmService implements Serializable {

	private static final long serialVersionUID = 1L;

	private static Vertretungsplan vertretungsplan;

	static String scraped_content_example = "DSB"
			+ "DSB Untis 2020 Elsa-Brändström-Schule D-30173 Hannover, Hilde-Schneider-Allee 30 Stundenplan 2018/2019 gülig ab 01.04.2019 Stand: 03.01.2020 11:58 3.1.2020 Freitag (Seite 1 / 3) Nachrichten zum Tag Information für die Klasse 6F/6B von LOE/PAD: Treffpunkt: Klassenraum P12/P11. Sportsachen für draußen mitbringen! Klasse(n) Stunde (Lehrer) Vertreter Raum (Fach) Fach Vertr. von Text 5A, 5B, 5C, 5F 1 DIS RIN P10 ENF ENF Mitbetreuung (5F) 3 - 4 GRA --- MA --- 6C 5 WOE --- --- SP --- 6C 6 WOE --- --- SP --- 6F 3 - 4 LAN GRA M3 PH EN 7A, 7B, 7C, 7D 3 - 4 CLS AMM P5 LA LA Aufg. CLS 7A, 7B, 7C, 7D 3 BE GEI P3 SN SN Aufg. BE 7A, 7B, 7C, 7D 3 DIS KUT P9 FR FR Fr-7.6. / 3 Aufg. DIS 7A, 7B, 7C, 7D 4 BE KUT P3 SN SN Aufg. BE 7A, 7B, 7C, 7D 4 DIS VLD P9 FR FR Fr-7.6. / 4 Aufg. DIS 7A 5 - 6 BAU BAU S2 SP SP 7D 5 SLH KAM Mu1 CH MU Do-6.6. / 5 7D 6 SLH KAM Mu1 CH MU Do-6.6. / 6 7F 1 - 2 YOU --- --- SP --- 7F 5 - 6 BE KAE P10 FRf MA 8A, 8B, 8C 5 - 6 DIS --- --- FR --- Untis 2020 Elsa-Brändström-Schule D-30173 Hannover, Hilde-Schneider-Allee 30 Stundenplan 2018/2019 gülig ab 01.04.2019 Stand: 03.01.2019 11:58 3.1.2020 Freitag (Seite 2 / 3) Klasse(n) Stunde (Lehrer) Vertreter Raum (Fach) Fach Vertr. von Text 8A, 8B, 8C 5 HRU TR P5 SN SN 9B 3 - 4 YOU ZIP Ch1 SP CH 9B 5 - 6 OST --- --- EN --- 9D 5 YOU KML M7 MA EN Fr-7.6. / 6 9D 6 KML --- --- EN --- 9S 1 OST OST M1 DE DE Klassenarbeit Deutsch 9S 2 OST SPT M1 DE DE Klassenarbeit Deutsch 11A, 11B, 11C, 11D 1 BE --- --- SN --- 11A, 11B, 11C, 11D 1 EV --- --- SN --- 11A 2 YAB YAB M4 DE DE Raumänderung beachten 11C 5 SPT MLR N2 EK PO Di-4.6. / 4 11C 6 SPT MLR N2 EK PO Di-4.6. / 3 (12) 1 - 2 BUE --- DE --- (12) 1 - 2 WOE --- BI --- (12) 1 - 2 GRA --- EN --- (12) 1 - 2 WO --- MA --- (12) 1 - 2 HPT --- MA --- (12) 3 - 4 SCH --- GE --- (12) 3 - 4 GEI --- EK --- (12) 3 - 4 MLR --- PO --- Untis 2020 Elsa-Brändström-Schule D-30173 Hannover, Hilde-Schneider-Allee 30 Stundenplan 2018/2019 gülig ab 01.04.2019 Stand: 07.06.2019 11:58 3.1.2020 Freitag (Seite 3 / 3) Klasse(n) Stunde (Lehrer) Vertreter Raum (Fach) Fach Vertr. von Text (12) 3 - 4 KRL --- BI --- (12) 3 - 4 DIE --- RE --- 2/3 KAM SLH HAL 3 KUT PFE BR BR 4 SLH --- --- BRV --- 4 VLD WES BR BR 4/5 SLH KAM HAL\\r\\n\"\r\n"
			+ "DSB Untis 2020 Elsa-Brändström-Schule D-30173 Hannover, Hilde-Schneider-Allee 30 Stundenplan 2018/2019 gülig ab 01.04.2019 Stand: 03.01.2020 11:58 4.1.2020 Mittwoch (Seite 1 / 2) Nachrichten zum Tag Nachschreibtermin Sek. I: 60-90 Minuten K3 bei KAP 45 Minuten K2 bei KUT Klasse(n) Stunde (Lehrer) Vertreter Raum (Fach) Fach Vertr. von Text 5, 6 7 SPR --- --- AG-SCH --- 5C 1 - 2 CL KML P2 KU EN 6A 5 - 6 LEH --- --- SP --- 7A 5 - 6 ROT NEU P9 MA PH 8A, 8B, 8C, 8F 5 - 6 SPR --- --- WN --- 9A 5 BAU CLS M9 EK EK Aufg. BAU 9A 6 BAU DIE M9 EK EK Aufg. BAU 9D 5 - 6 AMM AMM M7 SP SP 9S 5 SLH --- --- BI --- verlagert auf Do, 5. Std. 9S 6 SLH --- --- BI --- verlagert auf Do, 6. Std. 10B, 10A, 10C, 10S 1 - 2 STG STG K2 SN3 SN3 Klassenarbeit Spanisch 11D 5 - 6 SAN --- --- MA --- 4 ROT --- --- BRV --- 4/5 ROT SLH HGB 4/5 LEH AMM HAL Untis 2020 Elsa-Brändström-Schule D-30173 Hannover, Hilde-Schneider-Allee 30 Stundenplan 2018/2019 gülig ab 01.04.2019 Stand: 07.06.2019 11:58 4.1.2019 Mittwoch (Seite 2 / 2) Klasse(n) Stunde (Lehrer) Vertreter Raum (Fach) Fach Vertr. von Text 6 CL --- --- SL --- 7 - 8 KAP K3 Nachschreibtermin Sek. I 7 KUT K2 Nachschreibtermin Sek. I Untis 2020 Elsa-Brändström-Schule D-30173 Hannover, Hilde-Schneider-Allee 30 Stundenplan 2018/2019 gülig ab 01.04.2019 Stand: 07.06.2020 11:58 4.1.2020 Donnerstag (Seite 1 / 2) Nachrichten zum Tag Mensadienst: 10A (KO) Medien-AG (VRA) fällt heute aus. WPK (KML) schreibt heute in M7 Klausur. Klasse(n) Stunde (Lehrer) Vertreter Raum (Fach) Fach Vertr. von Text 11C 1 - 2 CL KML P7 DE EN 6A, 6B, 6C 5 AMM ANG H3 LA LA Aufg. AMM 6A, 6B, 6C 6 AMM PA H3 LA LA Do-13.6. / 6 Aufg. AMM 7B 3 SPR LTB P3 SP EN 7B 4 SPR HPT P3 SP SP 7C 5 ZRT KRL K3 SP KU Fr-14.6. / 5 statt Fr 7C 6 ZRT KRL K3 SP KU Fr-14.6. / 6 statt Fr 8A 1 EI --- --- SP --- 8A 2 EI MEY M6 SP MA 8C 5 - 6 VRA LTB M4 PO PO 9A, 9C, 9D, 9S 8 AMM --- --- LA3 --- 9B 5 OST ZIP Ch2 DE CH Di-18.6. / 8 statt 18.06. 9B 6 OST ZIP Ch2 DE CH Di-18.6. / 9 statt 18.06. 9C 5 - 6 LOE FRI Bi2 BI BI Untis 2020 Elsa-Brändström-Schule D-30173 Hannover, Hilde-Schneider-Allee 30 Stundenplan 2018/2019 gülig ab 01.04.2019 Stand: 07.06.2019 11:58 5.1.2020 Donnerstag (Seite 2 / 2) Klasse(n) Stunde (Lehrer) Vertreter Raum (Fach) Fach Vertr. von Text 9S 5 CL SLH Bi1 KU BI Mi-12.6. / 5 9S 6 CL SLH Bi1 KU BI Mi-12.6. / 6 10A, 10B, 10S 5 MAG --- --- RE --- Unterrichtsgang 10A, 10B, 10S 5 SPR MLR K2 WN WN Do-13.6. / 5 Aufg. SPR 10A, 10B, 10S 6 MAG --- --- RE --- Unterrichtsgang 10A, 10B, 10S 6 SPR JAN K2 WN WN Aufg. SPR 11A, 11B, 11C, 11D 1 - 2 SPR --- --- WN --- 11B 5 - 6 ROT ROT P11 BI BI verschoben auf 17.06. 11C 3 ZWE SPT N2 EN EK Fr-14.6. / 6 11C 4 ZWE ZWE N2 EN EN Fr-14.6. / 5 0/1 EI ARM EIN 4/5 ZRT EBH HGB 5 MLR MEY BR BR 6 PA HEI BR BR 6/7 SPR ALT ME7 7 BAU --- --- Aufs.7.Std. ---";
	public static String[] schoolClasses = { "5A", "5B", "5C", "5F", "6A", "6B", "6C", "6F", "7A", "7B", "7C", "7F",
			"8A", "8B", "8C", "8F", "9A", "9B", "9C", "9F", "10A", "10B", "10C", "10D", "10S", "11A", "11B", "11C",
			"11D" };

	private static String[] lessons = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14" };

	private static VertretungsDate dateForEvents = new VertretungsDate(16, 7, 2019);

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
		}, 0, 5 * 60 * 1000);
	}

	public Vertretungsplan getVertretungsplan() {
		return vertretungsplan;
	}

	public static void loadVertretungsplan() {
		try {

			// String scraped_content = readVertretungsplan();
			String scraped_content = scraped_content_example;

			ArrayList<VertretungsEvent> allVertretungsEvents = new ArrayList<>();

			// Aufbereitung des gescrapten Inhalts zur Weiterverarbeitung

			String[] snippets = scraped_content.split(" ");

			for (int i = 0; i < snippets.length; i++) {

				snippets[i] = snippets[i].replaceAll("[\\s\\(\\),]", "");

			}

			// for (String string : snippets) {
			// System.out.println("-->" + string + "<--");
			// }

			// Einzelne Schnipsel werden ausgelesen, verstanden und als VertretungsEvents
			// abgespeichert

			for (int i = 0; i < snippets.length; i++) {

				if (isVertretungsDate(snippets[i])) {
					String[] date_split = snippets[i].split("\\.", 3);

					int day = Integer.parseInt(date_split[0]);
					int month = Integer.parseInt(date_split[1]);
					int year = Integer.parseInt(date_split[2]);

					dateForEvents.setDate(day, month, year);

				} else if (isSchoolClassName(snippets[i])) {

					ArrayList<String> schoolClasses = new ArrayList<String>();
					ArrayList<String> lessons = new ArrayList<String>();

					String plannedTeacher = "ZUNK";
					String actualTeacher = "ZUNK";
					String room = "ZUNK";
					String plannedSubject = "ZUNK";
					String actualSubject = "ZUNK";
					String info = "ZUNK";

					VertretungsDate date = new VertretungsDate();

					// Speichern der genannten Schulklasse / -n

					while (isSchoolClassName(snippets[i])) {
						schoolClasses.add(snippets[i]);
						i++;
					}

					// Speichern der genannten Schulstunde / -n
					if (isLessonName(snippets[i]) && snippets[i + 1] != "-" && !isLessonName(snippets[i + 2])) {
						lessons.add(snippets[i]);
						i++;
					} else if (isLessonName(snippets[i]) && isLessonName(snippets[i + 2])) {
						lessons.add(snippets[i]);
						i++;
						i++;
						lessons.add(snippets[i]);
						i++;
					}

					plannedTeacher = snippets[i];
					i++;
					actualTeacher = snippets[i];
					i++;
					room = snippets[i];
					i++;
					plannedSubject = snippets[i];
					i++;
					actualSubject = snippets[i];
					i++;

					// for (int j = i; !isSchoolClassName(snippets[j]) &&
					// !snippets[j].contains("Untis")
					// && !snippets[j].contains("Raum") && j <= (i + 7); j++) {
					// info = info + snippets[j] + " ";
					// }

					// Hier: Splitten hinter ausgelesenem Event & hinter dem letzten "Nachrichten
					// zum Tag"; Dann splitten an "Klassen(n) Stunde(n)"

					date.setDate(dateForEvents.getDay(), dateForEvents.getMonth(), dateForEvents.getYear());

					if (!schoolClasses.isEmpty() && !lessons.isEmpty() && !plannedTeacher.contains("ZUNK")
							&& !actualTeacher.contains("ZUNK") && !room.contains("ZUNK")
							&& !plannedSubject.contains("ZUNK") && !actualSubject.contains("ZUNK")) {

						VertretungsEvent e = new VertretungsEvent(schoolClasses, lessons, plannedTeacher, actualTeacher,
								room, plannedSubject, actualSubject, info, date);
						allVertretungsEvents.add(e);
					}
				}
			}

			String message = "";

			for (VertretungsEvent vE : allVertretungsEvents) {
				System.out.println(vE.toString());

				message = message + vE.toString();
			}

			vertretungsplan = new Vertretungsplan(allVertretungsEvents);

		} catch (Exception e) {
		}

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
		if (Arrays.asList(lessons).contains(possibleLessonName) && possibleLessonName != null) {
			return true;
		}

		return false;
	}

	public static boolean isSchoolClassName(String possibleSchoolClassName) {
		if (Arrays.asList(schoolClasses).contains(possibleSchoolClassName)) {
			return true;
		}

		return false;
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
			final HtmlPage pageThree = button.click();

			System.out.println("Ausgelesen! --> " + pageThree.asText() + " <--");

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
		return null;

	}

}
