package de.zunk.vertretungsalarm.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Vertretungsplan implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<VertretungsEvent> events;
	private ArrayList<DayInfo> dayInfos;

	private VertretungsTime time;

	private String vertretungsplanAsString;

	public Vertretungsplan() {
		super();
	}

	public Vertretungsplan(ArrayList<VertretungsEvent> events, ArrayList<DayInfo> dayInfos) {
		this.events = events;
		this.dayInfos = dayInfos;
	}

	public Vertretungsplan(String s) {
		this.vertretungsplanAsString = s;
	}

	public ArrayList<VertretungsEvent> getVertretungsEvents() {
		return events;
	}

	public ArrayList<DayInfo> getDayInfos() {
		return dayInfos;
	}

	public VertretungsTime getTime() {
		return time;
	}

	@Override
	public String toString() {
		return vertretungsplanAsString;
	}

}
