# PLEASE NOTE
This library is not yet ready for production. It is actually under heavy development and still requires some refactor.
It does not work on the actual API specifications, if you need a java library that works with the actual API please use
the old [AudioBox.fm-JavaLib](https://github.com/icoretech/AudioBox.fm-JavaLib).


## Introduction
`audiobox-jlib` is a simple library to control [AudioBox](https://audiobox.fm) account through its powerful API.


[![Build Status](https://travis-ci.org/icoretech/audiobox-jlib.svg?branch=master)](https://travis-ci.org/icoretech/audiobox-jlib)

## Minimum requirements
* Java 1.7
* Android Build Tools 19+ (for android projects)

## Features
* You can choose amongst 3 HTTP Transport out of the box and if you are not happy you can implement your own.
* You can choose amongst 3 JSON parser (Jackson, Guava, AndroidJsonFactory)
* Transparent OAuth2 authorization flow
* Easy to use

## Dependencies
* [SLF4J](http://www.slf4j.org/) 1.7 (logging)
* [Typesafe Config](https://github.com/typesafehub/config) 1.2.0 (configuration)
* [Google HTTP Java Client](https://code.google.com/p/google-http-java-client/) 1.18 (HTTP layer)
* [Google OAuth Java Client](https://code.google.com/p/google-oauth-java-client/) 1.18 (OAuth2 layer)
* [Apache Common Validator](http://commons.apache.org/proper/commons-validator/) 1.4 (Utils)
* [Apache Commons Lang](http://commons.apache.org/proper/commons-lang/) 3.3.2 (Utils)
* [Java Object Diff](https://github.com/SQiShER/java-object-diff) 0.13.1 (Utils)


## Getting started

### 1. Add the dependency

If your project is under maven add this under the dependency management section:

    <dependency>
      <groupId>fm.audiobox</groupId>
      <artifactId>audiobox-jlib</artifactId>
      <version>1.0</version>
    </dependency>


If your project uses gradle add this line in your dependency clojure:

    dependencies {
      ...
      compile 'fm.audiobox:audiobox-jlib:1.0.0'
      ...
    }


Alternatively add [this jar](https://repository.sonatype.org/service/local/artifact/maven/redirect?r=central-proxy&g=fm.audiobox&a=audiobox-jlib&v=LATEST)
to your project classpath.

### 2. Register your application

1. Subscribe an AudioBox account [here](https://audiobox.fm/account/sign_up).
2. Register your application [here](https://audiobox.fm/oauth2/applications).
3. Take note of the API Consumer Key and Consumer Secret.


### 3. Configure the client

Now that you have all needed pieces you have to configure your client before making any operation:

```java
Configuration config = new Configuration()
  .setApiKey( "[Your Consumer Key]" )
  .setApiSecret( "[Your Consumer Secret]" );
```


Through the [Configuration](http://icoretech.github.io/audiobox-jlib/apidocs/reference/fm/audiobox/core/config/Configuration.html)
object you can configure many aspects of the library behaviors; some are trivial such as application name, version, etc.
and other are more complex such as HttpTransport or JSON parser.


This library does not offer a data store for credentials storage out of the box. You should provide
one like FileDataStoreFactory, MemoryDataStoreFactory or implementing one
by extending the [AbstractDataStoreFactory](https://code.google.com/p/google-http-java-client/source/browse/google-http-client/src/main/java/com/google/api/client/util/store/AbstractDataStoreFactory.java).
This data store is used to store credentials so you should be really carefully with it.

To set it use the configuration:

```java
config.setDataStoreFactory( new MyDataStoreFactory() );
```


Since this library wants to be as much agnostic as possible regarding the HTTP client and
the JSON parser libraries you should set them at this moment by choosing amongst:

* **NetHttpTransport:** based on HttpURLConnection that is found in all Java SDKs, and thus usually the simplest choice.
* **ApacheHttpTransport:** based on the popular Apache HttpClient that allows for more customization.
* **UrlFetchTransport:** based on URL Fetch Java API in the Google App Engine SDK

as HTTP transport, and:

* **JacksonFactory:** based on the popular Jackson library which is considered the fastest in terms of parsing/serialization speed
* **GsonFactory:** based on the Google GSON library which is a lighter-weight option (small size) that is pretty fast also (though not quite as fast as Jackson)
* **AndroidJsonFactory:** based on the JSON library built-in to Android Honeycomb (SDK 3.0) or higher that is identical to the Google GSON library

as JSON parser library.

There are no defaults that's why you must provide them through the configuration:

```java
config
 .setHttpTransport( new NetHttpTransport() )
 .setJsonFactory( new JacksonFactory() );
```


### 4. Authorize the user account

We just gone through the basic configuration and once the setup is completed you can create your Client,
but we still need to authorize the application to start performing any kind of operation supported by AudioBox API:

```java
Client client = new Client( config );
client.authorize( "username", "password" );
...
```


When the application is successfully authorized a grant token is stored in the configured data store.
Keep in mind that grant tokens may expires at any time. A request against AudioBox with an expired token will result in
an AuthorizationException. Your application should be ready to trap it in order to present a new login form.


### 5. Browse the collection
Now that the client is configured and authorized we can browse and perform any API-supported operation on AudioBox:

Get user information:
```java
client.getUser();
```

Get user's playlists:
```java
client.getPlaylists();
```

Or some other MediaFile operation:

```java
// Load playlists
List<Playlist> playlists = client.getPlaylists();
Playlist p = playlists.get(0);

// Load media files
List<MediaFile> mfs = p.getMediaFiles(client);
MediaFile m = mfs.get(0);

// Edit some label
m.setTitle("foo");
m.setArtist("bar");

// Save modifications
m.update(client);
```


To know more about this library checkout the [JavaDoc](http://icoretech.github.io/audiobox-jlib/apidocs/reference/fm/audiobox/core/Client.html).


## Use cases
* [AudioBox for Android](https://play.google.com/store/apps/details?id=fm.audiobox)


## Contribute
* Get familiar with the philosophy of the library design.
* [Check for open issues](https://github.com/icoretech/audiobox-jlib/issues?state=open) or open a new issue to fill a bug or a feature request.
* Fork the audiobox-jlib on Github and make your changes.
* Write some tests which show that the bug was fixed or that the feature works as expected.
* Send a pull request to get changes merged and published.


## Gradle Tasks for library developers


To generate test coverage report run

    gradle test jacocoTestReport


To generate JDoc run

    gradle generateDoclava

