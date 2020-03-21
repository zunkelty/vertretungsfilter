package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.storage.client.Storage;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;

public class ExceptionView extends AbsolutePanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VertretungsalarmBox nothingToShowBox;

	private ExceptionBox newException;

	public ExceptionView(ExceptionSettingsType type) {

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				newException.focusTextBox();
			}
		});

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");

		getElement().getStyle().setProperty("marginTop", "15px");
		getElement().getStyle().setProperty("padding", "8px 19px 19px 19px");

		VertretungsalarmBox note = new VertretungsalarmBox(
				"<b>Achte darauf, dass Du die gleiche Schreibweise, wie auf dem Vertretungsplan verwendest.</b><br>z.B. EN für Englisch oder WO für Wolter");
		note.getElement().getStyle().setProperty("order", "-2");
		add(note);

		String exceptions = Vertretungsalarm.getClientStorage()
				.getItem(type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions" : "teacherExceptions");

		String[] singleExceptions = exceptions.trim().split(",");

		if (exceptions.trim() != "") {
			for (String exception : singleExceptions) {

				ExceptionBox exceptionBox = new ExceptionBox(exception, type, false);

				remove(exceptionBox);

				add(exceptionBox);
			}

		} else

		{

			nothingToShowBox = new VertretungsalarmBox("Du hast keine "
					+ (type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "Fächer" : "Lehrer") + " hinzugefügt");
			nothingToShowBox.getElement().getStyle().setProperty("boxShadow",
					"0px 3px 6px 0px rgba(203, 203, 203, 0.9)");

			add(nothingToShowBox);
		}

		Storage.addStorageEventHandler(e -> {
			if (Vertretungsalarm.getClientStorage()
					.getItem(
							type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions" : "teacherExceptions")
					.trim() == "") {

				nothingToShowBox = new VertretungsalarmBox("Du hast keine "
						+ (type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "Fächer" : "Lehrer") + " hinzugefügt");
				nothingToShowBox.getElement().getStyle().setProperty("boxShadow",
						"0px 3px 6px 0px rgba(203, 203, 203, 0.9)");

				add(nothingToShowBox);

			}

		});

		VertretungsalarmButton add = new VertretungsalarmButton("Hinzufügen");
		add.getElement().getStyle().setProperty("order", "9999999999999999999");
		add.getElement().getStyle().setProperty("alignSelf", "flex-end");
		add.getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203, 203, 203, 0.9)");
		add.getElement().getStyle().setProperty("background", "icons/add-24px.svg");

		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				newException = new ExceptionBox("", type, true);
				newException.getElement().getStyle().setProperty("order", "-1");
				add(newException);

				if (exceptions.trim() == "") {
					remove(nothingToShowBox);
				}

			}
		});

		add(add);

	}

}
