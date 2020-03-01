package de.zunk.vertretungsalarm.client.ui.registration;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmTextField;
import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;

public class RegistrationView extends AbsolutePanel {

	VertretungsalarmBox desc;
	VertretungsalarmTextField textField;
	VertretungsalarmButton submit;

	public RegistrationView() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("flexShrink", "0");
		getElement().getStyle().setProperty("padding", "20px 25px 20px 25px");

		desc = new VertretungsalarmBox(
				"Wähle deine Klasse aus. Dann kriegst du nur zu sehen, was dich wirklich interessiert.");
		add(desc);

		textField = new VertretungsalarmTextField("Gib hier deine Klasse ein");
		add(textField);

		submit = new VertretungsalarmButton("Fertig");
		submit.getElement().getStyle().setProperty("alignSelf", "flex-end");
		submit.disable();
		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Vertretungsalarm.getClientStorage().removeItem("schoolClass");
				Vertretungsalarm.getClientStorage().setItem("schoolClass", textField.getText().toUpperCase());
				if (Vertretungsalarm.getClientStorage().getItem("schoolClass").contains("12")
						|| Vertretungsalarm.getClientStorage().getItem("schoolClass").contains("13")) {
					RootPanel.get().add(new Message("Einschränkungen für deinen Jahrgang",
							"Bitte beachte, dass der Vertretungsfilter für die Klassenstufen 5-11 optimiert ist, sodass nicht nach deinen Kursen gefiltert werden kann. Dir werden vom Vertretungsfilter alle Informationen für deinen Jahrgang angezeigt.",
							ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.RELOAD));
				} else {
					Location.reload();
				}

			}
		});
		add(submit);

		textField.getField().addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if (isSchoolClassNameOrSchoolYear(textField.getText())) {
					submit.enable();
				} else {
					submit.disable();
				}
			}

		});

	}

	public static boolean isSchoolClassNameOrSchoolYear(String possibleSchoolClassName) {
		try {
			if (!possibleSchoolClassName.matches("[a-zA-Z0-9]*")) {
				return false;
			}

			String[] split = possibleSchoolClassName.split("");
			List<String> parts = new ArrayList<String>();
			for (int i = 0; i < split.length; i++) {
				if (!split[i].trim().isEmpty()) {
					if (split[i].matches("[0-9]") && split[i + 1].matches("[0-9]")) {
						parts.add(split[i] + split[i + 1]);
						i++;
					} else {
						parts.add(split[i]);
					}
				}
			}

			if (parts.size() == 2 && parts.get(0).matches("[0-9]+") && parts.get(0).length() <= 2
					&& Integer.parseInt(parts.get(0)) <= 11 && parts.get(1).matches("[a-zA-Z]+")
					&& parts.get(1).length() == 1) {
				return true;
			}

			if ((possibleSchoolClassName.contains("12") || possibleSchoolClassName.contains("13"))
					&& possibleSchoolClassName.length() == 2) {
				return true;
			}

			return false;
		} catch (Exception e) {
			return false;
		}

	}

}
