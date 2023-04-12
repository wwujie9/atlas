package com.base.atlas.common.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import java.util.concurrent.TimeUnit;

/**
 * @author CaiJie Pang
 * @since 2023/2/5
 */
public class SimpleHttpUtils {

  private static final OkHttpClient HTTP_CLIENT =
      new OkHttpClient.Builder()
          //设置超时时间
          .connectTimeout(60, TimeUnit.SECONDS)
          .readTimeout(60, TimeUnit.SECONDS)
          .build();

  public static void execute(Request request) {
    HTTP_CLIENT.newCall(request);
  }
}
