package com.base.atlas.service.sms.aliyun;

import com.base.atlas.service.sms.SmsService;
import com.base.atlas.service.sms.SmsProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author CaiJie Pang
 * @since 2023/2/6
 */
@Configuration
public class AliyunConfig {

  @Resource
  private AliyunSmsProperties aliyunSmsProperties;
  @Resource
  private SmsProperties smsProperties;

  @Bean
  public SmsService smsService() {
    return new AliyunSmsService(aliyunSmsProperties, smsProperties);
  }
}
