package com.base.atlas.config.log;

import com.base.atlas.session.SessionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author CaiJie Pang
 * @since 2023/1/17
 */
public class AccessInterceptorAdapter extends HandlerInterceptorAdapter {

  private Logger accessLogger = LoggerFactory.getLogger("access_logger");

  private AccessProperties.Header headerKeys;

  public AccessInterceptorAdapter setHeaderKeys(AccessProperties.Header headerKeys) {
    this.headerKeys = headerKeys;
    return this;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
    accessLogger.info(accessInfo(request));
    return super.preHandle(request, response, handler);
  }

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    super.afterCompletion(request, response, handler, ex);
  }

  @Override
  public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    super.afterConcurrentHandlingStarted(request, response, handler);
  }

  private String accessInfo(HttpServletRequest request) {
    return "{" +
        "\"userId\":\"" + SessionUtil.getCurrentUserId() + "\"," +
        "\"origin\":\"" + request.getHeader(headerKeys.getOsVersionKey()) + "\"," +
        "\"deviceBand\":\"" + request.getHeader(headerKeys.getOsBandKey()) + "\"," +
        "\"deviceModel\":\"" + request.getHeader(headerKeys.getOsDeviceKey()) + "\"," +
        "\"sdk\":\"" + request.getHeader(headerKeys.getAppVersionKey()) + "\"," +
        "\"terminal\":\"" + request.getHeader(headerKeys.getAppTerminalKey()) + "\"," +
        "\"osId\":\"" + request.getHeader(headerKeys.getOsIdKey()) + "\"," +
        "\"ip\":\"" + SessionUtil.getSession().getRemoteIp() + "\"," +
        "\"method\":\"" + request.getMethod() + "\"," +
        "\"path\":\"" + request.getRequestURI() + "\"," +
        "\"query\":\"" + request.getQueryString() + "\"," +
        "\"visitDateTime\":\"" + new Date() + "\"" +
        "}";
  }


}
