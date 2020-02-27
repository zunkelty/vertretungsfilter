package de.zunk.vertretungsalarm.client.ui;

import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.TextBox;

public class VertretungsalarmTextField extends AbsolutePanel {

	TextBox textBox;

	public VertretungsalarmTextField(String hint) {
		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "flex-start");
		getElement().getStyle().setProperty("overflow", "hidden");
		getElement().getStyle().setProperty("marginBottom", "10px");

		getElement().getStyle().setProperty("borderRadius", "15px");

		getElement().getStyle().setProperty("background", "#FDFCFE");

		getElement().getStyle().setProperty("boxShadow", "0px 3px 6px 0px rgba(203,203,203,0.4)");

		textBox = new TextBox();
		// textBox.setText(hint);
		textBox.getElement().getStyle().setProperty("hint", hint);
		textBox.getElement().getStyle().setProperty("font", "300 17px Ubuntu");
		textBox.getElement().getStyle().setProperty("color", "#3E4158");
		textBox.getElement().getStyle().setProperty("padding", "15px 0px 0px 15px");
		textBox.getElement().getStyle().setProperty("marginBottom", "10px");
		textBox.getElement().getStyle().setProperty("border", "0");
		textBox.getElement().setPropertyString("placeholder", hint);

		// textBox.addClickHandler(new ClickHandler() {
		// @Override
		// public void onClick(ClickEvent event) {
		// if (textBox.getText() == hint) {
		// textBox.setText("");
		// }
		// }
		// });
		//
		// textBox.addBlurHandler(new BlurHandler() {
		//
		// @Override
		// public void onBlur(BlurEvent event) {
		// if (textBox.getText().trim().isEmpty()) {
		// textBox.setText(hint);
		// }
		// }
		// });
		add(textBox);

	}

	public String getText() {
		return textBox.getText();
	}

	public TextBox getField() {
		return textBox;
	}

}
