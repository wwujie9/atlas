package com.base.atlas.support.token;

import com.base.atlas.auth.JWTPrincipal;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.base.atlas.common.util.JsonUtils;
import com.base.atlas.common.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author CaiJie Pang
 * @since 2023/1/31
 */
public class JWTTokenHelper {

  private final static String CONTENT_CLAIM_NAME = "json_payload";

  public static String getToken(HttpServletRequest httpServletRequest, String tokenName) {
    if (httpServletRequest == null || tokenName == null) {
      return null;
    }
    return httpServletRequest.getHeader(tokenName);
  }

  public static String sign(JWTPrincipal principal, String secretKey, long expiredAfter) {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    return JWT.create()
        .withClaim(CONTENT_CLAIM_NAME, JsonUtils.toJson(principal))
        .withExpiresAt(new Date(System.currentTimeMillis() + expiredAfter))
        .sign(algorithm);
  }

  public static DecodedJWT verify(String token, String secretKey) throws JWTVerificationException {
    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    JWTVerifier verifier = JWT.require(algorithm).build();
    return verifier.verify(token);
  }

  public static JWTPrincipal getPayload(String token) {
    if (StringUtils.isBlank(token)) {
      return null;
    }

    try {
      DecodedJWT jwt = JWT.decode(token);
      String jsonPayload = jwt.getClaim(CONTENT_CLAIM_NAME).asString();
      return JsonUtils.toObject(jsonPayload, JWTPrincipal.class);
    } catch (JWTDecodeException e) {
      return null;
    }

  }
}
