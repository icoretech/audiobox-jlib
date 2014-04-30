package java.security.cert;
public abstract class CertPath
  implements java.io.Serializable
{
protected  CertPath(java.lang.String arg0) { throw new RuntimeException("Stub!"); }
public  java.lang.String getType() { throw new RuntimeException("Stub!"); }
public abstract  java.util.Iterator<java.lang.String> getEncodings();
public  boolean equals(java.lang.Object arg0) { throw new RuntimeException("Stub!"); }
public  int hashCode() { throw new RuntimeException("Stub!"); }
public  java.lang.String toString() { throw new RuntimeException("Stub!"); }
public abstract  byte[] getEncoded() throws java.security.cert.CertificateEncodingException;
public abstract  byte[] getEncoded(java.lang.String arg0) throws java.security.cert.CertificateEncodingException;
public abstract  java.util.List<? extends java.security.cert.Certificate> getCertificates();
protected  java.lang.Object writeReplace() throws java.io.ObjectStreamException { throw new RuntimeException("Stub!"); }
}
