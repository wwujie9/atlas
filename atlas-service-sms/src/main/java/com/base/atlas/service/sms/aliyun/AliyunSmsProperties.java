package com.base.atlas.service.sms.aliyun;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author CaiJie Pang
 * @since 2023/2/6
 */
@ConfigurationProperties(prefix = "atlas.sms.aliyun")
@Configuration
@Data
public class AliyunSmsProperties {

  private boolean enable = false;

  private String region = "cn-shanghai";

  private String endPoint = "dysmsapi.aliyuncs.com";

  private String defaultTemplateCode;

  private String signName;

  private String accessKey;

  private String accessKeySecret;
}
