package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class LoadingView extends AbsolutePanel {

	DateTimeFormat dtf;

	DayView dV;

	Button noEventsImage;
	Label noEventsLabel;

	Label timeLabel;

	public LoadingView() {

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				RootPanel.get().remove(LoadingView.this.asWidget());
				RootPanel.get().add(new LoadingView());
			}
		});

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#F3F4F8");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("marginBottom", "20px");
		getElement().getStyle().setProperty("order", "1");

		AbsolutePanel loading = new AbsolutePanel();
		loading.getElement().getStyle().setProperty("margin", "50px 25px 0px 25px");

		Label progress = new Label("Wird geladen");
		progress.getElement().getStyle().setProperty("textAlign", "center");
		progress.getElement().getStyle().setProperty("font", "400 17px Ubuntu");
		progress.getElement().getStyle().setProperty("paddingBottom", "10px");
		loading.add(progress);

		Timer t = new Timer() {

			@Override
			public void run() {
				switch (progress.getText()) {
				case "Wird geladen":
					progress.setText("Wird geladen .");
					break;
				case "Wird geladen .":
					progress.setText("Wird geladen . .");
					break;
				case "Wird geladen . .":
					progress.setText("Wird geladen . . .");
					break;
				case "Wird geladen . . .":
					progress.setText("Wird geladen");
					break;
				}

			}
		};

		t.scheduleRepeating(1 * 1000);

		add(loading);

	}

}
