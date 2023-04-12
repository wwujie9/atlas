package com.base.atlas.config.log;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CaiJie Pang
 * @since 2023/1/17
 */
@Configuration
public class AccessConfig implements WebMvcConfigurer {

  @Resource
  private AccessProperties accessProperties;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    // add log Interceptor
    HandlerInterceptor handlerInterceptor = accessProperties.getLog().getLogInterceptor();
    if (handlerInterceptor == null) {
      handlerInterceptor = new AccessInterceptorAdapter().setHeaderKeys(accessProperties.getHeader());
    }
    InterceptorRegistration accessLogInterceptorRegistration = registry.addInterceptor(handlerInterceptor).order(0);

    List<String> accessLogIncludePaths = accessProperties.getLog().getInclude();
    if (accessLogIncludePaths != null) {
      accessLogInterceptorRegistration.addPathPatterns(accessLogIncludePaths);
    }

    List<String> accessLogExcludePaths = accessProperties.getLog().getExclude();
      if (accessLogExcludePaths != null) {
        accessLogInterceptorRegistration.excludePathPatterns(accessLogExcludePaths);
      }
  }
}
