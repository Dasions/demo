package demo.dao;

import demo.bean.DemoBean;

public interface DemoDao {
	 public DemoBean selectDemoBeanById(Integer id); 
	 public void insertDemoBean(DemoBean demoBean);
	 public void updateDemoBean(DemoBean demoBean);
	 public void delDemoBean(DemoBean demoBean);
}
