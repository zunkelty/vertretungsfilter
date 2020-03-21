package de.zunk.vertretungsalarm.client.ui.optionscreens;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmBox;
import de.zunk.vertretungsalarm.client.ui.VertretungsalarmButton;
import de.zunk.vertretungsalarm.client.ui.vertretungsalarm.ExceptionBox;

public class ExceptionView extends AbsolutePanel {

	private VertretungsalarmBox nothingToShowBox;

	private ExceptionBox newException;

	public ExceptionView(ExceptionSettingsType type, boolean isAddState) {

		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				newException.focusTextBox();
			}
		});

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("background", "#F0C267");

		getElement().getStyle().setProperty("padding", "8px 19px 19px 19px");

		getElement().getStyle().setProperty("borderRadius", "35px 35px 0px 0px");

		Image icon = new Image("icons/expand_more-24px.svg");
		icon.getElement().getStyle().setProperty("transform", "scale(1.3)");
		icon.getElement().getStyle().setProperty("paddingBottom", "5px");
		add(icon);

		String exceptions = Vertretungsalarm.getClientStorage()
				.getItem(type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "subjectExceptions" : "teacherExceptions");

		String[] singleExceptions = exceptions.trim().split(",");

		if (exceptions.trim() != "") {
			for (String exception : singleExceptions) {

				ExceptionBox exceptionBox = new ExceptionBox(exception, type, false);

				remove(exceptionBox);

				add(exceptionBox);
			}

		} else

		{

			nothingToShowBox = new VertretungsalarmBox("Du hast noch keine "
					+ (type == ExceptionSettingsType.SUBJECT_EXCEPTION ? "Fächer" : "Lehrer") + " hinzugefügt");

			add(nothingToShowBox);
		}

		VertretungsalarmButton add = new VertretungsalarmButton("Hinzufügen");
		add.getElement().getStyle().setProperty("order", "9999999999999999999");
		add.getElement().getStyle().setProperty("background", "#FDFCFE");

		add.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				newException = new ExceptionBox("", type, true);
				add(newException);

				if (exceptions.trim() == "") {
					remove(nothingToShowBox);
				}

			}
		});

		if (isAddState) {
			add.fireEvent(new GwtEvent<ClickHandler>() {
				@Override
				protected void dispatch(ClickHandler handler) {
					handler.onClick(null);
				}

				@Override
				public com.google.gwt.event.shared.GwtEvent.Type<ClickHandler> getAssociatedType() {
					return ClickEvent.getType();
				}
			});
		}

		add(add);

	}

}
