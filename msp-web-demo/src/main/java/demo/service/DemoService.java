package demo.service;

import java.util.List;

import demo.bean.DemoBean;

public interface DemoService {
	public DemoBean selectDemoBeanById(String id); 
	public void insertDemoBean(DemoBean demoBean);
	public void updateDemoBean(DemoBean demoBean);
	public void delDemoBean(DemoBean demoBean);
	public void insertTransaction(DemoBean demoBean);
	public List<DemoBean> selectDemoBeans();
}
