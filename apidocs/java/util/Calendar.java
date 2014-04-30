package java.util;
public abstract class Calendar
  implements java.io.Serializable
{
protected  Calendar() { throw new RuntimeException("Stub!"); }
protected  Calendar(java.util.TimeZone arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public static  java.util.Calendar getInstance() { throw new RuntimeException("Stub!"); }
public static  java.util.Calendar getInstance(java.util.TimeZone arg0) { throw new RuntimeException("Stub!"); }
public static  java.util.Calendar getInstance(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public static  java.util.Calendar getInstance(java.util.TimeZone arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public static synchronized  java.util.Locale[] getAvailableLocales() { throw new RuntimeException("Stub!"); }
protected abstract  void computeTime();
protected abstract  void computeFields();
public final  java.util.Date getTime() { throw new RuntimeException("Stub!"); }
public final  void setTime(java.util.Date arg0) { throw new RuntimeException("Stub!"); }
public  long getTimeInMillis() { throw new RuntimeException("Stub!"); }
public  void setTimeInMillis(long arg0) { throw new RuntimeException("Stub!"); }
public  int get(int arg0) { throw new RuntimeException("Stub!"); }
protected final  int internalGet(int arg0) { throw new RuntimeException("Stub!"); }
public  void set(int arg0, int arg1) { throw new RuntimeException("Stub!"); }
public final  void set(int arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public final  void set(int arg0, int arg1, int arg2, int arg3, int arg4) { throw new RuntimeException("Stub!"); }
public final  void set(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5) { throw new RuntimeException("Stub!"); }
public final  void clear() { throw new RuntimeException("Stub!"); }
public final  void clear(int arg0) { throw new RuntimeException("Stub!"); }
public final  boolean isSet(int arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayName(int arg0, int arg1, java.util.Locale arg2) { throw new RuntimeException("Stub!"); }
public  java.util.Map<java.lang.String, java.lang.Integer> getDisplayNames(int arg0, int arg1, java.util.Locale arg2) { throw new RuntimeException("Stub!"); }
protected  void complete() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean before(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  boolean after(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int compareTo(java.util.Calendar arg0) { throw new RuntimeException("Stub!"); }
public abstract  void add(int arg0, int arg1);
public abstract  void roll(int arg0, boolean arg1);
public  void roll(int arg0, int arg1) { throw new RuntimeException("Stub!"); }
public  void setTimeZone(java.util.TimeZone arg0) { throw new RuntimeException("Stub!"); }
public  java.util.TimeZone getTimeZone() { throw new RuntimeException("Stub!"); }
public  void setLenient(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean isLenient() { throw new RuntimeException("Stub!"); }
public  void setFirstDayOfWeek(int arg0) { throw new RuntimeException("Stub!"); }
public  int getFirstDayOfWeek() { throw new RuntimeException("Stub!"); }
public  void setMinimalDaysInFirstWeek(int arg0) { throw new RuntimeException("Stub!"); }
public  int getMinimalDaysInFirstWeek() { throw new RuntimeException("Stub!"); }
public  boolean isWeekDateSupported() { throw new RuntimeException("Stub!"); }
public  int getWeekYear() { throw new RuntimeException("Stub!"); }
public  void setWeekDate(int arg0, int arg1, int arg2) { throw new RuntimeException("Stub!"); }
public  int getWeeksInWeekYear() { throw new RuntimeException("Stub!"); }
public abstract  int getMinimum(int arg0);
public abstract  int getMaximum(int arg0);
public abstract  int getGreatestMinimum(int arg0);
public abstract  int getLeastMaximum(int arg0);
public  int getActualMinimum(int arg0) { throw new RuntimeException("Stub!"); }
public  int getActualMaximum(int arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Object clone() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  int compareTo(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public static final int ERA = 0;
public static final int YEAR = 1;
public static final int MONTH = 2;
public static final int WEEK_OF_YEAR = 3;
public static final int WEEK_OF_MONTH = 4;
public static final int DATE = 5;
public static final int DAY_OF_MONTH = 5;
public static final int DAY_OF_YEAR = 6;
public static final int DAY_OF_WEEK = 7;
public static final int DAY_OF_WEEK_IN_MONTH = 8;
public static final int AM_PM = 9;
public static final int HOUR = 10;
public static final int HOUR_OF_DAY = 11;
public static final int MINUTE = 12;
public static final int SECOND = 13;
public static final int MILLISECOND = 14;
public static final int ZONE_OFFSET = 15;
public static final int DST_OFFSET = 16;
public static final int FIELD_COUNT = 17;
public static final int SUNDAY = 1;
public static final int MONDAY = 2;
public static final int TUESDAY = 3;
public static final int WEDNESDAY = 4;
public static final int THURSDAY = 5;
public static final int FRIDAY = 6;
public static final int SATURDAY = 7;
public static final int JANUARY = 0;
public static final int FEBRUARY = 1;
public static final int MARCH = 2;
public static final int APRIL = 3;
public static final int MAY = 4;
public static final int JUNE = 5;
public static final int JULY = 6;
public static final int AUGUST = 7;
public static final int SEPTEMBER = 8;
public static final int OCTOBER = 9;
public static final int NOVEMBER = 10;
public static final int DECEMBER = 11;
public static final int UNDECIMBER = 12;
public static final int AM = 0;
public static final int PM = 1;
public static final int ALL_STYLES = 0;
public static final int SHORT = 1;
public static final int LONG = 2;
protected int[] fields = null;
protected boolean[] isSet = null;
protected long time;
protected boolean isTimeSet;
protected boolean areFieldsSet;
}
