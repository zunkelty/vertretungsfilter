package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;

public class LegalScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	BottomBar bottom;

	AbsolutePanel legalView;
	VertretungsalarmBox nutzungsbedingungenBox;
	VertretungsalarmBox nameBox;
	VertretungsalarmButton goToContact;
	VertretungsalarmBox contactBox;

	public LegalScreen() {

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				RootPanel.get().remove(LegalScreen.this.asWidget());
				RootPanel.get().add(new LegalScreen());
			}
		});

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("<b>Rechtliches<b>", true);

		nutzungsbedingungenBox = new VertretungsalarmBox(
				"Diese W***REMOVED***ite / WebApp versteht sich als privat und nicht geschäftlich, da Sie nur durch Freischaltung zugänglich ist und keine Einnahmen generiert. Somit benötigt sie kein Impressum oder eine Datenschutzerklärung.<br><br>Auf dieser W***REMOVED***ite / WebApp werden Analysedaten durch Google Analytics erhoben und von Google gespeichert. Dieses Tool findet man auf vielen W***REMOVED***ites und es ermöglicht dem W***REMOVED***itebetreiber die Nutzeranzahl und weitere Analysedaten der W***REMOVED***ite zu verfolgen.");
		nutzungsbedingungenBox.getElement().getStyle().setProperty("padding", "0px 15px");

		nameBox = new VertretungsalarmBox(
				"<b>Hast du Fragen, Wünsche, Bedenken oder Verbesserungsvorschläge?<br>Dann melde dich bei Sönke!</b>");
		nameBox.getElement().getStyle().setProperty("padding", "0px 15px");

		contactBox = new VertretungsalarmBox(
				"<b>Wie du den W***REMOVED***ite / WebApp-Betreiber erreichen kannst siehst du auf der Kontaktseite");
		contactBox.getElement().getStyle().setProperty("padding", "0px 15px");

		goToContact = new VertretungsalarmButton("Zur Kontaktseite");
		goToContact.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Location.replace(Location.createUrlBuilder().setParameter("page", "contact").buildString());
			}
		});
		contactBox.add(goToContact);

		legalView = new AbsolutePanel();
		legalView.getElement().getStyle().setProperty("marginBottom", "20px");
		legalView.getElement().getStyle().setProperty("flexShrink", "0");
		legalView.getElement().getStyle().setProperty("padding", " 30px 25px 12px 25px");
		legalView.getElement().getStyle().setProperty("paddingRight", "25px");
		legalView.add(nutzungsbedingungenBox);
		legalView.add(nameBox);
		legalView.add(contactBox);

		bottom = new BottomBar();

		add(header);
		add(legalView);
		add(bottom);

		resizeComponents();

	}

	public void resizeComponents() {
		legalView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}
}