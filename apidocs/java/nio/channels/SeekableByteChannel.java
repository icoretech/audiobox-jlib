package java.nio.channels;
public interface SeekableByteChannel
{
public abstract  int read(java.nio.ByteBuffer arg0) throws java.io.IOException;
public abstract  int write(java.nio.ByteBuffer arg0) throws java.io.IOException;
public abstract  long position() throws java.io.IOException;
public abstract  java.nio.channels.SeekableByteChannel position(long arg0) throws java.io.IOException;
public abstract  long size() throws java.io.IOException;
public abstract  java.nio.channels.SeekableByteChannel truncate(long arg0) throws java.io.IOException;
}
