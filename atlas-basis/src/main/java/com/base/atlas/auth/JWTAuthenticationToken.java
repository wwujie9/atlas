package com.base.atlas.auth;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author CaiJie Pang
 * @since 2023/1/29
 */
public class JWTAuthenticationToken implements AuthenticationToken {

  private final JWTPrincipal jtwPrincipal;

  private static final long serialVersionUID = 8638786028184086455L;

  public JWTAuthenticationToken(JWTPrincipal jtwPrincipal) {
    this.jtwPrincipal = jtwPrincipal;
  }

  @Override
  public Object getPrincipal() {
    return jtwPrincipal;
  }

  @Override
  public Object getCredentials() {
    return null;
  }
}
