package com.thu.database.infoex.util;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.thu.database.infoex.entity.ParseElement;

/**
 * @author liukun
 *
 */
public class HtmlParser {

	private static final String articleId = "article";
	private static final String h1 = "h1";
	private static final String table = "table";
	private static final String photoClass = "photoHolder";
	private static final String attrKey = "class";
	private static final String attrValue = "exist";
	private static final String split = "活动年表";
	private static final String title = "title";
	private static final String splitName = "：";
	private static final String splitPer = "（";

	public static ParseElement parse(String entity, String baseUrl) {
		ParseElement parseElement = null;
		Document document = Jsoup.parse(entity);
		Element article = document.getElementById(articleId);

		String dirtyString = article.getElementsByClass(photoClass).text();
		String perNameString = article.getElementsByTag(h1).get(0).text();
		String perName = perNameString.split(splitName)[1];
		String desc = article.select(table).get(0).text();
		int length = dirtyString.length();
		desc = desc.substring(length);
		int endIndex = desc.indexOf(split);
		desc = desc.substring(0, endIndex);
		Elements links = article.select(table).get(0).getElementsByAttributeValueStarting(attrKey, attrValue);
		List<String> linkLists = new ArrayList<>();
		for (Element element : links) {
			linkLists.add(baseUrl + element.attr(title));
		}
		parseElement = new ParseElement(perName.split(splitPer)[0], desc, linkLists);
		return parseElement;
	}

}
