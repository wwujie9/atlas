package com.base.starter.elasticsearch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.Optional;

/**
 * @author CaiJie Pang
 * @since 2023/2/13
 */
public abstract class AbstractElasticService<T extends BaseElasticEntity> implements ElasticService<T> {

  @Autowired
  protected ElasticsearchRepository<T, String> baseRepository;

  public ElasticsearchRepository<T, String> getBaseRepository() {
    return baseRepository;
  }

  @Override
  public T save(T entity) {
    return baseRepository.save(entity);
  }

  @Override
  public Iterable<T> saveAll(Iterable<T> entities) {
    return baseRepository.saveAll(entities);
  }

  @Override
  public Optional<T> findById(String id) {
    return baseRepository.findById(id);
  }

  @Override
  public Iterable<T> findAll() {
    return baseRepository.findAll();
  }

  @Override
  public Iterable<T> findAll(Sort sort) {
    return baseRepository.findAll(sort);
  }

  @Override
  public long count() {
    return baseRepository.count();
  }

  @Override
  public boolean existsById(String id) {
    return baseRepository.existsById(id);
  }

}
