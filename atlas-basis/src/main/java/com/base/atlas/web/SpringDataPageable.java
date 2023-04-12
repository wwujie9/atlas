package com.base.atlas.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Min;

/**
 * @author wujie
 * @date 2023/2/23 16:18
 * 自定义分页实现
 */
@NoArgsConstructor
@AllArgsConstructor
public class SpringDataPageable implements Pageable {

    /** 当前页 */
    @Min(1)
    private int pageNumber;
    /** 页大小*/
    @Min(1)
    private int pageSize;
    /** 排序设置 */
    private Sort sort;

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    @Override
    public int getPageNumber() {
        return this.pageNumber;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return this.pageSize;
    }

    @Override
    public long getOffset() {
        return (getPageNumber() - 1) * getPageSize();
    }

    public void setSort(Sort sort) {
        this.sort = sort;
    }

    @Override
    public Sort getSort() {
        return this.sort;
    }

    @Override
    public Pageable next() {
        return null;
    }

    @Override
    public Pageable previousOrFirst() {
        return null;
    }

    @Override
    public Pageable first() {
        return null;
    }

    @Override
    public Pageable withPage(int i) {
        return null;
    }

    @Override
    public boolean hasPrevious() {
        return false;
    }
}
