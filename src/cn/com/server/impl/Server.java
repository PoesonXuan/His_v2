package cn.com.server.impl;

import java.util.List;
import java.util.Map;

import cn.com.bean.IBean;
import cn.com.dao.IDBDao;
import cn.com.server.IServer;

public class Server implements IServer {

	private IDBDao dao;
	
	@Override
	public void add(IBean bean) {
		dao.add(bean);
	}

	@Override
	public void delete(long id) {
		dao.delete(id);
	}

	@Override
	public void update(IBean bean) {
		dao.update(bean);
	}

	@Override
	public List<IBean> query(Map<String, Object> params, String sign) {
		return dao.query(params, sign);
	}

	public IDBDao getDao() {
		return dao;
	}

	public void setDao(IDBDao dao) {
		this.dao = dao;
	}

}
