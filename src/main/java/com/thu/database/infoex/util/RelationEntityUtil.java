package com.thu.database.infoex.util;

import java.util.HashSet;
import java.util.Set;

import com.thu.database.infoex.InfoExException;
import com.thu.database.infoex.entity.Relation;
import com.thu.database.infoex.entity.RelationContext;
import com.thu.database.infoex.entity.RelationEntity;
import com.thu.database.infoex.entity.State;

public class RelationEntityUtil {

	private static Set<RelationEntity> relationEntities = new HashSet<RelationEntity>();
	private static State state = State.UNKNOW;
	private static Relation relation = Relation.UNKNOW;
	private static Set<String> people = new HashSet<>();
	private static Set<String> unDupeople =  new HashSet<>();
	private static RelationContext relationContext = new RelationContext();
	
	private static String peopleName = "人名";

	public static Set<RelationEntity> getRelationEntities(String sentence, String rootName) throws InfoExException {

		String[] seg = NlpUtil.segFunction(sentence);
		String[] tag = NlpUtil.tagFunction(seg);
		int length = seg.length;
		if (length != tag.length) {
			throw new IllegalStateException("The segment length is not match tagment");
		}
		relationContext.init();
		for (int i = 0; i < length; i++) {
			// tag 是 人名
			if (tag[i].equals(peopleName)&&!seg[i].equals(rootName)&&seg[i].length()>=2) {
				String childName = seg[i];
				//childName 不能是rootName
				// state 当前状态是 unknow
				if (state==State.UNKNOW) {
					people.add(childName);
				// state 当前状态不是 unknow
				}else{
					RelationEntity relationEntity = new RelationEntity(rootName,relation,childName);
					relationEntities.add(relationEntity);
				}
			}else{
				String stateString = seg[i];
				Relation relation2 = relationContext.analyse(stateString);
				if (relation2==null) {
					// to do nothings
				}else{
					// state 不是 unknow
					relation = relation2;
					switch (relation) {
					case FAMILY:
						state = State.FAMILY;
						break;
					case MASTER:
						state = State.MASTER;
						break;	
					case APPRENTICE:
						state = State.APPRENTICE;
						break;
					case COOPERATION:
						state = State.COOPERATION;
						break;
					default:
						throw new InfoExException("Not support the relation "+relation);
					}
					// 如果 people中积累有了一定数据的人员那么就把这些人员全部加到这个state中与relation当中去
					for (String string : people) {
						RelationEntity relationEntity = new RelationEntity(rootName, relation, string);
						relationEntities.add(relationEntity);
					}
					people.clear();
				}
			}
		}
		//表示这一句话已经扫描结束
		//如果people set还有人的话就加入到entitys
		if (!people.isEmpty()) {
			for (String string : people) {
				RelationEntity relationEntity = new RelationEntity(rootName, relation, string);
				relationEntities.add(relationEntity);
			}
		}
		return relationEntities;
	}

	public static void Clear() {
		people.clear();
		relationEntities.clear();
		state = State.UNKNOW;
		relation = Relation.UNKNOW;
	}

}
