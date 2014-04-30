package java.text;
public abstract class Format
  implements java.io.Serializable
{
public static class Field
  extends java.text.AttributedCharacterIterator.Attribute
{
protected  Field(java.lang.String arg0) { super((java.lang.String)null); throw new RuntimeException("Stub!"); }
}
protected  Format() { throw new RuntimeException("Stub!"); }
public final  java.lang.String format(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.lang.StringBuffer format(java.lang.Object arg0, java.lang.StringBuffer arg1, java.text.FieldPosition arg2);
public  java.text.AttributedCharacterIterator formatToCharacterIterator(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public abstract  java.lang.Object parseObject(java.lang.String arg0, java.text.ParsePosition arg1);
public  java.lang.Object parseObject(java.lang.String arg0) throws java.text.ParseException { throw new RuntimeException("Stub!"); }
public  java.lang.Object clone() { throw new RuntimeException("Stub!"); }
}
