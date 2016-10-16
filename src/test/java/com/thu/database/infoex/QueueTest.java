package com.thu.database.infoex;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.thu.database.infoex.util.Queue;

public class QueueTest {

	private String url1 = "url1";
	private String url2 = "url2";
	private String url3 = "url3";
	private String url4 = "url4";
	
	private String same = "url1";

	@Before
	public void setUp() throws Exception {
		Queue.PushUnVisitUrl(url1);
		Queue.PushUnVisitUrl(url2);
		Queue.PushUnVisitUrl(url3);
		Queue.PushUnVisitUrl(url4);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(url1, Queue.popUnVisitUrl());
		Queue.addVisitUrl(url1);
		assertEquals(url2, Queue.popUnVisitUrl());
		Queue.addVisitUrl(url2);
		assertEquals(url3, Queue.popUnVisitUrl());
		Queue.addVisitUrl(url3);
		assertEquals(url4, Queue.popUnVisitUrl());
		Queue.addVisitUrl(url4);
		assertEquals(4, Queue.getVisitUrlNum());
		Queue.PushUnVisitUrl(same);
		assertEquals(false, Queue.getUnVisitUrl().contains(same));
	}

}
