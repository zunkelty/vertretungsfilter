package de.zunk.vertretungsalarm.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;

public class LockScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	BottomBar bottom;

	AbsolutePanel lockView;
	VertretungsalarmBox messageBox;
	VertretungsalarmBox infoBox;

	public LockScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("Wilkommen zum<b><br>Vertretungsfilter", false);

		messageBox = new VertretungsalarmBox(
				"<b>Dies ist eine private W***REMOVED***ite, die nur für freigeschaltete Nutzer zugänglich ist. <br><br>Bitte melde dich bei Sönke um freigeschaltet zu werden.");
		messageBox.getElement().getStyle().setProperty("padding", "0px 15px");

		infoBox = new VertretungsalarmBox(
				"Gibt es Probleme, hast du Fragen oder Wünsche?<br><br> Melde dich bei dem W***REMOVED***itebetreiber unter <br><b>zunkelty@gmx.de");
		infoBox.getElement().getStyle().setProperty("padding", "0px 15px");

		lockView = new AbsolutePanel();
		lockView.getElement().getStyle().setProperty("marginBottom", "20px");
		lockView.getElement().getStyle().setProperty("flexShrink", "0");
		lockView.getElement().getStyle().setProperty("padding", " 30px 25px 12px 25px");
		lockView.getElement().getStyle().setProperty("paddingRight", "25px");
		lockView.add(messageBox);
		lockView.add(infoBox);

		bottom = new BottomBar();

		add(header);
		add(lockView);
		add(bottom);

		resizeComponents();

	}

	public void resizeComponents() {
		lockView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}
}