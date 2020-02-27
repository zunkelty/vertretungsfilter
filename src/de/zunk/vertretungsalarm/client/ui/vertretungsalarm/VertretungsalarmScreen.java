package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.client.GreetingServiceAsync;
import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.client.ui.messagebox.ButtonLayoutOption;
import de.zunk.vertretungsalarm.client.ui.messagebox.CloseAction;
import de.zunk.vertretungsalarm.client.ui.messagebox.Message;
import de.zunk.vertretungsalarm.shared.DayInfo;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class VertretungsalarmScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	Header header;
	VertretungsplanView infoView;
	BottomBar bottom;

	public VertretungsalarmScreen() {

		// Timer timer = new Timer() {
		// @Override
		// public void run() {
		// Location.reload();
		// }
		// };
		// timer.schedule(5 * 60 * 1000);

		setPixelSize(Window.getClientWidth(), Window.getClientHeight());
		getElement().getStyle().setProperty("overflowX", "hidden");
		getElement().getStyle().setProperty("overflowY", "scroll");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("height", Window.getClientHeight() + "px");

		ArrayList<VertretungsEvent> userEvents = new ArrayList<VertretungsEvent>();
		ArrayList<DayInfo> dayInfos = new ArrayList<DayInfo>();

		header = new Header("Dein<br><p style=\"font-family: Ubuntu:700\"><b>Vertretungsplan</b>", false);

		bottom = new BottomBar();

		greetingService.getVertretungsplan(new AsyncCallback<Vertretungsplan>() {

			@Override
			public void onSuccess(Vertretungsplan vertretungsplan) {

				ArrayList<VertretungsEvent> vertretungs_events = new ArrayList<>();
				vertretungs_events = vertretungsplan.getVertretungsEvents();

				ArrayList<DayInfo> vertretungs_day_infos = new ArrayList<>();
				vertretungs_day_infos = vertretungsplan.getDayInfos();

				for (VertretungsEvent event : vertretungs_events) {
					if (event.getSchoolClasses().contains(Vertretungsalarm.getClientStorage().getItem("schoolClass"))) {
						userEvents.add(event);
					}
				}

				infoView = new VertretungsplanView(userEvents, vertretungs_day_infos);

				add(header);
				add(infoView);
				add(bottom);

				header.getElement().getStyle().setProperty("alignSelf", "stretch");
				infoView.getElement().getStyle().setProperty("alignSelf", "stretch");
				bottom.getElement().getStyle().setProperty("alignSelf", "stretch");

				resizeComponents();

			}

			@Override
			public void onFailure(Throwable caught) {
				Label reload = new Label("Neu laden");
				add(reload);

				reload.addClickHandler(new ClickHandler() {

					@Override
					public void onClick(ClickEvent event) {
						Location.reload();
					}
				});

				RootPanel.get()
						.add(new Message(
								"Der Vertretungsplan kann im Moment nicht geladen werden! Versuche es später erneut.",
								"Error: " + caught, ButtonLayoutOption.OK, CloseAction.CLOSE));
			}
		});

	}

	private void resizeComponents() {
		infoView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}

}