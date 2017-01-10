package com.gpch.filesysteminformation;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class App {
	public static void main(String[] args) {
		try{
			Map<String, Long> fileInformation = null;
			if (args.length > 0){
				File path = new File(args[0]);
				if (path.exists()){
					fileInformation = FileFolderUtility.fetchFileSystemInformation(args[0]);
				}else{
					System.out.println("Please provide a valid path");
				}
			}
			else{
				System.out.println("Please provide a path as argument");
			}
			if (fileInformation != null && fileInformation.size() > 0){
				ExcelUtility.generateReport(fileInformation);
			}
			System.out.println("The process has finished.");
		}catch(IOException iOException){
			iOException.printStackTrace();
		}
	}
}
