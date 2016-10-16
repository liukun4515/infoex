package com.thu.database.infoex.example;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.thu.database.infoex.InfoExException;
import com.thu.database.infoex.entity.RelationEntity;
import com.thu.database.infoex.util.FileUtil;
import com.thu.database.infoex.util.NlpUtil;
import com.thu.database.infoex.util.RelationEntityUtil;
import com.thu.database.infoex.util.StringUtil;

public class Example1 {

	public static void main(String[] args) throws InfoExException, IOException {
		String inputDir = null;
		String outputDir = "outDir";
		String outNmae = ".out";
		if (args.length < 1) {
			System.err.println("Please input the directory path");
			System.exit(0);
		}
		if (args.length == 1) {
			inputDir = args[0];
		} else {
			inputDir = args[0];
			outputDir = args[1];
		}
		// 判断inputpath是不是directory
		File inputFile = new File(inputDir);
		if (!inputFile.isDirectory()) {
			System.out.println("The input path must be directory");
			System.exit(0);
		}
		File outputFile = new File(outputDir);
		if (outputFile.exists()) {
			outputFile.delete();
		}
		outputFile.mkdirs();
		File[] inputFiles = inputFile.listFiles();
		NlpUtil.initFNLPFactory();
		for (File file : inputFiles) {
			if (file.isFile()) {
				List<RelationEntity> relationentis = new ArrayList<>();
				String fileName = file.getName();
				String content = FileUtil.getContent(file);
				String[] splits = StringUtil.splitToScentences(content);
				for (String string : splits) {
					string = StringUtil.clean(string);
					if (string != null && string.length() > 0) {
						// get relationentitys
						Set<RelationEntity> relationEntities = RelationEntityUtil.getRelationEntities(string, fileName);
						for (RelationEntity relationEntity : relationEntities) {
							relationentis.add(relationEntity);
						}
						// clear for the next get
						RelationEntityUtil.Clear();
					}
				}
				File out = new File(outputFile, fileName + outNmae);
				FileUtil.initWrite(out);
				for (RelationEntity relationEntity : relationentis) {
					FileUtil.writeContent(relationEntity.toString2() + "\n");
				}
				FileUtil.close();
				// output a file
				// close
			}
		}

	}

}
