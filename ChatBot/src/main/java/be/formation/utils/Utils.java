package be.formation.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Utils {

	/**
	 * A method validating and transforming a string into date
	 * 
	 * @param str
	 * @return [regex]!event yyyy MM dd hh mm
	 */
	public static LocalDateTime stringToDate(String message) {

		String[] strTab = message.split(" ");
		List<Integer> list = new ArrayList<>();
		try {

			for (int i = 1; i < strTab.length; i++) {
				list.add(Integer.parseInt(strTab[i]));
			}
		} catch (Exception e) {
			System.out.println("Mauvaise entrée numérique");
		}
		LocalDate date = null;
		if (list.size() == 3 || list.size() == 5) {
			date = LocalDate.of(list.get(0), list.get(1), list.get(2));

			switch (list.size()) {

			case 3:
				return LocalDateTime.of(date, LocalTime.of(8, 0));
			case 5:
				return LocalDateTime.of(date, LocalTime.of(list.get(3), list.get(4)));
			default:
				return null;
			}
		} else {
			return null;
		}

	}

	/**
	 * 
	 * @param [regex]!participate id
	 */
	public static int stringToParticipation(String message) {

		String[] strTab = message.split(" ");
		try {

			return Integer.parseInt(strTab[1]);

		} catch (Exception e) {
			System.out.println("Mauvaise entrée");
			return -1;
		}

	}

}
