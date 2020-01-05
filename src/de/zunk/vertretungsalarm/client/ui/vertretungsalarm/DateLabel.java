package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

import de.zunk.vertretungsalarm.shared.VertretungsDate;

public class DateLabel extends AbsolutePanel {

	Label dateLabel;

	VertretungsDate date;

	public DateLabel() {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("overflow", "auto");

		dateLabel = new Label("");

		dateLabel.getElement().getStyle().setProperty("fontSize", "2vh");
		dateLabel.getElement().getStyle().setProperty("fontFamily", "Roboto");
		dateLabel.getElement().getStyle().setProperty("textAlign", "left");
		dateLabel.getElement().getStyle().setProperty("padding", "0px 10px 2px 0px");

		add(dateLabel);
	}

	protected void setDate(VertretungsDate date) {
		this.date = date;
		dateLabel.setText(date.toString());
	}

	public VertretungsDate getDate() {
		return date;
	}

}
