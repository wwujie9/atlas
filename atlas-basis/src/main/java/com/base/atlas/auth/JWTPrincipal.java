package com.base.atlas.auth;

import com.base.atlas.session.Principal;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author CaiJie Pang
 * @since 2023/1/31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class JWTPrincipal extends Principal {

  private static final long serialVersionUID = -6856048932226034151L;

  private long expiredAt;
}
