package java.util;
public abstract class TimeZone
  implements java.io.Serializable
{
public  TimeZone() { throw new RuntimeException("Stub!"); }
public abstract  int getOffset(int arg0, int arg1, int arg2, int arg3, int arg4, int arg5);
public  int getOffset(long arg0) { throw new RuntimeException("Stub!"); }
public abstract  void setRawOffset(int arg0);
public abstract  int getRawOffset();
public  java.lang.String getID() { throw new RuntimeException("Stub!"); }
public  void setID(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String getDisplayName() { throw new RuntimeException("Stub!"); }
public final  java.lang.String getDisplayName(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String getDisplayName(boolean arg0, int arg1) { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayName(boolean arg0, int arg1, java.util.Locale arg2) { throw new RuntimeException("Stub!"); }
public  int getDSTSavings() { throw new RuntimeException("Stub!"); }
public abstract  boolean useDaylightTime();
public  boolean observesDaylightTime() { throw new RuntimeException("Stub!"); }
public abstract  boolean inDaylightTime(java.util.Date arg0);
public static synchronized  java.util.TimeZone getTimeZone(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static synchronized  java.lang.String[] getAvailableIDs(int arg0) { throw new RuntimeException("Stub!"); }
public static synchronized  java.lang.String[] getAvailableIDs() { throw new RuntimeException("Stub!"); }
public static  java.util.TimeZone getDefault() { throw new RuntimeException("Stub!"); }
public static  void setDefault(java.util.TimeZone arg0) { throw new RuntimeException("Stub!"); }
public  boolean hasSameRules(java.util.TimeZone arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Object clone() { throw new RuntimeException("Stub!"); }
public static final int SHORT = 0;
public static final int LONG = 1;
}
