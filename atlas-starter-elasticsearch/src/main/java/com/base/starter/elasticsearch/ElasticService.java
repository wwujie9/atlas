package com.base.starter.elasticsearch;

import org.springframework.data.domain.Sort;

import java.util.Optional;

/**
 * @author CaiJie Pang
 * @since 2023/2/13
 */
public interface ElasticService<T extends BaseElasticEntity> {

  T save(T entity);

  Iterable<T> saveAll(Iterable<T> entities);

  Optional<T> findById(String id);

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.CrudRepository#findAll()
   */
  Iterable<T> findAll();

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
   */
  Iterable<T> findAll(Sort sort);


  long count();

  boolean existsById(String id);
}
