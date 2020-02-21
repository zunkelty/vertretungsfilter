package de.zunk.vertretungsalarm.shared;

public class testclass {

	public static void main(String[] args) {
		System.out.println(isVertretungsDate("21.2.2020"));
	}

	public static boolean isVertretungsDate(String possibleDateName) {
		try {
			String[] date_split = possibleDateName.split("\\.", 3);

			// Check that the split parts are parsable to int
			int day = Integer.parseInt(date_split[0]);
			int month = Integer.parseInt(date_split[1]);
			int year = Integer.parseInt(date_split[2]);

			// Check that the parts have their correct length (day=2 digits; year=4 digits)
			if (String.valueOf(day).length() == 1 && day < 10) {
			} else if (String.valueOf(day).length() == 2 && day >= 10 && day <= 31) {
			} else {
				return false;
			}
			if (String.valueOf(month).length() == 1 && month < 10) {
			} else if (String.valueOf(month).length() == 2 && month >= 10 && month <= 12) {
			} else {
				return false;
			}
			if (String.valueOf(year).length() == 4 && year >= 2019) {
				return true;
			} else {
				return false;
			}

		} catch (Exception e) {
			return false;
		}
	}

}
