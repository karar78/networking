package com.wundermancommerce.interviewtests.graph;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.transform.TransformerFactory;

public class Config {
	public static void main(String[] args) {
		new Config().loadPropertyFile();
	}
	public  Properties loadPropertyFile() {
		Properties props = new Properties();
		try {
			String basePath = this.getClass().getClassLoader().getResource("").getPath();
			basePath=basePath.replaceAll("%20", " ");
			File in = new File(basePath + "/resources/app.properties");
			
			
			props.load(new FileInputStream(in));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//System.out.println(props.getProperty("people_file"));
		return props;
	}
}
