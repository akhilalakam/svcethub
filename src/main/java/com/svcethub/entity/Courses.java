package com.svcethub.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="courses")
public class Courses implements Serializable {

	@Id
	@Column(name="course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int courcesId;
	@Column(name="course_name")
	protected String courseName;
	
	public int getCourcesId() {
		return courcesId;
	}

	public void setCourcesId(int courcesId) {
		this.courcesId = courcesId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}	
}
