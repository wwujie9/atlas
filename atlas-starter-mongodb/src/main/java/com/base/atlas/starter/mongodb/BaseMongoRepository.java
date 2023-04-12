package com.base.atlas.starter.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author CaiJie Pang
 * @since 2023/2/9
 */
@NoRepositoryBean
public interface BaseMongoRepository<T extends BaseMongoEntity> extends MongoRepository<T, String> {
}
