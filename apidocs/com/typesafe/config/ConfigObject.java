package com.typesafe.config;
public interface ConfigObject
  extends com.typesafe.config.ConfigValue, java.util.Map<java.lang.String, com.typesafe.config.ConfigValue>
{
public abstract  com.typesafe.config.Config toConfig();
public abstract  java.util.Map<java.lang.String, java.lang.Object> unwrapped();
public abstract  com.typesafe.config.ConfigObject withFallback(com.typesafe.config.ConfigMergeable arg0);
public abstract  com.typesafe.config.ConfigValue get(java.lang.Object arg0);
public abstract  com.typesafe.config.ConfigObject withOnlyKey(java.lang.String arg0);
public abstract  com.typesafe.config.ConfigObject withoutKey(java.lang.String arg0);
public abstract  com.typesafe.config.ConfigObject withValue(java.lang.String arg0, com.typesafe.config.ConfigValue arg1);
}
