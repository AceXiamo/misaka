package com.misaka.utils2;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xiamo
 * @Description:
 * @ClassName: DateUtil
 * @date 2021/11/26 13:26
 */
public class DateUtils {

    public static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "HH:mm:ss","yyyyMMddHHmmss"};

    /**
     * 获取两个日期之间的天数
     *
     * @param before
     * @param after
     * @return
     */
    public static double getDistanceOfTwoDate(Date before, Date after) {
        long beforeTime = before.getTime();
        long afterTime = after.getTime();
        return (afterTime - beforeTime) / (1000 * 60 * 60 * 24);
    }

    /**
     * 返回默认日期 1900-01-01 00:00:00
     * @return
     */
    public static Date getDefaultDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(1900,01,01,0,0,0);
        return calendar.getTime();
    }

    /**
     * 转换成长日期格式yyyy-mm-dd
     * @param date
     * @return
     */
    public static String toLongDate(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(date == null) {
            date = getDefaultDate();
        }
        return format.format(date);
    }

    /**
     * 转换成长日期格式yyyy-mm-dd
     * @param date
     * @return
     */
    public static String toLongDate2(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        if(date == null){
            return null;
        }else{
            return format.format(date);
        }
    }
    /**
     * 转换成日期和时间格式 yyyy-mm-dd hh:MM:ss
     * @param date
     * @return
     */
    public static String toDateTime(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(date == null)
            date = getDefaultDate();
        return format.format(date);
    }

    /**
     * 日期运算
     * @param org
     * @param sum
     * @return
     */
    public static Date addDate(Date org,int sum){
        if(org == null)
            org = getDefaultDate();
        Calendar cal = Calendar.getInstance();
        cal.setTime(org);
        cal.add(Calendar.DAY_OF_YEAR, sum);
        return cal.getTime();
    }

    /**
     * 将字符日期(yyyy-mm-dd)转换成日期
     * @param strDate
     * @return 转换失败返回null
     */
    public static Date strToLongDate(String strDate){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        Date dResult = null;
        try {
            dResult = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dResult;
    }

    /**
     * 根据传入的时间得到当前月第一天
     * @param strDate yyyy-MM-dd格式的日期
     * @return
     */
    public static Date getMonthFirstDate(String strDate){
        Date dtCurrent = null;
        if (StringUtils.isBlank(strDate)) {
            dtCurrent = new Date(System.currentTimeMillis());
        } else {
            dtCurrent = strToLongDate(strDate);
        }
        return getMonthFirstDate(dtCurrent);
    }

    /**
     * 根据传入的时间得到当前月第一天
     * @param date yyyy-MM-dd格式的日期
     * @return
     */
    public static Date getMonthFirstDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int minDate = cal.getActualMinimum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, minDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 根据传入的时间得到当前月最后一天最后时刻
     * @param strDate yyyy-MM-dd格式的日期
     * @return
     */
    public static Date getMonthLastDate(String strDate){
        Date dtCurrent = null;
        if (strDate == null) {
            dtCurrent = new Date(System.currentTimeMillis());
        } else {
            dtCurrent = strToLongDate(strDate);
        }
        return getMonthLastDate(dtCurrent);
    }

    /**
     * 根据传入的时间得到当前月最后一天最后时刻
     * @param date yyyy-MM-dd格式的日期
     * @return
     */
    public static Date getMonthLastDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DAY_OF_MONTH, maxDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 得到月份
     * @param dtStart
     * @return
     */
    public static int getMonth(Date dtStart) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtStart);
        //cal.add(Calendar.MONTH, 1);
        return cal.get(Calendar.MONTH)+1;
    }
    /**
     * 得到天
     * @param dtStart
     * @return
     */
    public static int getDay(Date dtStart) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtStart);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 时间戳转时间
     * @param seconds 秒数
     * @param format 格式
     * @return
     */
    public static Date timestampParseDate(Integer seconds,String format) {
        if(seconds == null ){
            return null;
        }
        if(format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return getFormatDate(sdf.format(new Date(Long.valueOf(seconds+"000"))), format);
    }

    /**
     * 得到年份
     * @param dtStart 传入时间
     * @param defaultYear 默认年份
     * @return
     */
    public static String getYear(Date dtStart, String defaultYear) {
        if (dtStart == null) {
            return defaultYear;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtStart);
        return String.valueOf(cal.get(Calendar.YEAR));
    }

    /**
     * 得到年份
     * @param dtStart
     * @return
     */
    public static int getYear(Date dtStart) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtStart);
        return cal.get(Calendar.YEAR);
    }

    /**
     * 得到当前年份
     * @return
     */
    public static int getCurrentYear() {
        return getYear(new Date());
    }

    /**
     * 得到当前月份
     * @return
     */
    public static int getCurrentMonth() {
        return getMonth(new Date());
    }
    /**
     * 得到当前天
     * @return
     */
    public static int getCurrentDay() {
        return getDay(new Date());
    }
    /**
     * 根据时间以及对应的格式得到Date
     * @param strDate
     * @param pattern
     * @return
     */
    public static Date getFormatDate(String strDate, String pattern) {
        DateFormat format = new SimpleDateFormat(pattern);
        Date dResult = null;
        try {
            dResult = format.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dResult;
    }

    /**
     * 根据传入的Date对象及格式返回字符型日期
     * @param occurTime
     * @param pattern
     * @return
     */
    public static String getFormatDate(Date occurTime, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(occurTime);
    }

    /**
     * 得到默认格式化的日期
     * @param occurTime
     * @return
     */
    public static String getDefaultFormatDate(Date occurTime) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(occurTime);
    }

    /**
     * 根据月份及本月的第几天得到格式化的时间
     * @param month 月份，注意：1月为0，2月为1，以此类推
     * @param day 每月的第几天 注意：第一天为1，以此类推
     * @param pattern 格式
     * @return
     */
    public static String getFormatDateByMonth(int month, int day, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DATE, day);
        return df.format(calendar.getTime());
    }

    /**
     * 判断是否属于最后一天
     * @param strDate 时间字符串
     * @param pattern 上述时间字符串的格式
     * @return
     */
    public static boolean isMonthLastDay(String strDate, String pattern) {
        Date dt = getFormatDate(strDate, pattern);
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int currentDate = cal.get(Calendar.DATE);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        if (maxDate == currentDate){
            return true;
        }
        return false;
    }

    /**
     * 计算两个时间段之间包括几天
     * @param strStartDate
     * @param strEndDate
     * @return
     */
    public static int getDateOffset(String strStartDate, String strEndDate) {
        return getDateOffset(strToLongDate(strStartDate), strToLongDate(strEndDate));
    }

    /**
     * 计算两个时间段之间包括几天
     * @param dtStartDate
     * @param dtEndDate
     * @return
     */
    public static int getDateOffset(Date dtStartDate, Date dtEndDate) {
        long quot = 0;
        dtStartDate = DateUtils.clearTime(dtStartDate);
        dtEndDate = DateUtils.clearTime(dtEndDate);
        quot = dtEndDate.getTime() - dtStartDate.getTime();
        quot = quot/1000/60/60/24;
        return (int) quot;
    }

    /**获取给定日期往前/后偏移X天的日期
     * @param dtStartDate
     * @param days
     * @param dirc 为1表示未来的日期，为-1表示过去的日期
     * @return
     */
    public static Date getDateByDays(Date dtStartDate,int days,int dirc){
        long targetDate;
        long quot = dtStartDate.getTime();
        if(dirc == 1){
            targetDate = quot + 24*60*60*1000l*days;
        }else{
            targetDate = quot - 24*60*60*1000l*days;
        }
        return new Date(targetDate);
    }

    /**
     * 计算两个时间段之间包括几年
     * @param dtStartDate
     * @param dtEndDate
     * @return 年数
     */
    public static int getYearOffset(Date dtStartDate, Date dtEndDate) {
        int years = Math.abs(getMonthOffset(dtStartDate, dtEndDate) / 12);
        return years;
    }

    /**
     * 计算两个时间段之间包括几月
     * @param dtStartDate
     * @param dtEndDate
     * @return 月数
     */
    public static int getMonthOffset(Date dtStartDate, Date dtEndDate) {
        int offset = 0;
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtStartDate);
        int iStartDay = cal.get(Calendar.DATE);
        int iStart = cal.get(Calendar.MONTH);
        int iStartYear = cal.get(Calendar.YEAR);
        cal.setTime(dtEndDate);
        int iEndDay = cal.get(Calendar.DATE);
        int iEnd = cal.get(Calendar.MONTH);
        int iEndYear = cal.get(Calendar.YEAR);
        if (iStartYear == iEndYear) {
            offset = iEnd - iStart;
        } else {
            if(iEndDay >= iStartDay) {
                offset = iEnd + 12 * (iEndYear - iStartYear) - iStart;
            }else{
                offset = iEnd + 12 * (iEndYear - iStartYear) - (iStart + 1);
            }
        }
        return offset;
    }

    /**
     * 得到相差offset天的时间
     * @param date
     * @param offset
     * @return
     */
    public static String getDateByOffset(String date, int offset) {
        SimpleDateFormat sdf = new SimpleDateFormat("");
        sdf.applyPattern("yyyy-MM-dd");
        return sdf.format(getDateByOffset(strToLongDate(date), offset));
    }

    /**
     * 得到相差offset天的时间
     * @param date
     * @param offset
     * @return
     */
    public static Date getDateByOffset(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, offset);
        return cal.getTime();
    }

    /**
     * 得到相差offset周的日期
     * @param date
     * @param offset
     * @return
     */
    public static Date getWeekByOffset(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.WEEK_OF_YEAR, offset);
        return cal.getTime();
    }

    /**
     * 得到相差offset月的时间
     * @param date
     * @param offset
     * @return
     */
    public static Date getMonthByOffset(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, offset);
        return cal.getTime();
    }

    /**
     * 得到相差offset月的时间
     * @param year
     * @param month
     * @return
     */
    public static String[] getLastMonthYear(String year,String month) {
        Date  date = getDateByDefaultFmt(year+"-"+month+"-01");
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        String result = getFormatDate(cal.getTime(),"yyyyMMdd");
        return new String[]{result.substring(0, 4),result.substring(4, 6)};
    }

    /**
     * 得到指定日期的上个年、月
     * @param date
     * @return
     */
    public static String[] getLastMonthYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, -1);
        String result = getFormatDate(cal.getTime(),"yyyyMMdd");
        return new String[]{result.substring(0, 4),result.substring(4, 6)};
    }

    /**
     * 根据"YYYY-MM-DD"的格式，通过字符转换为日期
     * @param strDate
     * @return
     */
    public static Date getDateByDefaultFmt(String strDate){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 判断日期格式是否正确
     * @param strDate
     * @param format
     * @return
     */
    public static boolean isValidDateFmt(String strDate, String format){
        if(StringUtils.isBlank(strDate)){
            return false;
        }
        boolean result = false;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.parse(strDate);
            result = true;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }
    /**
     * 根据的格式，通过字符转换为日期
     * @param strDate
     * @return
     */
    public static Date getDateByDefaultFmt(String strDate, String format){
        Date date = null;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 得到往前推offset个月的时间
     * @param date
     * @param offset
     * @return
     */
    public static Date getDateByMonthOffset(int offset,Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int months = offset%12;
        int years = offset/12;
        //年
        if(years > 0){
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR)-years);
        }
        //月
        cal.set(Calendar.MONTH, cal.get(Calendar.MONTH)-months);
        return cal.getTime();
    }

    /**
     * 得到往后推offset个月的时间
     * @param date
     * @param offset
     * @return
     */
    public static Date getAfterByMonthOffset(int offset,Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH,-offset);
        return  c.getTime();
    }
    /**
     * 得到相差offset年的时间
     * @param date
     * @param offset
     * @return
     */
    public static Date getYearByOffset(Date date, int offset) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, offset);
        return cal.getTime();
    }

    /**
     * 得到当天的第一时刻
     * @param dtStart
     * @return
     */
    public static Date getDayFirstTime(Date dtStart) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtStart);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到当天的最后时刻
     * @param dtEnd
     * @return
     */
    public static Date getDayLastTime(Date dtEnd) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dtEnd);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 得到本周的第一天
     * @param date
     * @return
     */
    public static Date getWeekFirstDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 当传入时间为周日时，处理为上个星期的星期一(原因：国外星期日为一周的第一天，中国星期一是一周第一天)
        if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_YEAR, 0 - (cal.get(Calendar.DAY_OF_WEEK)-2));
        }else{
            cal.add(Calendar.DAY_OF_YEAR, -6);
        }
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 得到本周的最后一天
     * @param date
     * @return
     */
    public static Date getWeekLastDate(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            cal.add(Calendar.DAY_OF_YEAR, 8-cal.get(Calendar.DAY_OF_WEEK));
        }
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 得到当年的第一天
     * @param year
     * @return
     */
    public static Date getYearFirstDate(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置年初的日期为1月1日
        return cal.getTime();
    }

    /**
     * 设置年末的日期为12月31日,如为当前年，则只取到当天即可。
     * @param year
     * @return
     */
    public static Date getYearLastDate(int year) {
        //如年份为当年则取今天的年月日
        if(getCurrentYear() == year){
            return new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);////设置年末的日期为12月31日
        return cal.getTime();
    }

    /**
     * 得到当年的第一天
     * @param year
     * @return
     */
    public static String getYearFirstDateStr(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);//设置年初的日期为1月1日
        return getDefaultFormatDate(cal.getTime());
    }

    /**
     * 设置年末的日期为12月31日,如为当前年，则只取到当天即可。
     * @param year
     * @return
     */
    public static String getYearLastDateStr(int year) {
        //如年份为当年则取今天的年月日
        if(getCurrentYear() == year){
            return getTodayStr();
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, 11);
        cal.set(Calendar.DAY_OF_MONTH, 31);////设置年末的日期为12月31日
        return getDefaultFormatDate(cal.getTime());
    }

    /**
     * 得到当前年的第一天
     * @param date
     * @return
     */
    public static Date getYearFirstDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.JANUARY);
        int minDate = cal.getActualMinimum(Calendar.DATE);
        cal.set(Calendar.DATE, minDate);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取指定时间的前一年的时间
     * @param date 指定时间
     */
    public static Date getLastYearTime(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        Date lastYear = c.getTime();
        return lastYear;
    }

    /**
     * 获取指定时间的前一年的时间
     * @param date 指定时间
     * @param year 前后年份 如1 下一年，-1上一年
     */
    public static Date getLastYearTime(Date date, int year) {
        if(year == 0) {
            return date;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, year);
        Date lastYear = c.getTime();
        return lastYear;
    }

    /**
     * 得到当前年的最后一天
     * @param date
     * @return
     */
    public static Date getYearLastDate(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.MONTH, Calendar.DECEMBER);
        int maxDate = cal.getActualMaximum(Calendar.DATE);
        cal.set(Calendar.DATE, maxDate);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 计算某天是当年的第几天
     * @param date
     * @return
     */
    public static int getDayOfYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_YEAR);
    }

    /**
     * 获得只有时分秒清零的Calendar
     * @return
     */
    public static Calendar getShortDateCalendar(){
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        return cal;
    }

    /**
     * 将传入日期的时分秒清零
     * @param date
     * @return
     */
    public static Date clearTime(Date date){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date cDate = null;
        try {
            cDate = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return cDate;
    }

    /**
     * 计算某天是当周的第几天
     * @param date
     * @return
     */
    public static int getDayOfWeek(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 计算某天是当月的第几天
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算某天是当年的第几周
     * @param date
     * @return
     */
    public static int getWeekOfYear(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 得到传入日期的小时
     * @param date
     * @return
     */
    public static int getHourOfDay(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 得到传入日期的小时
     * @param date
     * @return
     */
    public static int getMinuteOfHour(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal.get(Calendar.MINUTE);
    }

    public static String getWeekName(Date date) {
        String weekName = null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        switch(calendar.get(Calendar.DAY_OF_WEEK)) {
            case 2:
                weekName = "星期一";
                break;
            case 3:
                weekName = "星期二";
                break;
            case 4:
                weekName = "星期三";
                break;
            case 5:
                weekName = "星期四";
                break;
            case 6:
                weekName = "星期五";
                break;
            case 7:
                weekName = "星期六";
                break;
            case 1:
                weekName = "星期日";
                break;
        }
        return weekName;
    }

    /**
     * 格式化日期
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, String pattern) {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(date);
    }

    public static String getTodayStr(){
        return formatDate(new Date(), "yyyy-MM-dd");
    }

    public static String getTodayStr(String format){
        return formatDate(new Date(), format);
    }

    public static String getNowStr(){
        return 		formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
    }

    public static String getNowStr2(){
        return 		formatDate(new Date(), "yyyy-MM-dd HH:mm");
    }



    /** 获取当前时间戳
     * @return
     */
    public static Timestamp getTimeStampNow(){
        return new Timestamp(new Date().getTime());
    }

    /** 获取当前时间戳
     * @return
     */
    public static String getCurTimeStamp(){
        return String.valueOf(new Date().getTime());
    }




    /**
     * @param date
     * @return
     */
    public static Date[] getQuarterDate(Date date) {    //根据当前日期返回当前季度的开始和结束日期
        Date[] dateArr = new Date[2];
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if ((cal.get(Calendar.MONTH) + 1) % 3 == 0){// 季度结束月
            cal.add(Calendar.MONTH, -2);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            dateArr[0]=cal.getTime(); //季度开始时间

            cal.add(Calendar.MONTH, 3);
            cal.set(Calendar.DAY_OF_MONTH, 0);
            dateArr[1]=cal.getTime(); //季度结束时间
        } else if ((cal.get(Calendar.MONTH) + 2) % 3 == 0){ // 季度中间月
            cal.add(Calendar.MONTH, -1);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            dateArr[0]=cal.getTime(); //季度开始时间

            cal.add(Calendar.MONTH, 3);
            cal.set(Calendar.DAY_OF_MONTH, 0);
            dateArr[1]=cal.getTime(); //季度结束时间
        } else if ((cal.get(Calendar.MONTH) + 3) % 3 == 0){ // 季度起始月
            cal.add(Calendar.MONTH, 0);
            cal.set(Calendar.DAY_OF_MONTH, 1);
            dateArr[0]=cal.getTime(); //季度开始时间

            cal.add(Calendar.MONTH, 3);
            cal.set(Calendar.DAY_OF_MONTH, 0);
            dateArr[1]=cal.getTime(); //季度结束时间
        }
        return dateArr;
    }

    /**
     * @param begin 开始日期
     * @param end   结束日期
     * @return
     */
    public static String[] getYearAndMoths(Date begin,Date end){
        int length = getMonthOffset(begin,end);
        String[] result = new String[length+1];
        // 1计算跨度
        for(int i=0;i<=length;i++ ){
            result[i] = getFormatDate(getMonthByOffset(begin,i), "yyyy-MM");
        }
        return result;
    }

    /**
     * 判断date1是否在date2的前面
     * @param date1
     * @param date2
     * @return
     */
    public static boolean before(Date date1,Date date2){
        if(date1==null || date2==null){
            return false;
        }
        return date1.before(date2);
    }

    /**
     * 通过时间秒毫秒数判断两个时间的间隔
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDaysByMillisecond(Date date1,Date date2)
    {
        int days = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return days;
    }

    /**
     * 判断date1是否在date2的后面
     * @param date1
     * @param date2
     * @return
     */
    public static boolean after(Date date1,Date date2){
        if(date1==null || date2==null){
            return false;
        }
        return date1.after(date2);
    }

    public static String longToDateStr(Long longTime , String format) {
        DateFormat sf = new SimpleDateFormat(format);
        return sf.format(longTime);
    }

    public static String longToDateStr(Long longTime) {
        DateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sf.format(longTime);
    }

    public static String getDateTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(parsePatterns[7]);
        return df.format(date);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param user_time 时间字符串
     * @return 时间戳
     */
    public static String getTime(String user_time) {
        String re_time = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d;
        try {
            d = sdf.parse(user_time);
            long l = d.getTime() / 1000;
            re_time = String.valueOf(l);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return re_time;
    }

    /**
     * 获取某个日期距离当前日多少天
     */
    public static String getDateDay(Date date) {
        long s1=date.getTime();//将时间转为毫秒
        long s2=System.currentTimeMillis();//得到当前的毫秒
        long  day=(s2-s1)/1000/60/60/24;
        return String.valueOf(day);
    }

    /**
     * 获取当前日期的字符串 如  20171116
     * @return
     */
    public static String getCurrentTime_yyyyMMddHHmmss(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
    /**
     * 获取当前日期的字符串 如  20171116
     * @return
     */
    public static String getCurrentTime_yyyyMMdd(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前日期的字符串(毫秒) 如
     * @return
     */
    public static String getCurrentTime_yyyyMMddHHmmssSSS(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        return sdf.format(new Date());
    }

    //获取当月第一天
    public static String beforeFirstDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //获取前月的第一天
        Calendar cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, 0);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format.format(cal_1.getTime());
        return firstDay;
    }

    //获取当月最后一天
    public static String beforeLastDay(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,cale.getActualMaximum(Calendar.DAY_OF_MONTH));//设置为1号,当前日期既为本月第一天
        String lastDay = format.format(cale.getTime());
        return lastDay;
    }

    /**
     * 时间比较，相差年份
     * @param date1
     * @param date2
     * @param type
     * @return
     * @throws ParseException
     */
    public static int getMonthNum(String date1,String date2,int type) throws ParseException{
        int result = 0;
        SimpleDateFormat sdf = new SimpleDateFormat(type==1?"yyyy":"yyyy-MM");

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();

        c1.setTime(sdf.parse(date1));
        c2.setTime(sdf.parse(date2));

        if(type==1){
            result = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            //System.out.println("日期："+date1+"|"+date2+"|,相差"+Math.abs(result)+"年");
            return result == 0 ? 1 : Math.abs(result);
        }else{
            result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
            int month = (c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR)) * 12;
            //System.out.println("日期："+date1+"|"+date2+"|,相差"+Math.abs(result)+"个月");
            return result == 0 ? 1 : Math.abs(month + result);
        }
    }

    /**
     * 转换成日期和时间格式 yyyy-mm-dd hh:MM:ss
     * @param date
     * @return date
     */
    public static Date parseDateTime(Date date){
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(date == null) {
            date = new Date();
        }
        return Timestamp.valueOf(format.format(date));
    }


}
