package cn.com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.management.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cn.com.bean.IBean;
import cn.com.bean.impl.Department;
import cn.com.bean.impl.Duty;
import cn.com.bean.impl.DutyInfo;
import cn.com.bean.impl.Ticket;
import cn.com.bean.impl.User;
import cn.com.dao.IDBDao;
import cn.com.dao.impl.DepartmentDao;
import cn.com.dao.impl.DutyDao;
import cn.com.dao.impl.DutyInfoDao;
import cn.com.dao.impl.TicketDao;
import cn.com.dao.impl.UserDao;
import cn.com.server.IServer;
import cn.com.server.impl.Server;
import cn.com.utils.CommonUtil;
import cn.com.utils.DateUtils;
import sun.org.mozilla.javascript.internal.json.JsonParser;

public class MyServlet extends HttpServlet {

	public static final String CURRENT_USER = "currentUser";
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日"); 
	private Date duty_current_date = new Date();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 这句话的意思，是让浏览器用utf8来解析返回的数据
		resp.setHeader("Content-type", "text/html;charset=UTF-8");

		String param = req.getParameter("param");
		if (param == null || "".equals(param)) {
			// 请求转发
			req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
		} else {
			if ("toLogin".equals(param)) {
				// 请求转发
				req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
			} else if ("loginOut".equals(param)) {
				req.setAttribute(CURRENT_USER, null);
				// 请求转发
				req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
			} else if ("login".equals(param)) {
				login(req, resp);
			} else if ("register".equals(param)) {
				register(req, resp);
			} else if ("getDepartments".equals(param)) {
				getDepartments(req, resp);
			} else {
				Object attribute = req.getSession().getAttribute(CURRENT_USER);
				if (attribute == null) {
					// 请求转发
					req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
				} else {

					if ("pages".equals(param)) {
						pages(req, resp);
					} else if ("addDepartment".equals(param)) {
						addDepartment(req, resp);
					} else if ("searchDepartmentLikeByName".equals(param)) {
						searchDepartmentLikeByName(req, resp);
					} else if ("queryDepartmentId".equals(param)) {
						queryDepartmentId(req, resp);
					} else if ("updateDepartment".equals(param)) {
						updateDepartment(req, resp);
					} else if ("deleteDepartmentId".equals(param)) {
						deleteDepartmentId(req, resp);
					} else if ("searchUserLikeByName".equals(param)) {
						searchUserLikeByName(req, resp);
					} else if ("queryUserById".equals(param)) {
						queryUserById(req, resp);
					} else if ("updateUserById".equals(param)) {
						updateUserById(req, resp);
					} else if ("getTicketCode".equals(param)) {
						getTicketCode(req, resp);
					} else if ("startTicket".equals(param)) {
						startTicket(req, resp);
					} else if ("getCheckList".equals(param)) {
						getCheckList(req, resp);
					} else if ("getCheckedList".equals(param)) {
						getCheckedList(req, resp);
					} else if ("getSignedList".equals(param)) {
						getSignedList(req, resp);
					} else if ("getRuningList".equals(param)) {
						getRuningList(req, resp);
					} else if ("getSubmitList".equals(param)) {
						getSubmitList(req, resp);
					} else if ("getSubmitedList".equals(param)) {
						getSubmitedList(req, resp);
					} else if ("ticketDetail".equals(param)) {
						ticketDetail(req, resp);
					} else if ("verifyTicket".equals(param)) {
						verifyTicket(req, resp);//
					} else if ("signTicket".equals(param)) {
						signTicket(req, resp);// signTicket 签收
					} else if ("submitTicket".equals(param)) {
						submitTicket(req, resp);// signTicket 签收
					} else if ("createDuty".equals(param)) {
						createDuty(req, resp);// signTicket 签收
					} else if ("getDutyById".equals(param)) {
						getDutyById(req, resp);// signTicket 签收
					} else if ("dutySubmit".equals(param)) {
						dutySubmit(req, resp);// signTicket 签收
					} else if ("getDutyList".equals(param)) {
						getDutyList(req, resp);// signTicket 签收
					} else if ("dutyListDetail".equals(param)) {
						dutyListDetail(req, resp);// signTicket 签收
					}/*
						 * else { Object attribute = req.getAttribute(CURRENT_USER); if(attribute ==
						 * null) { //请求转发
						 * req.getRequestDispatcher("/pages/login/login.jsp").forward(req,resp); } }
						 */
				}
			}

		}
	}

	private void dutyListDetail(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");
			
			String dutyId = req.getParameter("dutyId");
			
			IServer server = new Server();
			IDBDao dao = new DutyInfoDao();
			
			server.setDao(dao);
			
			Map<String, Object> params = new HashMap<String, Object>();
			
			params.put("dutyId", dutyId);
			
			
			List<IBean> query = server.query(params , "getDutyInfoByDutyId");
			
			if(query != null && query.size() > 0) {
				json.put("result", "Y");
				json.put("data", query);
				json.put("info", "数据加载成功");
			}else {
				json.put("info", "数据加载结果为空");
			}
			
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getDutyList(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");
			
			IServer server = new Server();
			IDBDao dao = new DutyDao();
			server.setDao(dao);
			
			Map<String, Object> params = new HashMap<String, Object>();
			List<IBean> query = server.query(params , "getAllDutyList");
			
			if(query != null && query.size() > 0) {
				json.put("result", "Y");
				json.put("data", query);
				json.put("info", "数据加载成功");
			}else {
				json.put("info", "数据加载结果为空");
			}
			
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void dutySubmit(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");
			
			User user = (User) req.getSession().getAttribute(CURRENT_USER);
			
			List<Map<String,Object>>  dutyDatas = (List<Map<String, Object>>) req.getSession().getAttribute("dutyDatas");
			
			if(user != null && dutyDatas != null && dutyDatas.size() > 0) {
				
				List<DutyInfo> dutyInfos = turnTODutyInfos(dutyDatas);
				
				String name = dutyDatas.get(0).get("dutyDate")+"-"+dutyDatas.get(dutyDatas.size()-1).get("dutyDate")+"-值日表";
				
				Duty duty = new Duty();
				
				duty.setCreateDate(new Date());
				duty.setCreateId(user.getId());
				duty.setDutyDesc("");
				duty.setName(name);
				
				duty.setDutyInfos(dutyInfos);
				
				IServer server = new Server();
				IDBDao dao = new DutyDao();
				server.setDao(dao);
				
				server.add(duty);
				
				json.put("result", "Y");
				json.put("info", "保存成功");
			}else {

				json.put("info", "保存失败");
			}
			
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private List<DutyInfo> turnTODutyInfos(List<Map<String, Object>> dutyDatas) {
		
		
		
		List<DutyInfo> datas = new ArrayList<DutyInfo>();
		try {
			for (int i = 0; i < dutyDatas.size(); i++) {
				DutyInfo dutyInfo = new DutyInfo();
				
				Map<String, Object> map = dutyDatas.get(i);
				
				long userid = (long) map.get("dutorID");
				User user = (User) map.get("dutor");
				Date date = sdf.parse( (String) map.get("dutyDate"));
				int status = (int) map.get("status");
				
				dutyInfo.setDutorID(userid);
				dutyInfo.setDutor(user);
				dutyInfo.setDutyDate(date);
				dutyInfo.setStatus(status);
				datas.add(dutyInfo);
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		return datas;
	}

	private void getDutyById(HttpServletRequest req, HttpServletResponse resp) {
		try {
			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");
			
			String id = req.getParameter("id");
			if(id != null && !"".equals(id)) {
				int seq = Integer.parseInt(id);
				List<Map<String,Object>>  dutyDatas = (List<Map<String, Object>>) req.getSession().getAttribute("dutyDatas");
				Map<String, Object> map = dutyDatas.get(seq);
				if(map == null ) {
					json.put("info", "查询数据失败");
				}else {
					json.put("data", map);
					json.put("result", "Y");	
				}
				
			}
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createDuty(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");
			
			String startDate = req.getParameter("startDate");
			String endDate = req.getParameter("endDate");
			
			if(startDate == null || "".equals(startDate) ) {
				json.put("info", "开始日期不能为空");
			}else if(endDate == null || "".equals(endDate) ) {
				json.put("info", "结束日期不能为空");
			}else if(!sdf.parse(startDate).before(sdf.parse(endDate))){
				json.put("info", "开始日期必须在结束日期之前");
			}else {
				try {
					Date start = sdf.parse(startDate);
					Date end = sdf.parse(endDate);
					
					duty_current_date = start;
					
					start = DateUtils.getSpecifiedDayBefore(start);
					end = DateUtils.getSpecifiedDayAfter(end);
					
					IServer server = new Server();
					IDBDao dao = new UserDao();
					server.setDao(dao);
					
					Map<String, Object> params = new HashMap<String, Object>();
					List<IBean> query = server.query(params , "queryAllUser");
					
					
					if(query != null && query.size() > 0) {
						
						List<Map<String,Object>> dutyDatas =  new ArrayList<Map<String, Object>>();
						boolean isContinue = true;
						while(isContinue) {
							if(duty_current_date.before(end) && duty_current_date.after(start)) {
								List<Map<String, Object>> datas = getDutyDatas(query,start,end);
								dutyDatas.addAll(datas);
							}else {
								isContinue = false;
							}
						}
						
						if(dutyDatas != null && dutyDatas.size() > 0) {
							dutyDatas = getJsonByDutyDatas(dutyDatas);
//							JSONArray array = getJsonByDutyDatas(dutyDatas);
							
							req.getSession().setAttribute("dutyDatas", dutyDatas);
							json.put("data", dutyDatas);
							json.put("result", "Y");
							json.put("info", "排班成功");
						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
			
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
	}
	
	
	private List<Map<String, Object>> getJsonByDutyDatas(List<Map<String, Object>> dutyDatas) {
		List<Map<String, Object>> ls = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < dutyDatas.size(); i++) {
			Map<String, Object> map = dutyDatas.get(i);
			map.put("seq", i);
			ls.add(map);
		}
		return ls;
	}
	/*private JSONArray getJsonByDutyDatas(List<Map<String, Object>> dutyDatas) {
		JSONArray array = new JSONArray(); 
		for (int i = 0; i < dutyDatas.size(); i++) {
			Map<String, Object> map = dutyDatas.get(i);
			
			JSONObject object  = new JSONObject(); 
			
			object.put("seq", i);
			
			for (String key : map.keySet()) {
				object.put(key, map.get(key));
			}
			array.put(object);
		}
		return array;
	}*/

	private List<Map<String, Object>> getDutyDatas(List<IBean> query, Date start, Date end) {
		List<Map<String, Object>> datas = new ArrayList<Map<String, Object>>();
		for (IBean iBean : query) {
			User user = (User) iBean;
			Map<String, Object> createDuty = createDuty(user,start,end);
			if(createDuty != null) {
				datas.add(createDuty);
				continue;
			}else {
				break;
			}
		}
		return datas;
	}

	private Map<String,Object> createDuty(User user, Date start, Date end) {
		
		if(duty_current_date.before(end) && duty_current_date.after(start)) {
			Map<String,Object> map = new HashMap<String,Object>();

			map.put("dutorID", user.getId());
			map.put("dutor", user);
			map.put("dutyDate", sdf.format(duty_current_date));
			map.put("status", 0);
			map.put("infoDesc", "");
			map.put("startDateTime", "");
			map.put("endDateTime", "");
			map.put("info", "");
			
			duty_current_date = DateUtils.getSpecifiedDayAfter(duty_current_date);
			
			return map;
		}
		
		return null;
	}

	private void register(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String name = req.getParameter("name");
			String loginName = req.getParameter("loginName");
			String pwd = req.getParameter("pwd");
			String repwd = req.getParameter("repwd");
			String age = req.getParameter("age");
			String sex = req.getParameter("sex");
			String lev = req.getParameter("lev");
			String tele = req.getParameter("tele");
			String departmentId = req.getParameter("departmentId");

			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");

			String info = validate(name, loginName, pwd, repwd, age, sex, lev, tele, departmentId);

			if (info != null && !"".equals(info)) {
				json.put("info", info);
			} else {
				int lv = 0;
				if ("2".equals(lev)) {
					lv = 2;
				} else if ("1".equals(lev)) {
					lv = 1;
				}

				User user = new User();
				user.setName(name);
				user.setLoginName(loginName);
				user.setPwd(pwd);
				user.setSex(Integer.parseInt(sex));
				user.setAge(Integer.parseInt(age));
				user.setLev(lv);
				user.setTele(tele);
				user.setDepartmentId(Long.parseLong(departmentId));

				IServer server = new Server();
				IDBDao dao = new UserDao();
				server.setDao(dao);

				server.add(user);

				json.put("result", "Y");
				json.put("info", "保存成功");

			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private boolean isNumericZidai(String str) {
		for (int i = 0; i < str.length(); i++) {
			System.out.println(str.charAt(i));
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}

	private String validate(String name, String loginName, String pwd, String repwd, String age, String sex, String lev,
			String tele, String departmentId) {
		if (name == null || "".equals(name)) {
			return "用户名不能为空";
		}
		if (loginName == null || "".equals(loginName)) {
			return "登录名不能为空";
		}

		if (pwd == null || "".equals(pwd)) {
			return "密码不能为空";
		}
		if (repwd == null || "".equals(repwd)) {
			return "确认密码不能为空";
		}
		if (!pwd.equals(repwd)) {
			return "密码和确认密码不一致";
		}

		if (departmentId == null || "".equals(departmentId)) {
			return "部门不能为空";
		}

		if (tele != null && !"".equals(tele)) {
			if (!isNumericZidai(tele)) {
				return "联系方式不规范";
			}
		}
		if (departmentId != null && !"".equals(departmentId)) {
			if (!isNumericZidai(departmentId)) {
				return "部门选择不能为空";
			}
		}

		IServer server = new Server();
		IDBDao dao = new UserDao();
		server.setDao(dao);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("loginName", loginName);
		List<IBean> query = server.query(params, "login");

		if (query != null && query.size() > 0) {
			return "账号已被注册";
		}

		return null;
	}

	private void getDepartments(HttpServletRequest req, HttpServletResponse resp) {
		try {

			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");

			IServer server = new Server();
			IDBDao dao = new DepartmentDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			List<IBean> query = server.query(params, "queryAllDepartments");

			if (query != null && query.size() > 0) {
				JSONArray array = new JSONArray();
				for (IBean iBean : query) {
					Department dp = (Department) iBean;
					JSONObject dpJson = new JSONObject();
					dpJson.put("ID", dp.getId());
					dpJson.put("NAME", dp.getName());
					dpJson.put("EN_NAME", dp.getEnName());
					array.put(dpJson);
				}
				json.put("result", "Y");
				json.put("data", array);
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void getRuningList(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "Y");
			json.put("info", "");

			/*
			 * String code = req.getParameter("code"); String name =
			 * req.getParameter("name"); String status = req.getParameter("status"); String
			 * desc = req.getParameter("desc"); String departmentName =
			 * req.getParameter("departmentName"); String departmentID =
			 * req.getParameter("departmentID");
			 * 
			 * Ticket t = new Ticket();
			 * 
			 * t.setCode(code);
			 * 
			 * t.setName(name);
			 * 
			 * t.setStatus(Integer.parseInt(status));
			 * 
			 * t.setDescription(desc);
			 * 
			 * t.setDepartment_id(Long.parseLong(departmentID));
			 * 
			 * System.out.println(t.toString());
			 */

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			String ticketCode = req.getParameter("ticketCode");
			String ticketName = req.getParameter("ticketName");
			params.put("code", ticketCode);
			params.put("name", ticketName);

			List<IBean> query = server.query(params, "getRuningList");

			if (query != null && query.size() > 0) {
				JSONArray array = new JSONArray();

				for (Object object : query) {

					JSONObject jsonObj = new JSONObject();

					Ticket t = (Ticket) object;

					jsonObj.put("id", t.getId());
					jsonObj.put("code", t.getCode());
					jsonObj.put("name", t.getName());
					jsonObj.put("startor", t.getStartor() == null ? "" : t.getStartor().getName());
					jsonObj.put("department", t.getDepartment() == null ? "" : t.getDepartment().getName());

					int status = t.getStatus();

					if (status == 3) {
						jsonObj.put("result", "签收");
					} else {
						jsonObj.put("result", "-");
					}

					array.put(jsonObj);
				}

				json.put("data", array);
			} else {
				JSONArray array = new JSONArray();
				json.put("data", array);
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void submitTicket(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "N");
			json.put("info", "");

			String idS = req.getParameter("id");
			// String result = req.getParameter("result");
			// String opinion = req.getParameter("opinion");

			System.out.println("idS=" + idS);

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("id", idS);

			List<IBean> query = server.query(params, "getTicketById");
			if (query != null && query.size() > 0) {
				Ticket ticket = (Ticket) query.get(0);
				ticket.setStatus(4);
				// ticket.setDealer_desc(opinion.trim());

				server.update(ticket);
				json.put("result", "Y");
				json.put("info", "更新成功");
			} else {
				json.put("info", "更新失败");
			}
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getSubmitedList(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "Y");
			json.put("info", "");

			/*
			 * String code = req.getParameter("code"); String name =
			 * req.getParameter("name"); String status = req.getParameter("status"); String
			 * desc = req.getParameter("desc"); String departmentName =
			 * req.getParameter("departmentName"); String departmentID =
			 * req.getParameter("departmentID");
			 * 
			 * Ticket t = new Ticket();
			 * 
			 * t.setCode(code);
			 * 
			 * t.setName(name);
			 * 
			 * t.setStatus(Integer.parseInt(status));
			 * 
			 * t.setDescription(desc);
			 * 
			 * t.setDepartment_id(Long.parseLong(departmentID));
			 * 
			 * System.out.println(t.toString());
			 */

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			String ticketCode = req.getParameter("ticketCode");
			String ticketName = req.getParameter("ticketName");
			params.put("code", ticketCode);
			params.put("name", ticketName);

			List<IBean> query = server.query(params, "getSubmitedList");

			if (query != null && query.size() > 0) {
				JSONArray array = new JSONArray();

				for (Object object : query) {

					JSONObject jsonObj = new JSONObject();

					Ticket t = (Ticket) object;

					jsonObj.put("id", t.getId());
					jsonObj.put("code", t.getCode());
					jsonObj.put("name", t.getName());
					jsonObj.put("startor", t.getStartor() == null ? "" : t.getStartor().getName());
					jsonObj.put("department", t.getDepartment() == null ? "" : t.getDepartment().getName());

					int status = t.getStatus();

					if (status == 3) {
						jsonObj.put("result", "签收");
					} else {
						jsonObj.put("result", "-");
					}

					array.put(jsonObj);
				}

				json.put("data", array);
			} else {
				JSONArray array = new JSONArray();
				json.put("data", array);
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getSubmitList(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "Y");
			json.put("info", "");

			/*
			 * String code = req.getParameter("code"); String name =
			 * req.getParameter("name"); String status = req.getParameter("status"); String
			 * desc = req.getParameter("desc"); String departmentName =
			 * req.getParameter("departmentName"); String departmentID =
			 * req.getParameter("departmentID");
			 * 
			 * Ticket t = new Ticket();
			 * 
			 * t.setCode(code);
			 * 
			 * t.setName(name);
			 * 
			 * t.setStatus(Integer.parseInt(status));
			 * 
			 * t.setDescription(desc);
			 * 
			 * t.setDepartment_id(Long.parseLong(departmentID));
			 * 
			 * System.out.println(t.toString());
			 */

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			String ticketCode = req.getParameter("ticketCode");
			String ticketName = req.getParameter("ticketName");
			params.put("code", ticketCode);
			params.put("name", ticketName);

			List<IBean> query = server.query(params, "getSignedList");

			if (query != null && query.size() > 0) {
				JSONArray array = new JSONArray();

				for (Object object : query) {

					JSONObject jsonObj = new JSONObject();

					Ticket t = (Ticket) object;

					jsonObj.put("id", t.getId());
					jsonObj.put("code", t.getCode());
					jsonObj.put("name", t.getName());
					jsonObj.put("startor", t.getStartor() == null ? "" : t.getStartor().getName());
					jsonObj.put("department", t.getDepartment() == null ? "" : t.getDepartment().getName());

					int status = t.getStatus();

					if (status == 3) {
						jsonObj.put("result", "签收");
					} else {
						jsonObj.put("result", "-");
					}

					array.put(jsonObj);
				}

				json.put("data", array);
			} else {
				JSONArray array = new JSONArray();
				json.put("data", array);
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void signTicket(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "N");
			json.put("info", "");

			String idS = req.getParameter("id");
			String result = req.getParameter("result");
			String opinion = req.getParameter("opinion");

			System.out.println("idS=" + idS + ",result=" + result + ",=" + opinion);

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("id", idS);

			List<IBean> query = server.query(params, "getTicketById");
			if (query != null && query.size() > 0) {
				Ticket ticket = (Ticket) query.get(0);
				ticket.setStatus(Integer.parseInt(result));
				ticket.setDealer_desc(opinion.trim());

				server.update(ticket);
				json.put("result", "Y");
				json.put("info", "更新成功");
			} else {
				json.put("info", "更新失败");
			}
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getSignedList(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "Y");
			json.put("info", "");

			/*
			 * String code = req.getParameter("code"); String name =
			 * req.getParameter("name"); String status = req.getParameter("status"); String
			 * desc = req.getParameter("desc"); String departmentName =
			 * req.getParameter("departmentName"); String departmentID =
			 * req.getParameter("departmentID");
			 * 
			 * Ticket t = new Ticket();
			 * 
			 * t.setCode(code);
			 * 
			 * t.setName(name);
			 * 
			 * t.setStatus(Integer.parseInt(status));
			 * 
			 * t.setDescription(desc);
			 * 
			 * t.setDepartment_id(Long.parseLong(departmentID));
			 * 
			 * System.out.println(t.toString());
			 */

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			String ticketCode = req.getParameter("ticketCode");
			String ticketName = req.getParameter("ticketName");
			params.put("code", ticketCode);
			params.put("name", ticketName);

			List<IBean> query = server.query(params, "getSignedList");

			if (query != null && query.size() > 0) {
				JSONArray array = new JSONArray();

				for (Object object : query) {

					JSONObject jsonObj = new JSONObject();

					Ticket t = (Ticket) object;

					jsonObj.put("id", t.getId());
					jsonObj.put("code", t.getCode());
					jsonObj.put("name", t.getName());
					jsonObj.put("startor", t.getStartor() == null ? "" : t.getStartor().getName());
					jsonObj.put("department", t.getDepartment() == null ? "" : t.getDepartment().getName());

					int status = t.getStatus();

					if (status == 3) {
						jsonObj.put("result", "签收");
					} else {
						jsonObj.put("result", "-");
					}

					array.put(jsonObj);
				}

				json.put("data", array);
			} else {
				JSONArray array = new JSONArray();
				json.put("data", array);
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCheckedList(HttpServletRequest req, HttpServletResponse resp) {


		String ticketCode = req.getParameter("ticketCode");
		String ticketName = req.getParameter("ticketName");
		
		try {
			JSONObject json = new JSONObject();
			json.put("result", "Y");
			json.put("info", "");

			/*
			 * String code = req.getParameter("code"); String name =
			 * req.getParameter("name"); String status = req.getParameter("status"); String
			 * desc = req.getParameter("desc"); String departmentName =
			 * req.getParameter("departmentName"); String departmentID =
			 * req.getParameter("departmentID");
			 * 
			 * Ticket t = new Ticket();
			 * 
			 * t.setCode(code);
			 * 
			 * t.setName(name);
			 * 
			 * t.setStatus(Integer.parseInt(status));
			 * 
			 * t.setDescription(desc);
			 * 
			 * t.setDepartment_id(Long.parseLong(departmentID));
			 * 
			 * System.out.println(t.toString());
			 */

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();
			 
			params.put("code", ticketCode);
			params.put("name", ticketName);

			List<IBean> query = server.query(params, "getCheckedList");

			if (query != null && query.size() > 0) {
				JSONArray array = new JSONArray();

				for (Object object : query) {

					JSONObject jsonObj = new JSONObject();

					Ticket t = (Ticket) object;

					jsonObj.put("id", t.getId());
					jsonObj.put("code", t.getCode());
					jsonObj.put("name", t.getName());
					jsonObj.put("startor", t.getStartor() == null ? "" : t.getStartor().getName());
					jsonObj.put("department", t.getDepartment() == null ? "" : t.getDepartment().getName());

					int status = t.getStatus();

					if (status == 1) {
						jsonObj.put("result", "审核通过");
					} else if (status == 2) {
						jsonObj.put("result", "审核不通过");
					} else {
						jsonObj.put("result", "-");
					}

					array.put(jsonObj);
				}

				json.put("data", array);
			} else {
				JSONArray array = new JSONArray();
				json.put("data", array);
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void verifyTicket(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "N");
			json.put("info", "");

			String idS = req.getParameter("id");
			String result = req.getParameter("result");
			String opinion = req.getParameter("opinion");

			System.out.println("idS=" + idS + ",result=" + result + ",=" + opinion);

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("id", idS);

			List<IBean> query = server.query(params, "getTicketById");
			if (query != null && query.size() > 0) {
				Ticket ticket = (Ticket) query.get(0);
				ticket.setStatus(Integer.parseInt(result));
				ticket.setCheckor_desc(opinion.trim());

				server.update(ticket);
				json.put("result", "Y");
				json.put("info", "更新成功");
			} else {
				json.put("info", "更新失败");
			}
			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void ticketDetail(HttpServletRequest req, HttpServletResponse resp) {

		try {
			String type = req.getParameter("type");
			if ("check".equals(type)) {
				String id = req.getParameter("id");

				IServer server = new Server();
				IDBDao dao = new TicketDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "getTicketById");
				if (query != null && query.size() > 0) {
					req.setAttribute("ticket", query.get(0));
				}
				// 请求转发 工单详情页面
				req.getRequestDispatcher("/pages/ticket/ticketDetailCheck.jsp").forward(req, resp);
			} else if ("checked".equals(type)) {

				String id = req.getParameter("id");

				IServer server = new Server();
				IDBDao dao = new TicketDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "getTicketById");
				if (query != null && query.size() > 0) {
					req.setAttribute("ticket", query.get(0));
				}
				// 请求转发 工单详情页面
				req.getRequestDispatcher("/pages/ticket/ticketDetailChecked.jsp").forward(req, resp);

			} else if ("sign".equals(type)) {
				// 代签收
				String id = req.getParameter("id");

				IServer server = new Server();
				IDBDao dao = new TicketDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "getTicketById");
				if (query != null && query.size() > 0) {
					req.setAttribute("ticket", query.get(0));
				}
				// 请求转发 工单详情页面
				req.getRequestDispatcher("/pages/ticket/ticketDetailSign.jsp").forward(req, resp);

			} else if ("signed".equals(type)) {
				// 代签收
				String id = req.getParameter("id");

				IServer server = new Server();
				IDBDao dao = new TicketDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "getTicketById");
				if (query != null && query.size() > 0) {
					req.setAttribute("ticket", query.get(0));
				}
				// 请求转发 工单详情页面
				req.getRequestDispatcher("/pages/ticket/ticketDetailSigned.jsp").forward(req, resp);

			} else if ("submit".equals(type)) {
				// 代归档
				String id = req.getParameter("id");

				IServer server = new Server();
				IDBDao dao = new TicketDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "getTicketById");
				if (query != null && query.size() > 0) {
					req.setAttribute("ticket", query.get(0));
				}
				// 请求转发 工单详情页面
				req.getRequestDispatcher("/pages/ticket/ticketDetailSubmit.jsp").forward(req, resp);

			} else if ("submited".equals(type)) {
				// 已归档
				String id = req.getParameter("id");

				IServer server = new Server();
				IDBDao dao = new TicketDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "getTicketById");
				if (query != null && query.size() > 0) {
					req.setAttribute("ticket", query.get(0));
				}
				// 请求转发 工单详情页面
				req.getRequestDispatcher("/pages/ticket/ticketDetailSubmited.jsp").forward(req, resp);

			} else if ("runing".equals(type)) {
				// 已归档
				String id = req.getParameter("id");

				IServer server = new Server();
				IDBDao dao = new TicketDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "getTicketById");
				if (query != null && query.size() > 0) {
					req.setAttribute("ticket", query.get(0));
				}
				// 请求转发 工单详情页面
				req.getRequestDispatcher("/pages/ticket/ticketDetailRuning.jsp").forward(req, resp);

			} // runing
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void getCheckList(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "Y");
			json.put("info", "");

			/*
			 * String code = req.getParameter("code"); String name =
			 * req.getParameter("name"); String status = req.getParameter("status"); String
			 * desc = req.getParameter("desc"); String departmentName =
			 * req.getParameter("departmentName"); String departmentID =
			 * req.getParameter("departmentID");
			 * 
			 * Ticket t = new Ticket();
			 * 
			 * t.setCode(code);
			 * 
			 * t.setName(name);
			 * 
			 * t.setStatus(Integer.parseInt(status));
			 * 
			 * t.setDescription(desc);
			 * 
			 * t.setDepartment_id(Long.parseLong(departmentID));
			 * 
			 * System.out.println(t.toString());
			 */

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			String ticketCode = req.getParameter("ticketCode");
			String ticketName = req.getParameter("ticketName");
			params.put("code", ticketCode);
			params.put("name", ticketName);

			List<IBean> query = server.query(params, "getCheckList");

			if (query != null && query.size() > 0) {
				JSONArray array = new JSONArray();

				for (Object object : query) {

					JSONObject jsonObj = new JSONObject();

					Ticket t = (Ticket) object;

					jsonObj.put("id", t.getId());
					jsonObj.put("code", t.getCode());
					jsonObj.put("name", t.getName());
					jsonObj.put("startor", t.getStartor() == null ? "" : t.getStartor().getName());
					jsonObj.put("department", t.getDepartment() == null ? "" : t.getDepartment().getName());

					array.put(jsonObj);
				}

				json.put("data", array);
			} else {
				JSONArray array = new JSONArray();
				json.put("data", array);
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startTicket(HttpServletRequest req, HttpServletResponse resp) {

		try {
			JSONObject json = new JSONObject();
			json.put("result", "N");
			json.put("info", "");

			String code = req.getParameter("code");
			String name = req.getParameter("name");
			String status = req.getParameter("status");
			String desc = req.getParameter("desc");
			String departmentName = req.getParameter("departmentName");
			String departmentID = req.getParameter("departmentID");

			Ticket t = new Ticket();

			t.setCode(code);

			t.setName(name);

			t.setStatus(Integer.parseInt(status));

			t.setDescription(desc);

			t.setDepartment_id(Long.parseLong(departmentID));

			System.out.println(t.toString());

			IServer server = new Server();
			IDBDao dao = new TicketDao();
			server.setDao(dao);

			Map<String, Object> params = new HashMap<String, Object>();

			params.put("code", code);

			List<IBean> query = server.query(params, "queryTicketByCode");

			if (query != null && query.size() > 0) {
				json.put("info", "单号重复");
			} else {
				User u = (User) req.getSession().getAttribute(CURRENT_USER);
				t.setStartor(u);
				t.setStartor_id(u.getId());
				server.add(t);
				json.put("result", "Y");
				json.put("info", "添加成功");
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void getTicketCode(HttpServletRequest req, HttpServletResponse resp) {
		try {

			String code = CommonUtil.getCode();

			System.out.println(code);

			JSONObject json = new JSONObject();

			json.put("result", "Y");
			json.put("info", code);

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateUserById(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String id = req.getParameter("id");
			if (id != null && !"".equals(id)) {

				JSONObject json = new JSONObject();

				json.put("result", "N");
				json.put("info", "");

				IServer server = new Server();
				IDBDao dao = new UserDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "queryUserById");

				if (query != null && query.size() > 0) {
					User u = (User) query.get(0);

					String idS = req.getParameter("id");
					String loginName = req.getParameter("loginName");
					String userName = req.getParameter("userName");
					String ageS = req.getParameter("age");
					String sexS = req.getParameter("sex");
					String useTypeS = req.getParameter("useType");
					String tele = req.getParameter("tele");

					u.setLoginName(loginName);
					u.setName(userName);
					u.setAge(Integer.parseInt(ageS));
					u.setSex(Integer.parseInt(sexS));
					u.setLev(Integer.parseInt(useTypeS));
					u.setTele(tele);

					server.update(u);

					json.put("result", "Y");

				} else {
					json.put("info", "更新用户失败");
				}

				PrintWriter writer = resp.getWriter();

				writer.write(json.toString());

				writer.close();
				writer.flush();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void queryUserById(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String id = req.getParameter("id");
			if (id != null && !"".equals(id)) {

				JSONObject json = new JSONObject();

				json.put("result", "N");
				json.put("info", "");

				IServer server = new Server();
				IDBDao dao = new UserDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "queryUserById");

				if (query != null && query.size() > 0) {
					JSONArray array = new JSONArray();
					for (IBean iBean : query) {
						User u = (User) iBean;
						JSONObject dpJson = new JSONObject();

						dpJson.put("id", u.getId());
						dpJson.put("loginName", u.getLoginName());
						dpJson.put("name", u.getName());
						dpJson.put("age", u.getAge());

						String sex = "空";
						if (u.getSex() == 1) {
							sex = "男";
						} else if (u.getSex() == 2) {
							sex = "女";
						}

						dpJson.put("sex", sex);

						String lev = "空";
						if (u.getLev() == 99) {
							lev = "超级管理员";
						} else if (u.getLev() == 3) {
							lev = "管理员";
						} else if (u.getLev() == 2) {
							lev = "维护人员";
						} else if (u.getLev() == 1) {
							lev = "医务人员";
						} else if (u.getLev() == 0) {
							lev = "Others";
						}

						dpJson.put("lev", lev);

						dpJson.put("tele", u.getTele());

						Department department = u.getDepartment();

						dpJson.put("department", department.getName());

						array.put(dpJson);
					}
					json.put("result", "Y");
					json.put("data", array);
				}

				PrintWriter writer = resp.getWriter();

				writer.write(json.toString());

				writer.close();
				writer.flush();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchUserLikeByName(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String searchInput = req.getParameter("searchInput");
			if (searchInput != null && !"".equals(searchInput)) {

				JSONObject json = new JSONObject();

				json.put("result", "N");
				json.put("info", "");

				IServer server = new Server();
				IDBDao dao = new UserDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("searchInput", searchInput);

				List<IBean> query = server.query(params, "queryLikeByName");

				if (query != null && query.size() > 0) {
					JSONArray array = new JSONArray();
					for (IBean iBean : query) {
						User u = (User) iBean;
						JSONObject dpJson = new JSONObject();

						dpJson.put("id", u.getId());
						dpJson.put("loginName", u.getLoginName());
						dpJson.put("name", u.getName());
						dpJson.put("age", u.getAge());

						String sex = "空";
						if (u.getSex() == 1) {
							sex = "男";
						} else if (u.getSex() == 2) {
							sex = "女";
						}

						dpJson.put("sex", sex);

						String lev = "空";
						if (u.getLev() == 99) {
							lev = "超级管理员";
						} else if (u.getLev() == 3) {
							lev = "管理员";
						} else if (u.getLev() == 2) {
							lev = "维护人员";
						} else if (u.getLev() == 1) {
							lev = "医务人员";
						} else if (u.getLev() == 0) {
							lev = "Others";
						}

						dpJson.put("lev", lev);

						dpJson.put("tele", u.getTele());

						Department department = u.getDepartment();

						dpJson.put("department", department.getName());

						array.put(dpJson);
					}
					json.put("result", "Y");
					json.put("data", array);
				}

				PrintWriter writer = resp.getWriter();

				writer.write(json.toString());

				writer.close();
				writer.flush();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// "queryLikeByName"
	}

	private void deleteDepartmentId(HttpServletRequest req, HttpServletResponse resp) {
		try {
			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");

			String id = req.getParameter("id");

			if (id != null && !"".equals(id)) {

				IServer server = new Server();

				IDBDao dao = new UserDao();

				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("departmentId", id);

				List<IBean> beans = server.query(params, "queryUserByDepartmentId");

				if (beans == null || beans.size() == 0) {
					dao = new DepartmentDao();

					server.setDao(dao);

					server.delete(Long.parseLong(id));

					json.put("result", "Y");
				} else {
					json.put("info", "存在关联无法删除");
				}

			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updateDepartment(HttpServletRequest req, HttpServletResponse resp) {
		try {
			JSONObject json = new JSONObject();

			json.put("result", "N");
			json.put("info", "");

			String id = req.getParameter("dpID");
			String name = req.getParameter("name");
			String en_name = req.getParameter("en_name");
			if (id != null && !"".equals(id)) {

				IServer server = new Server();
				IDBDao dao = new DepartmentDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				Department dp = new Department();

				dp.setId(Long.parseLong(id));
				dp.setEnName(en_name);
				dp.setName(name);

				server.update(dp);

				json.put("result", "Y");
			}

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void queryDepartmentId(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String id = req.getParameter("id");
			if (id != null && !"".equals(id)) {

				JSONObject json = new JSONObject();

				json.put("result", "N");
				json.put("info", "");

				IServer server = new Server();
				IDBDao dao = new DepartmentDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("id", id);

				List<IBean> query = server.query(params, "queryDepartmentId");

				if (query != null && query.size() > 0) {
					JSONArray array = new JSONArray();
					for (IBean iBean : query) {
						Department dp = (Department) iBean;
						JSONObject dpJson = new JSONObject();
						dpJson.put("ID", dp.getId());
						dpJson.put("NAME", dp.getName());
						dpJson.put("EN_NAME", dp.getEnName());
						array.put(dpJson);
					}
					json.put("result", "Y");
					json.put("data", array);
				}

				PrintWriter writer = resp.getWriter();

				writer.write(json.toString());

				writer.close();
				writer.flush();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void searchDepartmentLikeByName(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String searchInput = req.getParameter("searchInput");
			if (searchInput != null && !"".equals(searchInput)) {

				JSONObject json = new JSONObject();

				json.put("result", "N");
				json.put("info", "");

				IServer server = new Server();
				IDBDao dao = new DepartmentDao();
				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("searchInput", searchInput);

				List<IBean> query = server.query(params, "queryLikeByName");

				if (query != null && query.size() > 0) {
					JSONArray array = new JSONArray();
					for (IBean iBean : query) {
						Department dp = (Department) iBean;
						JSONObject dpJson = new JSONObject();
						dpJson.put("ID", dp.getId());
						dpJson.put("NAME", dp.getName());
						dpJson.put("EN_NAME", dp.getEnName());
						array.put(dpJson);
					}
					json.put("result", "Y");
					json.put("data", array);
				}

				PrintWriter writer = resp.getWriter();

				writer.write(json.toString());

				writer.close();
				writer.flush();
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// "queryLikeByName"
	}

	private void addDepartment(HttpServletRequest req, HttpServletResponse resp) {
		String name = req.getParameter("name");
		String en_name = req.getParameter("en_name");
		System.out.println("name=" + en_name + ",en_name=" + en_name);
		JSONObject json = new JSONObject();

		json.put("result", "N");
		json.put("info", "");

		try {
			Department dp = new Department();
			dp.setName(name);
			dp.setEnName(en_name);

			IServer server = new Server();
			IDBDao dao = new DepartmentDao();
			server.setDao(dao);

			server.add(dp);

			json.put("result", "Y");

			PrintWriter writer = resp.getWriter();

			writer.write(json.toString());

			writer.close();
			writer.flush();

		} catch (Exception e) {
			json.put("info", "保存部门信息出错");
			e.printStackTrace();
		}

	}

	private void pages(HttpServletRequest req, HttpServletResponse resp) {

		try {
			String index = req.getParameter("index");

			if (index != null && !"".equals(index)) {
				if ("101".equals(index)) {
					// 请求转发 部门管理
					req.getRequestDispatcher("/pages/department/department.jsp").forward(req, resp);
				} else if ("102".equals(index)) {
					// 请求转发 用户管理
					req.getRequestDispatcher("/pages/users/userInfo.jsp").forward(req, resp);
				} else if ("201".equals(index)) {
					// 请求转发 发起工单
					req.getRequestDispatcher("/pages/ticket/startTicket.jsp").forward(req, resp);
				} else if ("202".equals(index)) {
					// 请求转发 处理中工单列表
					req.getRequestDispatcher("/pages/ticket/toRuning.jsp").forward(req, resp);
				} else if ("203".equals(index)) {
					// 请求转发 待归档工单列表
					req.getRequestDispatcher("/pages/ticket/toSubmit.jsp").forward(req, resp);
				} else if ("204".equals(index)) {
					// 请求转发 已归档工单列表
					req.getRequestDispatcher("/pages/ticket/toSubmited.jsp").forward(req, resp);
				} else if ("301".equals(index)) {
					// 请求转发 值日管理
					req.getRequestDispatcher("/pages/duty/duty.jsp").forward(req, resp);
				} else if ("302".equals(index)) {
					// 请求转发 待审核工单
					req.getRequestDispatcher("/pages/ticket/checkList.jsp").forward(req, resp);
				} else if ("303".equals(index)) {
					// 请求转发 已审核工单
					req.getRequestDispatcher("/pages/ticket/checkedList.jsp").forward(req, resp);
				} else if ("304".equals(index)) {
					// 请求转发 已归档工单
					// req.getRequestDispatcher("/pages/ticket/submitList.jsp").forward(req,resp);
					// 请求转发 已完成工单
					req.getRequestDispatcher("/pages/ticket/toSubmited.jsp").forward(req, resp);
				} else if ("401".equals(index)) {
					// 请求转发 代签收工单
					req.getRequestDispatcher("/pages/ticket/toSign.jsp").forward(req, resp);
				} else if ("402".equals(index)) {
					// 请求转发 已签收工单
					req.getRequestDispatcher("/pages/ticket/toSigned.jsp").forward(req, resp);
				} else if ("403".equals(index)) {
					// 请求转发 已完成工单
					// req.getRequestDispatcher("/pages/ticket/finished.jsp").forward(req,resp);
					req.getRequestDispatcher("/pages/ticket/toSubmited.jsp").forward(req, resp);
				}else if ("1001".equals(index)) {
					// 请求转发 已完成工单
					req.getRequestDispatcher("/pages/duty/dutyList.jsp").forward(req, resp);
				} else {
					// 请求转发
					req.getRequestDispatcher("/pages/welcome/welcome.jsp").forward(req, resp);
				}
			} else {
				// 请求转发
				req.getRequestDispatcher("/pages/welcome/welcome.jsp").forward(req, resp);
			}
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void login(HttpServletRequest req, HttpServletResponse resp) {
		try {
			String loginName = req.getParameter("loginName");
			String password = req.getParameter("password");

			System.out.println("登录用户账号:" + loginName);

			if (loginName != null && !"".equals(loginName) && password != null && !"".equals(password)) {
				IServer server = new Server();
				IDBDao dao = new UserDao();

				server.setDao(dao);

				Map<String, Object> params = new HashMap<String, Object>();

				params.put("loginName", loginName);

				List<IBean> query = server.query(params, "login");

				if (query == null || query.size() == 0) {
					req.setAttribute("msg", "无此用户");
					// 请求转发
					req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
				} else {
					User loginUser = null;
					for (IBean iBean : query) {
						User user = (User) iBean;
						if (password.equals(user.getPwd())) {
							loginUser = user;
							break;
						}
					}

					if (loginUser == null) {
						req.setAttribute("msg", "密码错误");
						// 请求转发
						req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
					} else {
						req.getSession().setAttribute(CURRENT_USER, loginUser);

						if (loginUser.getLev() == 99) {// 超级管理员
							// 请求转发
							req.getRequestDispatcher("/pages/main/mainRoot.jsp").forward(req, resp);
						} else if (loginUser.getLev() == 0) {// 非医务人员
							// 请求转发
							req.getRequestDispatcher("/pages/main/mainOthers.jsp").forward(req, resp);
						} else if (loginUser.getLev() == 1) {// 医务人员
							// 请求转发
							req.getRequestDispatcher("/pages/main/mainYiWu.jsp").forward(req, resp);
						} else if (loginUser.getLev() == 2) {// 维护人员
							// 请求转发
							req.getRequestDispatcher("/pages/main/mainWeiHu.jsp").forward(req, resp);
						} else if (loginUser.getLev() == 3) {// 管理人员
							// 请求转发
							req.getRequestDispatcher("/pages/main/mainAdmin.jsp").forward(req, resp);
						} else {
							// 请求转发
							req.getRequestDispatcher("/pages/main/mainOthers.jsp").forward(req, resp);
						}

					}
				}

			} else {
				if (loginName == null || "".equals(loginName)) {
					req.setAttribute("msg", "登录名为空");
					// 请求转发
					req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
				} else if (password == null || "".equals(password)) {
					req.setAttribute("msg", "密码为空");
					// 请求转发
					req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
				} else {
					// 请求转发
					req.getRequestDispatcher("/pages/login/login.jsp").forward(req, resp);
				}
			}

		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}

}
