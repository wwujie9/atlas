package com.base.atlas.common.http;

import io.reactivex.observers.DisposableObserver;

public abstract class HttpObserver<T> extends DisposableObserver<T> {

  public HttpObserver() {}

  @Override
  public void onNext(T response) {
    onSuccess(response);
  }

  @Override
  public void onError(Throwable e) {
    e.printStackTrace();
  }

  @Override
  public void onComplete() {}

  public abstract void onSuccess(T t);
}
