package com.base.atlas.domain;

import com.base.atlas.constants.GlobalConstant;
import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author CaiJie Pang
 * @since 2023/1/26
 */
@Data
public class BaseEntity {

  public String id;

  /**
   * 逻辑删除标记
   * "1" - 已删除
   * "0" - 未删除（默认）
   */
  @TableField(exist = false)
  public Integer isDeleted = GlobalConstant.NOT_DELETED;

  @Version
  public int version;

  /**
   * 创建人
   */
  @TableField(
      value = "create_user",
      fill = FieldFill.INSERT
  )
  public String createUser;
  /**
   * 创建时间
   */
  @TableField(
      value = "create_time",
      fill = FieldFill.INSERT
  )
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date createTime;
  /**
   * 更新人
   */
  @TableField(
      value = "update_user",
      fill = FieldFill.UPDATE
  )
  public String updateUser;
  /**
   * 更新时间
   */
  @TableField(
      value = "update_time",
      fill = FieldFill.UPDATE
  )
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
  public Date updateTime;
}
