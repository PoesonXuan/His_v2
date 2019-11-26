package cn.com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.com.bean.IBean;
import cn.com.bean.impl.Department;
import cn.com.dao.IDBDao;
import cn.com.db.DBUtils;

public class DepartmentDao implements IDBDao {

	@Override
	public void add(IBean bean) {
		Department d = (Department) bean;
		if(d != null) {
			addDepartment(d);
		}
	}


	@Override
	public void delete(long id) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			
			
			String sql = "  delete from db_department where ID =  "+id+" ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
	}

	@Override
	public void update(IBean bean) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Department dp = (Department) bean ;
		try {
			
			
			String sql = "  update db_department   set `NAME`='"+dp.getName()+"' , EN_NAME='"+dp.getEnName()+"' where ID=  "+dp.getId()+" ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.execute();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
//			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
	}

	@Override
	public List<IBean> query(Map<String, Object> params, String sign) {
		if("queryLikeByName".equals(sign)) {
			return queryLikeByName(params);
		}else if("queryDepartmentId".equals(sign)){
			return queryDepartmentId(params);
		}else if("queryAllDepartments".equals(sign)){
			return queryAllDepartments(params);
		}// queryAllDepartments
		return null;
	}
	
	
	private List<IBean> queryAllDepartments(Map<String, Object> params) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		try {
			String sql = "  select ID,NAME,EN_NAME from  db_department dp where 1=1 ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String enName = rs.getString("EN_NAME");
				String name = rs.getString("NAME");
				
				Department dp = new Department();
			
				dp.setId(id);
				dp.setName(name);
				dp.setEnName(enName);
				lst.add(dp);
			}
			return lst;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		return null;
	}


	private List<IBean> queryDepartmentId(Map<String, Object> params) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		try {
			
			String idStr = (String) params.get("id").toString();
			
			String sql = "  select ID,NAME,EN_NAME from  db_department dp where 1=1 and dp.`ID` = "+idStr+" ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String enName = rs.getString("EN_NAME");
				String name = rs.getString("NAME");
				
				Department dp = new Department();
			
				dp.setId(id);
				dp.setName(name);
				dp.setEnName(enName);
				lst.add(dp);
			}
			return lst;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		return null;
	}


	private List<IBean> queryLikeByName(Map<String, Object> params) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		try {
			
			String searchInput = (String) params.get("searchInput");
			
			String sql = "  select ID,NAME,EN_NAME from  db_department dp where 1=1 and dp.`NAME` like '%"+searchInput+"%' ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String enName = rs.getString("EN_NAME");
				String name = rs.getString("NAME");
				
				Department dp = new Department();
			
				dp.setId(id);
				dp.setName(name);
				dp.setEnName(enName);
				lst.add(dp);
			}
			return lst;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		return null;
	}


	private void addDepartment(Department d) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " insert into db_department   (NAME,EN_NAME)  values('"+d.getName()+"','"+d.getEnName()+"') ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			int executeUpdate = ps.executeUpdate();
			System.out.println("executeUpdate="+executeUpdate);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
	}

}
