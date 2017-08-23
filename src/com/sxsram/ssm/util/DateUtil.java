package com.sxsram.ssm.util;

import java.util.Date;

public class DateUtil {
	public static Date getCurrentDate() {
		return new Date();
	}
	
	public static int getIntervalDays(Date fDate, Date oDate) {

		if (null == fDate || null == oDate) {

			return -1;

		}

		long intervalMilli = oDate.getTime() - fDate.getTime();

		return (int) (intervalMilli / (24 * 60 * 60 * 1000));

	}
}
