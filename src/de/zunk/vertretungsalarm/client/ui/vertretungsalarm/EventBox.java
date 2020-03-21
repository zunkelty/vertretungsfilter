package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.shared.VertretungsEvent;

public class EventBox extends AbsolutePanel {

	public EventBox(VertretungsEvent event) {
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("marginBottom", "20px");

		getElement().getStyle().setProperty("borderRadius", "15px");

		getElement().getStyle().setProperty("background", "#FDFCFE");

		getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203,203,203,0.4)");

		AbsolutePanel topPart = new AbsolutePanel();
		topPart.getElement().getStyle().setProperty("overflow", "hidden");
		topPart.getElement().getStyle().setProperty("flexShrink", "0");
		topPart.getElement().getStyle().setProperty("display", "flex");
		topPart.getElement().getStyle().setProperty("justifyContent", "flex-start");

		Label title = new Label(EventTranslator.getEventTitle(event));
		title.getElement().getStyle().setProperty("font", "450 15px Ubuntu");
		title.getElement().getStyle().setProperty("color", "#3E4158");
		title.getElement().getStyle().setProperty("padding", "15px 0px 0px 15px");
		title.getElement().getStyle().setProperty("marginRight", "10px");
		title.getElement().getStyle().setProperty("width", "100%");

		Image icon = new Image("icons/vectorpaint.svg");
		icon.getElement().getStyle().setProperty("paddingRight", "20px");

		PushButton showDetailView = new PushButton(icon);
		showDetailView.getElement().getStyle().setProperty("display", "block");
		showDetailView.getElement().getStyle().setProperty("textIdent", "-9999px");
		showDetailView.getElement().getStyle().setProperty("boxShadow", "0px");
		showDetailView.getElement().getStyle().setProperty("border", "0px");
		showDetailView.getElement().getStyle().setProperty("paddingTop", "15px");

		showDetailView.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent e) {
				RootPanel.get().add(new EventDetailView(event));
			}
		});

		HTML desc = new HTML(EventTranslator.getEventSubtitle(event));
		desc.getElement().getStyle().setProperty("font", "300 15px Ubuntu");
		desc.getElement().getStyle().setProperty("color", "#3E4158");
		desc.getElement().getStyle().setProperty("lineHeight", "21px");
		desc.getElement().getStyle().setProperty("padding", "7px 0px 20px 15px");

		topPart.add(title);
		topPart.add(showDetailView);
		add(topPart);
		add(desc);

	}

}
