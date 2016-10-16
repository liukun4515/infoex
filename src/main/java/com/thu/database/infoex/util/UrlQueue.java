package com.thu.database.infoex.util;

import java.util.LinkedList;

/**
 * @author liukun
 *
 */
public class UrlQueue {

	private LinkedList<Object> queue = new LinkedList<>();

	public boolean isEmpty() {
		return queue.isEmpty();
	}

	public boolean contains(Object o) {
		return queue.contains(o);
	}

	public void enQueue(Object o) {
		queue.add(o);
	}

	public Object deQueue() {
		return queue.removeFirst();
	}

}
