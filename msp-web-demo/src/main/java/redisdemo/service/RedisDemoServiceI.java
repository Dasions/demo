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
		//���л�java����==>�����ƣ���ŵ�redis
		redisUtl.setValue(bean.getId(), SerializeUtil.serialize(bean));
	}

	public RedisDemoBean getRedisDemo(String key) {
		RedisDemoBean bean;
		//ͨ��key��ȡ����Ķ�������������
		byte[] value=(byte[]) redisUtl.getValue(key);
		//���������������鷴���л�Ϊjava����
		bean=(RedisDemoBean) SerializeUtil.unserialize(value);
		return bean;
	}

}
