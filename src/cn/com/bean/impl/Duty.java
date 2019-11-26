package cn.com.bean.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.com.bean.IBean;

public class Duty implements IBean {

	private long id;
	private String name;
	private String dutyDesc;
	private long createId;
	private User creator;
	private Date createDate;
	private List<DutyInfo> dutyInfos = new ArrayList<DutyInfo>();
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDutyDesc() {
		return dutyDesc;
	}
	public void setDutyDesc(String dutyDesc) {
		this.dutyDesc = dutyDesc;
	}
	public long getCreateId() {
		return createId;
	}
	public void setCreateId(long createId) {
		this.createId = createId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public List<DutyInfo> getDutyInfos() {
		return dutyInfos;
	}
	public void setDutyInfos(List<DutyInfo> dutyInfos) {
		this.dutyInfos = dutyInfos;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	
	

}
