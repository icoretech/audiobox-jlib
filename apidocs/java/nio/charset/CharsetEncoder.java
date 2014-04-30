package java.nio.charset;
public abstract class CharsetEncoder
{
protected  CharsetEncoder(java.nio.charset.Charset arg0, float arg1, float arg2, byte[] arg3) { throw new RuntimeException("Stub!"); }
protected  CharsetEncoder(java.nio.charset.Charset arg0, float arg1, float arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.Charset charset() { throw new RuntimeException("Stub!"); }
public final  byte[] replacement() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetEncoder replaceWith(byte[] arg0) { throw new RuntimeException("Stub!"); }
protected  void implReplaceWith(byte[] arg0) { throw new RuntimeException("Stub!"); }
public  boolean isLegalReplacement(byte[] arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.charset.CodingErrorAction malformedInputAction() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetEncoder onMalformedInput(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
protected  void implOnMalformedInput(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
public  java.nio.charset.CodingErrorAction unmappableCharacterAction() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetEncoder onUnmappableCharacter(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
protected  void implOnUnmappableCharacter(java.nio.charset.CodingErrorAction arg0) { throw new RuntimeException("Stub!"); }
public final  float averageBytesPerChar() { throw new RuntimeException("Stub!"); }
public final  float maxBytesPerChar() { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CoderResult encode(java.nio.CharBuffer arg0, java.nio.ByteBuffer arg1, boolean arg2) { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CoderResult flush(java.nio.ByteBuffer arg0) { throw new RuntimeException("Stub!"); }
protected  java.nio.charset.CoderResult implFlush(java.nio.ByteBuffer arg0) { throw new RuntimeException("Stub!"); }
public final  java.nio.charset.CharsetEncoder reset() { throw new RuntimeException("Stub!"); }
protected  void implReset() { throw new RuntimeException("Stub!"); }
protected abstract  java.nio.charset.CoderResult encodeLoop(java.nio.CharBuffer arg0, java.nio.ByteBuffer arg1);
public final  java.nio.ByteBuffer encode(java.nio.CharBuffer arg0) throws java.nio.charset.CharacterCodingException { throw new RuntimeException("Stub!"); }
public  boolean canEncode(char arg0) { throw new RuntimeException("Stub!"); }
public  boolean canEncode(java.lang.CharSequence arg0) { throw new RuntimeException("Stub!"); }
}
