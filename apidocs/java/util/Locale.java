package java.util;
public final class Locale
  implements java.io.Serializable
{
public static enum Category
{
DISPLAY(),
FORMAT();
}
public  Locale(java.lang.String arg0, java.lang.String arg1, java.lang.String arg2) { throw new RuntimeException("Stub!"); }
public  Locale(java.lang.String arg0, java.lang.String arg1) { throw new RuntimeException("Stub!"); }
public  Locale(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public static  java.util.Locale getDefault() { throw new RuntimeException("Stub!"); }
public static  java.util.Locale getDefault(java.util.Locale.Category arg0) { throw new RuntimeException("Stub!"); }
public static synchronized  void setDefault(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public static synchronized  void setDefault(java.util.Locale.Category arg0, java.util.Locale arg1) { throw new RuntimeException("Stub!"); }
public static  java.util.Locale[] getAvailableLocales() { throw new RuntimeException("Stub!"); }
public static  java.lang.String[] getISOCountries() { throw new RuntimeException("Stub!"); }
public static  java.lang.String[] getISOLanguages() { throw new RuntimeException("Stub!"); }
public  java.lang.String getLanguage() { throw new RuntimeException("Stub!"); }
public  java.lang.String getScript() { throw new RuntimeException("Stub!"); }
public  java.lang.String getCountry() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVariant() { throw new RuntimeException("Stub!"); }
public  java.lang.String getExtension(char arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Set<java.lang.Character> getExtensionKeys() { throw new RuntimeException("Stub!"); }
public  java.util.Set<java.lang.String> getUnicodeLocaleAttributes() { throw new RuntimeException("Stub!"); }
public  java.lang.String getUnicodeLocaleType(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.util.Set<java.lang.String> getUnicodeLocaleKeys() { throw new RuntimeException("Stub!"); }
public final  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public  java.lang.String toLanguageTag() { throw new RuntimeException("Stub!"); }
public static  java.util.Locale forLanguageTag(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String getISO3Language() throws java.util.MissingResourceException { throw new RuntimeException("Stub!"); }
public  java.lang.String getISO3Country() throws java.util.MissingResourceException { throw new RuntimeException("Stub!"); }
public final  java.lang.String getDisplayLanguage() { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayLanguage(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayScript() { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayScript(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String getDisplayCountry() { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayCountry(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String getDisplayVariant() { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayVariant(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public final  java.lang.String getDisplayName() { throw new RuntimeException("Stub!"); }
public  java.lang.String getDisplayName(java.util.Locale arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.Object clone() { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public static final java.util.Locale ENGLISH;
public static final java.util.Locale FRENCH;
public static final java.util.Locale GERMAN;
public static final java.util.Locale ITALIAN;
public static final java.util.Locale JAPANESE;
public static final java.util.Locale KOREAN;
public static final java.util.Locale CHINESE;
public static final java.util.Locale SIMPLIFIED_CHINESE;
public static final java.util.Locale TRADITIONAL_CHINESE;
public static final java.util.Locale FRANCE;
public static final java.util.Locale GERMANY;
public static final java.util.Locale ITALY;
public static final java.util.Locale JAPAN;
public static final java.util.Locale KOREA;
public static final java.util.Locale CHINA;
public static final java.util.Locale PRC;
public static final java.util.Locale TAIWAN;
public static final java.util.Locale UK;
public static final java.util.Locale US;
public static final java.util.Locale CANADA;
public static final java.util.Locale CANADA_FRENCH;
public static final java.util.Locale ROOT;
public static final char PRIVATE_USE_EXTENSION = 120;
public static final char UNICODE_LOCALE_EXTENSION = 117;
static { ENGLISH = null; FRENCH = null; GERMAN = null; ITALIAN = null; JAPANESE = null; KOREAN = null; CHINESE = null; SIMPLIFIED_CHINESE = null; TRADITIONAL_CHINESE = null; FRANCE = null; GERMANY = null; ITALY = null; JAPAN = null; KOREA = null; CHINA = null; PRC = null; TAIWAN = null; UK = null; US = null; CANADA = null; CANADA_FRENCH = null; ROOT = null; }
}
