package com.base.atlas.auth;

import com.base.atlas.support.token.JWTTokenHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CaiJie Pang
 * @since 2023/1/31
 */
@Service
public class Oauth2TokenService {

  @Resource
  private JWTAuthProperties jwtAuthProperties;

  public String tokenIssue(JWTPrincipal principal, HttpServletResponse httpServletResponse) {
    String token = JWTTokenHelper.sign(principal,
        jwtAuthProperties.getSecretKey(),
        jwtAuthProperties.getMaxIdleInMinutes() * 1000);
    httpServletResponse.addHeader(jwtAuthProperties.getTokenName(), token);
    return token;
  }

  public String tokenIssue(JWTPrincipal principal) {
    return JWTTokenHelper.sign(principal,
        jwtAuthProperties.getSecretKey(),
        jwtAuthProperties.getMaxIdleInMinutes() * 1000);
  }
}
