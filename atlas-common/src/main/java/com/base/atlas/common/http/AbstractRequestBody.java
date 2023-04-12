package com.base.atlas.common.http;

import com.base.atlas.common.util.JsonUtils;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.io.Serializable;

/**
 * @author CaiJie Pang
 * @since 2023/3/17
 */
public abstract class AbstractRequestBody implements Serializable {

  private static final long serialVersionUID = -7738225791048662506L;

  public RequestBody build() {
    String content = JsonUtils.toJson(this);
    return RequestBody.create(MediaType.parse("application/json; charset=utf-8"), content);
  }
}
