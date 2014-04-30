package com.typesafe.config;
public interface ConfigValue
  extends com.typesafe.config.ConfigMergeable
{
public abstract  com.typesafe.config.ConfigOrigin origin();
public abstract  com.typesafe.config.ConfigValueType valueType();
public abstract  java.lang.Object unwrapped();
public abstract  java.lang.String render();
public abstract  java.lang.String render(com.typesafe.config.ConfigRenderOptions arg0);
public abstract  com.typesafe.config.ConfigValue withFallback(com.typesafe.config.ConfigMergeable arg0);
public abstract  com.typesafe.config.Config atPath(java.lang.String arg0);
public abstract  com.typesafe.config.Config atKey(java.lang.String arg0);
}
