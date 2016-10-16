package com.thu.database.infoex.entity;

import java.util.Arrays;
import java.util.List;

import com.thu.database.infoex.InfoExException;

public class RelationContext {
	
	static String[] master={"学戏","向","学","师","拜","指点","习艺","从师","学于","指导","学艺","受业","老师","师从","拜入","受业于","教师"};
	static String[] apprentice = {"学生","其习艺","传授","培养","其习戏","门生","招","弟子"};
	static String[] cooperation = {"合作","合演","主演","共同","配演","公演","搭班"};
	static String[] family = {"曾祖父","父","母","父亲","母亲","结婚","爷爷","祖父","养父","夫人","嫡子","儿子","长子","次子","女婿","女儿"};
	
	private List<String> MASTER;
	private List<String> APPRENTICE;
	private List<String> COOPERATION;
	private List<String> FAMILY ;
	public void init(){
		MASTER = Arrays.asList(master);
		APPRENTICE = Arrays.asList(apprentice);
		COOPERATION = Arrays.asList(cooperation);
		FAMILY = Arrays.asList(family);
	}
	public static String[] getMasterRelationContext(){
		return master;
	}
	public static String[] getApprenticeRelationContext(){
		return apprentice;
	}
	public static String[] getCooperationRelationContext(){
		return cooperation;
	}
	public static String[] getFamilyRelationContext(){
		return family;
	}
	
	public Relation analyse(String anaString) throws InfoExException{
		Relation relation = null;
		if (anaString!=null) {
			if (MASTER.contains(anaString)) {
				relation = Relation.MASTER;
			}else if (APPRENTICE.contains(anaString)) {
				relation = Relation.APPRENTICE;
			}else if (COOPERATION.contains(anaString)) {
				relation = Relation.COOPERATION;
			}else if (FAMILY.contains(anaString)) {
				relation = Relation.FAMILY;
			}
		}else{
			throw new InfoExException("The analyse string should not be null");
		}
		return relation;
	}
	
}
