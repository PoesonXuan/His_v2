package cn.com.bean.impl;

import cn.com.bean.IBean;

public class Ticket implements IBean{
	private long id;
	private String code;
	private String name;
	private int status;
	private String description;
	private long startor_id;
	private User startor;
	private long checkor_id;
	private User checkor;
	private String checkor_desc;
	private long dealer_id;
	private User dealer;
	private String dealer_desc;
	private long department_id;
	private Department department;
	
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getStartor_id() {
		return startor_id;
	}
	public void setStartor_id(long startor_id) {
		this.startor_id = startor_id;
	}
	public User getStartor() {
		return startor;
	}
	public void setStartor(User startor) {
		this.startor = startor;
	}
	public long getCheckor_id() {
		return checkor_id;
	}
	public void setCheckor_id(long checkor_id) {
		this.checkor_id = checkor_id;
	}
	public User getCheckor() {
		return checkor;
	}
	public void setCheckor(User checkor) {
		this.checkor = checkor;
	}
	public String getCheckor_desc() {
		return checkor_desc;
	}
	public void setCheckor_desc(String checkor_desc) {
		this.checkor_desc = checkor_desc;
	}
	public long getDealer_id() {
		return dealer_id;
	}
	public void setDealer_id(long dealer_id) {
		this.dealer_id = dealer_id;
	}
	public User getDealer() {
		return dealer;
	}
	public void setDealer(User dealer) {
		this.dealer = dealer;
	}
	public String getDealer_desc() {
		return dealer_desc;
	}
	public void setDealer_desc(String dealer_desc) {
		this.dealer_desc = dealer_desc;
	}
	public long getDepartment_id() {
		return department_id;
	}
	public void setDepartment_id(long department_id) {
		this.department_id = department_id;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", code=" + code + ", name=" + name + ", status=" + status + ", description="
				+ description + ", startor_id=" + startor_id + ", startor=" + startor + ", checkor_id=" + checkor_id
				+ ", checkor=" + checkor + ", checkor_desc=" + checkor_desc + ", dealer_id=" + dealer_id + ", dealer="
				+ dealer + ", dealer_desc=" + dealer_desc + ", department_id=" + department_id + ", department="
				+ department + ", getId()=" + getId() + ", getName()=" + getName() + ", getStatus()=" + getStatus()
				+ ", getDescription()=" + getDescription() + ", getStartor_id()=" + getStartor_id() + ", getStartor()="
				+ getStartor() + ", getCheckor_id()=" + getCheckor_id() + ", getCheckor()=" + getCheckor()
				+ ", getCheckor_desc()=" + getCheckor_desc() + ", getDealer_id()=" + getDealer_id() + ", getDealer()="
				+ getDealer() + ", getDealer_desc()=" + getDealer_desc() + ", getDepartment_id()=" + getDepartment_id()
				+ ", getDepartment()=" + getDepartment() + ", getCode()=" + getCode() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}
}
