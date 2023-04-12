package com.base.atlas.web;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Range;

/**
 * @author CaiJie Pang
 * @since 2023/2/1
 */
@ApiModel(description = "分页参数",value = "PageRequest")
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class PageRequest {

  @ApiModelProperty(value = "当前页号")
  private Integer pageNo = 1;

  @ApiModelProperty(value = "每页大小")
  @Range(min = 1, max = 500, message = "分页范围：1-500" )
  private Integer pageSize = 20;

}
