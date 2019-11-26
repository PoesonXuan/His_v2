package cn.com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.bean.IBean;
import cn.com.bean.impl.Department;
import cn.com.bean.impl.Duty;
import cn.com.bean.impl.DutyInfo;
import cn.com.bean.impl.User;
import cn.com.dao.IDBDao;
import cn.com.db.DBUtils;
import cn.com.utils.CommonUtil;

public class DutyDao implements IDBDao  {

	@Override
	public void add(IBean bean) {
		Duty duty = (Duty) bean;
		if(duty != null) {
			addDuty(duty);
		}
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(IBean bean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IBean> query(Map<String, Object> params, String sign) {
		if("getAllDutyList".equals(sign)) {
			return getAllDutyList(params);
		}else if("queryDutyById".equals(sign)) {
			return queryDutyById(params);
		}
		return null;
	}


	private void addDuty(Duty duty) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " insert into "
					+ " db_duty "
					+ "  (  NAME , "
					+ "  DUTY_DESC, "
					+ " CREATE_ID, "
					+ " CREATE_DATE ) "
					+ " values("
					+ "'" + duty.getName()+"' , "
					+ "'" + (duty.getDutyDesc() == null ?"":duty.getDutyDesc()) +"' , "
					+ duty.getCreateId() + " , "
					+ "'" + CommonUtil.sdf.format(duty.getCreateDate()) + "'"
					/*+ duty.getCreateDate()*/
					+ " ) ";
			System.out.println("sql="+sql);
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			int executeUpdate = ps.executeUpdate();
			System.out.println("executeUpdate="+executeUpdate);
			
			rs = ps.getGeneratedKeys();
			rs.next();
			long id = rs.getLong(1);
			
			List<DutyInfo> dutyInfos = duty.getDutyInfos();
			if(dutyInfos != null && dutyInfos.size() > 0) {
				for (DutyInfo dutyInfo : dutyInfos) {
					dutyInfo.setDutyId(id);
					DutyInfoDao dao = new DutyInfoDao();
					dao.add(dutyInfo);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
	}
	
	private List<IBean> getAllDutyList(Map<String, Object> params) {
		List<IBean> lst = new ArrayList<IBean>();
		String sql = " SELECT " + 
				"	ID, " + 
				"	NAME, " + 
				"	DUTY_DESC, " + 
				"	CREATE_ID, "
				+ " CREATE_DATE " + 
				"FROM " + 
				"	db_duty ";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String name = rs.getString("NAME");
				String dutyDesc = rs.getString("DUTY_DESC");
				long createId = rs.getLong("CREATE_ID");
				Date createDate = rs.getDate("CREATE_DATE");
				
				Duty duty = new Duty();

				duty.setId(id);
				duty.setCreateDate(createDate);
				duty.setCreateId(createId);
				duty.setDutyDesc(dutyDesc);
				duty.setName(name);
				
				UserDao dao = new UserDao(); 
				
				Map<String, Object> p = new HashMap<String, Object>();
				
				p.put("id", createId);
				
				List<IBean> query = dao.query(p , "queryUserById");
				
				if(query != null && query.size() >0 ) {
					duty.setCreator((User) query.get(0));
				}
				
				lst.add(duty);
			}
			return lst;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		return lst;
	}
	
	private List<IBean> queryDutyById(Map<String, Object> params) {
		
		Object object = params.get("id");
		
		long ids = 0;
		if(object != null) {
			ids = (long) object;
		}
			
		
		List<IBean> lst = new ArrayList<IBean>();
		String sql = " SELECT " + 
				"	ID, " + 
				"	NAME, " + 
				"	DUTY_DESC, " + 
				"	CREATE_ID, "
				+ " CREATE_DATE " + 
				"FROM " + 
				"	db_duty where 1=1 and ID =  "+ids;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String name = rs.getString("NAME");
				String dutyDesc = rs.getString("DUTY_DESC");
				long createId = rs.getLong("CREATE_ID");
				Date createDate = rs.getDate("CREATE_DATE");
				
				Duty duty = new Duty();

				duty.setId(id);
				duty.setCreateDate(createDate);
				duty.setCreateId(createId);
				duty.setDutyDesc(dutyDesc);
				duty.setName(name);
				
				UserDao dao = new UserDao(); 
				
				Map<String, Object> p = new HashMap<String, Object>();
				
				p.put("id", createId);
				
				List<IBean> query = dao.query(p , "queryUserById");
				
				if(query != null && query.size() >0 ) {
					duty.setCreator((User) query.get(0));
				}
				
				lst.add(duty);
			}
			return lst;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		return lst;
	}
}
