package com.thu.database.infoex.example;

import java.io.IOException;
import java.util.List;

import com.thu.database.infoex.entity.ParseElement;
import com.thu.database.infoex.entity.Person;
import com.thu.database.infoex.util.HtmlParser;
import com.thu.database.infoex.util.HttpMethod;
import com.thu.database.infoex.util.Queue;
import com.thu.database.infoex.util.XmlUtil;

public class Example2 {

	public static void main(String[] args) throws IOException {
		String baseUrl = "http://history.xikao.com/person/";
		String baseName = "顾正秋";
		String file = "people_info";
		ParseElement parseElement;
		Queue.PushUnVisitUrl(baseUrl + baseName);
		// init the xml root
		XmlUtil.initXmlRootWriter();
		while (!Queue.unVisitUrlIsEmpty() && Queue.getVisitUrl().size() < 1000) {
			String url = Queue.popUnVisitUrl();
			String document = HttpMethod.doGet(url);
			parseElement = HtmlParser.parse(document, baseUrl);
			Person person = new Person(parseElement.getName(), url, parseElement.getDesc());
			System.out.println(person.toString());
			// write person to xml file
			XmlUtil.addElement(person);
			List<String> linkLists = parseElement.getLinkLists();
			for (String string : linkLists) {
				Queue.PushUnVisitUrl(string);
			}
			Queue.addVisitUrl(url);
		}
		XmlUtil.printDocument();
		XmlUtil.outPutTheXml(file);
	}

}
