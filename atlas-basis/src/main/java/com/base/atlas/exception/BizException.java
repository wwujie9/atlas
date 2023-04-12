package com.base.atlas.exception;

/**
 * @author CaiJie Pang
 * @since 2023/2/2
 */
public class BizException extends RuntimeException {

  private static final long serialVersionUID = 4897727272768356250L;

  private String errorCode;

  public BizException(String errorCode, String message) {
    super(message);
  }

  public BizException(String errorCode, String message, Throwable cause) {
    super(message, cause);
  }

  public BizException(String errorCode, Throwable cause) {
    super(cause);
  }

  public String getErrorCode() {
    return errorCode;
  }
}
