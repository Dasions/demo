package demo.service;

import demo.bean.DemoBean;

public interface DemoService {
	public DemoBean selectDemoBeanById(Integer id); 
	public void insertDemoBean(DemoBean demoBean);
	public void updateDemoBean(DemoBean demoBean);
	public void delDemoBean(DemoBean demoBean);
	public void insertTransaction(DemoBean demoBean);
}
