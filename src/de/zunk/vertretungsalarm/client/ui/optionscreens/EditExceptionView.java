package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;

public class EditExceptionView extends AbsolutePanel {

	private ExceptionView exceptionView;

	public EditExceptionView(ExceptionSettingsType type, boolean isAddState) {

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				RootPanel.get().remove(EditExceptionView.this.asWidget());
				RootPanel.get().add(new EditExceptionView(type, true));
			}
		});

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-end");
		getElement().getStyle().setProperty("background", "rgba(0, 0, 0, .45)");

		setSize(Window.getClientWidth() + "px", Window.getClientHeight() + "px");

		getElement().getStyle().setProperty("animation", "fade-in 0.2s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");

		exceptionView = new ExceptionView(type, isAddState);

		add(exceptionView);

		exceptionView.getElement().getStyle().setProperty("animation",
				" slide-in-bottom 0.3s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");

		addDomHandler(new MouseDownHandler() {

			@Override
			public void onMouseDown(MouseDownEvent event) {
				if (event.getClientY() < Window.getClientHeight() - exceptionView.getOffsetHeight()) {
					getElement().getStyle().setProperty("animation",
							"fade-out 0.3s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");
					exceptionView.getElement().getStyle().setProperty("animation",
							"slide-out-bottom 0.2s cubic-bezier(0.250, 0.460, 0.450, 0.940) both");

					// Short delay to wait for animations to finish
					Timer timer = new Timer() {
						@Override
						public void run() {
							for (int i = 0; i < RootPanel.get().getWidgetCount(); i++) {
								if (RootPanel.get().getWidget(i).getClass() == EditExceptionView.class) {
									Window.alert("Removed " + i);
									RootPanel.get().remove(i);
								}
								RootPanel.get().remove(EditExceptionView.this.asWidget());
							}

						}
					};
					timer.schedule(500);
				}

			}
		}, MouseDownEvent.getType());

	}

}
