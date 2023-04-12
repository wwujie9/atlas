package com.base.atlas.starter.mongodb;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

/**
 * @author CaiJie Pang
 * @since 2023/2/9
 */
public interface MongoService<T extends BaseMongoEntity> {

  T save(T entity);

  List<T> saveAll(Iterable<T> entities);

  Optional<T> findById(String id);

  Optional<T> findOne(Example<T> example);

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.CrudRepository#findAll()
   */
  List<T> findAll();

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.PagingAndSortingRepository#findAll(org.springframework.data.domain.Sort)
   */
  List<T> findAll(Sort sort);

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example)
   */
  List<T> findAll(Example<T> example);

  Page<T> findAll(Example<T> example, Pageable pageable);

  /*
   * (non-Javadoc)
   * @see org.springframework.data.repository.query.QueryByExampleExecutor#findAll(org.springframework.data.domain.Example, org.springframework.data.domain.Sort)
   */
  List<T> findAll(Example<T> example, Sort sort);

  long count(Example<T> example);

  boolean existsById(String id);

  boolean exists(Example<T> example);
}
