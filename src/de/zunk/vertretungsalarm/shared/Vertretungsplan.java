package de.zunk.vertretungsalarm.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class Vertretungsplan implements Serializable {

	private static final long serialVersionUID = 1L;

	private ArrayList<VertretungsEvent> events;
	private ArrayList<DayInfo> dayInfos;

	private String time;

	private String vertretungsplanAsString;

	public Vertretungsplan() {
		super();
	}

	public Vertretungsplan(ArrayList<VertretungsEvent> events, ArrayList<DayInfo> dayInfos, String time) {
		this.events = events;
		this.dayInfos = dayInfos;
		this.time = time;
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

	@Override
	public String toString() {
		return vertretungsplanAsString;
	}

	public String getTime() {
		return time;
	}
}
