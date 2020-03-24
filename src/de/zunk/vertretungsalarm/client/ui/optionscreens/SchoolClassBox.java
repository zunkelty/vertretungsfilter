package de.zunk.vertretungsalarm.client.ui.optionscreens;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;

public class SchoolClassBox extends AbsolutePanel {

	private HTML title;

	private TextBox input;

	public SchoolClassBox(String schoolClass, boolean isNewBox) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("marginBottom", "10px");

		getElement().getStyle().setProperty("borderRadius", "15px");

		getElement().getStyle().setProperty("background", "#FDFCFE");

		getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203, 203, 203, 0.9)");
		if (isNewBox) {

			Image submit = new Image("icons/done-24px.svg");
			submit.getElement().getStyle().setProperty("marginRight", "20px");

			input = new TextBox();
			input.getElement().getStyle().setProperty("font", "300 17px Ubuntu");
			input.getElement().getStyle().setProperty("color", "#3E4158");
			input.getElement().getStyle().setProperty("padding", "15px 0px 0px 15px");
			input.getElement().getStyle().setProperty("marginBottom", "10px");
			input.getElement().getStyle().setProperty("border", "0");
			input.getElement().getStyle().setProperty("order", "-1");
			input.getElement().getStyle().setProperty("width", "100%");
			input.getElement().setPropertyString("placeholder", "Gib eine neue Schulklasse ein");
			add(input);
			add(submit);

			submit.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {

					String input_s = input.getText().trim().toUpperCase();

					if (isValidSchoolClass(input_s)) {

						ArrayList<String> userSchoolClasses = new ArrayList<String>();
						String userSchoolClass = Vertretungsalarm.getClientStorage().getItem("schoolClass");

						if (userSchoolClass.indexOf(",") <= 0) {
							userSchoolClasses.add(userSchoolClass.trim());
						} else {
							String[] parts = userSchoolClass.split(",");
							for (String schoolClass : parts) {
								userSchoolClasses.add(schoolClass);
							}
						}

						if (userSchoolClasses.size() >= 1) {
							userSchoolClass += ", " + input_s;
						}

						Vertretungsalarm.getClientStorage().setItem("schoolClass", userSchoolClass);

						remove(input);
						remove(submit);

						setStandardLayout(input.getText().toUpperCase());
					} else {
						RootPanel.get()
								.add(new Message("Das ist keine Schulklasse",
										(input_s.trim() != "" ? input_s : "Deine Eingabe")
												+ " kann nicht als Schulklasse verwendet werden.",
										ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.CLOSE));
					}
				}

			});
		} else {
			setStandardLayout(schoolClass);
		}

	}

	private void setStandardLayout(String schoolClass) {
		getElement().getStyle().setProperty("order", "0");

		title = new HTML(schoolClass);
		title.getElement().getStyle().setProperty("font", "500 17px Ubuntu");
		title.getElement().getStyle().setProperty("color", "#3E4158");
		title.getElement().getStyle().setProperty("padding", "15px 10px 0px 15px");
		title.getElement().getStyle().setProperty("marginBottom", "10px");
		title.getElement().getStyle().setProperty("width", "100%");

		add(title);

		Image delete = new Image("icons/delete_forever-24px.svg");
		delete.getElement().getStyle().setProperty("marginRight", "20px");

		delete.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				ArrayList<String> userSchoolClasses = new ArrayList<String>();
				String userSchoolClass = Vertretungsalarm.getClientStorage().getItem("schoolClass");

				if (userSchoolClass.indexOf(",") <= 0) {
					userSchoolClasses.add(userSchoolClass.trim());
				} else {
					String[] parts = userSchoolClass.split(",");
					for (String schoolClass : parts) {
						userSchoolClasses.add(schoolClass);
					}
				}

				if (userSchoolClasses.size() > 1) {

					userSchoolClass = "";
					for (String s : userSchoolClasses) {
						if (s != schoolClass) {
							userSchoolClass += (userSchoolClass != "" ? ", " : "") + s.trim();
						}
					}

					Vertretungsalarm.getClientStorage().setItem("schoolClass", userSchoolClass);
					removeFromParent();
				} else {
					RootPanel.get()
							.add(new Message("Du kannst nicht alle Klassen entfernen",
									"Du musst immer mindestens eine Klasse gespeichert haben",
									ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.CLOSE));
				}

			}

		});

		add(delete);
	}

	public void focusTextBox() {
		input.setFocus(true);
	}

	private boolean isValidSchoolClass(String possibleSchoolClassName) {
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
