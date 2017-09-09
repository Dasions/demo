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
	 * redis�洢����
	 */
	public void addRedisDemo(final RedisDemoBean bean) {
		// ���л�java����==>�����ƣ���ŵ�redis
		redisUtl.setValue(bean.getId(), SerializeUtil.serialize(bean));
	}

	/**
	 * redis��ȡ��������У������л�Ϊjava����
	 */
	public RedisDemoBean getRedisDemo(String key) {
		RedisDemoBean bean;
		// ͨ��key��ȡ����Ķ�������������
		byte[] value = (byte[]) redisUtl.getValue(key);
		// ���������������鷴���л�Ϊjava����
		bean = (RedisDemoBean) SerializeUtil.unserialize(value);
		return bean;
	}

	/**
	 * redis������÷�
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
	 * redis��ȡ�ַ����������� 
	 */
	public String getRedisTransactionsDemo(String key){
		return (String) redisUtl.getValue(key);
	}

}
