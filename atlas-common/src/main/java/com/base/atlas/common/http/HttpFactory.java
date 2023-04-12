package com.base.atlas.common.http;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author CaiJie Pang
 * @since 2023/2/16
 */
public class HttpFactory {

  private HttpFactory() {}

  private final static OkHttpClient DEFAULT_HTTP_CLIENT =
      new OkHttpClient.Builder()
          .connectionPool(new ConnectionPool(10, 1L, TimeUnit.MINUTES))
          .connectTimeout(60, TimeUnit.SECONDS)
          .readTimeout(180, TimeUnit.SECONDS)
          .build();

  protected static <T> T getRestClient(String baseUrl, Class<T> restInterface) {
    return getRestClient(baseUrl, restInterface, DEFAULT_HTTP_CLIENT);
  }

  protected static <T> T getRestClient(String baseUrl, Class<T> restInterface, OkHttpClient execClient) {
    return new Retrofit.Builder().baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(execClient)
        .build()
        .create(restInterface);
  }
}
