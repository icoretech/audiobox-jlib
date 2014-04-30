package java.nio.charset;
public abstract class CharsetDecoder
{
protected  CharsetDecoder(java.nio.charset.Charset arg0, float arg1, float arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.Charset charset() { throw new RuntimeException("Stub!"); }
public final  java.lang.String replacement() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetDecoder replaceWith(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
protected  void implReplaceWith(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.charset.CodingErrorAction malformedInputAction() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetDecoder onMalformedInput(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
protected  void implOnMalformedInput(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.charset.CodingErrorAction unmappableCharacterAction() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetDecoder onUnmappableCharacter(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
protected  void implOnUnmappableCharacter(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
public final  float averageCharsPerByte() { throw new RuntimeException("Stub!"); }
public final  float maxCharsPerByte() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CoderResult decode(java.nio.ByteBuffer arg0, java.nio.CharBuffer arg1, boolean arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CoderResult flush(java.nio.CharBuffer arg0) { throw new RuntimeException("Stub!"); }
protected  java.nio.charset.CoderResult implFlush(java.nio.CharBuffer arg0) { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetDecoder reset() { throw new RuntimeException("Stub!"); }
protected  void implReset() { throw new RuntimeException("Stub!"); }
protected abstract  java.nio.charset.CoderResult decodeLoop(java.nio.ByteBuffer arg0, java.nio.CharBuffer arg1);
public final  java.nio.CharBuffer decode(java.nio.ByteBuffer arg0) throws java.nio.charset.CharacterCodingException { throw new RuntimeException("Stub!"); }
public  boolean isAutoDetecting() { throw new RuntimeException("Stub!"); }
public  boolean isCharsetDetected() { throw new RuntimeException("Stub!"); }
public  java.nio.charset.Charset detectedCharset() { throw new RuntimeException("Stub!"); }
}
