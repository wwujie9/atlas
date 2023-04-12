package com.base.atlas.config.web;

import com.base.atlas.auth.JWTAuthProperties;
import com.base.atlas.session.SessionUtil;
import com.base.atlas.session.UserSession;
import com.base.atlas.auth.JWTPrincipal;
import com.base.atlas.support.token.JWTTokenHelper;
import com.base.atlas.common.util.IpUtils;
import com.base.atlas.common.util.StringUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CaiJie Pang
 * @since 2023/2/1
 */
@Configuration
public class AccessSessionConfig implements WebMvcConfigurer {

  @Resource
  private JWTAuthProperties jwtAuthProperties;

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new SessionInterceptorAdapter())
        .addPathPatterns("/**")
        .order(Integer.MIN_VALUE);
  }

  class SessionInterceptorAdapter extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

      UserSession userSession = new UserSession();

      String token = request.getHeader(jwtAuthProperties.getTokenName());
      if (StringUtils.isNotBlank(token)) {
        JWTPrincipal principal = JWTTokenHelper.getPayload(token);
        if (principal != null) {
          userSession.setUserId(principal.getUserId());
          userSession.setName(principal.getName());
        }
      }

      userSession.setOsVersion(request.getHeader("os_version"));
      userSession.setOsDevice(request.getHeader("os_device"));
      userSession.setOsBand(request.getHeader("os_band"));
      userSession.setTerminal(request.getHeader("app_terminal"));
      userSession.setAppVersion(request.getHeader("app_version"));
      userSession.setOsId("os_id");
      if (request.getHeader("lat") != null && request.getHeader("lon") != null) {
        try {
          userSession.setLocation(new Double[]{
              Double.valueOf(request.getHeader("lat")),
              Double.valueOf(request.getHeader("lon"))
          });
        } catch (Exception e) {
          // ignore
        }
      }
      userSession.setRemoteIp(IpUtils.getIp(request));
      userSession.setRemoteUri(request.getRequestURI());
      SessionUtil.setUserSession(userSession);
      return true;
    }

  }
}
