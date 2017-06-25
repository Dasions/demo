package demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;

import demo.bean.DemoBean;
import demo.dao.DemoDao;


@Service 
@Transactional
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
