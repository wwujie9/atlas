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
@ConfigurationProperties(prefix = "auth")
public class AuthProperties {

  private List<String> anonPaths;

  private List<String> authcPaths;
}
