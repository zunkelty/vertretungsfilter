package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.user.client.Window;

import de.zunk.vertretungsalarm.client.ui.BackOption;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;

public class ExceptionScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	BottomBar bottom;

	VertretungsalarmBox changeClassBox;
	VertretungsalarmBox resetAllBox;

	VertretungsalarmButton changeClass;
	VertretungsalarmButton resetAll;

	ExceptionView exceptionView;

	public ExceptionScreen(ExceptionSettingsType type) {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header(
				"<b>" + (type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "Fächer" : "Lehrer") + "<br>ausblenden<b>",
				true, BackOption.SUBPAGE_BACK);

		exceptionView = new ExceptionView(type);

		bottom = new BottomBar();

		add(header);
		add(exceptionView);
		add(bottom);

		resizeComponents();

	}

	public void resizeComponents() {
		exceptionView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}
}