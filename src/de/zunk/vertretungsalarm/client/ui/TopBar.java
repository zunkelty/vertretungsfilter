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

	private Button placeholder;

	public TopBar() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "row");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "space-evenly");

		info = new Button("Infoleiste");

		info.getElement().getStyle().setProperty("background", "#ECE9FC");
		info.getElement().getStyle().setProperty("fontFamily", "Roboto");
		info.getElement().getStyle().setBorderWidth(0, Unit.PX);
		info.getElement().getStyle().setProperty("fontSize", "2.5vh");
		info.getElement().getStyle().setProperty("textAlign", "center");
		info.getElement().getStyle().setProperty("flex", "1 0 auto");
		add(info);

		back = new Button("");
		back.getElement().getStyle().setProperty("background", "#ECE9FC");
		back.getElement().getStyle().setProperty("fontFamily", "Roboto");
		back.getElement().getStyle().setBorderWidth(0, Unit.PX);
		back.getElement().getStyle().setProperty("fontSize", "2.5vh");
		back.getElement().getStyle().setProperty("order", "-1");
		back.getElement().getStyle().setProperty("flex", "0.5 0 auto");
		back.getElement().getStyle().setProperty("background", "url(icons/back.png) no-repeat left transparent");
		back.getElement().getStyle().setProperty("backgroundSize", "contain");

		back.addClickHandler(event -> goBack());

		forward = new Button("");
		forward.getElement().getStyle().setProperty("background", "#ECE9FC");
		forward.getElement().getStyle().setProperty("fontFamily", "Roboto");
		forward.getElement().getStyle().setBorderWidth(0, Unit.PX);
		forward.getElement().getStyle().setProperty("fontSize", "2.5vh");
		forward.getElement().getStyle().setProperty("order", "2");
		forward.getElement().getStyle().setProperty("flex", "0.5 0 auto");
		forward.getElement().getStyle().setProperty("background", "url(icons/forward.png) no-repeat right transparent");
		forward.getElement().getStyle().setProperty("backgroundSize", "contain");

		placeholder = new Button("");
		placeholder.getElement().getStyle().setProperty("background", "#ECE9FC");
		placeholder.getElement().getStyle().setProperty("fontFamily", "Roboto");
		placeholder.getElement().getStyle().setBorderWidth(0, Unit.PX);
		placeholder.getElement().getStyle().setProperty("fontSize", "2.5vh");
		placeholder.getElement().getStyle().setProperty("order", "-1");
		placeholder.getElement().getStyle().setProperty("flex", "0.5 0 auto");

		add(back);
		add(forward);
		add(info);
		add(placeholder);

		back.setVisible(false);
		forward.setVisible(false);
		placeholder.setVisible(false);

	}

	public void setInfoText(String text) {
		info.setText(text);
	}

	public void setMainColor(String color) {
		info.getElement().getStyle().setProperty("background", color);
		placeholder.getElement().getStyle().setProperty("background", color);
	}

	public void setSecondColor(String secondColor) {
		back.getElement().getStyle().setProperty("background", secondColor);
		forward.getElement().getStyle().setProperty("background", secondColor);
	}

	public void addPageBackOption() {
		back.setVisible(true);
		if (!forward.isVisible()) {
			placeholder.setVisible(true);
			placeholder.getElement().getStyle().setProperty("order", "2");
		} else {
			placeholder.setVisible(false);
		}
	}

	public void addPageForwardOption(ClickHandler handler) {
		forward.setVisible(true);
		if (!back.isVisible()) {
			placeholder.setVisible(true);
			placeholder.getElement().getStyle().setProperty("order", "-1");
		} else {
			placeholder.setVisible(false);
		}
		forward.addClickHandler(handler);
	}

	public void removePageBackOption() {
		back.setVisible(false);
		if (forward.isVisible()) {
			placeholder.setVisible(true);
			placeholder.getElement().getStyle().setProperty("order", "-1");
		} else {
			placeholder.setVisible(false);
		}
	}

	public void removePageForwardOption() {
		forward.setVisible(false);
		if (back.isVisible()) {
			placeholder.setVisible(true);
			placeholder.getElement().getStyle().setProperty("order", "2");
		} else {
			placeholder.setVisible(false);
		}
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
