package com.base.atlas.session;

import lombok.Data;

import java.io.Serializable;

/**
 * @author CaiJie Pang
 * @since 2023/2/1
 */
@Data
public class Principal implements Serializable {

  private static final long serialVersionUID = -8243733335231053121L;

  private String userId;

  private String name;
}
