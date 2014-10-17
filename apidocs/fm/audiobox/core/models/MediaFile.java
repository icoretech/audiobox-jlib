package fm.audiobox.core.models;
public class MediaFile
  extends fm.audiobox.core.models.Model
{
public  MediaFile() { throw new RuntimeException("Stub!"); }
public static  java.lang.String getPath() { throw new RuntimeException("Stub!"); }
public  java.lang.String getStreamPath() { throw new RuntimeException("Stub!"); }
public  java.lang.String getStreamUrl(fm.audiobox.core.AudioBoxClient audioBoxClient, boolean secure) { throw new RuntimeException("Stub!"); }
public  java.lang.String getDownloadPath() { throw new RuntimeException("Stub!"); }
public  java.lang.String getDownloadUrl(fm.audiobox.core.AudioBoxClient audioBoxClient) { throw new RuntimeException("Stub!"); }
public static  fm.audiobox.core.models.MediaFile load(fm.audiobox.core.AudioBoxClient audioBoxClient, java.lang.String token) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile reload(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.OutputStream download(fm.audiobox.core.AudioBoxClient audioBoxClient, java.io.OutputStream out) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.OutputStream download(fm.audiobox.core.AudioBoxClient audioBoxClient, java.io.OutputStream out, fm.audiobox.core.net.NetworkProgressListener listener) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile update(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean destroy(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile scrobble(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.String getLyrics(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile love(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile unlove(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile toggleLove(fm.audiobox.core.AudioBoxClient audioBoxClient) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.String getType() { throw new RuntimeException("Stub!"); }
public  java.lang.String getToken() { throw new RuntimeException("Stub!"); }
public  java.lang.String getArtist() { throw new RuntimeException("Stub!"); }
public  void setArtist(java.lang.String artist) { throw new RuntimeException("Stub!"); }
public  java.lang.String getAlbum() { throw new RuntimeException("Stub!"); }
public  void setAlbum(java.lang.String album) { throw new RuntimeException("Stub!"); }
public  java.lang.String getGenre() { throw new RuntimeException("Stub!"); }
public  void setGenre(java.lang.String genre) { throw new RuntimeException("Stub!"); }
public  int getReleaseYear() { throw new RuntimeException("Stub!"); }
public  void setReleaseYear(int releaseYear) { throw new RuntimeException("Stub!"); }
public  java.lang.String getTitle() { throw new RuntimeException("Stub!"); }
public  void setTitle(java.lang.String title) { throw new RuntimeException("Stub!"); }
public  java.lang.String getLenStr() { throw new RuntimeException("Stub!"); }
public  int getLenInt() { throw new RuntimeException("Stub!"); }
public  int getPosition() { throw new RuntimeException("Stub!"); }
public  void setPosition(int position) { throw new RuntimeException("Stub!"); }
public  java.lang.String getFilename() { throw new RuntimeException("Stub!"); }
public  boolean isLoved() { throw new RuntimeException("Stub!"); }
public  void setLoved(boolean loved) { throw new RuntimeException("Stub!"); }
public  int getDiscNumber() { throw new RuntimeException("Stub!"); }
public  void setDiscNumber(int discNumber) { throw new RuntimeException("Stub!"); }
public  java.lang.String getMime() { throw new RuntimeException("Stub!"); }
public  java.lang.String getRemotePath() { throw new RuntimeException("Stub!"); }
public  java.lang.String getSource() { throw new RuntimeException("Stub!"); }
public  java.lang.String getShareToken() { throw new RuntimeException("Stub!"); }
public  java.lang.String getArtwork() { throw new RuntimeException("Stub!"); }
public  void setArtwork(java.lang.String artwork) { throw new RuntimeException("Stub!"); }
public  long getSize() { throw new RuntimeException("Stub!"); }
public  java.lang.String getAlbumArtist() { throw new RuntimeException("Stub!"); }
public  void setAlbumArtist(java.lang.String albumArtist) { throw new RuntimeException("Stub!"); }
public  java.lang.String getHash() { throw new RuntimeException("Stub!"); }
public  java.lang.String getComposer() { throw new RuntimeException("Stub!"); }
public  void setComposer(java.lang.String composer) { throw new RuntimeException("Stub!"); }
public  java.lang.String getComment() { throw new RuntimeException("Stub!"); }
public  void setComment(java.lang.String comment) { throw new RuntimeException("Stub!"); }
public  java.lang.String getVideoBitrate() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVideoCodec() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVideoResolution() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVideoFps() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVideoAspect() { throw new RuntimeException("Stub!"); }
public  java.lang.String getVideoContainer() { throw new RuntimeException("Stub!"); }
public  java.lang.String getAudioBitrate() { throw new RuntimeException("Stub!"); }
public  java.lang.String getAudioCodec() { throw new RuntimeException("Stub!"); }
public  java.lang.String getAudioSampleRate() { throw new RuntimeException("Stub!"); }
public  void setType(java.lang.String type) { throw new RuntimeException("Stub!"); }
public  void setToken(java.lang.String token) { throw new RuntimeException("Stub!"); }
public  void setLenStr(java.lang.String lenStr) { throw new RuntimeException("Stub!"); }
public  void setLenInt(int lenInt) { throw new RuntimeException("Stub!"); }
public  void setFilename(java.lang.String filename) { throw new RuntimeException("Stub!"); }
public  void setMediaFileName(java.lang.String mediaFileName) { throw new RuntimeException("Stub!"); }
public  void setMime(java.lang.String mime) { throw new RuntimeException("Stub!"); }
public  void setRemotePath(java.lang.String remotePath) { throw new RuntimeException("Stub!"); }
public  void setSource(java.lang.String source) { throw new RuntimeException("Stub!"); }
public  void setShareToken(java.lang.String shareToken) { throw new RuntimeException("Stub!"); }
public  void setSize(long size) { throw new RuntimeException("Stub!"); }
public  void setHash(java.lang.String hash) { throw new RuntimeException("Stub!"); }
public  void setVideoBitrate(java.lang.String videoBitrate) { throw new RuntimeException("Stub!"); }
public  void setVideoCodec(java.lang.String videoCodec) { throw new RuntimeException("Stub!"); }
public  void setVideoResolution(java.lang.String videoResolution) { throw new RuntimeException("Stub!"); }
public  void setVideoFps(java.lang.String videoFps) { throw new RuntimeException("Stub!"); }
public  void setVideoAspect(java.lang.String videoAspect) { throw new RuntimeException("Stub!"); }
public  void setVideoContainer(java.lang.String videoContainer) { throw new RuntimeException("Stub!"); }
public  void setAudioBitrate(java.lang.String audioBitrate) { throw new RuntimeException("Stub!"); }
public  void setAudioCodec(java.lang.String audioCodec) { throw new RuntimeException("Stub!"); }
public  void setAudioSampleRate(java.lang.String audioSampleRate) { throw new RuntimeException("Stub!"); }
public  void setLyrics(java.lang.String lyrics) { throw new RuntimeException("Stub!"); }
protected java.lang.String type;
protected java.lang.String token;
protected java.lang.String artist;
protected java.lang.String album;
protected java.lang.String genre;
protected int releaseYear;
protected java.lang.String title;
protected java.lang.String lenStr;
protected int lenInt;
protected int position;
protected java.lang.String filename;
protected java.lang.String mediaFileName;
protected boolean loved;
protected int discNumber;
protected java.lang.String mime;
protected java.lang.String remotePath;
protected java.lang.String source;
protected java.lang.String shareToken;
protected java.lang.String artwork;
protected long size;
protected java.lang.String albumArtist;
protected java.lang.String hash;
protected java.lang.String composer;
protected java.lang.String comment;
protected java.lang.String videoBitrate;
protected java.lang.String videoCodec;
protected java.lang.String videoResolution;
protected java.lang.String videoFps;
protected java.lang.String videoAspect;
protected java.lang.String videoContainer;
protected java.lang.String audioBitrate;
protected java.lang.String audioCodec;
protected java.lang.String audioSampleRate;
protected java.lang.String lyrics;
}
