package fm.audiobox.core.models;
public class Playlist
  extends fm.audiobox.core.models.Model
{
public  Playlist() { throw new RuntimeException("Stub!"); }
public  Playlist(java.lang.String name) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist create(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist update(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean destroy(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean sync(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean toggleVisibility(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<? extends fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<? extends fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.Client client, long since) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<? extends fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.Client client, long since, java.lang.String set) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Albums getAlbums(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Genres getGenres(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Artists getArtists(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist addMediaFiles(fm.audiobox.core.Client client, java.util.List<java.lang.String> tokens) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist removeMediaFiles(fm.audiobox.core.Client client, java.util.List<java.lang.String> tokens) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<? extends fm.audiobox.core.models.MediaFile> getFingerprints(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public static  java.lang.String getPath() { throw new RuntimeException("Stub!"); }
public  java.lang.String getToken() { throw new RuntimeException("Stub!"); }
public  java.lang.String getName() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist setName(java.lang.String name) { throw new RuntimeException("Stub!"); }
public  java.lang.String getSystemName() { throw new RuntimeException("Stub!"); }
public  java.lang.String getType() { throw new RuntimeException("Stub!"); }
public  long getMediaFilesCount() { throw new RuntimeException("Stub!"); }
public  long getPosition() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist setPosition(long position) { throw new RuntimeException("Stub!"); }
public  boolean isEmbeddable() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist setEmbeddable(boolean embeddable) { throw new RuntimeException("Stub!"); }
public  boolean isVisible() { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist setVisible(boolean visible) { throw new RuntimeException("Stub!"); }
public  boolean isLastAccessed() { throw new RuntimeException("Stub!"); }
public  java.lang.String getUpdatedAt() { throw new RuntimeException("Stub!"); }
public  boolean isSyncable() { throw new RuntimeException("Stub!"); }
public final  boolean equals(java.lang.Object other) { throw new RuntimeException("Stub!"); }
public final  int hashCode() { throw new RuntimeException("Stub!"); }
}
