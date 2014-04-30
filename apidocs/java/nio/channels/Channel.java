package java.nio.channels;
public interface Channel
{
public abstract  boolean isOpen();
public abstract  void close() throws java.io.IOException;
}
