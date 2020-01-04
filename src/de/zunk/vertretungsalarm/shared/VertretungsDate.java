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

		String day_s = (day < 10 ? "0" : "") + day;
		String month_s = (month < 10 ? "0" : "") + month;
		String year_s = year + "";

		return day_s + "." + month_s + "." + year_s;
	}
}
