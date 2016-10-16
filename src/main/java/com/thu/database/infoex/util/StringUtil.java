package com.thu.database.infoex.util;

import org.fnlp.nlp.similarity.EditDistance;

public class StringUtil {

	private static final String splitSignJu = "。";
	private static final String splitSignDou = "，";
	private static final String fu1 = "《";
	private static final String fu2 = "》";

	public static String[] splitToScentences(String article) {
		return article.split(splitSignJu);
	}

	public static String clean(String dirString) {
		while (dirString.indexOf(fu1) > -1 && dirString.indexOf(fu2) > -1) {
			int begin = dirString.indexOf(fu1);
			int end = dirString.indexOf(fu2);
			if (begin < end) {
				dirString = dirString.substring(0, begin) + dirString.substring(end + 1, dirString.length());
			} else {
				// 这里就是 end<begin 这个时候就把end去掉
				dirString = dirString.substring(0, end) + dirString.substring(end + 1, dirString.length());
			}
		}
		return dirString;
	}
}
