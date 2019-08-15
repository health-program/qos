package com.paladin.qos.analysis;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class TimeUtil {

	public static final int SECONDS_IN_DAY = 60 * 60 * 24;
	public static final long MILLIS_IN_DAY = 1000L * SECONDS_IN_DAY;

	private static final TimeZone timeZone = TimeZone.getDefault();

	/**
	 * 快速判断两个时间是否同一天
	 * 
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean isSameDay(final Date d1, final Date d2) {
		if (d1 == null || d2 == null) {
			return false;
		}
		return isSameDay(d1.getTime(), d2.getTime());
	}

	/**
	 * 快速判断两个时间是否同一天
	 * 
	 * @param ms1
	 * @param ms2
	 * @return
	 */
	public static boolean isSameDay(final long ms1, final long ms2) {
		final long interval = ms1 - ms2;
		return interval < MILLIS_IN_DAY && interval > -1L * MILLIS_IN_DAY && toDay(ms1) == toDay(ms2);
	}

	/**
	 * 转换为相对（1970）天数
	 * 
	 * @param millis
	 * @return
	 */
	public static long toDay(long millis) {
		// 除以一天的毫秒数等于相对的天数，但是需要考虑时区问题
		return (millis + timeZone.getOffset(millis)) / MILLIS_IN_DAY;
	}

	/**
	 * 获取去除了时分秒的日期
	 * 
	 * @param time
	 * @return
	 */
	public static Date toDayTime(Date time) {
		if (time == null)
			return null;
		long millis = time.getTime();
		millis = millis - ((millis + timeZone.getOffset(millis)) % MILLIS_IN_DAY);
		return new Date(millis);
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {
		Date time = format.parse("2019-8-13 24:00:00");

		time = toDayTime(time);
		System.out.println(format.format(time));

	}

	public static int getSerialNumberByDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int day = c.get(Calendar.DAY_OF_MONTH);

		return year * 10000 + month * 100 + day;
	}

	
	public static int getSerialNumberByMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);

		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;

		return year * 100 + month;
	}
}
