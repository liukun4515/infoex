package com.thu.database.infoex.entity;

public enum Relation {
	UNKNOW("未知"), FAMILY("家庭"), MASTER("师徒"), APPRENTICE("徒弟"), COOPERATION("合作");

	private String name;

	Relation(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	

}
