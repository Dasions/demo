package demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import demo.bean.DemoBean;
import demo.dao.DemoDao;

@Service 
public class DemoServiceI implements DemoService{
	@Autowired 
	DemoDao demoDao;
	public DemoBean selectDemoBeanById(Integer id) {
		// TODO Auto-generated method stub
		return demoDao.selectDemoBeanById(id);
	}
	public void insertDemoBean(DemoBean demoBean) {
		// TODO Auto-generated method stub
		demoDao.insertDemoBean(demoBean);
	}
	public void updateDemoBean(DemoBean demoBean) {
		// TODO Auto-generated method stub
		demoDao.updateDemoBean(demoBean);
	}
	public void delDemoBean(DemoBean demoBean) {
		// TODO Auto-generated method stub
		demoDao.delDemoBean(demoBean);
	}

}
