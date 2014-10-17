package fm.audiobox.core.models;
public class MediaFiles
  extends fm.audiobox.core.models.Model
{
public static class MediaCollectionCustomParser
  extends com.google.api.client.json.CustomizeJsonParser
{
public  MediaCollectionCustomParser(fm.audiobox.core.models.Model observable) { throw new RuntimeException("Stub!"); }
public  java.util.Collection<java.lang.Object> newInstanceForArray(java.lang.Object context, java.lang.reflect.Field field) { throw new RuntimeException("Stub!"); }
}
public  MediaFiles() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.collections.EventedModelList<fm.audiobox.core.models.MediaFile> getMediaFiles() { throw new RuntimeException("Stub!"); }
public static  boolean destroyAll(fm.audiobox.core.AudioBoxClient audioBoxClient, java.util.List<java.lang.String> tokens) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public static  java.util.Collection<java.lang.Object> newList(java.lang.Object context) { throw new RuntimeException("Stub!"); }
public static final java.lang.String JSON_TOKEN = "media_files";
public static final java.lang.String PARAM_SET = "set";
public static final java.lang.String PARAM_SINCE = "since";
public static final java.lang.String PARAM_TOKENS = "tokens[]";
protected fm.audiobox.core.models.collections.EventedModelList<fm.audiobox.core.models.MediaFile> media_files;
}
