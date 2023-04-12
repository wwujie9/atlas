package com.base.atlas.config.log;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

/**
 * @author CaiJie Pang
 * @since 2023/1/17
 */
@Component
@ConfigurationProperties(prefix = "app.access")
public class AccessProperties {

  private Log log = new Log();

  private Header header = new Header();

  public Log getLog() {
    return log;
  }

  public void setLog(Log log) {
    this.log = log;
  }

  public Header getHeader() {
    return header;
  }

  public void setHeader(Header header) {
    this.header = header;
  }

  public static class Log {
    private HandlerInterceptor logInterceptor;

    private List<String> include;

    private List<String> exclude;

    public HandlerInterceptor getLogInterceptor() {
      return logInterceptor;
    }

    public void setLogInterceptor(HandlerInterceptor logInterceptor) {
      this.logInterceptor = logInterceptor;
    }

    public List<String> getInclude() {
      return include;
    }

    public void setInclude(List<String> include) {
      this.include = include;
    }

    public List<String> getExclude() {
      return exclude;
    }

    public void setExclude(List<String> exclude) {
      this.exclude = exclude;
    }
  }

  public static class Header {

    private String osVersionKey = "os_version";
    private String osDeviceKey = "os_device";
    private String osBandKey = "os_band";
    private String appTerminalKey = "app_terminal";
    private String appVersionKey = "app_version";
    private String osIdKey = "os_id";

    public String getOsVersionKey() {
      return osVersionKey;
    }

    public void setOsVersionKey(String osVersionKey) {
      this.osVersionKey = osVersionKey;
    }

    public String getOsDeviceKey() {
      return osDeviceKey;
    }

    public void setOsDeviceKey(String osDeviceKey) {
      this.osDeviceKey = osDeviceKey;
    }

    public String getOsBandKey() {
      return osBandKey;
    }

    public void setOsBandKey(String osBandKey) {
      this.osBandKey = osBandKey;
    }

    public String getAppTerminalKey() {
      return appTerminalKey;
    }

    public void setAppTerminalKey(String appTerminalKey) {
      this.appTerminalKey = appTerminalKey;
    }

    public String getAppVersionKey() {
      return appVersionKey;
    }

    public void setAppVersionKey(String appVersionKey) {
      this.appVersionKey = appVersionKey;
    }

    public String getOsIdKey() {
      return osIdKey;
    }

    public void setOsIdKey(String osIdKey) {
      this.osIdKey = osIdKey;
    }
  }
}
