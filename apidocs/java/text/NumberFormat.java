package java.text;
public abstract class NumberFormat
  extends java.text.Format
{
protected  NumberFormat() { throw new RuntimeException("Stub!"); }
public  java.lang.StringBuffer format(java.lang.Object arg0, java.lang.StringBuffer arg1, java.text.FieldPosition arg2) { throw new RuntimeException("Stub!"); }
public final  java.lang.Object parseObject(java.lang.String arg0, java.text.ParsePosition arg1) { throw new RuntimeException("Stub!"); }
public final  java.lang.String format(double arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String format(long arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.lang.StringBuffer format(double arg0, java.lang.StringBuffer arg1, java.text.FieldPosition arg2);
public abstract  java.lang.StringBuffer format(long arg0, java.lang.StringBuffer arg1, java.text.FieldPosition arg2);
public abstract  java.lang.Number parse(java.lang.String arg0, java.text.ParsePosition arg1);
public  java.lang.Number parse(java.lang.String arg0) throws java.text.ParseException { throw new RuntimeException("Stub!"); }
public  boolean isParseIntegerOnly() { throw new RuntimeException("Stub!"); }
public  void setParseIntegerOnly(boolean arg0) { throw new RuntimeException("Stub!"); }
public static final  java.text.NumberFormat getInstance() { throw new RuntimeException("Stub!"); }
public static  java.text.NumberFormat getInstance(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public static final  java.text.NumberFormat getNumberInstance() { throw new RuntimeException("Stub!"); }
public static  java.text.NumberFormat getNumberInstance(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public static final  java.text.NumberFormat getIntegerInstance() { throw new RuntimeException("Stub!"); }
public static  java.text.NumberFormat getIntegerInstance(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public static final  java.text.NumberFormat getCurrencyInstance() { throw new RuntimeException("Stub!"); }
public static  java.text.NumberFormat getCurrencyInstance(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public static final  java.text.NumberFormat getPercentInstance() { throw new RuntimeException("Stub!"); }
public static  java.text.NumberFormat getPercentInstance(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public static  java.util.Locale[] getAvailableLocales() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Object clone() { throw new RuntimeException("Stub!"); }
public  boolean isGroupingUsed() { throw new RuntimeException("Stub!"); }
public  void setGroupingUsed(boolean arg0) { throw new RuntimeException("Stub!"); }
public  int getMaximumIntegerDigits() { throw new RuntimeException("Stub!"); }
public  void setMaximumIntegerDigits(int arg0) { throw new RuntimeException("Stub!"); }
public  int getMinimumIntegerDigits() { throw new RuntimeException("Stub!"); }
public  void setMinimumIntegerDigits(int arg0) { throw new RuntimeException("Stub!"); }
public  int getMaximumFractionDigits() { throw new RuntimeException("Stub!"); }
public  void setMaximumFractionDigits(int arg0) { throw new RuntimeException("Stub!"); }
public  int getMinimumFractionDigits() { throw new RuntimeException("Stub!"); }
public  void setMinimumFractionDigits(int arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Currency getCurrency() { throw new RuntimeException("Stub!"); }
public  void setCurrency(java.util.Currency arg0) { throw new RuntimeException("Stub!"); }
public  java.math.RoundingMode getRoundingMode() { throw new RuntimeException("Stub!"); }
public  void setRoundingMode(java.math.RoundingMode arg0) { throw new RuntimeException("Stub!"); }
public static final int INTEGER_FIELD = 0;
public static final int FRACTION_FIELD = 1;
}
