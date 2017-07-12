package redisdemo.service;

import redisdemo.bean.RedisDemoBean;

public interface RedisDemoService {

	void addRedisDemo(RedisDemoBean bean);
	RedisDemoBean getRedisDemo(String key);
	void addRedisTransactionsDemo();
	String getRedisTransactionsDemo(String key);
}
