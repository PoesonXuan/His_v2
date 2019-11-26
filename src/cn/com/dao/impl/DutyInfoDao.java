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

import javax.smartcardio.CommandAPDU;

import cn.com.bean.IBean;
import cn.com.bean.impl.Duty;
import cn.com.bean.impl.DutyInfo;
import cn.com.bean.impl.User;
import cn.com.dao.IDBDao;
import cn.com.db.DBUtils;
import cn.com.utils.CommonUtil;

public class DutyInfoDao implements IDBDao {

	@Override
	public void add(IBean bean) {
		DutyInfo dutyInfo = (cn.com.bean.impl.DutyInfo) bean;
		if (dutyInfo != null) {
			addDutyInfo(dutyInfo);
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
		if("getDutyInfoByDutyId".equals(sign)) {
			return getDutyInfoByDutyId(params);
		}
		return null;
	}

	

	private void addDutyInfo(DutyInfo dutyInfo) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			String sql = " insert into " + " db_duty_info " + "  ( DUTY_ID, " + " DUTOR, "
					+ (dutyInfo.getDutyDate() == null ? "" : " DUTY_DATE, ") + " STATUS, " + " INFO_DESC , "
					+ (dutyInfo.getStartDateTime() == null ? "" : " START_DATE_TIME, ")
					+ (dutyInfo.getEndDateTime() == null ? "" : " END_DATE_TIME, ") + " INFO ) " + " values("
					+ dutyInfo.getDutyId() + " , " + dutyInfo.getDutorID() + " , "
					+ (dutyInfo.getDutyDate() == null ? ""
							: "'" + CommonUtil.sdf.format(dutyInfo.getDutyDate()) + "' , ")
					/* + dutyInfo.getDutyDate() +" , " */
					+ dutyInfo.getStatus() + " , " + "'"
					+ (dutyInfo.getInfoDesc() == null ? "" : dutyInfo.getInfoDesc()) + "', "
					+ (dutyInfo.getStartDateTime() == null ? ""
							: "'" + CommonUtil.sdf.format(dutyInfo.getStartDateTime()) + "',")
					+ (dutyInfo.getEndDateTime() == null ? ""
							: "'" + CommonUtil.sdf.format(dutyInfo.getEndDateTime()) + "',")
					/*
					 * + dutyInfo.getStartDateTime() + "," + dutyInfo.getEndDateTime() + ","
					 */
					+ "'" + (dutyInfo.getInfo() == null ? "" : dutyInfo.getInfo()) + "'" + " ) ";
			System.out.println("sql=" + sql);
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			int executeUpdate = ps.executeUpdate();
			System.out.println("executeUpdate=" + executeUpdate);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeResultSet(rs);
			DBUtils.closeStatement(ps);
		}
	}
	
	private List<IBean> getDutyInfoByDutyId(Map<String, Object> params) {
		
		List<IBean> ls = new ArrayList<IBean>();
		
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			String dutyId = "";
			Object o = params.get("dutyId");
			if(o != null) {
				dutyId = o.toString();
			}
			
			String sql = " SELECT " + 
					"	ID, " + 
					"	DUTY_ID, " + 
					"	DUTOR, " + 
					"	DUTY_DATE, " + 
					"	STATUS , " + 
					"	INFO_DESC, " + 
					"	START_DATE_TIME, " + 
					"	END_DATE_TIME, " + 
					"	INFO " + 
					"FROM " + 
					"	db_duty_info  where 1=1 and  DUTY_ID = "+dutyId;
			System.out.println("sql=" + sql);
			conn = DBUtils.getConn();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				long id = rs.getLong("ID");
				long dutyId2 = rs.getLong("DUTY_ID");
				long useId = rs.getLong("DUTOR");
				Date dutyDate = rs.getDate("DUTY_DATE");
				int status = rs.getInt("STATUS");
				String infoDesc = rs.getString("INFO_DESC");
				Date start = rs.getDate("START_DATE_TIME");
				Date end = rs.getDate("END_DATE_TIME");
				String info = rs.getString("INFO");
				
				
				
				DutyInfo dutyInfo = new DutyInfo();
				
				dutyInfo.setId(id);
				dutyInfo.setDutor(getUserById(useId));
				dutyInfo.setDutorID(useId);
				dutyInfo.setDuty(getDutyById(dutyId2));
				dutyInfo.setDutyDate(dutyDate);
				dutyInfo.setStartDateTime(start);
				dutyInfo.setEndDateTime(end);
				dutyInfo.setInfo(info);
				dutyInfo.setInfoDesc(infoDesc);
				dutyInfo.setStatus(status);
				dutyInfo.setDutyId(dutyId2);
				
				ls.add(dutyInfo);
				
				
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return ls;
	}

	private Duty getDutyById(long dutyId) {
		DutyDao dao = new DutyDao();
		Map<String, Object> p = new HashMap<String, Object>();
		
		p.put("id", dutyId);
		
		List<IBean> query = dao.query(p , "queryDutyById");
		
		if(query != null && query.size() >0 ) {
			return  (Duty) query.get(0);
		}
		return null;
	}

	private User getUserById(long useId) {
		UserDao dao = new UserDao(); 
		
		Map<String, Object> p = new HashMap<String, Object>();
		
		p.put("id", useId);
		
		List<IBean> query = dao.query(p , "queryUserById");
		
		if(query != null && query.size() >0 ) {
			return (User) query.get(0);
		}
		return null;
	}
}
