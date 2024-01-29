package com.gis.util;

/**
 * String Utils
 * @author mkrout
 **/
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtils {

	static Log logger = LogFactory.getLog(StringUtils.class.getName());

	public StringUtils() {

	}
	
	public static String getSeperAdmin() {
		return "adminmk@" + getddMMYYY();
	}

	public static String checkString(String aValue) {

		if (aValue == null) {

			return "null";
		} else {

			if (aValue.equals("")) {

				return "null";
			}

			// preparing string for database insert
			String tempStr = "";
			char quot = '\'';
			if ((aValue != null) && (aValue.length() > 0)) {

				for (int i = 0; i < aValue.length(); i++) {

					char c = aValue.charAt(i);
					if (c != quot) {

						tempStr = tempStr + aValue.charAt(i);
					} else {

						tempStr = tempStr + aValue.charAt(i) + aValue.charAt(i);
					}
				}

				aValue = tempStr;
			}

			// else {
			aValue = "'" + aValue + "'";

			return aValue;

			// }
		}
	}

	public static String date2str(Date d) {

		String strDate = null;
		if (d != null) {

			try {

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY hh:mm");
				strDate = formatter.format(d);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}

		return strDate;
	}

	public static String date2ddMMYYYY(Date d) {

		String strDate = null;
		if (d != null) {

			try {

				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/YYYY");
				strDate = formatter.format(d);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}

		return strDate;
	}

	public static String maxDateForCalendar(Date d) {

		String strDate = null;
		if (d != null) {

			try {

				SimpleDateFormat formatter = new SimpleDateFormat("YYYY/MM/dd");
				strDate = formatter.format(d);
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}

		return strDate;
	}

	public static String getUniqueFileName() {

		String strTimeStamp = null;
		Calendar cal = Calendar.getInstance();
		if (cal != null) {

			try {

				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyyMMdd");
				SimpleDateFormat formatter2 = new SimpleDateFormat("hhmmss");
				strTimeStamp = formatter1.format(cal.getTime());
				strTimeStamp = strTimeStamp + "_" + formatter2.format(cal.getTime());
			} catch (Exception e) {

				logger.error(e.getMessage());
			}
		}

		return strTimeStamp;
	}

	public static String getddMMYYY() {

		String str = null;

		try {

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("ddMMYYYY");

			str = sdf.format(today);
		} catch (Exception e) {

			logger.error(e.getMessage());
		}

		return str;
	}

	public static String getToday() {

		String str = null;

		try {

			Date today = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.YYYY");

			str = sdf.format(today);
		} catch (Exception e) {

			logger.error(e.getMessage());
		}

		return str;
	}

	public static int s2i(String s) {

		try {

			StringBuffer b = new StringBuffer();
			for (int j = 0; j < s.length(); j++) {

				char c = s.charAt(j);
				if ((c >= '0') && (c <= '9')) {

					b.append(c);
				}

				if (c == '.') {

					break;
				}
			}

			Integer i = new Integer(b.toString());

			return i.intValue();
		} catch (Exception _ex) {

			return -1;
		}
	}

	public static String i2s(int val) {

		try {

			Integer i = new Integer(val);

			return i.toString();
		} catch (Exception _ex) {

			return "";
		}
	}

	public static double s2d(String s) {

		try {

			StringBuffer b = new StringBuffer();
			for (int j = 0; j < s.length(); j++) {

				char c = s.charAt(j);
				if ((c >= '0') && (c <= '9')) {

					b.append(c);
				}

				if (c == '.') {

					break;
				}
			}

			Double i = new Double(b.toString());

			return i.doubleValue();
		} catch (Exception _ex) {

			return -1;
		}
	}

	public static String nullReplaceWithEmpty(String aValue) {

		if ((aValue == null) || (aValue.trim().equalsIgnoreCase("null"))) {

			return "";
		} else {

			return aValue.trim();
		}
	}

	public static String toMySQLDate(String s) {

		if (s == null) {
			return s;
		} else if (s.equals("")) {
			return null;
		} else {
			return "STR_TO_DATE(" + checkString(s) + ",'%d/%m/%Y')";
		}
	}

	public static String emptyReplaceWithNull(String aValue) {
		if (aValue == null || (aValue.trim().length() == 0))

			return null;
		else

			return aValue.trim();

	}

	public static Date str2SQLDate(String strDate) {
		if (strDate == null || strDate.trim().equalsIgnoreCase("")) {
			return null;
		}

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		java.util.Date dateJava = null;
		Date date = null;
		try {
			dateJava = sdf.parse(strDate);
			date = new Date(dateJava.getTime());

		} catch (Exception e) {
			return null;
		}

		return date;
	}

	public static String SQLDate2str(Date sqlDate) {
		try {
			if (sqlDate != null) {
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

				return sdf.format(sqlDate).toString();
			} else

				return null;

		} catch (Exception e) {
			return null;
		}

	}

	public static String str2Datetime(String strDate) {
		if (strDate == null || strDate.trim().equalsIgnoreCase("")) {
			return null;
		}
		String pattern = "yyyy-MM-dd HH:mm";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		java.util.Date dateJava = null;
		String date = null;
		try {
			dateJava = simpleDateFormat.parse(strDate);
			date = DateFormat.getDateTimeInstance().format(dateJava);
		} catch (Exception e) {
			return null;
		}

		return date;
	}
	
	public static String getTodayDateTime() {
		String strDate = null;
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMYYYYHHmmss");
			strDate = formatter.format(new Date());
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return strDate;
	}
}
