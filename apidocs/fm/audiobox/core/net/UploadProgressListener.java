package fm.audiobox.core.net;
public interface UploadProgressListener
{
public abstract  void onProgressUpdate(long total, long current);
}
