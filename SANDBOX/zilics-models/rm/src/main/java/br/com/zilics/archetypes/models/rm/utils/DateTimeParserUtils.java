package br.com.zilics.archetypes.models.rm.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateTimeParserUtils {
	private Locale locale;
	private SimpleDateFormat dateParser = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
	private SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	private static DateTimeParserUtils instance;
	private final String REGULAR_EXPRESSION = "(\\d\\d\\d\\d-\\d\\d-\\d\\d)\\w(\\d\\d:\\d\\d)\\w\\w\\w";
	private Pattern pattern = Pattern.compile(REGULAR_EXPRESSION, Pattern.MULTILINE);

	private DateTimeParserUtils() {
		this.dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	}

	private DateTimeParserUtils(Locale locale) {
		this.setLocale(locale);
		this.dateFormatter = new SimpleDateFormat("dd/MM/yyyy HH:mm", locale);
	}

	public static void init(Locale locale) {
		if (instance == null || instance.getLocale() == null || !instance.getLocale().equals(locale))
			instance = new DateTimeParserUtils(locale);
	}

	public static String parseDate(String date, String pattern) {
		if (instance == null) {
			instance = new DateTimeParserUtils();
		}
		instance.dateFormatter = new SimpleDateFormat(pattern);

		return parseDate(date);
	}

	public static String parseDate(String date) {

		if (instance == null) {
			instance = new DateTimeParserUtils();
		}

		Matcher m = instance.pattern.matcher(new StringBuffer(date));
		if (m.find())
			try {
				return instance.dateFormatter.format(instance.dateParser.parse(m.group(1) + "-" + m.group(2)));
			} catch (ParseException e) {
				e.printStackTrace();

			}
		return date;

	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}

}
