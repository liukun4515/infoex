package com.thu.database.infoex.entity;

public class RelationEntity {

	private String rootNode;
	private Relation relation;
	private String childNode;

	public RelationEntity() {

	}

	public RelationEntity(String rootNode, Relation relation, String childNode) {
		this.rootNode = rootNode;
		this.relation = relation;
		this.childNode = childNode;
	}

	public String getRootNode() {
		return rootNode;
	}

	public void setRootNode(String rootNode) {
		this.rootNode = rootNode;
	}

	public Relation getRelation() {
		return relation;
	}

	public void setRelation(Relation relation) {
		this.relation = relation;
	}

	public String getChildNode() {
		return childNode;
	}

	public void setChildNode(String childNode) {
		this.childNode = childNode;
	}

	@Override
	public String toString() {
		return "RelationEntity [rootNode=" + rootNode + ", relation=" + relation + ", childNode=" + childNode + "]";
	}

	public String toString2() {
		if (relation.equals(Relation.APPRENTICE)) {
			return rootNode+" "+Relation.MASTER.getName()+" "+childNode;
		}
		return childNode + " " + relation.getName() + " " + rootNode;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int result = 0;
		result = result * prime + rootNode.hashCode();
		result = result * prime + relation.toString().hashCode();
		result = result * prime + childNode.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		} else {
			if (obj instanceof RelationEntity) {
				obj = (RelationEntity) obj;
				if (getRootNode().equals(((RelationEntity) obj).getRootNode())
						&& getRelation().equals(((RelationEntity) obj).getRelation())
						&& getChildNode().equals(((RelationEntity) obj).getChildNode())) {
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}

}
