package com.base.atlas.common.http;

import com.base.atlas.common.util.StringUtils;
import com.base.atlas.common.util.encrypt.MD5Encrypt;
import okhttp3.OkHttpClient;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author CaiJie Pang
 * @since 2023/2/16
 */
public class RestfulApiClient {

  private final static ConcurrentHashMap<String, Object> apiFactory = new ConcurrentHashMap<>();

  public static <T> T getService(String baseUrl, Class<T> restInterface) {
    return getService(baseUrl, restInterface, null, null);
  }

  public static <T> T getService(String baseUrl, Class<T> restInterface, String alias) {
    return getService(baseUrl, restInterface, alias, null);
  }

  public static <T> T getService(String baseUrl, Class<T> restInterface, String alias, OkHttpClient execClient) {
    if (StringUtils.isBlank(alias)) {
      alias = MD5Encrypt.encrypt(baseUrl);
    }
    if (apiFactory.containsKey(alias)) {
      return (T) apiFactory.get(alias);
    }
    T apiService;
    if (execClient != null) {
      apiService =  HttpFactory.getRestClient(baseUrl, restInterface, execClient);
    } else {
      apiService =  HttpFactory.getRestClient(baseUrl, restInterface);
    }

    assert alias != null;
    apiFactory.put(alias, apiService);
    return apiService;
  }
}
