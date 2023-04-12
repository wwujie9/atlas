package com.base.atlas.web;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author CaiJie Pang
 * @since 2022/11/18
 */
@Data
@Accessors(chain = true)
public class PageBean<T> implements Serializable {

  private static final long serialVersionUID = -2994671732177745583L;

  // 当前页数
  private int pageNo;
  // 每页数量
  private int pageSize;

  private List<T> content = new ArrayList<>();

  public static <T> PageBean<T> of(List<T> content) {
    PageBean<T> pageBean = new PageBean<>();
    pageBean.setContent(content);
    return pageBean;
  }

  public PageBean<T> withPageSize(int pageSize) {
    this.pageSize = pageSize;
    return this;
  }

  public PageBean<T> withPageNo(int pageNo) {
    this.pageNo = pageNo;
    return this;
  }

}
