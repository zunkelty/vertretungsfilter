package de.zunk.vertretungsalarm.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Vertretungsplan implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<VertretungsEvent> events;

	private VertretungsTime time;

	private String vertretungsplanAsString;

	public Vertretungsplan() {
		super();
	}

	public Vertretungsplan(ArrayList<VertretungsEvent> events) {
		this.events = events;
		// time = new VertretungsTime();

	}

	public Vertretungsplan(String s) {
		this.vertretungsplanAsString = s;
	}

	public ArrayList<VertretungsEvent> getVertretungsEvents() {
		return events;
	}

	public VertretungsTime getTime() {
		return time;
	}

	@Override
	public String toString() {
		return vertretungsplanAsString;
	}

}
