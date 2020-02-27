package de.zunk.vertretungsalarm.client.ui.registration;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;
import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;

public class ImportantNoticeView extends AbsolutePanel {

	VertretungsalarmBox importantNotice;
	VertretungsalarmButton accept;

	public ImportantNoticeView() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("flexShrink", "0");
		getElement().getStyle().setProperty("padding", "20px 25px 20px 25px");

		importantNotice = new VertretungsalarmBox(
				"<b>Durch Klicken auf \"Nutzungsbedingungen akzeptieren\" erklärst Du dich mit Folgendem einverstanden:</b><br><br>Diese W***REMOVED***ite / WebApp versteht sich als privat und nicht geschäftlich, da Sie nur durch Freischaltung zugänglich ist und keine Einnahmen generiert. Somi	t benötigt sie kein Impressum oder eine Datenschutzerklärung. Trotzdem findest Du unter \"Rechtliches\" Kontaktdaten vom W***REMOVED***ite-/WebApp-Betreiber.<br><br>Auf dieser W***REMOVED***ite / WebApp werden Analysedaten durch Google Analytics erhoben und von Google gespeichert. Dieses Tool findet man auf vielen W***REMOVED***ites und es ermöglicht dem W***REMOVED***itebetreiber die Nutzeranzahl und weitere Analysedaten der W***REMOVED***ite zu verfolgen. <br><br><b>Wenn Du Fragen oder Bedenken zum Vertretungsfilter hast, sprich gerne jederzeit Sönke (11B) an.");
		add(importantNotice);

		accept = new VertretungsalarmButton("Nutzungsbedingungen akzeptieren");
		accept.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Vertretungsalarm.getClientStorage().setItem("hasAcceptedImportantNotice", "yes");
				RootPanel.get()
						.add(new Message("Herzlich Willkommen zum Vertretungsfilter!",
								"Der Vertretungsfilter zeigt dir hier ab sofort immer alles, was die "
										+ Vertretungsalarm.getClientStorage().getItem("schoolClass") + " betrifft.",
								ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.RELOAD));
			}
		});
		add(accept);

	}

}
