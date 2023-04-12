package com.base.atlas.web;

import com.base.atlas.constants.BaseBizCode;
import com.base.atlas.exception.BizException;
import com.base.atlas.exception.ValidateException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author CaiJie Pang
 * @since 2023/1/18
 */
@Slf4j
public abstract class BaseController {

  @ExceptionHandler(BizException.class)
  public ApiResponse<Void> bizExceptionHandler(BizException exception) {
    String msg = exception.getMessage();
    String errorCode = exception.getErrorCode();
    log.error(msg, exception);
    return ApiResponse.error(errorCode == null? BaseBizCode.ERROR_UNKNOWN : errorCode, msg == null ? "未知错误" : msg);
  }

  @ExceptionHandler(ValidateException.class)
  public ApiResponse<Void> validateExceptionHandler(ValidateException exception) {
    String msg = exception.getMessage();
    String errorCode = exception.getErrorCode();
    log.error(msg, exception);
    return ApiResponse.error(errorCode == null? BaseBizCode.ERROR_UNKNOWN : errorCode, msg == null ? "未知错误" : msg);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ApiResponse<Void> argumentNotValidExceptionExceptionHandler(MethodArgumentNotValidException exception) {
    log.error(exception.getMessage(), exception);
    return ApiResponse.error(BaseBizCode.ERROR_PARAM_ILLEGAL,exception.getBindingResult().getAllErrors().get(0).getDefaultMessage());
  }

  @ExceptionHandler(FileSizeLimitExceededException.class)
  public ApiResponse<Void> fileSizeLimitExceptionHandler() {
    return ApiResponse.error(BaseBizCode.ERROR_FILESIZE_LIMIT, "文件大小超出上传限制");
  }

  @ExceptionHandler(Exception.class)
  public ApiResponse<Void> commonExceptionHandler(Exception exception) {
    log.error(exception.getMessage(), exception);
    return ApiResponse.error(BaseBizCode.ERROR_UNKNOWN, exception.getMessage() == null? "未知错误" : exception.getMessage());
  }
}
