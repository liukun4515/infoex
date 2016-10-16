package com.thu.database.infoex.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileUtil {

	private static BufferedWriter bufferedWriter = null;

	public static String getContent(File file) throws IOException {
		String content = "";
		String temp = null;
		FileInputStream in = new FileInputStream(file);
		InputStreamReader reader = new InputStreamReader(in, "UTF-8");
		BufferedReader bufferedReader = new BufferedReader(reader);
		while ((temp = bufferedReader.readLine()) != null) {
			content = content + temp;
		}
		bufferedReader.close();
		reader.close();
		return content;

	}

	public static void writeContent(String oneString) throws IOException {
		bufferedWriter.write(oneString);
	}

	public static void initWrite(File file) throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		bufferedWriter = new BufferedWriter(fileWriter);
	}

	public static void close() throws IOException {
		bufferedWriter.close();
	}
}
