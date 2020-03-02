package de.zunk.vertretungsalarm.client.ui.messagebox;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window.Location;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

import de.zunk.vertretungsalarm.client.Vertretungsalarm;

public class MessageBox extends AbsolutePanel {

	Label titleLabel;
	HTML contentLabel;

	Label tapToCloseLabel;
	Button acceptToCloseButton;
	Button yesButton;
	Button noButton;

	public MessageBox(String title, String content, ButtonLayoutOption buttonLayout, CloseAction closeAction) {

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
		titleLabel.getElement().getStyle().setProperty("fontSize", "20px");
		titleLabel.getElement().getStyle().setProperty("fontWeight", "bold");
		titleLabel.getElement().getStyle().setProperty("color", "black");
		add(titleLabel);

		contentLabel = new HTML();
		contentLabel.setHTML(new SafeHtmlBuilder().appendEscapedLines(content).toSafeHtml());
		contentLabel.getElement().getStyle().setProperty("fontSize", "17px");
		contentLabel.getElement().getStyle().setProperty("color", "black");
		contentLabel.getElement().getStyle().setProperty("marginTop", "10px");
		add(contentLabel);

		if (buttonLayout == ButtonLayoutOption.TAP_TO_CLOSE) {
			tapToCloseLabel = new Label("TIPPEN ZUM SCHLIEßEN");
			tapToCloseLabel.getElement().getStyle().setProperty("fontSize", "15px");
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
		} else if (buttonLayout == ButtonLayoutOption.OK) {
			acceptToCloseButton = new Button("OK");
			acceptToCloseButton.getElement().getStyle().setProperty("fontSize", "1.5vh");
			acceptToCloseButton.getElement().getStyle().setProperty("color", "black");
			acceptToCloseButton.getElement().getStyle().setProperty("background", "#F0C267");
			acceptToCloseButton.getElement().getStyle().setProperty("marginTop", "30px");
			acceptToCloseButton.getElement().getStyle().setProperty("padding", "10px");
			acceptToCloseButton.getElement().getStyle().setProperty("border", "0px");
			add(acceptToCloseButton);

			acceptToCloseButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (closeAction == CloseAction.RESET_EVERYTHING) {
						Vertretungsalarm.getClientStorage().clear();
						Location.replace(Location.createUrlBuilder().removeParameter("page").buildString());
					}
					RootPanel.get().remove(MessageBox.this.getParent().asWidget());
				}
			});

		} else if (buttonLayout == ButtonLayoutOption.YES_NO) {

			yesButton = new Button("JA");
			yesButton.getElement().getStyle().setProperty("fontSize", "1.5vh");
			yesButton.getElement().getStyle().setProperty("color", "black");
			yesButton.getElement().getStyle().setProperty("background", "#E1E0E2");
			yesButton.getElement().getStyle().setProperty("marginTop", "30px");
			yesButton.getElement().getStyle().setProperty("padding", "10px");
			yesButton.getElement().getStyle().setProperty("borderRadius", "15px");
			yesButton.getElement().getStyle().setProperty("border", "0px");
			add(yesButton);

			noButton = new Button("NEIN");
			noButton.getElement().getStyle().setProperty("fontSize", "1.5vh");
			noButton.getElement().getStyle().setProperty("color", "black");
			noButton.getElement().getStyle().setProperty("background", "#F0C267");
			noButton.getElement().getStyle().setProperty("marginTop", "10px");
			noButton.getElement().getStyle().setProperty("borderRadius", "15px");
			noButton.getElement().getStyle().setProperty("padding", "10px");
			noButton.getElement().getStyle().setProperty("border", "0px");
			add(noButton);

			yesButton.addClickHandler(new ClickHandler() {

				@Override
				public void onClick(ClickEvent event) {
					if (closeAction == CloseAction.RESET_EVERYTHING) {
						Vertretungsalarm.getClientStorage().clear();
						RootPanel.get().add(new Message("Schade, dass du gehst!", "Du bist jederzeit wieder wilkommen.",
								ButtonLayoutOption.TAP_TO_CLOSE, CloseAction.RELOAD));
					}
					RootPanel.get().remove(MessageBox.this.getParent().asWidget());
				}
			});

			noButton.addClickHandler(e -> RootPanel.get().remove(MessageBox.this.getParent().asWidget()));

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
