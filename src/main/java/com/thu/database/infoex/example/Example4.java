package com.thu.database.infoex.example;

import java.io.IOException;
import java.util.Set;

import org.dom4j.DocumentException;

import com.thu.database.infoex.InfoExException;
import com.thu.database.infoex.entity.Person;
import com.thu.database.infoex.entity.RelationEntity;
import com.thu.database.infoex.util.NlpUtil;
import com.thu.database.infoex.util.RelationEntityUtil;
import com.thu.database.infoex.util.StringUtil;
import com.thu.database.infoex.util.XmlUtil;

public class Example4 {

	public static void main(String[] args) throws DocumentException, InfoExException, IOException {
		long beginTime = System.currentTimeMillis();
		NlpUtil.initFNLPFactory();
		long endTime = System.currentTimeMillis();
		System.out.println("载入NLP模型时间为：" + (endTime - beginTime) + "毫秒");
		String outFile = "people_relation";

		// 从 xml文件中读取人物介绍等信息
		String fileName = "people_info.xml";
		Person person = null;
		// 初始化读取xml文件
		XmlUtil.initXmlRootReader(fileName);
		XmlUtil.initXmlRootWriter();
		// 访问xml文件中的信息
		int index = 1;
		while (XmlUtil.hasNextElement()) {
			person = XmlUtil.next();
			System.out.println(index);
			index++;
			String rootName = person.getName();
			XmlUtil.addPersonElement(rootName);
			String[] splits = StringUtil.splitToScentences(person.getDesc());
			for (String string : splits) {
				string = StringUtil.clean(string);
				if (string!=null&&string.length()>0) {
					Set<RelationEntity> relationEntities = RelationEntityUtil.getRelationEntities(string, rootName);
//					System.out.println("The relation size is " + relationEntities.size());
					XmlUtil.addRelationElement(relationEntities);
					// 一定要注意clear
				}
				RelationEntityUtil.Clear();
			}
		}
		XmlUtil.outPutTheXml(outFile);
	}

}
