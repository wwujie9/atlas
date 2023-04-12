package com.base.atlas.database.mybatis;

import com.base.atlas.session.SessionUtil;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author CaiJie Pang
 * @since 2023/2/2
 */
@Component
public class MetaHandler implements MetaObjectHandler {

  @Override
  public void insertFill(MetaObject metaObject) {
    this.setFieldValByName("createTime", new Date(), metaObject);
    this.setFieldValByName("createUser", getOperateUserId(), metaObject);
  }

  @Override
  public void updateFill(MetaObject metaObject) {
    this.setFieldValByName("updateTime", new Date(), metaObject);
    this.setFieldValByName("updateUser", getOperateUserId(), metaObject);
  }

  private String getOperateUserId() {
    if (SessionUtil.getSession() == null) {
      return "";
    }

    return SessionUtil.getSession().getUserId();
  }
}
