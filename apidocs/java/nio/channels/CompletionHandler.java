package java.nio.channels;
public interface CompletionHandler<V, A>
{
public abstract  void completed(V arg0, A arg1);
public abstract  void failed(java.lang.Throwable arg0, A arg1);
}
