package de.zunk.vertretungsalarm.client.ui.messagebox;

import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.ui.CloseAction;

public class MessageBox extends AbsolutePanel {

	Label titleLabel;
	HTML contentLabel;

	Label tapToCloseLabel;
	Button acceptToCloseButton;

	public MessageBox(String title, String content, boolean hasToBeAccepted, CloseAction closeAction) {

		getElement().getStyle().setProperty("display", "flex");
		getElement().getStyle().setProperty("flexDirection", "column");
		getElement().getStyle().setProperty("alignItems", "stretch");
		getElement().getStyle().setProperty("justifyContent", "center");
		getElement().getStyle().setProperty("background", "#F3F4F8");
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
			tapToCloseLabel = new Label("TIPPEN ZUM SCHLIEßEN");
			tapToCloseLabel.getElement().getStyle().setProperty("fontSize", "1.5vh");
			tapToCloseLabel.getElement().getStyle().setProperty("color", "black");
			tapToCloseLabel.getElement().getStyle().setProperty("marginTop", "30px");
			tapToCloseLabel.getElement().getStyle().setProperty("textAlign", "center");
			tapToCloseLabel.getElement().getStyle().setProperty("background", "#F0C267");
			tapToCloseLabel.getElement().getStyle().setProperty("borderRadius", "15px");
			tapToCloseLabel.getElement().getStyle().setProperty("padding", "6px");
			add(tapToCloseLabel);

			addDomHandler(e -> {
				if (closeAction == CloseAction.RELOAD || closeAction == CloseAction.CLOSE_AFTER_1_SECOND) {
					Location.reload();
				}

				RootPanel.get().remove(MessageBox.this.getParent().asWidget());

			}, MouseDownEvent.getType());
		} else {
			acceptToCloseButton = new Button("OK");
			acceptToCloseButton.getElement().getStyle().setProperty("fontSize", "1.5vh");
			acceptToCloseButton.getElement().getStyle().setProperty("color", "black");
			acceptToCloseButton.getElement().getStyle().setProperty("background", "#F0C267");
			acceptToCloseButton.getElement().getStyle().setProperty("marginTop", "30px");
			acceptToCloseButton.getElement().getStyle().setProperty("padding", "10px");
			acceptToCloseButton.getElement().getStyle().setProperty("border", "0px");
			add(acceptToCloseButton);

			acceptToCloseButton.addClickHandler(e -> RootPanel.get().remove(MessageBox.this.getParent().asWidget()));
		}

		if (closeAction == CloseAction.CLOSE_AFTER_1_SECOND) {
			Timer t = new Timer() {
				@Override
				public void run() {
					Location.reload();
				}
			};
			t.schedule(1000);
		}

	}

}
