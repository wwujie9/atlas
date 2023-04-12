package com.base.atlas.starter.mongodb;

import jdk.nashorn.internal.runtime.GlobalConstants;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

/**
 * @author CaiJie Pang
 * @since 2023/2/9
 */
@Data
public abstract class BaseMongoEntity {

  @Id
  private String id;

  @CreatedBy
  private String createUser;

  @CreatedDate
  private Date createDate;

  @LastModifiedBy
  private String updateUser;

  @LastModifiedDate
  private Date updateDate;

  private Boolean deleted = false;

}
