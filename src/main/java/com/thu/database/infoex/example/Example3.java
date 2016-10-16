package com.thu.database.infoex.example;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.DocumentException;

import com.thu.database.infoex.entity.Person;
import com.thu.database.infoex.util.NlpUtil;
import com.thu.database.infoex.util.StringUtil;
import com.thu.database.infoex.util.XmlUtil;

public class Example3 {

	private static String outputFile = "people_entity";

	public static void main(String[] args) throws DocumentException, IOException {
		int index = 1;
		Person person = null;
		System.out.println("Begin to seg, tag, ner sentence");
		String file = "people_info.xml";
		// 初始化 NLP
		NlpUtil.initFNLPFactory();
		// 初始化写出xml文件
		XmlUtil.initXmlPersonRootWriter();
		// 初始化读xml文件
		XmlUtil.initXmlRootReader(file);
		// 使用set，保证相关人物不重复
		HashSet<String> relatedPerson = new HashSet<>();
		long begin = System.currentTimeMillis();
		while (XmlUtil.hasNextElement()) {
			person = XmlUtil.next();
			System.out.println("Exeture the " + index);
			index++;
			String name = person.getName();
			String desc = person.getDesc();
			desc = StringUtil.clean(desc);
			XmlUtil.addPersonElement(name);
			HashMap<String, String> namedEntity = NlpUtil.nerFunction(desc);
			Iterator<Map.Entry<String, String>> iterator = namedEntity.entrySet().iterator();
			relatedPerson.clear();
			while (iterator.hasNext()) {
				Map.Entry<String, String> entry = iterator.next();
				if ("人名".equals(entry.getValue()) && !name.equals(entry.getKey())) {
					relatedPerson.add(entry.getKey());
				}
			}
			for (String string : relatedPerson) {
				if (string.length() > 1) {
					XmlUtil.addPersonChildElement(string);
				}
			}
		}
		XmlUtil.outPutTheXml(outputFile);
		long end = System.currentTimeMillis();
		System.out.println("Time cost is: " + (end - begin));
	}

}
