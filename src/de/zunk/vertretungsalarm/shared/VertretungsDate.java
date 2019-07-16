package de.zunk.vertretungsalarm.shared;

import java.io.Serializable;

public class VertretungsDate implements Serializable {

	int day = 0;
	int month = 0;
	int year = 0;
	String purpose = "";

	private static final long serialVersionUID = 1L;

	public VertretungsDate() {
		super();
	}

	public VertretungsDate(int day, int month, int year) {
		assert day <= 31 : "Precondition failed: day <= 31";
		assert month <= 12 : "Precondition failed: month <= 12";
		assert year >= 2019 : "Precondition failed: year >= 2019";

		this.day = day;
		this.month = month;
		this.year = year;
	}

	public void setDate(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int getDay() {
		return day;
	}

	public int getMonth() {
		return month;
	}

	public int getYear() {
		return year;
	}

	@Override
	public String toString() {
		return day + "." + month + "." + year;
	}

	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}

	public String getPurpose() {
		return purpose;
	}
}
