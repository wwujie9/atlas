package com.base.atlas.starter.mongodb;

import org.springframework.beans.factory.annotation.Autowired;
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
public abstract class AbstractMongoService<T extends BaseMongoEntity>
    implements MongoService<T> {

  @Autowired
  protected BaseMongoRepository<T> baseMongoRepository;

  @Override
  public T save(T entity) {
    return baseMongoRepository.save(entity);
  }

  @Override
  public List<T> saveAll(Iterable<T> entities) {
    return baseMongoRepository.saveAll(entities);
  }

  @Override
  public Optional<T> findById(String id) {
    return baseMongoRepository.findById(id);
  }

  @Override
  public Optional<T> findOne(Example<T> example) {
    return baseMongoRepository.findOne(example);
  }

  @Override
  public List<T> findAll() {
    return baseMongoRepository.findAll();
  }

  @Override
  public List<T> findAll(Sort sort) {
    return baseMongoRepository.findAll(sort);
  }

  @Override
  public List<T> findAll(Example<T> example) {
    return baseMongoRepository.findAll(example);
  }

  @Override
  public Page<T> findAll(Example<T> example, Pageable pageable) {
    return baseMongoRepository.findAll(example, pageable);
  }

  @Override
  public List<T> findAll(Example<T> example, Sort sort) {
    return baseMongoRepository.findAll(example, sort);
  }

  @Override
  public long count(Example<T> example) {
    return baseMongoRepository.count(example);
  }

  @Override
  public boolean existsById(String id) {
    return baseMongoRepository.existsById(id);
  }

  @Override
  public boolean exists(Example<T> example) {
    return baseMongoRepository.exists(example);
  }
}
