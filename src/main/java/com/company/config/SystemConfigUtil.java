package com.company.config;

import java.util.HashMap;
import java.util.Map;

public class SystemConfigUtil {

  private static SystemConfigUtil config;
  private Map<String, Object> values = new HashMap<>();

  public static SystemConfigUtil getInstance() {
    if (config == null) {
      config = new SystemConfigUtil();
    }
    return config;
  }

  public Object get(String key) {
    return values.get(key);
  }

  public SystemConfigUtil set(String key, Object value) {
    values.put(key, value);
    return this;
  }
}
