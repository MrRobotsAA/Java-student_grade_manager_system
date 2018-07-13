package com.sdut_grade.model;

public class GradeModel {
	private Integer grade_id;
	private String grade_name;
	public Integer getGrade_id() {
		return grade_id;
	}
	public void setGrade_id(Integer grade_id) {
		this.grade_id = grade_id;
	}
	public String getGrade_name() {
		return grade_name;
	}
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	public String toString(){
		return this.grade_name;
	}

}
