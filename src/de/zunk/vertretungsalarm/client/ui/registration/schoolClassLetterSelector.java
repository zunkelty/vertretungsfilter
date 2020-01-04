package de.zunk.vertretungsalarm.client.ui.registration;

import java.io.Serializable;
import java.util.ArrayList;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class schoolClassLetterSelector extends AbsolutePanel implements Serializable {

	/**
	 * 
	 */

	String[] schoolClassLetters = { "A", "B", "C", "D", "F", "S" };

	private static final long serialVersionUID = 1L;
	private ArrayList<selectableVertretungsalarmButton> schoolClassLetterButtons;

	public schoolClassLetterSelector() {

		schoolClassLetterButtons = new ArrayList<>();

		getElement().getStyle().setBorderWidth(3, Unit.PX);
		getElement().getStyle().setBorderColor("#000000");
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("alignItems", "center");
		getElement().getStyle().setProperty("justifyContent", "space-evenly");
		getElement().getStyle().setProperty("flex", "1 1 auto");
		getElement().getStyle().setProperty("flex", "0.5 1 auto");

		for (int i = 0; i < schoolClassLetters.length; i++) {
			selectableVertretungsalarmButton schoolClassLetterButton;
			schoolClassLetterButton = new selectableVertretungsalarmButton(schoolClassLetters[i] + "");
			schoolClassLetterButtons.add(schoolClassLetterButton);

			schoolClassLetterButton.setColor("#ECE9FC");

			schoolClassLetterButton.addClickHandler(event -> setSelectedSchoolClassLetter(schoolClassLetterButton));

			add(schoolClassLetterButton);
		}

	}

	protected void setSelectedSchoolClassLetter(selectableVertretungsalarmButton schoolClassLetterButton) {
		for (selectableVertretungsalarmButton b : schoolClassLetterButtons) {
			if (b.isSelected()) {
				b.deselect();
			}
		}
		schoolClassLetterButton.select();
	}

	public String getChoice() {
		for (selectableVertretungsalarmButton b : schoolClassLetterButtons) {
			if (b.isSelected()) {
				return b.getText();
			}
		}
		return "";
	}

}
