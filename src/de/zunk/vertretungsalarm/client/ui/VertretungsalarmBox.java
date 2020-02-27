package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HTML;

public class VertretungsalarmBox extends AbsolutePanel {

	private HTML title;

	public VertretungsalarmBox(String htmlText) {
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("marginBottom", "10px");

		getElement().getStyle().setProperty("borderRadius", "15px");

		getElement().getStyle().setProperty("background", "#FDFCFE");

		getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203,203,203,0.4)");

		title = new HTML(htmlText);
		title.getElement().getStyle().setProperty("font", "500 17px Ubuntu");
		title.getElement().getStyle().setProperty("color", "#3E4158");
		title.getElement().getStyle().setProperty("padding", "15px 10px 0px 15px");
		title.getElement().getStyle().setProperty("marginBottom", "10px");

		add(title);

	}

	public void setHTML(String html) {
		title.setHTML(html);
	}

}
