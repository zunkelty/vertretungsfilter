package de.zunk.vertretungsalarm.client.ui.vertretungsalarm;

import java.util.Date;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Label;

public class DateLabel extends AbsolutePanel {

	Label dateLabel;

	public DateLabel(Date date) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "flex-start");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("background", "#AFE09C");
		getElement().getStyle().setProperty("overflow", "auto");

		DateTimeFormat dtf = DateTimeFormat.getFormat("dd.MM.yyyy");
		dateLabel = new Label(dtf.format(date));

		dateLabel.getElement().getStyle().setProperty("fontSize", "2vh");
		dateLabel.getElement().getStyle().setProperty("fontFamily", "Roboto");
		dateLabel.getElement().getStyle().setProperty("textAlign", "left");
		dateLabel.getElement().getStyle().setProperty("padding", "0px 10px 2px 0px");

		add(dateLabel);
	}

}
