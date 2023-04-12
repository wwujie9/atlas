package com.base.atlas.auth;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author CaiJie Pang
 * @since 2023/1/31
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "auth.oauth2.jwt")
public class JWTAuthProperties {

  private String tokenName = "o-token";

  private String secretKey = "w$aT&*l!aa(s12+--=H!^";

  private long maxIdleInMinutes = 60 * 24 * 30;

  private List<String> authcPaths;
}
