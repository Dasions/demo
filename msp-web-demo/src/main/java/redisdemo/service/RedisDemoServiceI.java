package redisdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import commons.utils.SerializeUtil;
import redisdemo.bean.RedisDemoBean;

@Service 
public class RedisDemoServiceI implements RedisDemoService{

	@Autowired
	private RedisTemplate<String, byte[]> redisTemplate;
	
	public void addRedisDemo(final RedisDemoBean bean) {
		ValueOperations<String, byte[]> valueops = redisTemplate.opsForValue();
		//序列化java对象==>二进制，存放到redis
		valueops.set(bean.getId(), SerializeUtil.serialize(bean));
	}

	public RedisDemoBean getRedisDemo(String key) {
		// TODO Auto-generated method stub
		ValueOperations<String, byte[]> valueops = redisTemplate.opsForValue();
		RedisDemoBean bean;
		//通过key获取对象的二进制数据数组
		byte[] value=valueops.get(key);
		//将二进制数据数组反序列化为java对象
		bean=(RedisDemoBean) SerializeUtil.unserialize(value);
		return bean;
	}

}
