package de.zunk.vertretungsalarm.client.ui;

import java.io.Serializable;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;

public class TopBar extends AbsolutePanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Button info;
	private Button back;
	private Button forward;

	public TopBar() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "space-around");
		getElement().getStyle().setProperty("padding", "5px");
		setHeight("6vh");

		info = new Button("Infoleiste");

		info.getElement().getStyle().setProperty("background", "#ECE9FC");
		info.getElement().getStyle().setProperty("fontFamily", "Roboto");
		info.getElement().getStyle().setBorderWidth(0, Unit.PX);
		info.getElement().getStyle().setProperty("fontSize", "2.5vh");
		info.getElement().getStyle().setProperty("textAlign", "center");
		info.getElement().getStyle().setProperty("flex", "1 0 auto");
		add(info);

		back = new Button("");
		back.setHeight("6vh");
		back.setWidth("6vh");
		back.getElement().getStyle().setProperty("background", "#ECE9FC");
		back.getElement().getStyle().setProperty("fontFamily", "Roboto");
		back.getElement().getStyle().setProperty("fontSize", "2.5vh");
		back.getElement().getStyle().setProperty("order", "-1");
		back.getElement().getStyle().setProperty("flex", "0 0 auto");
		back.getElement().getStyle().setProperty("outline", "0");
		back.getElement().getStyle().setProperty("border", "1.5px solid black");
		back.getElement().getStyle().setProperty("background", "url(icons/back.png) no-repeat center transparent");
		back.getElement().getStyle().setProperty("backgroundSize", "contain");

		back.addClickHandler(event -> goBack());

		forward = new Button("");
		forward.setHeight("6vh");
		forward.setWidth("6vh");
		forward.getElement().getStyle().setProperty("background", "#ECE9FC");
		forward.getElement().getStyle().setProperty("fontFamily", "Roboto");
		forward.getElement().getStyle().setProperty("fontSize", "2.5vh");
		forward.getElement().getStyle().setProperty("order", "2");
		forward.getElement().getStyle().setProperty("flex", "0 0 auto");
		forward.getElement().getStyle().setProperty("outline", "0");
		forward.getElement().getStyle().setProperty("border", "1.5px solid black");
		forward.getElement().getStyle().setProperty("background",
				"url(icons/forward.png) no-repeat center transparent");
		forward.getElement().getStyle().setProperty("backgroundSize", "contain");

		add(back);
		add(forward);
		add(info);

		back.setVisible(false);
		forward.setVisible(false);

	}

	public void setInfoText(String text) {
		info.setText(text);
	}

	public void addPageBackOption() {
		back.setVisible(true);
	}

	public void addPageForwardOption(ClickHandler handler) {
		forward.setVisible(true);
		forward.addClickHandler(handler);
	}

	public void removePageBackOption() {
		back.setVisible(false);
	}

	public void removePageForwardOption() {
		forward.setVisible(false);
	}

	private void goBack() {
		if (Location.getParameter("page") != null) {
			Location.replace(Location.createUrlBuilder().removeParameter("page").buildString());
		}
		if (Location.getParameter("step") != null && Location.getParameter("page") == null) {
			Location.replace(Location.createUrlBuilder()
					.setParameter("step", Integer.parseInt(Location.getParameter("step")) - 1 + "").buildString());
		}
	}
}
