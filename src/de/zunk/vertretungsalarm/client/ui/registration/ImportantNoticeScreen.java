package de.zunk.vertretungsalarm.client.ui.registration;

import com.google.gwt.user.client.Window;

import de.zunk.vertretungsalarm.client.ui.BackOption;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;

public class ImportantNoticeScreen extends Screen {

	private static final long serialVersionUID = 1L;

	Header header;
	ImportantNoticeView importantView;
	BottomBar bottom;

	public ImportantNoticeScreen() {

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		header = new Header("Beachte <b><br>Folgendes:", false, BackOption.PAGE_BACK);
		importantView = new ImportantNoticeView();
		bottom = new BottomBar();

		add(header);
		add(importantView);
		add(bottom);

		header.getElement().getStyle().setProperty("alignSelf", "stretch");
		importantView.getElement().getStyle().setProperty("alignSelf", "stretch");
		bottom.getElement().getStyle().setProperty("alignSelf", "stretch");

		resizeComponents();

	}

	private void resizeComponents() {
		importantView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}

}