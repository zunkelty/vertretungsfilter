package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.RootPanel;

public class Header extends AbsolutePanel {

	public Header(String htmlTitleText, boolean hasPageBackOption, BackOption backOption) {
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("order", "0");

		getElement().getStyle().setProperty("animationName", "fadeIn");
		getElement().getStyle().setProperty("animationDuration", "0.3s");

		getElement().getStyle().setProperty("background", "#F0C267");
		getElement().getStyle().setProperty("borderBottomLeftRadius", "49px");
		getElement().getStyle().setProperty("borderBottomRightRadius", "49px");
		getElement().getStyle().setProperty("minHeight", "115px");
		getElement().getStyle().setProperty("maxHeight", "157px");

		HTML title = new HTML(htmlTitleText);
		title.getElement().getStyle().setProperty("font", "33px Ubuntu");
		title.getElement().getStyle().setProperty("color", "#FFFFFF");
		title.getElement().getStyle().setProperty("paddingLeft", "25px");
		title.getElement().getStyle().setProperty("paddingTop", "15px");
		add(title);

		if (hasPageBackOption) {
			Image icon = new Image("icons/navigate_before-24px.svg");
			icon.getElement().getStyle().setProperty("transform", "scale(1.3)");
			icon.getElement().getStyle().setProperty("padding", "0px 0px 0px 15px");

			PushButton goBack = new PushButton(icon);
			goBack.getElement().getStyle().setProperty("display", "block");
			goBack.getElement().getStyle().setProperty("textIdent", "-9999px");
			goBack.getElement().getStyle().setProperty("boxShadow", "0px");
			goBack.getElement().getStyle().setProperty("border", "0px");
			goBack.getElement().getStyle().setProperty("paddingTop", "23px");
			goBack.getElement().getStyle().setProperty("order", "-1");

			goBack.addClickHandler(event -> goBack(backOption));

			add(goBack);

		} else {
			addDomHandler(e -> {
				AbsolutePanel white = new AbsolutePanel();
				white.setSize(Window.getClientWidth() + "px", Window.getClientHeight() + "px");
				RootPanel.get().add(white);
				Location.reload();
			}, ClickEvent.getType());
		}

	}

	public void goBack(BackOption option) {
		switch (option) {
		case PAGE_BACK:
			Location.replace(Location.createUrlBuilder().removeParameter("page").buildString());
			break;
		case SUBPAGE_BACK:
			Location.replace(Location.createUrlBuilder().removeParameter("subpage").buildString());
			break;

		}
	}

}
