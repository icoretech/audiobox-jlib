package com.typesafe.config;
public interface ConfigOrigin
{
public abstract  java.lang.String description();
public abstract  java.lang.String filename();
public abstract  java.net.URL url();
public abstract  java.lang.String resource();
public abstract  int lineNumber();
public abstract  java.util.List<java.lang.String> comments();
}
