package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.ArrayList;
import java.util.Arrays;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.GreetingService;
import de.zunk.vertretungsalarm.client.GreetingServiceAsync;
import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.BackOption;
import de.zunk.vertretungsalarm.client.ui.BottomBar;
import de.zunk.vertretungsalarm.client.ui.Header;
import de.zunk.vertretungsalarm.client.ui.Screen;
import de.zunk.vertretungsalarm.shared.DayInfo;
import de.zunk.vertretungsalarm.shared.VertretungsEvent;
import de.zunk.vertretungsalarm.shared.Vertretungsplan;

public class VertretungsalarmScreen extends Screen {

	private static final long serialVersionUID = 1L;

	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	Header header;
	VertretungsplanView infoView;
	LoadingView loadingView;
	BottomBar bottom;

	public VertretungsalarmScreen() {

		// Timer timer = new Timer() {
		// @Override
		// public void run() {
		// Location.reload();
		// }
		// };
		// timer.schedule(5 * 60 * 1000);

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				RootPanel.get().remove(VertretungsalarmScreen.this.asWidget());
				RootPanel.get().add(new VertretungsalarmScreen());
			}
		});

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

		header = new Header("Dein<br><p style=\"font-family: Ubuntu:700\"><b>Vertretungsplan</b>", false,
				BackOption.PAGE_BACK);

		bottom = new BottomBar();

		loadingView = new LoadingView();

		infoView = new VertretungsplanView();

		add(header);
		add(loadingView);
		add(bottom);

		header.getElement().getStyle().setProperty("alignSelf", "stretch");
		header.getElement().getStyle().setProperty("order", "0");
		loadingView.getElement().getStyle().setProperty("alignSelf", "stretch");
		bottom.getElement().getStyle().setProperty("alignSelf", "stretch");
		bottom.getElement().getStyle().setProperty("order", "2");

		resizeComponents();

		Timer t = new Timer() {

			@Override
			public void run() {
				greetingService.getVertretungsplan(new AsyncCallback<Vertretungsplan>() {

					@Override
					public void onSuccess(Vertretungsplan vertretungsplan) {

						ArrayList<VertretungsEvent> vertretungs_events = new ArrayList<VertretungsEvent>();
						vertretungs_events = vertretungsplan.getVertretungsEvents();

						ArrayList<DayInfo> vertretungs_day_infos = new ArrayList<DayInfo>();
						vertretungs_day_infos = vertretungsplan.getDayInfos();

						userEvents.clear();

						String[] singleSubjectExceptions = Vertretungsalarm.getClientStorage()
								.getItem("subjectExceptions").trim().split(",");

						String[] singleTeacherExceptions = Vertretungsalarm.getClientStorage()
								.getItem("teacherExceptions").trim().split(",");

						for (VertretungsEvent event : vertretungs_events) {
							if (event.getSchoolClasses()
									.contains(Vertretungsalarm.getClientStorage().getItem("schoolClass"))
									&& !Arrays.asList(singleSubjectExceptions).contains(event.getPlannedSubject())
									&& !Arrays.asList(singleTeacherExceptions).contains(event.getPlannedTeacher())) {
								userEvents.add(event);
							}
						}

						infoView = new VertretungsplanView(userEvents, vertretungs_day_infos,
								vertretungsplan.getTime());
						infoView.getElement().getStyle().setProperty("alignSelf", "stretch");
						infoView.getElement().getStyle().setProperty("order", "1");

						for (int j = 0; j < getWidgetCount(); j++) {
							if (getWidget(j).getClass() == VertretungsplanView.class) {
								remove(getWidget(j));
							}
						}

						add(infoView);
						resizeComponents();

					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}
				});
			}
		};

		greetingService.getVertretungsplan(new AsyncCallback<Vertretungsplan>() {

			@Override
			public void onSuccess(Vertretungsplan vertretungsplan) {
				remove(loadingView);

				ArrayList<VertretungsEvent> vertretungs_events = new ArrayList<>();
				vertretungs_events = vertretungsplan.getVertretungsEvents();

				ArrayList<DayInfo> vertretungs_day_infos = new ArrayList<>();
				vertretungs_day_infos = vertretungsplan.getDayInfos();

				String[] singleSubjectExceptions = Vertretungsalarm.getClientStorage().getItem("subjectExceptions")
						.trim().split(",");

				String[] singleTeacherExceptions = Vertretungsalarm.getClientStorage().getItem("teacherExceptions")
						.trim().split(",");

				for (VertretungsEvent event : vertretungs_events) {
					if (event.getSchoolClasses().contains(Vertretungsalarm.getClientStorage().getItem("schoolClass"))
							&& !Arrays.asList(singleSubjectExceptions).contains(event.getPlannedSubject())
							&& !Arrays.asList(singleTeacherExceptions).contains(event.getPlannedTeacher())) {
						userEvents.add(event);
					}
				}

				infoView = new VertretungsplanView(userEvents, vertretungs_day_infos, vertretungsplan.getTime());
				infoView.getElement().getStyle().setProperty("alignSelf", "stretch");

				add(header);
				add(infoView);
				add(bottom);

				resizeComponents();

			}

			@Override
			public void onFailure(Throwable caught) {
			}
		});

		t.scheduleRepeating(1 * 30 * 1000);

	}

	private void resizeComponents() {
		infoView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
		loadingView.getElement().getStyle().setProperty("minHeight",
				Window.getClientHeight() - header.getOffsetHeight() + "px");
	}

}