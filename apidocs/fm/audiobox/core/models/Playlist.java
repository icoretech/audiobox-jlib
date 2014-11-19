package fm.audiobox.core.models;
public class Playlist
  extends fm.audiobox.core.models.Model
{
public  Playlist() { throw new RuntimeException("Stub!"); }
public  Playlist(java.lang.String name) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist create(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist update(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean destroy(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean sync(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean toggleVisibility(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.AudioBoxClient audioBoxClient, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.AudioBoxClient audioBoxClient, long since) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.AudioBoxClient audioBoxClient, long since, java.lang.String set) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<fm.audiobox.core.models.MediaFile> getMediaFiles(fm.audiobox.core.AudioBoxClient audioBoxClient, long since, java.lang.String set, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Albums getAlbums(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Genres getGenres(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Artists getArtists(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist addMediaFiles(fm.audiobox.core.AudioBoxClient audioBoxClient, java.util.List<java.lang.String> tokens) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist removeMediaFiles(fm.audiobox.core.AudioBoxClient audioBoxClient, java.util.List<java.lang.String> tokens) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<? extends fm.audiobox.core.models.MediaFile> getFingerprints(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.util.List<? extends fm.audiobox.core.models.MediaFile> getFingerprints(fm.audiobox.core.AudioBoxClient audioBoxClient, com.google.api.client.json.JsonObjectParser parser) throws java.io.IOException { throw new RuntimeException("Stub!"); }
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
public  void setToken(java.lang.String token) { throw new RuntimeException("Stub!"); }
public  void setSystemName(java.lang.String systemName) { throw new RuntimeException("Stub!"); }
public  void setType(java.lang.String type) { throw new RuntimeException("Stub!"); }
public  void setMediaFilesCount(long mediaFilesCount) { throw new RuntimeException("Stub!"); }
public  void setLastAccessed(boolean lastAccessed) { throw new RuntimeException("Stub!"); }
public  void setUpdatedAt(java.lang.String updatedAt) { throw new RuntimeException("Stub!"); }
public  void setSyncable(boolean syncable) { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.Playlist setDescription(java.lang.String description) { throw new RuntimeException("Stub!"); }
public  java.lang.String getDescription() { throw new RuntimeException("Stub!"); }
public  boolean isCustom() { throw new RuntimeException("Stub!"); }
public  boolean isSmart() { throw new RuntimeException("Stub!"); }
public  boolean isOffline() { throw new RuntimeException("Stub!"); }
public  boolean isDrive() { throw new RuntimeException("Stub!"); }
public final  boolean equals(java.lang.Object other) { throw new RuntimeException("Stub!"); }
public final  int hashCode() { throw new RuntimeException("Stub!"); }
}
