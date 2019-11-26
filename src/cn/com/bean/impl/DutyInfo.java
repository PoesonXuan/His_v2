package cn.com.bean.impl;

import java.util.Date;

import cn.com.bean.IBean;

public class DutyInfo implements IBean {
	private long id;
	private long dutyId;
	private Duty duty;
	private long dutorID;
	private User dutor;
	private Date dutyDate;
	private int status;
	private String infoDesc;
	private Date startDateTime;
	private Date endDateTime;
	private String info;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDutyId() {
		return dutyId;
	}
	public void setDutyId(long dutyId) {
		this.dutyId = dutyId;
	}
	public long getDutorID() {
		return dutorID;
	}
	public void setDutorID(long dutorID) {
		this.dutorID = dutorID;
	}
	public User getDutor() {
		return dutor;
	}
	public void setDutor(User dutor) {
		this.dutor = dutor;
	}
	public Date getDutyDate() {
		return dutyDate;
	}
	public void setDutyDate(Date dutyDate) {
		this.dutyDate = dutyDate;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getInfoDesc() {
		return infoDesc;
	}
	public void setInfoDesc(String infoDesc) {
		this.infoDesc = infoDesc;
	}
	public Date getStartDateTime() {
		return startDateTime;
	}
	public void setStartDateTime(Date startDateTime) {
		this.startDateTime = startDateTime;
	}
	public Date getEndDateTime() {
		return endDateTime;
	}
	public void setEndDateTime(Date endDateTime) {
		this.endDateTime = endDateTime;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Duty getDuty() {
		return duty;
	}
	public void setDuty(Duty duty) {
		this.duty = duty;
	}
	

}
