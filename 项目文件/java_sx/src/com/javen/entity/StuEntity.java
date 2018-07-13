package com.javen.entity;

/**
 * @author Javen
 * @Email zyw205@gmail.com
 * 
 */
public class StuEntity {
	private int id;
	private String name;
	private String sex;
	private int num;
	
	
	
	public StuEntity() {
	}
	public StuEntity(int id, String name, String sex, int num) {
		this.id = id;
		this.name = name;
		this.sex = sex;
		this.num = num;
	}
	
	@Override
	public String toString() {
		return "StuEntity [id=" + id + ", name=" + name + ", sex=" + sex
				+ ", num=" + num + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
	
	
}
