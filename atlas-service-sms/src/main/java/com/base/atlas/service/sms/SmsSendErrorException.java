package com.base.atlas.service.sms;

/**
 * @author CaiJie Pang
 * @since 2023/2/6
 */
public class SmsSendErrorException extends Exception {
  private static final long serialVersionUID = 772652014340038765L;

  public SmsSendErrorException(String message) {
    super(message);
  }

  public SmsSendErrorException(String message, Throwable cause) {
    super(message, cause);
  }

  public SmsSendErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
