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
import cn.com.bean.impl.User;
import cn.com.dao.IDBDao;
import cn.com.db.DBUtils;

public class UserDao implements IDBDao {

	@Override
	public void add(IBean bean) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = (User) bean;
		try {

			String sql = "  INSERT   into  db_user "
					+ " ( "
					+ " LOGINNAME, "
					+ " PWD, "
					+ " NAME, "
					+ " AGE, "
					+ " SEX, "
					+ " LEV, "
					+ " TELE, "
					+ " DEPARTMENT_ID "
					+ " ) "
					+ " VALUES ( "
					+ " '"+u.getLoginName()+"', "
					+ " '"+u.getPwd()+"', "
					+ " '"+u.getName()+"', "
					+ " "+u.getAge()+", "
					+ " "+u.getSex()+", "
					+ " "+u.getLev()+", "
					+ " '"+u.getTele()+"', "
					+ " "+u.getDepartmentId()+"  "
					+ " )  ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(ps);
		}
	}

	@Override
	public void delete(long id) {

	}

	@Override
	public void update(IBean bean) {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User u = (User) bean;
		try {

			String sql = "  update  db_user     set " + " LOGINNAME='" + u.getLoginName() + "', PWD='" + u.getPwd()
					+ "', NAME = '" + u.getName() + "', AGE=" + u.getAge() + ",SEX=" + u.getSex() + ", LEV="
					+ u.getLev() + ", TELE = '" + u.getTele() + "' where ID=  " + u.getId() + " ";
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.execute();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(ps);
		}
	}

	@Override
	public List<IBean> query(Map<String, Object> params, String sign) {
		if (sign != null && !"".equals(sign)) {
			if ("login".equals(sign)) {
				return login(params);
			} else if ("queryUserByDepartmentId".equals(sign)) {
				return queryUserByDepartmentId(params);
			} else if ("queryLikeByName".equals(sign)) {
				return queryLikeByName(params);
			}  else if ("queryUserById".equals(sign)) {
				return queryUserById(params);
			} else if ("queryAllUser".equals(sign)) {
				return queryAllUser(params);
			}
		}
		return null;
	}



	private List<IBean> queryAllUser(Map<String, Object> params) {

		List<IBean> lst = new ArrayList<IBean>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

//			if (object != null) {

				conn = DBUtils.getConn();
				String sql = "SELECT " + "	u.ID, " + "	u.LOGINNAME, " + "	u.PWD, " + "	u.NAME, " + "	u.AGE, "
						+ "	u.SEX, " + "	u.LEV, " + "	u.TELE, " + "	u.DEPARTMENT_ID, " + "	dp.`NAME` dp_name, "
						+ "	dp.EN_NAME dp_en_name " + "FROM " + "	db_user u, " + "	db_department dp "
						+ "WHERE 1=1 " + " and dp.ID = u.DEPARTMENT_ID " ;
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					long idl = rs.getLong("ID");
					String loginName2 = rs.getString("LOGINNAME");
					String pwd = rs.getString("PWD");
					String name = rs.getString("NAME");
					int age = rs.getInt("AGE");
					int sex = rs.getInt("SEX");
					int lev = rs.getInt("LEV");
					String tel = rs.getString("TELE");

					long departmentIdl = rs.getLong("DEPARTMENT_ID");
					String dpName = rs.getString("dp_name");
					String dpEnName = rs.getString("dp_en_name");

					Department dp = new Department();
					dp.setId(departmentIdl);
					dp.setName(dpName);
					dp.setEnName(dpEnName);

					User user = new User();
					user.setId(idl);
					user.setLoginName(loginName2);
					user.setPwd(pwd);
					user.setName(name);
					user.setAge(age);
					user.setSex(sex);
					user.setLev(lev);
					user.setTele(tel);
					user.setDepartmentId(departmentIdl);
					user.setDepartment(dp);
					lst.add(user);
//				}

				
			}
			return lst;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}

		return lst;

	}

	private List<IBean> queryUserById(Map<String, Object> params) {

		List<IBean> lst = new ArrayList<IBean>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Object object = params.get("id").toString();

			if (object != null) {
				String id = (String) object;

				conn = DBUtils.getConn();
				String sql = "SELECT " + "	u.ID, " + "	u.LOGINNAME, " + "	u.PWD, " + "	u.NAME, " + "	u.AGE, "
						+ "	u.SEX, " + "	u.LEV, " + "	u.TELE, " + "	u.DEPARTMENT_ID, " + "	dp.`NAME` dp_name, "
						+ "	dp.EN_NAME dp_en_name " + "FROM " + "	db_user u, " + "	db_department dp "
						+ "WHERE 1=1 " + " and dp.ID = u.DEPARTMENT_ID " + " and u.ID=" + id + " ";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					long idl = rs.getLong("ID");
					String loginName2 = rs.getString("LOGINNAME");
					String pwd = rs.getString("PWD");
					String name = rs.getString("NAME");
					int age = rs.getInt("AGE");
					int sex = rs.getInt("SEX");
					int lev = rs.getInt("LEV");
					String tel = rs.getString("TELE");

					long departmentIdl = rs.getLong("DEPARTMENT_ID");
					String dpName = rs.getString("dp_name");
					String dpEnName = rs.getString("dp_en_name");

					Department dp = new Department();
					dp.setId(departmentIdl);
					dp.setName(dpName);
					dp.setEnName(dpEnName);

					User user = new User();
					user.setId(idl);
					user.setLoginName(loginName2);
					user.setPwd(pwd);
					user.setName(name);
					user.setAge(age);
					user.setSex(sex);
					user.setLev(lev);
					user.setTele(tel);
					user.setDepartmentId(departmentIdl);
					user.setDepartment(dp);
					lst.add(user);
				}

				return lst;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}

		return lst;

	}

	private List<IBean> queryLikeByName(Map<String, Object> params) {

		List<IBean> lst = new ArrayList<IBean>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Object object = params.get("searchInput");

			if (object != null) {
				String searchInput = (String) object;

				conn = DBUtils.getConn();
				String sql = "SELECT " + "	u.ID, " + "	u.LOGINNAME, " + "	u.PWD, " + "	u.NAME, " + "	u.AGE, "
						+ "	u.SEX, " + "	u.LEV, " + "	u.TELE, " + "	u.DEPARTMENT_ID, " + "	dp.`NAME` dp_name, "
						+ "	dp.EN_NAME dp_en_name " + "FROM " + "	db_user u, " + "	db_department dp "
						+ "WHERE 1=1 " + " and dp.ID = u.DEPARTMENT_ID " + " and u.NAME like '%" + searchInput + "%' ";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					long id = rs.getLong("ID");
					String loginName2 = rs.getString("LOGINNAME");
					String pwd = rs.getString("PWD");
					String name = rs.getString("NAME");
					int age = rs.getInt("AGE");
					int sex = rs.getInt("SEX");
					int lev = rs.getInt("LEV");
					String tel = rs.getString("TELE");

					long departmentIdl = rs.getLong("DEPARTMENT_ID");
					String dpName = rs.getString("dp_name");
					String dpEnName = rs.getString("dp_en_name");

					Department dp = new Department();
					dp.setId(departmentIdl);
					dp.setName(dpName);
					dp.setEnName(dpEnName);

					User user = new User();
					user.setId(id);
					user.setLoginName(loginName2);
					user.setPwd(pwd);
					user.setName(name);
					user.setAge(age);
					user.setSex(sex);
					user.setLev(lev);
					user.setTele(tel);
					user.setDepartmentId(departmentIdl);
					user.setDepartment(dp);
					lst.add(user);
				}

				return lst;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}

		return lst;

	}

	private List<IBean> queryUserByDepartmentId(Map<String, Object> params) {

		List<IBean> lst = new ArrayList<IBean>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Object object = params.get("departmentId");

			if (object != null) {
				String departmentId = (String) object;

				conn = DBUtils.getConn();
				String sql = "SELECT " + "	u.ID, " + "	u.LOGINNAME, " + "	u.PWD, " + "	u.NAME, " + "	u.AGE, "
						+ "	u.SEX, " + "	u.LEV, " + "	u.TELE, " + "	u.DEPARTMENT_ID, " + "	dp.`NAME` dp_name, "
						+ "	dp.EN_NAME dp_en_name " + "FROM " + "	db_user u, " + "	db_department dp "
						+ "WHERE 1=1 " + " and dp.ID = u.DEPARTMENT_ID " + " and u.DEPARTMENT_ID='" + departmentId
						+ "' ";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					long id = rs.getLong("ID");
					String loginName2 = rs.getString("LOGINNAME");
					String pwd = rs.getString("PWD");
					String name = rs.getString("NAME");
					int age = rs.getInt("AGE");
					int sex = rs.getInt("SEX");
					int lev = rs.getInt("LEV");
					String tel = rs.getString("TELE");

					long departmentIdl = rs.getLong("DEPARTMENT_ID");
					String dpName = rs.getString("dp_name");
					String dpEnName = rs.getString("dp_en_name");

					Department dp = new Department();
					dp.setId(departmentIdl);
					dp.setName(dpName);
					dp.setEnName(dpEnName);

					User user = new User();
					user.setId(id);
					user.setLoginName(loginName2);
					user.setPwd(pwd);
					user.setName(name);
					user.setAge(age);
					user.setSex(sex);
					user.setLev(lev);
					user.setTele(tel);
					user.setDepartmentId(departmentIdl);
					user.setDepartment(dp);
					lst.add(user);
				}

				return lst;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}

		return lst;

	}

	private List<IBean> login(Map<String, Object> params) {

		List<IBean> lst = new ArrayList<IBean>();

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Object object = params.get("loginName");

			if (object != null) {
				String loginName = (String) object;

				conn = DBUtils.getConn();
				String sql = "SELECT " + "	u.ID, " + "	u.LOGINNAME, " + "	u.PWD, " + "	u.NAME, " + "	u.AGE, "
						+ "	u.SEX, " + "	u.LEV, " + "	u.TELE, " + "	u.DEPARTMENT_ID, " + "	dp.`NAME` dp_name, "
						+ "	dp.EN_NAME dp_en_name " + "FROM " + "	db_user u, " + "	db_department dp "
						+ "WHERE 1=1 " + " and dp.ID = u.DEPARTMENT_ID " + " and u.LOGINNAME='" + loginName + "' ";
				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery();

				while (rs.next()) {
					long id = rs.getLong("ID");
					String loginName2 = rs.getString("LOGINNAME");
					String pwd = rs.getString("PWD");
					String name = rs.getString("NAME");
					int age = rs.getInt("AGE");
					int sex = rs.getInt("SEX");
					int lev = rs.getInt("LEV");
					String tel = rs.getString("TELE");

					long departmentId = rs.getLong("DEPARTMENT_ID");
					String dpName = rs.getString("dp_name");
					String dpEnName = rs.getString("dp_en_name");

					Department dp = new Department();
					dp.setId(departmentId);
					dp.setName(dpName);
					dp.setEnName(dpEnName);

					User user = new User();
					user.setId(id);
					user.setLoginName(loginName2);
					user.setPwd(pwd);
					user.setName(name);
					user.setAge(age);
					user.setSex(sex);
					user.setLev(lev);
					user.setTele(tel);
					user.setDepartmentId(departmentId);
					user.setDepartment(dp);
					lst.add(user);
				}

				return lst;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}

		return lst;

	}

}
