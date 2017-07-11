package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import demo.bean.DemoBean;
import demo.dao.DemoDao;


@Service 
@Transactional
public class DemoServiceI implements DemoService{
	@Autowired 
	DemoDao demoDao;
	
	@Cacheable(value="cacheTest")
	public DemoBean selectDemoBeanById(String id) {
		// TODO Auto-generated method stub
		System.out.println("query from db");
		return demoDao.selectDemoBeanById(id);
	}
	public void insertDemoBean(DemoBean demoBean) {
		// TODO Auto-generated method stub
		demoDao.insertDemoBean(demoBean);
	}
	
	@CacheEvict(value="cacheTest",key="#demoBean.getValue()")
	public void updateDemoBean(DemoBean demoBean) {
		// TODO Auto-generated method stub
		System.out.println("update db");
		demoDao.updateDemoBean(demoBean);
	}
	public void delDemoBean(DemoBean demoBean) {
		// TODO Auto-generated method stub
		demoDao.delDemoBean(demoBean);
	}
	public void insertTransaction(DemoBean demoBean) {
		// TODO Auto-generated method stub
		demoDao.insertDemoBean(demoBean);
		throw new RuntimeException();
	}
	public List<DemoBean> selectDemoBeans() {
		// TODO Auto-generated method stub
		return demoDao.selectDemoBeans();
	}

}
