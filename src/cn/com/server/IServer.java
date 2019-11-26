package cn.com.server;

import java.util.List;
import java.util.Map;

import cn.com.bean.IBean;
import cn.com.dao.IDBDao;

public interface IServer {
	void setDao(IDBDao dao);
	void add(IBean bean);
	void delete(long id);
	void update(IBean bean);
	List<IBean> query(Map<String,Object> params,String sign);
}
