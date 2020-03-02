package de.zunk.vertretungsalarm.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmPasswordField;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmTextField;
import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;

public class LockView extends AbsolutePanel {

	AbsolutePanel lockView;
	VertretungsalarmBox messageBox;
	VertretungsalarmBox infoBox;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	private VertretungsalarmTextField username;
	private VertretungsalarmPasswordField password;
	private VertretungsalarmButton submit;

	public LockView() {

		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("flexShrink", "0");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("padding", " 30px 25px 12px 25px");
		getElement().getStyle().setProperty("paddingRight", "25px");

		messageBox = new VertretungsalarmBox(
				"<b>Dies ist eine W***REMOVED***ite, die nur für Schüler und Schülerinnen der Elsa-Brändström-Schule, deren Eltern und für Lehrer und Lehrerinnen zugänglich ist. <br><br>Bitte melde Dich mit den Logindaten des Schulvertretungsplans an.");
		messageBox.getElement().getStyle().setProperty("padding", "0px 15px");

		infoBox = new VertretungsalarmBox(
				"Weißt Du die Anmeldedaten nicht? Dann melde Dich unter<br><br><b>kontakt@vertretungsfilter.de");
		infoBox.getElement().getStyle().setProperty("padding", "0px 15px");
		infoBox.getElement().getStyle().setProperty("marginBottom", "25px");

		username = new VertretungsalarmTextField("Benutzername");
		password = new VertretungsalarmPasswordField("Passwort");
		submit = new VertretungsalarmButton("Anmelden");

		username.getField().addKeyUpHandler(e -> checkInput());
		password.getField().addKeyUpHandler(e -> checkInput());

		submit.getElement().getStyle().setProperty("alignSelf", "flex-end");
		submit.setEnabled(false);
		submit.getElement().getStyle().setProperty("background", "#cccccc");

		add(messageBox);
		add(infoBox);
		add(username);
		add(password);
		add(submit);

		submit.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Message msg = new Message("Wird verarbeitet",
						"Der Vertretungsfilter überprüft gerade, ob deine Eingaben stimmen", ButtonLayoutOption.NONE,
						CloseAction.NONE);

				RootPanel.get().add(msg);

				greetingService.validateLogin(username.getField().getText(), password.getField().getText(),
						new AsyncCallback<Boolean>() {

							@Override
							public void onSuccess(Boolean success) {
								RootPanel.get().remove(msg);
								if (success) {
									Vertretungsalarm.getClientStorage().setItem("username",
											username.getField().getText());
									Vertretungsalarm.getClientStorage().setItem("password",
											password.getField().getText());
									Location.reload();
								} else {
									RootPanel.get().add(new Message("Anmeldung nicht erfolgreich",
											"Die eingegebenen Logindaten sind nicht korrekt. Bitte versuche es erneut.",
											ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.CLOSE));
								}

							}

							@Override
							public void onFailure(Throwable caught) {
								RootPanel.get().remove(msg);
								RootPanel.get().add(new Message("Fehler",
										"Die Logindaten konnten nicht überprüft werden. Bitte versuche es später erneut."
												+ caught,
										ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.CLOSE));
							}
						});

			}
		});
	}

	public void checkInput() {
		if (!username.getField().getText().trim().isEmpty() && !password.getField().getText().trim().isEmpty()) {
			submit.setEnabled(true);
			submit.getElement().getStyle().setProperty("background", "#F0C267");
		} else {
			submit.setEnabled(false);
			submit.getElement().getStyle().setProperty("background", "#cccccc");
		}
	}

}
