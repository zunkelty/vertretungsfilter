package de.zunk.vertretungsalarm.client.ui;

import java.io.Serializable;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Button;

public class selectableVertretungsalarmButton extends Button implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean isSelected = false;

	public selectableVertretungsalarmButton(String text) {
		setText(text);

		getElement().getStyle().setProperty("borderRadius", "40%");
		getElement().getStyle().setProperty("outlineOffset", "-10px");
		getElement().getStyle().setBorderColor("#000000");
		getElement().getStyle().setBorderWidth(0.5, Unit.PX);
		getElement().getStyle().setProperty("fontSize", "2.5vh");
		getElement().getStyle().setProperty("padding", "20px");
		getElement().getStyle().setProperty("background", "#000000");
		getElement().getStyle().setProperty("color", "#000000");
	}

	public void setColor(String color) {
		getElement().getStyle().setProperty("background", color);
	}

	public void select() {
		isSelected = true;

		getElement().getStyle().setBorderWidth(3, Unit.PX);
	}

	public void deselect() {
		isSelected = false;

		getElement().getStyle().setBorderWidth(0.5, Unit.PX);
	}

	public boolean isSelected() {
		return isSelected;
	}

}
