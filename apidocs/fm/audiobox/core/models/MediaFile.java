package fm.audiobox.core.models;
public class MediaFile
  extends fm.audiobox.core.models.Model
{
public  MediaFile() { throw new RuntimeException("Stub!"); }
public static  java.lang.String getPath() { throw new RuntimeException("Stub!"); }
public  java.lang.String getStreamPath() { throw new RuntimeException("Stub!"); }
public  java.lang.String getDownloadPath() { throw new RuntimeException("Stub!"); }
public static  fm.audiobox.core.models.MediaFile load(fm.audiobox.core.Client client, java.lang.String token) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile reload(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.OutputStream download(fm.audiobox.core.Client client, java.io.OutputStream out) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.io.OutputStream download(fm.audiobox.core.Client client, java.io.OutputStream out, fm.audiobox.core.net.NetworkProgressListener listener) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile update(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  boolean destroy(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile scrobble(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  java.lang.String getLyrics(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile love(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile unlove(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
public  fm.audiobox.core.models.MediaFile toggleLove(fm.audiobox.core.Client client) throws java.io.IOException { throw new RuntimeException("Stub!"); }
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
protected java.lang.String type;
protected java.lang.String token;
protected java.lang.String artist;
protected java.lang.String album;
protected java.lang.String genre;
protected int release_year;
protected java.lang.String title;
protected java.lang.String len_str;
protected int len_int;
protected int position;
protected java.lang.String filename;
protected java.lang.String media_file_name;
protected boolean loved;
protected int disc_number;
protected java.lang.String mime;
protected java.lang.String remote_path;
protected java.lang.String source;
protected java.lang.String share_token;
protected java.lang.String artwork;
protected long size;
protected java.lang.String album_artist;
protected java.lang.String hash;
protected java.lang.String composer;
protected java.lang.String comment;
protected java.lang.String video_bitrate;
protected java.lang.String video_codec;
protected java.lang.String video_resolution;
protected java.lang.String video_fps;
protected java.lang.String video_aspect;
protected java.lang.String video_container;
protected java.lang.String audio_bitrate;
protected java.lang.String audio_codec;
protected java.lang.String audio_sample_rate;
protected java.lang.String lyrics;
}
