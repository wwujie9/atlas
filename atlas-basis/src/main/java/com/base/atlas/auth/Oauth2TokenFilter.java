package com.base.atlas.auth;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.base.atlas.support.token.JWTTokenHelper;
import com.base.atlas.web.ApiResponse;
import com.base.atlas.common.util.JsonUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author CaiJie Pang
 * @since 2023/1/29
 */
public class Oauth2TokenFilter extends AuthenticatingFilter {

  private final JWTAuthProperties jwtAuthProperties;

  public Oauth2TokenFilter(JWTAuthProperties jwtAuthProperties) {
    this.jwtAuthProperties = jwtAuthProperties;
  }

  @Override
  protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    return null;
  }

  @Override
  protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    return isAccessToken(httpServletRequest) || super.isAccessAllowed(request, response, mappedValue);
  }

  @Override
  protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    if (httpServletRequest.getRequestURI().endsWith("html")) {
//      WebUtils.issueRedirect(request, response, "");
    } else {
      ApiResponse<Boolean> accessError = ApiResponse.error("4001", "Access denied!");
      response.getWriter().write(Objects.requireNonNull(JsonUtils.toJson(accessError)));
    }
    return false;
  }

  private boolean isAccessToken(HttpServletRequest httpServletRequest) {
    String token = JWTTokenHelper.getToken(httpServletRequest, jwtAuthProperties.getTokenName());
    try {
      JWTTokenHelper.verify(token, jwtAuthProperties.getSecretKey());
      return true;
    } catch (TokenExpiredException expiredException) {
      // 对于token过期的照常放行
      return true;
    } catch (JWTVerificationException verificationException) {
      return false;
    }
  }


}
