package com.base.atlas.session;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaiJie Pang
 * @since 2023/1/26
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class UserSession extends Principal {

  private static final long serialVersionUID = 8970888844111182957L;

  private String remoteIp;

  private String remoteUri;

  private String osVersion;

  private String osDevice;

  private String osBand;

  private String osId;

  private String terminal;

  private String appVersion;

  private Double[] location;

}
