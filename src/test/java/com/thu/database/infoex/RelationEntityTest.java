package com.thu.database.infoex;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thu.database.infoex.entity.Relation;
import com.thu.database.infoex.entity.RelationEntity;

public class RelationEntityTest {

	private RelationEntity entity1;
	private RelationEntity entity2;
	private String root1 = "root1";
	private Relation relation;
	private String child1 = "child1";


	@Before
	public void setUp() throws Exception {
		relation = Relation.UNKNOW;
		entity1 = new RelationEntity(root1, relation, child1);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		//测试 enum
		assertEquals(relation, Relation.UNKNOW);
		assertEquals("unknow", relation.toString().toLowerCase());
		//测试 relationEntity
		assertEquals(root1, entity1.getRootNode());
		assertEquals(relation, entity1.getRelation());
		assertEquals(child1, entity1.getChildNode());
		//测试 relationentity 的hascode 和 equals
		entity2 = new RelationEntity(root1, relation, child1);
		assertEquals(entity1.hashCode(), entity2.hashCode());
		assertEquals(entity1, entity2);
		//测试修改 relation以后 entity的relation不发生变化
		relation = Relation.COOPERATION;
		assertEquals(Relation.UNKNOW, entity1.getRelation());
	}

}
