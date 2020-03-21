package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.TextBox;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.optionscreens.ExceptionSettingsType;

public class ExceptionBox extends AbsolutePanel {

	private HTML title;

	String exception;

	private TextBox input;

	public ExceptionBox(String exception, ExceptionSettingsType type, boolean isNewBox) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("marginBottom", "10px");

		getElement().getStyle().setProperty("borderRadius", "15px");

		getElement().getStyle().setProperty("background", "#FDFCFE");

		getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203,203,203,0.4)");

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
			input.getElement().setPropertyString("placeholder",
					type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "Gib ein neues Fach ein"
							: "Gib einen neuen Lehrer ein");
			add(input);
			add(submit);

			submit.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (isValidException(input.getText().trim().toUpperCase())) {
						String exceptions = Vertretungsalarm.getClientStorage()
								.getItem(type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions"
										: "teacherExceptions");
						Vertretungsalarm.getClientStorage()
								.removeItem(type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions"
										: "teacherExceptions");
						Vertretungsalarm.getClientStorage()
								.setItem(
										type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions"
												: "teacherExceptions",
										exceptions + input.getText().toUpperCase() + ", ");

						remove(input);
						remove(submit);

						setStandardLayout(input.getText().toUpperCase(), type);
					}
				}

			});
		} else {
			setStandardLayout(exception, type);
		}

	}

	private void setStandardLayout(String exception, ExceptionSettingsType type) {
		title = new HTML(exception);
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

				try {
					String exceptions = Vertretungsalarm.getClientStorage()
							.getItem(type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions"
									: "teacherExceptions");

					Vertretungsalarm.getClientStorage()
							.removeItem(type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions"
									: "teacherExceptions");

					exceptions = exceptions.replace(exception + ",", "");

					Vertretungsalarm.getClientStorage().setItem(
							type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions" : "teacherExceptions",
							exceptions);

					removeFromParent();
				} catch (Exception e) {

				}

			}

		});

		add(delete);
	}

	public void focusTextBox() {
		input.setFocus(true);
	}

	private boolean isValidException(String possibleException) {
		return possibleException == "" ? false : possibleException.matches("[A-Z]+");
	}

}
