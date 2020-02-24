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
@Table(name="univercity")
public class Univercity implements Serializable{
	@Id
	@Column(name="univercity_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected int univercityId;
	
	@Column(name="univercity_name")
	protected String univercityName;
	
	public int getUnivercityId() {
		return univercityId;
	}
	public void setUnivercityId(int univercityId) {
		this.univercityId = univercityId;
	}
	public String getUnivercityName() {
		return univercityName;
	}
	public void setUnivercityName(String univercityName) {
		this.univercityName = univercityName;
	}
	
}
