package redisdemo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import commons.utils.RedisUtil;
import commons.utils.SerializeUtil;
import redisdemo.bean.RedisDemoBean;

@Service
public class RedisDemoServiceI<T> implements RedisDemoService {

	@Autowired
	private RedisUtil redisUtl;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	/**
	 * redis存储对象
	 */
	public void addRedisDemo(final RedisDemoBean bean) {
		// 序列化java对象==>二进制，存放到redis
		redisUtl.setValue(bean.getId(), SerializeUtil.serialize(bean));
	}

	/**
	 * redis获取对象的序列，反序列化为java对象
	 */
	public RedisDemoBean getRedisDemo(String key) {
		RedisDemoBean bean;
		// 通过key获取对象的二进制数据数组
		byte[] value = (byte[]) redisUtl.getValue(key);
		// 将二进制数据数组反序列化为java对象
		bean = (RedisDemoBean) SerializeUtil.unserialize(value);
		return bean;
	}

	/**
	 * redis事务的用法
	 */
	public void addRedisTransactionsDemo() {
		List<Object> txResults =redisTemplate.execute(new SessionCallback<List<Object>>() {

			public <K, V> List<Object> execute(RedisOperations<K, V> operations) throws DataAccessException {
				operations.multi();
				redisTemplate.opsForValue().set("test-1",  "2");
				redisTemplate.opsForValue().set("test-2",  "3");
				redisTemplate.opsForValue().set("test-3",  "4");
				return operations.exec();
			}
		});
	}
	
	/**
	 * redis获取字符串类型数据 
	 */
	public String getRedisTransactionsDemo(String key){
		return (String) redisUtl.getValue(key);
	}

}
