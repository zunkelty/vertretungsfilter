package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.ui.messagebox.Message;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class Event extends Label {

	VertretungsEvent vE;

	public Event(VertretungsEvent vE) {

		this.vE = vE;

		getElement().getStyle().setProperty("paddingBottom", "10px");

		getElement().getStyle().setProperty("borderBottom", "1px dashed #000000");

		getElement().getStyle().setProperty("fontSize", "2vh");
		getElement().getStyle().setProperty("textAlign", "center");
		getElement().getStyle().setProperty("paddingTop", "10px");

		EventTranslator translator = new EventTranslator();
		setText(translator.translateEvent(vE));

		addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				// UrlBuilder builder = Location.createUrlBuilder();
				// builder.setParameter("page", "fullscreenEventView");
				//
				// builder.setParameter("lessons", vE.getLessonsAsString());
				// builder.setParameter("plannedTeacher", vE.getPlannedTeacher());
				// builder.setParameter("actualTeacher", vE.getActualTeacher());
				// builder.setParameter("room", vE.getRoom());
				// builder.setParameter("plannedSubject", vE.getPlannedSubject());
				// builder.setParameter("actualSubject", vE.getActualSubject());
				// builder.setParameter("date", vE.getDateAsText());

				// Location.replace(builder.buildString());

				RootPanel.get()
						.add(new Message(vE.getLessonsAsString() + " Stunde/-n am " + vE.getDateAsText() + "\n\n",
								"Fach: " + vE.getActualSubject() + "\nLehrer: " + vE.getActualTeacher() + "\nRaum: "
										+ vE.getRoom() + "\n\nGeplantes Fach: " + vE.getPlannedSubject()
										+ "\nGeplanter Lehrer " + vE.getPlannedTeacher(),
								false), 0, 0);

			}
		});

	}

}
