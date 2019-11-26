package cn.com.dao;

import java.util.List;
import java.util.Map;

import cn.com.bean.IBean;

public interface IDBDao {
	void add(IBean bean);
	void delete(long id);
	void update(IBean bean);
	List<IBean> query(Map<String,Object> params,String sign);
}
