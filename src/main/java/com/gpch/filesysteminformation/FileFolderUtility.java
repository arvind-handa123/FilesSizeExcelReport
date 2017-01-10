package com.gpch.filesysteminformation;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;

public class FileFolderUtility {
	
	private static Map<String, Long> result = new HashMap<String, Long>();


	public static String getFolderSize(String path) {
		String hrSize = "";
		long size = FileUtils.sizeOfDirectory(new File(path));

		double b = size;
		double k = size / 1024.0;
		double m = ((size / 1024.0) / 1024.0);
		double g = (((size / 1024.0) / 1024.0) / 1024.0);
		double t = ((((size / 1024.0) / 1024.0) / 1024.0) / 1024.0);

		DecimalFormat dec = new DecimalFormat("0.00");

		if (t > 1) {
			hrSize = dec.format(t).concat(" TB");
		} else if (g > 1) {
			hrSize = dec.format(g).concat(" GB");
		} else if (m > 1) {
			hrSize = dec.format(m).concat(" MB");
		} else if (k > 1) {
			hrSize = dec.format(k).concat(" KB");
		} else {
			hrSize = dec.format(b).concat(" Bytes");
		}

		return hrSize;
	}

	public static Map<String, Long> fetchFileSystemInformation(String path) {
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files.length == 0) {
			System.out.println("The directory is empty: " + path);
		} else {
			for (File file : files) {
				if (file.isDirectory()) {
					fetchFileSystemInformation(file.getAbsolutePath());
				} else {
					result.put(file.getAbsolutePath(), file.length());
				}
			}
		}
		return result;
	}

}
