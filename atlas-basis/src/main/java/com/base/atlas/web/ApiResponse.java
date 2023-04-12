package com.base.atlas.web;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * @author CaiJie Pang
 * @since 2022/11/18
 */
public class ApiResponse<T> implements Serializable {

  private static final long serialVersionUID = 5645495899900316228L;

  private String returnCode;

  private String returnMessage;

  private T data;

  public static <T> ApiResponse<T> success(T data) {
    ApiResponse<T> apiResponse = new ApiResponse<>();
    apiResponse.returnCode = "0000";
    apiResponse.returnMessage = "响应成功";
    apiResponse.data = data;
    return apiResponse;
  }

  public static <T> ApiResponse<T> success() {
    return success(null);
  }

  public static <T> ApiResponse<T> error(String returnCode, String returnMessage, T data) {
    ApiResponse<T> apiResponse = new ApiResponse<>();
    apiResponse.returnCode = returnCode;
    apiResponse.returnMessage = returnMessage;
    apiResponse.data = data;
    return apiResponse;
  }

  public static <T> ApiResponse<T> error(String returnCode, String returnMessage) {
    return error(returnCode, returnMessage, null);
  }

  public static <T> ApiResponse<T> errorWithEmptyArray(String returnCode, String returnMessage) {
    return (ApiResponse<T>) error(returnCode, returnMessage, new ArrayList<>());
  }


  public String getReturnCode() {
    return returnCode;
  }

  public void setReturnCode(String returnCode) {
    this.returnCode = returnCode;
  }

  public String getReturnMessage() {
    return returnMessage;
  }

  public void setReturnMessage(String returnMessage) {
    this.returnMessage = returnMessage;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
