package com.base.atlas.service.sms;

/**
 * @author CaiJie Pang
 * @since 2022/10/18
 */
public interface SmsService {

  <S extends SmsBody> void sendMsg(S smsBody) throws SmsSendErrorException;

  void sendVerifyCode(String code, String phoneNumbers) throws SmsSendErrorException;

}
