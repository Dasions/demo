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
		//���л�java����==>�����ƣ���ŵ�redis
		valueops.set(bean.getId(), SerializeUtil.serialize(bean));
	}

	public RedisDemoBean getRedisDemo(String key) {
		// TODO Auto-generated method stub
		ValueOperations<String, byte[]> valueops = redisTemplate.opsForValue();
		RedisDemoBean bean;
		//ͨ��key��ȡ����Ķ�������������
		byte[] value=valueops.get(key);
		//���������������鷴���л�Ϊjava����
		bean=(RedisDemoBean) SerializeUtil.unserialize(value);
		return bean;
	}

}
