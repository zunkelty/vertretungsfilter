package de.zunk.vertretungsalarm.client.ui.registration;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class schoolYearSelector extends AbsolutePanel implements Serializable {

	/**
	 * 
	 */

	Integer[] schoolClassYears = { 5, 6, 7, 8, 9, 10, 11 };

	private static final long serialVersionUID = 1L;
	private ArrayList<selectableVertretungsalarmButton> schoolYearButtons;

	public schoolYearSelector() {

		schoolYearButtons = new ArrayList<>();

		getElement().getStyle().setBorderWidth(3, Unit.PX);
		getElement().getStyle().setBorderColor("#000000");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#ECE9FC");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "space-between");
		getElement().getStyle().setProperty("flex", "0.5 1 auto");

		// Für die Darstelung in Edge / IE kann nicht space-evenly verwendet werden und
		// SimplePanels werden als Lösung verwendet
		add(new SimplePanel());

		for (int i = 0; i < schoolClassYears.length; i++) {
			selectableVertretungsalarmButton schoolYearButton;
			schoolYearButton = new selectableVertretungsalarmButton(schoolClassYears[i] + "");
			schoolYearButtons.add(schoolYearButton);

			schoolYearButton.setColor("#AFE09C");

			schoolYearButton.addClickHandler(event -> setSelectedSchoolYear(schoolYearButton));

			add(schoolYearButton);
		}

		add(new SimplePanel());

	}

	protected void setSelectedSchoolYear(selectableVertretungsalarmButton schoolYearButton) {
		for (selectableVertretungsalarmButton b : schoolYearButtons) {
			if (b.isSelected()) {
				b.deselect();
			}
		}
		schoolYearButton.select();
	}

	public String getChoice() {
		for (selectableVertretungsalarmButton b : schoolYearButtons) {
			if (b.isSelected()) {
				return b.getText();
			}
		}
		return "";
	}

}
