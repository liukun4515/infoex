package com.thu.database.infoex.util;

import java.util.HashSet;
import java.util.Set;

/**
 * @author liukun
 *
 */
public class Queue {

	// 已经访问过的url
	private static Set<String> visitUrl = new HashSet<>();
	// 没有被访问的url
	private static UrlQueue unVisitUrl = new UrlQueue();

	public static UrlQueue getUnVisitUrl() {
		return unVisitUrl;
	}

	public static Set<String> getVisitUrl() {
		return visitUrl;
	}

	public static void addVisitUrl(String url) {
		visitUrl.add(url);
	}

	public static void PushUnVisitUrl(String url) {
		if (url != null && !"".equals(url) && !unVisitUrl.contains(url) && !visitUrl.contains(url)) {
			unVisitUrl.enQueue(url);
		}
	}

	public static int getVisitUrlNum() {
		return visitUrl.size();
	}

	public static boolean unVisitUrlIsEmpty() {
		return unVisitUrl.isEmpty();
	}

	public static String popUnVisitUrl() {
		return (String) unVisitUrl.deQueue();
	}
}
