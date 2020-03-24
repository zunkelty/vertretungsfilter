package de.zunk.vertretungsalarm.client.ui.guide;

import com.google.gwt.user.client.Window;

import de.zunk.vertretungsalarm.client.ui.BackOption;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;

public class GuideScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	GuideView guideView;
	BottomBar bottom;

	public GuideScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "hidden");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("<b>Dein<br>Vertretungsplan", false, BackOption.PAGE_BACK);
		guideView = new GuideView();
		bottom = new BottomBar();

		add(header);
		add(guideView);
		add(bottom);

		header.getElement().getStyle().setProperty("alignSelf", "stretch");
		guideView.getElement().getStyle().setProperty("alignSelf", "stretch");
		bottom.getElement().getStyle().setProperty("alignSelf", "stretch");

		resizeComponents();

	}

	private void resizeComponents() {
		guideView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}

}