package com.base.atlas.support.redis;

import org.springframework.data.redis.core.RedisTemplate;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * redis连接工具
 */
public class RedisClient {

	private final RedisTemplate<String, Object> cStringRedisTemplate;

	public RedisClient(RedisTemplate<String, Object> cStringRedisTemplate) {
		this.cStringRedisTemplate = cStringRedisTemplate;
	}

	/**
	 * 设置值，不过期
	 */
	public void setValue(String key, Object value) {
		cStringRedisTemplate.opsForValue().set(key, value);
	}

	/**
	 * 设置值和其过期时间
	 * 过期时间为微秒
	 */
	public void setValue(String key, Object value, long timeout) {
		cStringRedisTemplate.opsForValue().set(key, value, timeout, TimeUnit.MILLISECONDS);
	}

	/**
	 * 设置 key 的过期时间
	 * @param key redis中存的key
	 * @param timeout 过期时间-微秒
	 */
	public void expire(String key, long timeout) {
		cStringRedisTemplate.expire(key, timeout, TimeUnit.MILLISECONDS);
	}

	public void expire(String key, long timeout, TimeUnit timeUnit) {
		cStringRedisTemplate.expire(key, timeout, timeUnit);
	}

	public Object getValue(String key) {
		return cStringRedisTemplate.opsForValue().get(key);
	}

	public void delete(String key) {
		cStringRedisTemplate.delete(key);
	}

	public void delete(Collection<String> keys) {
		cStringRedisTemplate.delete(keys);
	}

	public void pusInSets(String key, Object... members) {
		cStringRedisTemplate.opsForSet().add(key, members);
	}

	public boolean isMemberInSets(String setsKey, Object member) {
		Boolean result = cStringRedisTemplate.opsForSet().isMember(setsKey, member);
		return result != null && result;
	}

	public void removeFromSets(String setsKey, Object... members) {
		cStringRedisTemplate.opsForSet().remove(setsKey, members);
	}

	public void leftPushInList(String key, Object value) {
		cStringRedisTemplate.opsForList().leftPush(key, value);
	}

	public Object leftPopFromList(String key) {
		return cStringRedisTemplate.opsForList().leftPop(key, 100, TimeUnit.MILLISECONDS);
	}

	public void rightPushInList(String key, Object value) {
		cStringRedisTemplate.opsForList().rightPush(key, value);
	}

	public Object rightPopFromList(String key) {
		return cStringRedisTemplate.opsForList().rightPop(key, 100, TimeUnit.MILLISECONDS);
	}

	public void trimList(String key, Integer index, Integer length) {
		cStringRedisTemplate.opsForList().trim(key, index, length);
	}

	public List<Object> getList(String key, int start, int end) {
		return cStringRedisTemplate.opsForList().range(key, start, end);
	}

	public Long removeListIterm(String key, int count, Object iterm) {
		return cStringRedisTemplate.opsForList().remove(key, count, iterm);
	}

}
