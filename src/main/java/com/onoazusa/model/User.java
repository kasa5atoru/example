package com.onoazusa.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="USER")
public class User {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Getter
	@Setter
	private int id;

	@Getter
	@Setter	
	private String userName;
	
	@Override
	public String toString(){
		return "id="+id+", userName="+userName;
	}
}
