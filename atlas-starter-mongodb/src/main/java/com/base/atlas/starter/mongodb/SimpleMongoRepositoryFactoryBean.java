package com.base.atlas.starter.mongodb;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactoryBean;
import org.springframework.data.repository.core.RepositoryInformation;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

/**
 * @author CaiJie Pang
 * @since 2023/2/9
 */
public class SimpleMongoRepositoryFactoryBean<T extends MongoRepository<S, String>, S extends BaseMongoEntity>
    extends MongoRepositoryFactoryBean<T, S, String> {
  /**
   * Creates a new {@link MongoRepositoryFactoryBean} for the given repository interface.
   *
   * @param repositoryInterface must not be {@literal null}.
   */
  public SimpleMongoRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
    super(repositoryInterface);
  }

  @Override
  protected RepositoryFactorySupport getFactoryInstance(MongoOperations operations) {
    return new SimpleMongoRepositoryFactory(operations);
  }

  private static class SimpleMongoRepositoryFactory<T extends BaseMongoEntity> extends MongoRepositoryFactory {
    /**
     * Creates a new {@link MongoRepositoryFactory} with the given {@link MongoOperations}.
     *
     * @param mongoOperations must not be {@literal null}.
     */
    private final MongoOperations mongoOperations;


    public SimpleMongoRepositoryFactory(MongoOperations mongoOperations) {
      super(mongoOperations);
      this.mongoOperations = mongoOperations;
    }

    @Override
    protected Object getTargetRepository(RepositoryInformation information) {
      MongoEntityInformation<T, String> metadata = getEntityInformation((Class<T>) information.getDomainType());
      return new SimpleMongoRepositoryImplement<>(metadata, mongoOperations);
    }

    @Override
    protected Class<?> getRepositoryBaseClass(RepositoryMetadata metadata) {
      return SimpleMongoRepositoryImplement.class;
    }
  }

}
