package com.base.atlas.starter.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;

/**
 * @author CaiJie Pang
 * @since 2023/2/9
 */
public class SimpleMongoRepositoryImplement<T extends BaseMongoEntity>
    extends SimpleMongoRepository<T, String>
    implements BaseMongoRepository<T> {

  private final MongoOperations mongoOperations;

  private final MongoEntityInformation<T, String> entityInformation;
  /**
   * Creates a new {@link SimpleMongoRepository} for the given {@link MongoEntityInformation} and {@link MongoTemplate}.
   *  @param metadata must not be {@literal null}.
   * @param mongoOperations must not be {@literal null}.
   */
  public SimpleMongoRepositoryImplement(MongoEntityInformation<T, String> metadata, MongoOperations mongoOperations) {
    super(metadata, mongoOperations);
    this.mongoOperations = mongoOperations;
    this.entityInformation = metadata;
  }
}
