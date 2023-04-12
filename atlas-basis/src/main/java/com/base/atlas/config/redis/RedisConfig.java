package com.base.atlas.config.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.base.atlas.session.SessionUtil;
import com.base.atlas.support.redis.RedisClient;
import com.base.atlas.common.util.CollectionUtils;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;

/**
 * @author CaiJie Pang
 * @since 2023/2/6
 */
@Configuration
@ConditionalOnProperty(value = "spring.redis.enable", havingValue = "true", matchIfMissing = true)
public class RedisConfig {

  @Resource
  private RedisProperties redisProperties;

  @Bean
  public RedisClient redisClient() {
    return new RedisClient(cStringRedisTemplate());
  }

  @Bean
  public JedisConnectionFactory jedisConnectionFactory() {

    if (redisProperties.getCluster() != null && CollectionUtils.isNotEmpty(redisProperties.getCluster().getNodes())) {
      RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration();
      Set<RedisNode> clusterNodes = new HashSet<>();
      for (String node : redisProperties.getCluster().getNodes()) {
        String[] hostAndPort = node.split(":");
        RedisNode redisNode = new RedisNode(hostAndPort[0], Integer.parseInt(hostAndPort[1]));
        clusterNodes.add(redisNode);
      }
      redisClusterConfiguration.setClusterNodes(clusterNodes);
      redisClusterConfiguration.setPassword(redisProperties.getPassword());

      return new JedisConnectionFactory(redisClusterConfiguration, new JedisPoolConfig());
    } else {
      RedisStandaloneConfiguration standaloneConfig = new RedisStandaloneConfiguration();
      standaloneConfig.setPort(redisProperties.getPort());
      standaloneConfig.setPassword(redisProperties.getPassword());
      standaloneConfig.setHostName(redisProperties.getHost());
      standaloneConfig.setDatabase(redisProperties.getDatabase());
      return new JedisConnectionFactory(standaloneConfig);
    }

  }

  @Bean
  public RedisCacheManager redisCacheManager() {
    RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
        .entryTtl(Duration.ofDays(1))
        .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    return RedisCacheManager.builder(jedisConnectionFactory())
        .cacheDefaults(config)
        .build();
  }

  @Bean(name = "userIdCacheKey")
  public KeyGenerator userIdCacheKey() {
    return (target, method, params) -> {
      String userId = SessionUtil.getCurrentUserId();
      if (userId == null) {
        userId = "-1";
      }
      return userId;
    };
  }

  @Bean
  public RedisTemplate<String, Object> cStringRedisTemplate() {

    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    RedisSerializer<String> redisSerializer = new StringRedisSerializer();
    Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
    ObjectMapper mapper = new ObjectMapper();
    mapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
    mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    jackson2JsonRedisSerializer.setObjectMapper(mapper);
    redisTemplate.setConnectionFactory(jedisConnectionFactory());
    // redis存储的 key 序列化方式
    redisTemplate.setKeySerializer(redisSerializer);
    redisTemplate.setHashKeySerializer(redisSerializer);
    // redis存储的值序列化方式
    redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
    redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
    return redisTemplate;
  }
}
