package com.thu.database.infoex.entity;

import java.util.List;

/**
 * @author liukun
 *
 */
public class ParseElement {
	private String name;
	private String desc;
	private List<String> linkLists;

	public ParseElement(String name, String desc, List<String> linkLists) {
		this.name = name;
		this.desc = desc;
		this.linkLists = linkLists;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<String> getLinkLists() {
		return linkLists;
	}

	public void setLinkLists(List<String> linkLists) {
		this.linkLists = linkLists;
	}

	@Override
	public String toString() {
		
		return name.toString()+","+desc.toString()+","+linkLists.toString();
	}

}
