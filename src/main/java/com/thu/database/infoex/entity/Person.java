package com.thu.database.infoex.entity;

/**
 * @author liukun
 *
 */
public class Person {

	private String name;
	private String url;
	private String desc;

	public Person() {

	}

	public Person(String name, String url, String desc) {
		super();
		this.name = name;
		this.url = url;
		this.desc = desc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public int hashCode() {
		int prime = 31;
		int reslut = 0;
		reslut = reslut * prime + name.hashCode();
		reslut = reslut * 31 + url.hashCode();
		reslut = reslut * 31 + desc.hashCode();
		return reslut;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj instanceof Person) {
			Person other = (Person) obj;
			if (other.getName() == this.getName() && other.getUrl() == this.getUrl()
					&& other.getDesc() == this.getDesc()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("The person{");
		builder.append(name + ",");
		builder.append(url + ",");
		builder.append(desc);
		builder.append("}");
		return builder.toString();
	}

}
