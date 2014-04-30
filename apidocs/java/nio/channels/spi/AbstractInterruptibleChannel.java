package java.nio.channels.spi;
public abstract class AbstractInterruptibleChannel
  implements java.nio.channels.Channel
{
protected  AbstractInterruptibleChannel() { throw new RuntimeException("Stub!"); }
public final  void close() throws java.io.IOException { throw new RuntimeException("Stub!"); }
protected abstract  void implCloseChannel() throws java.io.IOException;
public final  boolean isOpen() { throw new RuntimeException("Stub!"); }
protected final  void begin() { throw new RuntimeException("Stub!"); }
protected final  void end(boolean arg0) throws java.nio.channels.AsynchronousCloseException { throw new RuntimeException("Stub!"); }
}
