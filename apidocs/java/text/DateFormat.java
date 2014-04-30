package java.text;
public abstract class DateFormat
  extends java.text.Format
{
protected  DateFormat() { throw new RuntimeException("Stub!"); }
public final  java.lang.StringBuffer format(java.lang.Object arg0, java.lang.StringBuffer arg1, java.text.FieldPosition arg2) { throw new RuntimeException("Stub!"); }
public abstract  java.lang.StringBuffer format(java.util.Date arg0, java.lang.StringBuffer arg1, java.text.FieldPosition arg2);
public final  java.lang.String format(java.util.Date arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Date parse(java.lang.String arg0) throws java.text.ParseException { throw new RuntimeException("Stub!"); }
public abstract  java.util.Date parse(java.lang.String arg0, java.text.ParsePosition arg1);
public  java.lang.Object parseObject(java.lang.String arg0, java.text.ParsePosition arg1) { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getTimeInstance() { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getTimeInstance(int arg0) { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getTimeInstance(int arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getDateInstance() { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getDateInstance(int arg0) { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getDateInstance(int arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getDateTimeInstance() { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getDateTimeInstance(int arg0, int arg1) { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getDateTimeInstance(int arg0, int arg1, java.util.Locale arg2) { throw new RuntimeException("Stub!"); }
public static final  java.text.DateFormat getInstance() { throw new RuntimeException("Stub!"); }
public static  java.util.Locale[] getAvailableLocales() { throw new RuntimeException("Stub!"); }
public  void setCalendar(java.util.Calendar arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Calendar getCalendar() { throw new RuntimeException("Stub!"); }
public  void setNumberFormat(java.text.NumberFormat arg0) { throw new RuntimeException("Stub!"); }
public  java.text.NumberFormat getNumberFormat() { throw new RuntimeException("Stub!"); }
public  void setTimeZone(java.util.TimeZone arg0) { throw new RuntimeException("Stub!"); }
public  java.util.TimeZone getTimeZone() { throw new RuntimeException("Stub!"); }
public  void setLenient(boolean arg0) { throw new RuntimeException("Stub!"); }
public  boolean isLenient() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Object clone() { throw new RuntimeException("Stub!"); }
protected java.util.Calendar calendar;
protected java.text.NumberFormat numberFormat;
public static final int ERA_FIELD = 0;
public static final int YEAR_FIELD = 1;
public static final int MONTH_FIELD = 2;
public static final int DATE_FIELD = 3;
public static final int HOUR_OF_DAY1_FIELD = 4;
public static final int HOUR_OF_DAY0_FIELD = 5;
public static final int MINUTE_FIELD = 6;
public static final int SECOND_FIELD = 7;
public static final int MILLISECOND_FIELD = 8;
public static final int DAY_OF_WEEK_FIELD = 9;
public static final int DAY_OF_YEAR_FIELD = 10;
public static final int DAY_OF_WEEK_IN_MONTH_FIELD = 11;
public static final int WEEK_OF_YEAR_FIELD = 12;
public static final int WEEK_OF_MONTH_FIELD = 13;
public static final int AM_PM_FIELD = 14;
public static final int HOUR1_FIELD = 15;
public static final int HOUR0_FIELD = 16;
public static final int TIMEZONE_FIELD = 17;
public static final int FULL = 0;
public static final int LONG = 1;
public static final int MEDIUM = 2;
public static final int SHORT = 3;
public static final int DEFAULT = 2;
}
