package redisdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import commons.utils.RedisUtil;
import commons.utils.SerializeUtil;
import redisdemo.bean.RedisDemoBean;

@Service 
public class RedisDemoServiceI implements RedisDemoService{

	@Autowired
	private RedisUtil redisUtl;
	
	public void addRedisDemo(final RedisDemoBean bean) {
		//序列化java对象==>二进制，存放到redis
		redisUtl.setValue(bean.getId(), SerializeUtil.serialize(bean));
	}

	public RedisDemoBean getRedisDemo(String key) {
		RedisDemoBean bean;
		//通过key获取对象的二进制数据数组
		byte[] value=(byte[]) redisUtl.getValue(key);
		//将二进制数据数组反序列化为java对象
		bean=(RedisDemoBean) SerializeUtil.unserialize(value);
		return bean;
	}

}
