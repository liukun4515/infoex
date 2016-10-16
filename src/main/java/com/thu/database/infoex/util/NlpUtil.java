package com.thu.database.infoex.util;

import java.util.HashMap;

import org.fnlp.nlp.cn.CNFactory;
import org.fnlp.util.exception.LoadModelException;

public class NlpUtil {

	private static final String MODELS_PATH = "models";
	private static CNFactory nlpFactory;

	public static void initFNLPFactory() {
		try {
			nlpFactory = CNFactory.getInstance(MODELS_PATH);
		} catch (LoadModelException e) {
			e.printStackTrace();
		}
	}
	public static String[] segFunction(String input) {
		return nlpFactory.seg(input);
	}

	public static String[] tagFunction(String[] input) {
		return nlpFactory.tag(input);
	}

	public static String[][] tagFunction(String input) {
		return nlpFactory.tag(input);
	}

	public static HashMap<String, String> nerFunction(String input) {
		return nlpFactory.ner(input);
	}
}
