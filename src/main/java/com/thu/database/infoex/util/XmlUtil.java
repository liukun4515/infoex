package com.thu.database.infoex.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Set;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.thu.database.infoex.entity.Person;
import com.thu.database.infoex.entity.RelationEntity;

public class XmlUtil {

	private static SAXReader reader = null;
	private static Document document = null;
	private static Document readDocument = null;
	private static Element currentElement = null;
	private static Element child = null;
	private static List<Element> children = null;
	private static int childrenSize;
	private static int currentPos;
	private static Element readRoot = null;
	private static final String ROOT = "information_root";
	private static final String PERSON = "person";
	private static final String RELATED_PERSON = "related_person";
	private static final String NAME = "name";
	private static final String ID = "id";
	private static final String URL = "url";
	private static int index = 1;
	private static Element root = null;

	/**
	 * 初始化xml的document和root
	 */
	public static void initXmlRootWriter() {
		document = DocumentHelper.createDocument();
		document.setName("戏剧人物");
		root = document.addElement(ROOT);
	}

	/**
	 * @param filePath
	 * @throws DocumentException
	 */
	@SuppressWarnings("unchecked")
	public static void initXmlRootReader(String filePath) throws DocumentException {
		File file = new File(filePath);
		if (!file.exists()) {
			throw new RuntimeException("File is not exist：" + filePath);
		}
		reader = new SAXReader();
		readDocument = reader.read(file);
		readRoot = readDocument.getRootElement();
		if (!readRoot.getName().equals(ROOT)) {
			throw new RuntimeException("The file is not right");
		}
		System.out.println("Root name is " + readRoot.getName());
		System.out.println("Begin read xml file");
		children = readRoot.elements();
		childrenSize = children.size();
		currentPos = 0;
	}

	public static void closeRead() {
		reader = null;
	}

	/**
	 * document中是否有下一个节点
	 * 
	 * @return
	 */
	public static boolean hasNextElement() {
		if (currentPos < childrenSize) {
			currentElement = children.get(currentPos);
			currentPos++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 得到当前element
	 */
	public static Person next() {
		Person person = new Person(currentElement.attributeValue(NAME), currentElement.attributeValue(URL),
				currentElement.getTextTrim());
		return person;
	}

	/**
	 * 在document中添加一个节点
	 * 
	 * @param person
	 */
	public static void addElement(Person person) {
		root.addElement(PERSON).addAttribute(NAME, person.getName()).addAttribute(URL, person.getUrl())
				.addText(person.getDesc());
	}

	/**
	 * 将document输出到filepath目录
	 * 
	 * @param filePath
	 * @throws IOException
	 */
	public static void outPutTheXml(String filePath) throws IOException {
		String filename = filePath + ".xml";
		File file = new File(filename);
		if (file.exists()) {
			file.delete();
		}
		FileWriter fileWriter = new FileWriter(file);
		OutputFormat format = OutputFormat.createPrettyPrint();
		XMLWriter writer = new XMLWriter(fileWriter, format);
		writer.write(document);
		writer.close();

		document.clearContent();
		root.clearContent();
		root = null;
		document = null;
	}

	/**
	 * 
	 */
	public static void printDocument() {
		try {
			XMLWriter writer = new XMLWriter(OutputFormat.createPrettyPrint());
			try {
				writer.write(document);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public static void initXmlPersonRootWriter() {
		document = DocumentHelper.createDocument();
		document.setName("关联人物");
		root = document.addElement(ROOT);
		index = 1;
	}

	/**
	 * @param rootPerson
	 */
	public static void addPersonElement(String rootPerson) {
		child = root.addElement(PERSON).addAttribute(PERSON, rootPerson).addAttribute(ID, String.valueOf(index));
		index++;
	}

	/**
	 * @param name
	 */
	public static void addPersonChildElement(String name) {
		child.addElement(RELATED_PERSON).addText(name);
	}

	public static void addRelationElement(Set<RelationEntity> relationEntities) {
		for (RelationEntity relationEntity : relationEntities) {
			child.addElement(RELATED_PERSON).addAttribute(PERSON, relationEntity.getChildNode())
					.setText(relationEntity.getRelation().getName());
		}
	}

}
