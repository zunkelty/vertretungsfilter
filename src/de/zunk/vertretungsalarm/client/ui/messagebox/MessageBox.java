package de.zunk.vertretungsalarm.client.ui.messagebox;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class MessageBox extends AbsolutePanel {

	Label titleLabel;
	HTML contentLabel;

	Label tapToCloseLabel;
	Button acceptToCloseButton;

	public MessageBox(String title, String content, boolean hasToBeAccepted) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "center");
		getElement().getStyle().setProperty("background", "#ECE9FC");
		getElement().getStyle().setProperty("padding", "20px 30px");
		getElement().getStyle().setProperty("margin", "0px 20px");
		getElement().getStyle().setProperty("opacity", "1");
		getElement().getStyle().setProperty("boxShadow", "4px 7px 24px 0px rgba(0,0,0,0.51)");
		getElement().getStyle().setProperty("borderRadius", "10px");

		titleLabel = new Label(title.toUpperCase());
		titleLabel.getElement().getStyle().setProperty("fontSize", "2.5vh");
		titleLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		titleLabel.getElement().getStyle().setProperty("color", "black");
		add(titleLabel);

		contentLabel = new HTML();
		contentLabel.setHTML(new SafeHtmlBuilder().appendEscapedLines(content).toSafeHtml());
		contentLabel.getElement().getStyle().setProperty("fontSize", "2vh");
		contentLabel.getElement().getStyle().setProperty("color", "black");
		contentLabel.getElement().getStyle().setProperty("marginTop", "10px");
		add(contentLabel);

		if (!hasToBeAccepted) {
			tapToCloseLabel = new Label("KLICKEN ZUM SCHLIEßEN");
			tapToCloseLabel.getElement().getStyle().setProperty("fontSize", "1.5vh");
			tapToCloseLabel.getElement().getStyle().setProperty("color", "black");
			tapToCloseLabel.getElement().getStyle().setProperty("marginTop", "30px");
			tapToCloseLabel.getElement().getStyle().setProperty("textAlign", "center");
			add(tapToCloseLabel);

			addDomHandler(e -> RootPanel.get().remove(MessageBox.this.getParent().asWidget()),
					MouseDownEvent.getType());
		} else {
			acceptToCloseButton = new Button("OK");
			acceptToCloseButton.getElement().getStyle().setProperty("fontSize", "1.5vh");
			acceptToCloseButton.getElement().getStyle().setProperty("color", "black");
			acceptToCloseButton.getElement().getStyle().setProperty("background", "#AFE09C");
			acceptToCloseButton.getElement().getStyle().setProperty("marginTop", "30px");
			acceptToCloseButton.getElement().getStyle().setProperty("padding", "10px");
			acceptToCloseButton.getElement().getStyle().setProperty("border", "0px");
			add(acceptToCloseButton);

			acceptToCloseButton.addClickHandler(e -> RootPanel.get().remove(MessageBox.this.getParent().asWidget()));
		}

	}

}
