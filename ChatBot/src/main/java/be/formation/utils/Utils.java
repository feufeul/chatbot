package be.formation.utils;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import be.formation.exceptions.RegexNotRespectedException;

public class Utils {
	/**
	 *  regex (description) yyyy MM dd hh mm
	 */
	private static final String PATTERN_WITH_HOURS = "([(])(.*)([)])([ ])(\\d+)([ ])(\\d+)([ ])(\\d+)([ ])(\\d+)([ ])(\\d+)";
	/**
	 *  regex (description) yyyy MM dd
	 */
	private static final String PATTERN_WITHOUT_HOURS = "([(])(.*)([)])([ ])(\\d+)([ ])(\\d+)([ ])(\\d+)";
	/**
	 * regex yyyy MM dd
	 */
	private static final String PATTERN_WITHOUT_DESC_AND_HOURS = "(\\d+)([ ])(\\d+)([ ])(\\d+)";/**
	 * regex yyyy MM dd hh mm
	 */
	private static final String PATTERN_WITHOUT_DESC = "(\\d+)([ ])(\\d+)([ ])(\\d+)([ ])(\\d+)([ ])(\\d+)";
	

	/**
	 * Regex resolver
	 * 
	 * @param message
	 * @param pattern
	 * @return
	 */
	public static Matcher regexResolve(String message, String pattern) {

		Pattern r = Pattern.compile(pattern);
		return r.matcher(message);

	}

	/**
	 * A method which truncate the message, so only the parameters are retrieved
	 * @param message to redirect to the right function
	 */
	public static Object startsWith(String message) throws Exception {
		String starter = message.split(" ")[0];
		switch (starter) {

		case "!event":
			System.out.println("event");
			return stringToDate(message.replaceFirst("!event ", ""));
		case "!participate":
			return stringToParticipation(message.replaceFirst("!participate ", ""));
		default:
			System.out.println("Mauvaise écriture.");
			return null;

		}
	}

	/**
	 * A method validating and transforming a string into date
	 * @param message without the !participate
	 * @return [regex]!event (description) yyyy MM dd hh mm
	 */
	public static LocalDateTime stringToDate(String message) throws Exception {

		Matcher m = regexResolve(message, PATTERN_WITH_HOURS);

		if (m.groupCount() > 11 && m.find()) {
			System.out.println("pattern avec heures");
			try {
				LocalDateTime date = LocalDateTime.of(Integer.parseInt(m.group(5)), Integer.parseInt(m.group(7)),
						Integer.parseInt(m.group(9)), Integer.parseInt(m.group(11)), Integer.parseInt(m.group(13)));
				if (date.isAfter(LocalDateTime.now())) {
					return date;
				} else {
					throw new Exception("Date antérieure à la date actuelle");
				}
			} catch (Exception e) {
				throw new Exception(e.getMessage());
			}
		} else {

			m = regexResolve(message, PATTERN_WITHOUT_HOURS);

			if (m.find()) {
				System.out.println("pattern sans heures");
				try {
					LocalDateTime date = LocalDateTime.of(Integer.parseInt(m.group(5)), Integer.parseInt(m.group(7)),
							Integer.parseInt(m.group(9)), 8, 0);
					if (date.isAfter(LocalDateTime.now())) {
						return date;
					} else {
						throw new Exception("Date antérieure à la date actuelle");
					}
				} catch (Exception e) {
					throw new RegexNotRespectedException(e.getMessage());
				}
			}
		}
		throw new Exception("Message ne respectant pas les contraintes");

	}

	/**
	 * Look for the description in a message
	 * @param message
	 * @return
	 */
	public static String stringToDescription(String message) {

		Matcher m = regexResolve(message, PATTERN_WITH_HOURS);

		if (m.groupCount() > 11 && m.find()) {
			System.out.println(m.group(2));
			return m.group(2);
		} else {
			m = regexResolve(message, PATTERN_WITHOUT_HOURS);

			if (m.find()) {
				System.out.println(m.group(2));
				return m.group(2);
			}

		}
		System.out.println("Problème dans le pattern");
		return null;
	}

	/**
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
