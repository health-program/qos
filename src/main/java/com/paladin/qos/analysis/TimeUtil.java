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

	public static long getIntervalDays(long start, long end) {
		start = start - ((start + timeZone.getOffset(start)) % MILLIS_IN_DAY);
		long endd = (end + timeZone.getOffset(end)) % MILLIS_IN_DAY;
		if (endd == 0) {
			return (end - start) / MILLIS_IN_DAY;
		} else {
			return (end - endd - start) / MILLIS_IN_DAY + 1;
		}
	}

	public static int getSerialNumberByDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getSerialNumberByDay(c);
	}

	public static int getSerialNumberByDay(Calendar c) {
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

	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	public static boolean isToday(Date time) {
		if (time == null)
			return false;

		long millis = time.getTime();
		millis = millis - ((millis + timeZone.getOffset(millis)) % MILLIS_IN_DAY);

		long nowMillis = System.currentTimeMillis();
		nowMillis = nowMillis - ((nowMillis + timeZone.getOffset(nowMillis)) % MILLIS_IN_DAY);

		return millis == nowMillis;
	}

	public static boolean isAfterOrEqualToday(Date time) {
		if (time == null)
			return false;

		long millis = time.getTime();
		millis = millis - ((millis + timeZone.getOffset(millis)) % MILLIS_IN_DAY);

		long nowMillis = System.currentTimeMillis();
		nowMillis = nowMillis - ((nowMillis + timeZone.getOffset(nowMillis)) % MILLIS_IN_DAY);

		return millis >= nowMillis;
	}

	private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static void main(String[] args) throws Exception {
		Date time = format.parse("2019-8-13 00:00:00");
		Date time2 = format.parse("2019-8-15 01:00:00");
		System.out.println(getIntervalDays(time.getTime(),time2.getTime()));
	}
}
