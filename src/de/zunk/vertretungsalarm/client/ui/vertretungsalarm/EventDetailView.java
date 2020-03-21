package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class EventDetailView extends AbsolutePanel {

	public EventDetailView(VertretungsEvent event) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-end");
		getElement().getStyle().setProperty("background", "rgba(243, 244, 248, .6)");

		setSize(Window.getClientWidth() + "px", Window.getClientHeight() + "px");

		getElement().getStyle().setProperty("background", "rgba(0, 0, 0, .45)");

		getElement().getStyle().setProperty("animation", "fade-in 0.2s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");

		DetailView detailView = new DetailView(event);

		addDomHandler(e -> {
			if (e.getClientY() < Window.getClientHeight() - detailView.getOffsetHeight()) {

				getElement().getStyle().setProperty("animation",
						"fade-out 0.3s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");
				detailView.getElement().getStyle().setProperty("animation",
						"slide-out-bottom 0.2s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");

				// Short delay to wait for animations to finish
				Timer timer = new Timer() {
					@Override
					public void run() {
						RootPanel.get().remove(EventDetailView.this.asWidget());
					}
				};
				timer.schedule(500);
			}
		}, MouseDownEvent.getType());

		add(detailView);

		detailView.getElement().getStyle().setProperty("animation",
				" slide-in-bottom 0.3s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");
	}
}
