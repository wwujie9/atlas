package com.base.atlas.service.sms.aliyun;

import com.aliyun.dysmsapi20170525.Client;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.teaopenapi.models.Config;
import com.base.atlas.common.util.JsonUtils;
import com.base.atlas.common.util.StringUtils;
import com.base.atlas.service.sms.SmsBody;
import com.base.atlas.service.sms.SmsSendErrorException;
import com.base.atlas.service.sms.SmsService;
import com.base.atlas.service.sms.SmsProperties;

/**
 * @author CaiJie Pang
 * @since 2022/10/18
 */

public class AliyunSmsService implements SmsService {

  private final Config config;

  private AliyunSmsProperties properties;

  private SmsProperties commonSmsProperties;

  public AliyunSmsService(AliyunSmsProperties properties, SmsProperties commonSmsProperties) {
    this.properties = properties;
    this.commonSmsProperties = commonSmsProperties;
    config = new Config()
        .setAccessKeyId(properties.getAccessKey())
        .setAccessKeySecret(properties.getAccessKeySecret())
        .setEndpoint(properties.getEndPoint())
        .setRegionId(properties.getRegion());
  }

  @Override
  public void sendVerifyCode(String code, String phoneNumbers) throws SmsSendErrorException {
    AliyunSmsBody smsBody = new AliyunSmsBody();
    smsBody.setPhoneNumbers(phoneNumbers);
    smsBody.put("code", code);
    smsBody.setTemplateCode(commonSmsProperties.getVerifyCodeTemplateKey());
    sendMsg(smsBody);
  }

  @Override
  public <S extends SmsBody> void sendMsg(S smsBody) throws SmsSendErrorException {
    if (smsBody == null || StringUtils.isEmpty(smsBody.getTemplateCode()) || smsBody.getParams() == null) {
      throw new SmsSendErrorException("Arguments should not be null!");
    }

    // Parameter settings for API request
    SendSmsRequest sendSmsRequest = new SendSmsRequest()
        .setSignName(properties.getSignName())
        .setTemplateCode(smsBody.getTemplateCode())
        .setPhoneNumbers(smsBody.getPhoneNumbers())
        .setTemplateParam(JsonUtils.toJson(smsBody.getParams()));


    try {
      Client client = new Client(config);
      SendSmsResponse resp = client.sendSms(sendSmsRequest);
      if ("OK".equals(resp.getBody().getCode())) {
        return;
      }
      throw new SmsSendErrorException(resp.getBody().getMessage());
    } catch (Exception e) {
      throw new SmsSendErrorException(e.getMessage(), e);
    }
  }

}
