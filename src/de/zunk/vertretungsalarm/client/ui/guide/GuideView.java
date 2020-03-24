package de.zunk.vertretungsalarm.client.ui.guide;

import java.util.ArrayList;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.vertretungsalarm.DayView;
import de.zunk.vertretungsalarm.client.ui.vertretungsalarm.EventBox;
import de.zunk.vertretungsalarm.shared.DayInfo;
import de.zunk.vertretungsalarm.shared.VertretungsDate;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.VertretungsEventType;

public class GuideView extends AbsolutePanel {

	DayView sampleDayView;

	VertretungsEvent sampleEvent;

	int step;

	private VertretungsalarmBox explanationBox;

	private AbsolutePanel explanationOverlay;
	private AbsolutePanel explanationSection;

	public GuideView() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("flexShrink", "0");

		ArrayList<String> schoolClasses = new ArrayList<String>();
		schoolClasses.add(Vertretungsalarm.getClientStorage().getItem("schoolClass"));

		ArrayList<String> lessons = new ArrayList<String>();
		lessons.add("1");
		lessons.add("2");

		VertretungsDate date = new VertretungsDate(1, 1, 2020);

		sampleEvent = new VertretungsEvent(schoolClasses, lessons, VertretungsEventType.ROOM_CHANGE, "Beispiellehrer",
				"Beispiellehrer", "BeispielraumB", "BeispielraumA", "Beispielfach", "Beispielfach",
				"Beispielinformation", date, true);

		ArrayList<VertretungsEvent> sampleEvents = new ArrayList<VertretungsEvent>();
		sampleEvents.add(sampleEvent);

		sampleDayView = new DayView(sampleEvents, new DayInfo("Beispielinformationen zum Tag", date));

		step = 0;

		explanationOverlay = new AbsolutePanel();
		explanationOverlay.getElement().getStyle().setProperty("display", "flex");
		explanationOverlay.getElement().getStyle().setProperty("flexDirection", "column");
		explanationOverlay.getElement().getStyle().setProperty("alignItems", "stretch");
		explanationOverlay.getElement().getStyle().setProperty("justifyContent", "flex-end");
		explanationOverlay.getElement().getStyle().setProperty("flexShrink", "0");
		explanationOverlay.getElement().getStyle().setProperty("padding", "10px");
		explanationOverlay.getElement().getStyle().setProperty("background",
				"linear-gradient(360deg, rgba(0,0,0,0.2) 0%, rgba(0,0,0,0) 35%)");
		explanationOverlay.setSize(Window.getClientWidth() + "px", Window.getClientHeight() + "px");

		explanationSection = new AbsolutePanel();
		explanationSection.getElement().getStyle().setProperty("display", "flex");
		explanationSection.getElement().getStyle().setProperty("flexDirection", "column");
		explanationSection.getElement().getStyle().setProperty("alignItems", "stretch");
		explanationSection.getElement().getStyle().setProperty("justifyContent", "center");
		explanationSection.getElement().getStyle().setProperty("flexShrink", "0");
		explanationSection.getElement().getStyle().setProperty("padding", "10px");

		explanationBox = new VertretungsalarmBox("");

		Image next = new Image("icons/navigate_next-24px.svg");
		next.getElement().getStyle().setProperty("background", "#F0C267");
		next.getElement().getStyle().setProperty("borderRadius", "15px");
		next.getElement().getStyle().setProperty("alignSelf", "flex-end");
		next.getElement().getStyle().setProperty("padding", "8px 15px");
		next.addClickHandler(e -> updateStepView(++step));

		explanationSection.add(explanationBox);
		explanationSection.add(next);

		updateStepView(step);

		explanationOverlay.add(explanationSection);
		RootPanel.get().add(explanationOverlay);

	}

	public void updateStepView(int step) {

		switch (step) {
		case 0: {
			explanationBox.setHTML(
					"<b>Wilkommen zum Vertretungsfilter. Bevor Du starten kannst, wird dir kurz der Vertretungsfilter erklärt.");
		}
			break;
		case 1: {
			explanationBox.setHTML(
					"Jede Minute wird der Vertretungsfilter aktualisiert und zeigt dir hier nach dieser Erklärung alles an, was dich betrifft.");
		}
			break;
		case 2: {
			explanationBox.setHTML("Alle Informationen des Vertretungsplans werden gleich für dich nach deiner Klasse "
					+ Vertretungsalarm.getClientStorage().getItem("schoolClass")
					+ " gefiltert. Weitere Optionen findest Du später in den Einstellungen.");
		}
			break;
		case 3: {
			add(sampleDayView);
			sampleDayView.getElement().getStyle().setProperty("animation", "fade-in 1s linear");
			explanationBox.setHTML(
					"Das Wichtigste zuerst: <br><b>Das große Kästchen stellt hier eine Beispielmeldung des Vertretungsplans dar.");
			sampleDayView.getWidget(2).getElement().getStyle().setProperty("animation",
					"scale-up-center 1s cubic-bezier(0.455, 0.030, 0.515, 0.955) infinite alternate both");
		}
			break;

		case 4: {
			explanationBox.setHTML(
					"Du findest dort immer eine kurze Beschreibung der Meldung, darunter die betroffenen Schulstunden und Informationen zum Vertretungsunterricht.");
			sampleDayView.getWidget(2).getElement().getStyle().setProperty("animation",
					"scale-up-center 1s cubic-bezier(0.455, 0.030, 0.515, 0.955) infinite alternate both");
		}
			break;
		case 5: {
			explanationBox
					.setHTML("Alle Meldungen sind nach Tagen sortiert und erscheinen unter dem entsprechenden Datum.");
			sampleDayView.getWidget(2).getElement().getStyle().setProperty("animation", "");
			sampleDayView.getWidget(0).getElement().getStyle().setProperty("animation",
					"scale-up-center 1s cubic-bezier(0.455, 0.030, 0.515, 0.955) infinite alternate both");
		}
			break;
		case 6: {
			explanationBox.setHTML("In dem grauen Kästchen findest Du die Informationen zum Tag.");
			sampleDayView.getWidget(0).getElement().getStyle().setProperty("animation", "");
			sampleDayView.getWidget(1).getElement().getStyle().setProperty("animation",
					"scale-up-center 1s cubic-bezier(0.455, 0.030, 0.515, 0.955) infinite alternate both");
		}
			break;
		case 7: {
			explanationBox.setHTML(
					"Damit Dir keine Informationen fehlen, kannst Du über die drei Pünktchen eine Detailansicht zu jeder Meldung aufrufen.");
			sampleDayView.getWidget(1).getElement().getStyle().setProperty("animation", "");
			EventBox eventBox = (EventBox) sampleDayView.getWidget(2);
			AbsolutePanel topPart = (AbsolutePanel) eventBox.getWidget(0);
			topPart.getWidget(1).getElement().getStyle().setProperty("animation",
					"scale-up-center-large 1s cubic-bezier(0.455, 0.030, 0.515, 0.955) infinite alternate both");
		}
			break;
		case 8: {
			explanationBox.setHTML(
					"Da der Vertretungsfilter auf dem Schulvertretungsplan basiert, sollten alle Informationen verlässlich sein. Falls Du trotzdem einen Fehler finden solltest, melde dich bitte bei dem Entwickler. Kontaktinformationen findest Du unter Kontakt unten auf der W***REMOVED***ite.");
			EventBox eventBox = (EventBox) sampleDayView.getWidget(2);
			AbsolutePanel topPart = (AbsolutePanel) eventBox.getWidget(0);
			topPart.getWidget(1).getElement().getStyle().setProperty("animation", "");
		}
			break;
		case 9: {
			explanationBox.setHTML(
					"Wenn Du die Einstellungen oder Kontaktinformationen suchst, findest Du sie indem Du nach dieser Erklärung nach unten scrollst.");
			EventBox eventBox = (EventBox) sampleDayView.getWidget(2);
			AbsolutePanel topPart = (AbsolutePanel) eventBox.getWidget(0);
			topPart.getWidget(1).getElement().getStyle().setProperty("animation", "");
		}
			break;
		case 10: {
			explanationBox.setHTML("Jetzt weißt Du Bescheid! Viel Spaß mit dem Vertretungsfilter.");
		}
			break;
		case 11: {
			Vertretungsalarm.getClientStorage().setItem("hasCompletedGuide", "yes");
			Location.reload();
		}
			break;
		}

	}

}
