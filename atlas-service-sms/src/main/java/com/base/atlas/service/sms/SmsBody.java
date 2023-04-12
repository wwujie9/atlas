package com.base.atlas.service.sms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @author CaiJie Pang
 * @since 2022/10/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SmsBody implements Serializable {

  private static final long serialVersionUID = -3730193405203093489L;

  /** 发送目标手机号，多个手机号用逗号隔开 */
  private String phoneNumbers;

  private String templateCode;

  private HashMap<String, String> params;

  public SmsBody put(String paramName, String param) {
    if (this.params == null) {
      this.params = new HashMap<>();
    }
    this.params.put(paramName, param);
    return this;
  }
}
