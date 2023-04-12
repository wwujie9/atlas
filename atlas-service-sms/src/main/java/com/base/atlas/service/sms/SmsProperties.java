package com.base.atlas.service.sms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaiJie Pang
 * @since 2023/2/17
 */
@ConfigurationProperties(prefix = "atlas.sms")
@Configuration
@Data
public class SmsProperties {

  private String verifyCodeTemplateKey;

  private Integer verifyCodeLength = 6;
}
