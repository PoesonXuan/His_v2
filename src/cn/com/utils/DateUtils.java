package cn.com.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtils {
	/**
	 * * 判断输入的年月日日期是否属于休息日
	 * 
	 * @param date
	 *            需要判断的日期（年月日）
	 * @param lawHolidayList
	 *            国家规定放假的时间
	 * @param lawWorkList
	 *            国家规定的工作日期 *
	 * @return
	 */
	public static boolean isDayOff(Date date, List<Date> lawHolidayList, List<Date> lawWorkList) {
		for (Date date1 : lawHolidayList) {
			int c = date.compareTo(date1);
			if (c == 0) {
				// 休息日
				return true;
			}
		}
		for (Date date1 : lawWorkList) {
			int c = date.compareTo(date1);
			if (c == 0) {
				// 工作日
				return false;
			}
		}
		return isZhouLiuZhouRiDate(date);
	}

	/**
	 * * 判断时间是否属于正常周六日 *
	 * 
	 * @param date
	 *            *
	 * @return *
	 */
	public static boolean isZhouLiuZhouRiDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
		// 是否属于周六日
		boolean flag = (week == 0 || week == 6);
		return flag;
	}

	
	  /** 
     * 获得指定日期的前一天 
     *  
     * @param specifiedDay 
     * @return 
     * @throws Exception 
     */  
    public static Date getSpecifiedDayBefore(Date date) {//可以用new Date().toLocalString()传递参数  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        int day = c.get(Calendar.DATE);  
        c.set(Calendar.DATE, day - 1);  
  
        return c.getTime();  
    }  
  
    /** 
     * 获得指定日期的后一天 
     *  
     * @param specifiedDay 
     * @return 
     */  
    public static Date getSpecifiedDayAfter(Date date) {  
        String dayAfter = null;
		Calendar c = Calendar.getInstance();  
		c.setTime(date);  
		int day = c.get(Calendar.DATE);  
		c.set(Calendar.DATE, day + 1);  
        return c.getTime();  
    }  
	
	
	/**
	 * * 排除国家法定的休息日、正常周六日， 计算两个时间相差多少小时数（休息日当天时间为零处理） *
	 * 
	 * @param startTimeYYYYMMDDHHMMSS
	 *            年月日时分秒 *
	 * @param endTimeYYYYMMDDHHMMSS
	 *            年月日时分秒 *
	 * @param lawHolidayList
	 *            *
	 * @param lawWorkList
	 *            *
	 * @return
	 */
	public static long workHours(Date startTimeYYYYMMDDHHMMSS, Date endTimeYYYYMMDDHHMMSS, List<Date> lawHolidayList,
			List<Date> lawWorkList) throws Exception {
		// 开始时间转成年月日格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String strStartTimeYYYYMMDD = sdf.format(startTimeYYYYMMDDHHMMSS);
		Date startTimeYYYYMMDD = sdf.parse(strStartTimeYYYYMMDD);
		// 开始时间是否属于休息日
		boolean startTimeIsDayOff = isDayOff(startTimeYYYYMMDD, lawHolidayList, lawWorkList);
		// 结束时间转成年月日格式
		String strEndTimeYYYYMMDD = sdf.format(endTimeYYYYMMDDHHMMSS);
		Date endTimeYYYYMMDD = sdf.parse(strEndTimeYYYYMMDD);
		// 结束时间是否属于休息日
		boolean endTimeIsDayOff = isDayOff(endTimeYYYYMMDD, lawHolidayList, lawWorkList);
		// 分为4种情况
		if (startTimeIsDayOff) {
			if (!endTimeIsDayOff) {
				// 开始时间在休息日里，结束时间不在休息日里（开始那天不计算小时数，结束那天计算小时数）
				Calendar cal = Calendar.getInstance();
				cal.setTime(startTimeYYYYMMDD);
				cal.add(Calendar.DAY_OF_MONTH, +1);
				Date validStartTimeYYYYMMDD = cal.getTime();
				Date validStartTimeYYYYMMDDTemp = validStartTimeYYYYMMDD;
				int skipDay = 0;
				// 循环遍历开始时间之后的每一个日期
				while (validStartTimeYYYYMMDDTemp.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
					if (isDayOff(validStartTimeYYYYMMDDTemp, lawHolidayList, lawWorkList)) {
						skipDay += 1;
					}
					cal.add(Calendar.DAY_OF_MONTH, +1);
					validStartTimeYYYYMMDDTemp = cal.getTime();
				}
				return ((endTimeYYYYMMDDHHMMSS.getTime() - validStartTimeYYYYMMDD.getTime()) / (60 * 60 * 1000))
						- skipDay * 24;
			} else {
				// 开始时间在休息日里，结束时间也在休息日里（开始那天不计算小时数，结束那天也不计算小时数，看中间有多少个工作日）
				Calendar cal = Calendar.getInstance();
				cal.setTime(startTimeYYYYMMDD);
				cal.add(Calendar.DAY_OF_MONTH, +1);
				Date validStartTimeYYYYMMDD = cal.getTime();
				// 工作日天数
				int workDays = 0;
				// 循环遍历开始时间之后的每一个日期
				while (validStartTimeYYYYMMDD.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
					if (!isDayOff(validStartTimeYYYYMMDD, lawHolidayList, lawWorkList)) {
						workDays += 1;
					}
					cal.add(Calendar.DAY_OF_MONTH, +1);
					validStartTimeYYYYMMDD = cal.getTime();
				}
				return workDays * 24;
			}
		} else {
			if (endTimeIsDayOff) {
				int skipDay = 0;
				// 开始时间不在休息日里，结束时间在休息日里
				Calendar cal = Calendar.getInstance();
				cal.setTime(startTimeYYYYMMDD);
				cal.add(Calendar.DAY_OF_MONTH, +1);
				Date validStartTimeYYYYMMDD = cal.getTime();
				while (validStartTimeYYYYMMDD.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
					if (!isDayOff(validStartTimeYYYYMMDD, lawHolidayList, lawWorkList)) {
						skipDay += 1;
					}
					cal.add(Calendar.DAY_OF_MONTH, +1);
					validStartTimeYYYYMMDD = cal.getTime();
				}
				Calendar ca = Calendar.getInstance();
				ca.setTime(startTimeYYYYMMDDHHMMSS);
				int startHour = ca.get(Calendar.HOUR_OF_DAY);
				return (24 - startHour) + skipDay * 24;
			} else {
				// 开始时间在不在休息日里，结束时间也不在休息日里
				int skipDay = 0;
				Calendar cal = Calendar.getInstance();
				cal.setTime(startTimeYYYYMMDD);
				cal.add(Calendar.DAY_OF_MONTH, +1);
				Date validStartTimeYYYYMMDD = cal.getTime();
				while (validStartTimeYYYYMMDD.compareTo(endTimeYYYYMMDDHHMMSS) != 1) {
					if (isDayOff(validStartTimeYYYYMMDD, lawHolidayList, lawWorkList)) {
						skipDay += 1;
					}
					cal.add(Calendar.DAY_OF_MONTH, +1);
					validStartTimeYYYYMMDD = cal.getTime();
				}
				return ((endTimeYYYYMMDDHHMMSS.getTime() - startTimeYYYYMMDDHHMMSS.getTime()) / (60 * 60 * 1000))
						- skipDay * 24;
			}
		}
	}

	public static void main(String args[]) throws Exception {
		SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
		List<Date> lawHolidayDate = new ArrayList<>();
		List<Date> lawWorkDate = new ArrayList<>();
		String[] lawHolidayDateStr = new String[] { "2018-01-01", "2018-02-15", "2018-02-16", "2018-02-17",
				"2018-02-18", "2018-02-19", "2018-02-20", "2018-02-21", "2018-04-05", "2018-04-06", "2018-04-07",
				"2018-04-29", "2018-04-30", "2018-05-01", "2018-06-16", "2018-06-17", "2018-06-18", "2018-09-22",
				"2018-09-23", "2018-09-24", "2018-10-01", "2018-10-02", "2018-10-03", "2018-10-04", "2018-10-05",
				"2018-10-06", "2018-10-07", "2018-12-30", "2018-12-31", "2019-01-01", "2019-02-04", "2019-02-05",
				"2019-02-06", "2019-02-07", "2019-02-08", "2019-02-09", "2019-02-10", "2019-04-05", "2019-04-06",
				"2019-04-07", "2019-05-01", "2019-06-07", "2019-06-08", "2019-06-09", "2019-09-13", "2019-09-14",
				"2019-09-15", "2019-10-01", "2019-10-02", "2019-10-03", "2019-10-04", "2019-10-05", "2019-10-06",
				"2019-10-07" };
		String[] lawWorkDateStr = new String[] { "2018-02-11", "2018-02-24", "2018-04-08", "2018-04-28", "2018-09-29",
				"2018-09-30", "2018-12-29", "2019-02-02", "2019-02-03", "2019-09-29", "2019-10-12" };
		for (String str : lawHolidayDateStr) {
			lawHolidayDate.add(yyyyMMdd.parse(str));
		}
		for (String str : lawWorkDateStr) {
			lawWorkDate.add(yyyyMMdd.parse(str));
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date startDate = sdf.parse("2018-02-22 8:45:10");
		Date endDate = sdf.parse("2018-02-24 7:10:10");
		System.out.println("相差小时数：" + workHours(startDate, endDate, lawHolidayDate, lawWorkDate));
	}
	// }}}

}
