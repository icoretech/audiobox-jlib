package java.text;
public interface AttributedCharacterIterator
{
public static class Attribute
  implements java.io.Serializable
{
protected  Attribute(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public final  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public final  int hashCode() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
protected  java.lang.String getName() { throw new RuntimeException("Stub!"); }
protected  java.lang.Object readResolve() throws java.io.InvalidObjectException { throw new RuntimeException("Stub!"); }
public static final java.text.AttributedCharacterIterator.Attribute LANGUAGE;
public static final java.text.AttributedCharacterIterator.Attribute READING;
public static final java.text.AttributedCharacterIterator.Attribute INPUT_METHOD_SEGMENT;
static { LANGUAGE = null; READING = null; INPUT_METHOD_SEGMENT = null; }
}
public abstract  int getRunStart();
public abstract  int getRunStart(java.text.AttributedCharacterIterator.Attribute arg0);
public abstract  int getRunStart(java.util.Set<? extends java.text.AttributedCharacterIterator.Attribute> arg0);
public abstract  int getRunLimit();
public abstract  int getRunLimit(java.text.AttributedCharacterIterator.Attribute arg0);
public abstract  int getRunLimit(java.util.Set<? extends java.text.AttributedCharacterIterator.Attribute> arg0);
public abstract  java.util.Map<java.text.AttributedCharacterIterator.Attribute, java.lang.Object> getAttributes();
public abstract  java.lang.Object getAttribute(java.text.AttributedCharacterIterator.Attribute arg0);
public abstract  java.util.Set<java.text.AttributedCharacterIterator.Attribute> getAllAttributeKeys();
}
