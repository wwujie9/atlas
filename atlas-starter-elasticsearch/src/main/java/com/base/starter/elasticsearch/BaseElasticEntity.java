package com.base.starter.elasticsearch;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serializable;
import java.util.Date;

/**
 * @author CaiJie Pang
 * @since 2023/2/13
 */
@Data
public abstract class BaseElasticEntity implements Serializable {
  private static final long serialVersionUID = 2728974656841377260L;

//  @Id
//  private String id;
//
//  @CreatedBy
//  private Long createUser;
//
//  @CreatedDate
//  private Date createDate;
//
//  @LastModifiedBy
//  private Long updateUser;
//
//  @LastModifiedDate
//  private Date updateDate;
}
