package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;

public class ContactScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	BottomBar bottom;

	AbsolutePanel contactView;
	VertretungsalarmBox infoBox;
	VertretungsalarmBox schoolInfo;
	VertretungsalarmBox mailBox;
	VertretungsalarmButton sendMailButton;

	public ContactScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("<b>Kontakt<b>", true);

		infoBox = new VertretungsalarmBox(
				"<b>Hast du Fragen, Wünsche, Bedenken oder Verbesserungsvorschläge?<br>Dann melde dich bei Sönke!");
		infoBox.getElement().getStyle().setProperty("padding", "0px 15px");

		schoolInfo = new VertretungsalarmBox("In der Schule:<br><br>Klasse: 11B<br>Klassenraum: N2");
		schoolInfo.getElement().getStyle().setProperty("padding", "0px 15px");

		mailBox = new VertretungsalarmBox("<b>Per Mail:</b><br><br>zunkelty@gmx.de");
		mailBox.getElement().getStyle().setProperty("padding", "0px 15px");

		sendMailButton = new VertretungsalarmButton("E-Mail schreiben");
		sendMailButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Window.Location.assign(
						" mailto:zunkelty@gmx.de?subject=Fragen,%20Wünsche,%20Bedenken%20oder%20Verbesserungsvorschläge%20zum%20Vertretungsfilter&body=%0D%0A%0D%0AGeräteinformationen%20für%20den%20Entwickler:%0D%0ABildschirm:%20"
								+ Window.getClientHeight() + "%20x%20" + Window.getClientWidth() + "%0D%0A"
								+ Navigator.getPlatform() + "%0D%0A" + Navigator.getUserAgent() + "%0D%0A"
								+ Vertretungsalarm.getClientStorage().getItem("schoolClass"));
			}
		});
		mailBox.add(sendMailButton);

		contactView = new AbsolutePanel();
		contactView.getElement().getStyle().setProperty("marginBottom", "20px");
		contactView.getElement().getStyle().setProperty("flexShrink", "0");
		contactView.getElement().getStyle().setProperty("padding", " 30px 25px 12px 25px");
		contactView.getElement().getStyle().setProperty("paddingRight", "25px");
		contactView.add(infoBox);
		contactView.add(schoolInfo);
		contactView.add(mailBox);

		bottom = new BottomBar();

		add(header);
		add(contactView);
		add(bottom);

		resizeComponents();

	}

	public void resizeComponents() {
		contactView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}
}