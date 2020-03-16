package de.zunk.vertretungsalarm.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DayInfo implements IsSerializable {

	private static final long serialVersionUID = 1L;

	String dayInfo;
	VertretungsDate date;

	public DayInfo() {
		super();
		// Empty const to match Serializable
	}

	public DayInfo(String dayInfo, VertretungsDate date) {
		this.dayInfo = dayInfo;
		this.date = date;
	}

	public void setInfo(String dayInfo) {
		this.dayInfo = dayInfo;
	}

	public String getInfo() {
		return dayInfo;
	}

	public void setDate(VertretungsDate date) {
		this.date = date;
	}

	public VertretungsDate getDate() {
		return date;
	}

	@Override
	public String toString() {
		return dayInfo + " " + date.toString();
	}

}
