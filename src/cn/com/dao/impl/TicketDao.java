package cn.com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.bean.IBean;
import cn.com.bean.impl.Department;
import cn.com.bean.impl.Ticket;
import cn.com.bean.impl.User;
import cn.com.dao.IDBDao;
import cn.com.db.DBUtils;

public class TicketDao implements IDBDao {

	@Override
	public void add(IBean bean) {
		Ticket t = (Ticket) bean;
		if(t != null) {
			addTicket(t);
		}
	}

	

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(IBean bean) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		
		Ticket ticket = (Ticket) bean;
		
		try {
			String sql = "   update DB_TICKET  set  " +
					"	STATUS="+ticket.getStatus()+", " + 
					"	DESCRIPTION='"+ticket.getDescription()+"', " + 
					"	STARTOR_ID="+ticket.getStartor_id()+", " + 
					"	CHECKOR_ID = "+ticket.getCheckor_id()+", " + 
					"	CHECKOR_DESC= '"+ticket.getCheckor_desc()+"', " + 
					"	DEALER_ID = "+ticket.getDealer_id()+", " + 
					"	DEALER_DESC = '"+ticket.getDealer_desc()+"', " + 
					"	DEPARTMENT_ID = "+ticket.getDepartment_id()+" " 
					+ " where ID = "+ticket.getId()+" ";
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			int executeUpdate = ps.executeUpdate();
			System.out.println("executeUpdate="+executeUpdate);
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		
		
		
		
	}

	@Override
	public List<IBean> query(Map<String, Object> params, String sign) {
		
		if("queryTicketByCode".equals(sign)) {
			return queryTicketByCode(params);
		}else if("getCheckList".equals(sign)) {
			return getCheckList(params);
		}else if("getCheckedList".equals(sign)) {
			return getCheckedList(params);
		}else if("getSignedList".equals(sign)) {
			return getSignedList(params);
		} else if("getRuningList".equals(sign)) {
			return getRuningList(params);
		}else if("getSubmitedList".equals(sign)) {
			return getSubmitedList(params);
		}else if("getTicketById".equals(sign)) {
			return getTicketById(params);
		}// getSignedList  getSubmitList  getRuningList
		
		return null;
	}

	
	private List<IBean> getRuningList(Map<String, Object> params) {
		String code_params = (String) params.get("code");
		String name_params = (String) params.get("name");

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		String code1 = (String) params.get("code");
		try {
			String sql = " SELECT " + 
					"	DT.ID, " + 
					"	DT.CODE, " + 
					"	DT.NAME, " + 
					"	DT.STATUS, " + 
					"	DT.DESCRIPTION, " + 
					"	DT.STARTOR_ID, " + 
					"	DT.CHECKOR_ID, " + 
					"	DT.CHECKOR_DESC, " + 
					"	DT.DEALER_ID, " + 
					"	DT.DEALER_DESC, " + 
					"	DT.DEPARTMENT_ID " + 
					" FROM " + 
					"	DB_TICKET DT  where 1=1 ";
			
			sql +=  " and DT.STATUS not in (2,3,4) ";
			
			if(code_params != null && !"".equals(code_params)) {
				sql +=  " and DT.CODE like  '%"+code_params+"%' ";
			}
			
			if(name_params != null && !"".equals(name_params)) {
				sql +=  " and DT.NAME like  '%"+name_params+"%' ";
			}
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				int status = rs.getInt("STATUS");
				String description = rs.getString("DESCRIPTION");
				long startor_id = rs.getLong("STARTOR_ID");
				long checkor_id = rs.getLong("CHECKOR_ID");
				String checkor_desc = rs.getString("CHECKOR_DESC");
				long dealer_id = rs.getLong("DEALER_ID");
				String dealer_desc = rs.getString("DEALER_DESC");
				long department_id = rs.getLong("DEPARTMENT_ID");

				Ticket tk = new Ticket();
				tk.setId(id);
				tk.setCode(code);
				tk.setName(name);
				tk.setStatus(status);
				tk.setDescription(description);
				tk.setStartor_id(startor_id);
				tk.setCheckor_id(checkor_id);
				tk.setCheckor_desc(checkor_desc);
				tk.setDealer_id(dealer_id);
				tk.setDealer_desc(dealer_desc);
				tk.setDepartment_id(department_id);
				

				Department department = getDepartment(id);
				
				tk.setDepartment(department);
				
				User startor = getUserById(startor_id);
				User checkor = getUserById(checkor_id);
				User dealer = getUserById(dealer_id);

				tk.setStartor(startor);
				tk.setCheckor(checkor);
				tk.setDealer(dealer);
				
				lst.add(tk);
				
			}
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		return null;
	}



	private List<IBean> getSubmitedList(Map<String, Object> params) {
		String code_params = (String) params.get("code");
		String name_params = (String) params.get("name");

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		String code1 = (String) params.get("code");
		try {
			String sql = " SELECT " + 
					"	DT.ID, " + 
					"	DT.CODE, " + 
					"	DT.NAME, " + 
					"	DT.STATUS, " + 
					"	DT.DESCRIPTION, " + 
					"	DT.STARTOR_ID, " + 
					"	DT.CHECKOR_ID, " + 
					"	DT.CHECKOR_DESC, " + 
					"	DT.DEALER_ID, " + 
					"	DT.DEALER_DESC, " + 
					"	DT.DEPARTMENT_ID " + 
					" FROM " + 
					"	DB_TICKET DT  where 1=1 ";
			
			sql +=  " and DT.STATUS in (4) ";
			
			if(code_params != null && !"".equals(code_params)) {
				sql +=  " and DT.CODE like  '%"+code_params+"%' ";
			}
			
			if(name_params != null && !"".equals(name_params)) {
				sql +=  " and DT.NAME like  '%"+name_params+"%' ";
			}
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				int status = rs.getInt("STATUS");
				String description = rs.getString("DESCRIPTION");
				long startor_id = rs.getLong("STARTOR_ID");
				long checkor_id = rs.getLong("CHECKOR_ID");
				String checkor_desc = rs.getString("CHECKOR_DESC");
				long dealer_id = rs.getLong("DEALER_ID");
				String dealer_desc = rs.getString("DEALER_DESC");
				long department_id = rs.getLong("DEPARTMENT_ID");

				Ticket tk = new Ticket();
				tk.setId(id);
				tk.setCode(code);
				tk.setName(name);
				tk.setStatus(status);
				tk.setDescription(description);
				tk.setStartor_id(startor_id);
				tk.setCheckor_id(checkor_id);
				tk.setCheckor_desc(checkor_desc);
				tk.setDealer_id(dealer_id);
				tk.setDealer_desc(dealer_desc);
				tk.setDepartment_id(department_id);
				

				Department department = getDepartment(id);
				
				tk.setDepartment(department);
				
				User startor = getUserById(startor_id);
				User checkor = getUserById(checkor_id);
				User dealer = getUserById(dealer_id);

				tk.setStartor(startor);
				tk.setCheckor(checkor);
				tk.setDealer(dealer);
				
				lst.add(tk);
				
			}
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		return null;
	}



	private List<IBean> getSignedList(Map<String, Object> params) {
		String code_params = (String) params.get("code");
		String name_params = (String) params.get("name");

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		String code1 = (String) params.get("code");
		try {
			String sql = " SELECT " + 
					"	DT.ID, " + 
					"	DT.CODE, " + 
					"	DT.NAME, " + 
					"	DT.STATUS, " + 
					"	DT.DESCRIPTION, " + 
					"	DT.STARTOR_ID, " + 
					"	DT.CHECKOR_ID, " + 
					"	DT.CHECKOR_DESC, " + 
					"	DT.DEALER_ID, " + 
					"	DT.DEALER_DESC, " + 
					"	DT.DEPARTMENT_ID " + 
					" FROM " + 
					"	DB_TICKET DT  where 1=1 ";
			
			sql +=  " and DT.STATUS in (3) ";
			
			if(code_params != null && !"".equals(code_params)) {
				sql +=  " and DT.CODE like  '%"+code_params+"%' ";
			}
			
			if(name_params != null && !"".equals(name_params)) {
				sql +=  " and DT.NAME like  '%"+name_params+"%' ";
			}
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				int status = rs.getInt("STATUS");
				String description = rs.getString("DESCRIPTION");
				long startor_id = rs.getLong("STARTOR_ID");
				long checkor_id = rs.getLong("CHECKOR_ID");
				String checkor_desc = rs.getString("CHECKOR_DESC");
				long dealer_id = rs.getLong("DEALER_ID");
				String dealer_desc = rs.getString("DEALER_DESC");
				long department_id = rs.getLong("DEPARTMENT_ID");

				Ticket tk = new Ticket();
				tk.setId(id);
				tk.setCode(code);
				tk.setName(name);
				tk.setStatus(status);
				tk.setDescription(description);
				tk.setStartor_id(startor_id);
				tk.setCheckor_id(checkor_id);
				tk.setCheckor_desc(checkor_desc);
				tk.setDealer_id(dealer_id);
				tk.setDealer_desc(dealer_desc);
				tk.setDepartment_id(department_id);
				

				Department department = getDepartment(id);
				
				tk.setDepartment(department);
				
				User startor = getUserById(startor_id);
				User checkor = getUserById(checkor_id);
				User dealer = getUserById(dealer_id);

				tk.setStartor(startor);
				tk.setCheckor(checkor);
				tk.setDealer(dealer);
				
				lst.add(tk);
				
			}
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		return null;
	}



	private List<IBean> getCheckedList(Map<String, Object> params) {
		String code_params = (String) params.get("code");
		String name_params = (String) params.get("name");

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		String code1 = (String) params.get("code");
		try {
			String sql = " SELECT " + 
					"	DT.ID, " + 
					"	DT.CODE, " + 
					"	DT.NAME, " + 
					"	DT.STATUS, " + 
					"	DT.DESCRIPTION, " + 
					"	DT.STARTOR_ID, " + 
					"	DT.CHECKOR_ID, " + 
					"	DT.CHECKOR_DESC, " + 
					"	DT.DEALER_ID, " + 
					"	DT.DEALER_DESC, " + 
					"	DT.DEPARTMENT_ID " + 
					" FROM " + 
					"	DB_TICKET DT  where 1=1 ";
			
			sql +=  " and DT.STATUS in (1,2) ";
			
			if(code_params != null && !"".equals(code_params)) {
				sql +=  " and DT.CODE like  '%"+code_params+"%' ";
			}
			
			if(name_params != null && !"".equals(name_params)) {
				sql +=  " and DT.NAME like  '%"+name_params+"%' ";
			}
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				int status = rs.getInt("STATUS");
				String description = rs.getString("DESCRIPTION");
				long startor_id = rs.getLong("STARTOR_ID");
				long checkor_id = rs.getLong("CHECKOR_ID");
				String checkor_desc = rs.getString("CHECKOR_DESC");
				long dealer_id = rs.getLong("DEALER_ID");
				String dealer_desc = rs.getString("DEALER_DESC");
				long department_id = rs.getLong("DEPARTMENT_ID");

				Ticket tk = new Ticket();
				tk.setId(id);
				tk.setCode(code);
				tk.setName(name);
				tk.setStatus(status);
				tk.setDescription(description);
				tk.setStartor_id(startor_id);
				tk.setCheckor_id(checkor_id);
				tk.setCheckor_desc(checkor_desc);
				tk.setDealer_id(dealer_id);
				tk.setDealer_desc(dealer_desc);
				tk.setDepartment_id(department_id);
				

				Department department = getDepartment(id);
				
				tk.setDepartment(department);
				
				User startor = getUserById(startor_id);
				User checkor = getUserById(checkor_id);
				User dealer = getUserById(dealer_id);

				tk.setStartor(startor);
				tk.setCheckor(checkor);
				tk.setDealer(dealer);
				
				lst.add(tk);
				
			}
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		
		
		
		
	
		
		return null;
	}



	private List<IBean> getTicketById(Map<String, Object> params) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		Object o = (String) params.get("id");
		try {
			String sql = " SELECT " + 
					"	DT.ID, " + 
					"	DT.CODE, " + 
					"	DT.NAME, " + 
					"	DT.STATUS, " + 
					"	DT.DESCRIPTION, " + 
					"	DT.STARTOR_ID, " + 
					"	DT.CHECKOR_ID, " + 
					"	DT.CHECKOR_DESC, " + 
					"	DT.DEALER_ID, " + 
					"	DT.DEALER_DESC, " + 
					"	DT.DEPARTMENT_ID " + 
					" FROM " + 
					"	DB_TICKET DT  where 1=1 and DT.ID = "+o+" ";
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				int status = rs.getInt("STATUS");
				String description = rs.getString("DESCRIPTION");
				long startor_id = rs.getLong("STARTOR_ID");
				long checkor_id = rs.getLong("CHECKOR_ID");
				String checkor_desc = rs.getString("CHECKOR_DESC");
				long dealer_id = rs.getLong("DEALER_ID");
				String dealer_desc = rs.getString("DEALER_DESC");
				long department_id = rs.getLong("DEPARTMENT_ID");

				Ticket tk = new Ticket();
				tk.setId(id);
				tk.setCode(code);
				tk.setName(name);
				tk.setStatus(status);
				tk.setDescription(description);
				tk.setStartor_id(startor_id);
				tk.setCheckor_id(checkor_id);
				tk.setCheckor_desc(checkor_desc);
				tk.setDealer_id(dealer_id);
				tk.setDealer_desc(dealer_desc);
				tk.setDepartment_id(department_id);
				

				Department department = getDepartment(id);
				
				tk.setDepartment(department);
				
				User startor = getUserById(startor_id);
				User checkor = getUserById(checkor_id);
				User dealer = getUserById(dealer_id);

				tk.setStartor(startor);
				tk.setCheckor(checkor);
				tk.setDealer(dealer);
				
				lst.add(tk);
				
			}
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		
		return null;
		
		
		
	}



	private List<IBean> getCheckList(Map<String, Object> params) {
		String code_params = (String) params.get("code");
		String name_params = (String) params.get("name");

		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		String code1 = (String) params.get("code");
		try {
			String sql = " SELECT " + 
					"	DT.ID, " + 
					"	DT.CODE, " + 
					"	DT.NAME, " + 
					"	DT.STATUS, " + 
					"	DT.DESCRIPTION, " + 
					"	DT.STARTOR_ID, " + 
					"	DT.CHECKOR_ID, " + 
					"	DT.CHECKOR_DESC, " + 
					"	DT.DEALER_ID, " + 
					"	DT.DEALER_DESC, " + 
					"	DT.DEPARTMENT_ID " + 
					" FROM " + 
					"	DB_TICKET DT  where 1=1 ";
			
			sql +=  " and DT.STATUS in (0) ";
			
			if(code_params != null && !"".equals(code_params)) {
				sql +=  " and DT.CODE like  '%"+code_params+"%' ";
			}
			
			if(name_params != null && !"".equals(name_params)) {
				sql +=  " and DT.NAME like  '%"+name_params+"%' ";
			}
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				int status = rs.getInt("STATUS");
				String description = rs.getString("DESCRIPTION");
				long startor_id = rs.getLong("STARTOR_ID");
				long checkor_id = rs.getLong("CHECKOR_ID");
				String checkor_desc = rs.getString("CHECKOR_DESC");
				long dealer_id = rs.getLong("DEALER_ID");
				String dealer_desc = rs.getString("DEALER_DESC");
				long department_id = rs.getLong("DEPARTMENT_ID");

				Ticket tk = new Ticket();
				tk.setId(id);
				tk.setCode(code);
				tk.setName(name);
				tk.setStatus(status);
				tk.setDescription(description);
				tk.setStartor_id(startor_id);
				tk.setCheckor_id(checkor_id);
				tk.setCheckor_desc(checkor_desc);
				tk.setDealer_id(dealer_id);
				tk.setDealer_desc(dealer_desc);
				tk.setDepartment_id(department_id);
				

				Department department = getDepartment(id);
				
				tk.setDepartment(department);
				
				User startor = getUserById(startor_id);
				User checkor = getUserById(checkor_id);
				User dealer = getUserById(dealer_id);

				tk.setStartor(startor);
				tk.setCheckor(checkor);
				tk.setDealer(dealer);
				
				lst.add(tk);
				
			}
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		
		
		
		
	
		
		return null;
	}



	private List<IBean> queryTicketByCode(Map<String, Object> params) {
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<IBean> lst = new ArrayList<IBean>(); 
		String code1 = (String) params.get("code");
		try {
			String sql = " SELECT " + 
					"	DT.ID, " + 
					"	DT.CODE, " + 
					"	DT.NAME, " + 
					"	DT.STATUS, " + 
					"	DT.DESCRIPTION, " + 
					"	DT.STARTOR_ID, " + 
					"	DT.CHECKOR_ID, " + 
					"	DT.CHECKOR_DESC, " + 
					"	DT.DEALER_ID, " + 
					"	DT.DEALER_DESC, " + 
					"	DT.DEPARTMENT_ID " + 
					" FROM " + 
					"	DB_TICKET DT  where 1=1 and DT.CODE = '"+code1+"' ";
			
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				long id = rs.getLong("ID");
				String code = rs.getString("CODE");
				String name = rs.getString("NAME");
				int status = rs.getInt("STATUS");
				String description = rs.getString("DESCRIPTION");
				long startor_id = rs.getLong("STARTOR_ID");
				long checkor_id = rs.getLong("CHECKOR_ID");
				String checkor_desc = rs.getString("CHECKOR_DESC");
				long dealer_id = rs.getLong("DEALER_ID");
				String dealer_desc = rs.getString("DEALER_DESC");
				long department_id = rs.getLong("DEPARTMENT_ID");

				Ticket tk = new Ticket();
				tk.setId(id);
				tk.setCode(code);
				tk.setName(name);
				tk.setStatus(status);
				tk.setDescription(description);
				tk.setStartor_id(startor_id);
				tk.setCheckor_id(checkor_id);
				tk.setCheckor_desc(checkor_desc);
				tk.setDealer_id(dealer_id);
				tk.setDealer_desc(dealer_desc);
				tk.setDepartment_id(department_id);
				

				Department department = getDepartment(id);
				
				tk.setDepartment(department);
				
				User startor = getUserById(startor_id);
				User checkor = getUserById(checkor_id);
				User dealer = getUserById(dealer_id);

				tk.setStartor(startor);
				tk.setCheckor(checkor);
				tk.setDealer(dealer);
				
				lst.add(tk);
				
			}
			return lst;
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
		
		
		return null;
		
		
		
	}


	private Department getDepartment(long id) {
		
		DepartmentDao dao = new DepartmentDao();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		
		List<IBean> query = dao.query(params, "queryDepartmentId");
		
		if(query != null && query.size() >0) {
			return (Department) query.get(0);
		}
		
		return null;
	}



	private User getUserById(long id) {
		UserDao userDao = new UserDao();
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		List<IBean> query = userDao.query(params, "queryUserById");
		
		if(query != null && query.size() >0) {
			return (User) query.get(0);
		}
		return null;
	}
	
	



	private void addTicket(Ticket t) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " insert into DB_TICKET (CODE,NAME,STATUS,DESCRIPTION,DEPARTMENT_ID,STARTOR_ID) "
					+ "  values('"+t.getCode()+"','"+t.getName()+"',"+t.getStatus()+",'"+t.getDescription()+"',"+t.getDepartment_id()+","+t.getStartor_id()+") ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			int executeUpdate = ps.executeUpdate();
			System.out.println("executeUpdate="+executeUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
	}
	
}
