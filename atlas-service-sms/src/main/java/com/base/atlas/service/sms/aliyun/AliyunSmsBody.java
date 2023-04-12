package com.base.atlas.service.sms.aliyun;

import com.base.atlas.service.sms.SmsBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CaiJie Pang
 * @since 2022/10/18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AliyunSmsBody extends SmsBody {

  private static final long serialVersionUID = -8681453054260483875L;

  private String outId;
}
