package de.zunk.vertretungsalarm.client.ui;

import java.io.Serializable;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.Window.Navigator;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class Screen extends AbsolutePanel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static int width;
	public static int height;

	public Screen() {
		if (Navigator.getPlatform().contains("Win") || Navigator.getPlatform().contains("Mac")) {
			width = Integer.valueOf(Window.getClientWidth() / 4);
			height = Integer.valueOf(Window.getClientHeight());

		} else {
			width = Integer.valueOf(Window.getClientWidth());
			height = Integer.valueOf(Window.getClientHeight());
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
