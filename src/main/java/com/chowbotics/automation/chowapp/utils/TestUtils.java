package com.chowbotics.automation.chowapp.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.aventstack.extentreports.Status;
import com.chowbotics.automation.chowapp.base.BaseTest;
import com.chowbotics.automation.chowapp.reports.ExtentReport;

public class TestUtils {
	public static final long WAIT = 10;

	public HashMap<String, String> parseStringXML(InputStream file) throws Exception {
		HashMap<String, String> stringMap = new HashMap<String, String>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(file);
		document.getDocumentElement().normalize();
		NodeList nList = document.getElementsByTagName("string");
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element eElement = (Element) node;
				stringMap.put(eElement.getAttribute("name"), eElement.getTextContent());
			}
		}
		return stringMap;
	}

	public String dateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void log(String txt) {
		Logger log = LogManager.getLogger();
		log.info(Thread.currentThread().getStackTrace()[2].getClassName() + ":"
				+ Thread.currentThread().getStackTrace()[2].getLineNumber() + ":" + txt);
		if (ExtentReport.extent != null) {
			ExtentReport.getTest().log(Status.INFO, txt);

			String strFile = "logs" + File.separator + BaseTest.platform + "_" + BaseTest.deviceName + File.separator
					+ BaseTest.dateTime;

			File logFile = new File(strFile);
			if (!logFile.exists()) {
				logFile.mkdirs();
			}

			FileWriter fileWriter = null;
			try {
				fileWriter = new FileWriter(logFile + File.separator + "log.txt", true);
			} catch (IOException e) {
				e.printStackTrace();
			}
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.println(Status.INFO + ":" + Thread.currentThread().getStackTrace()[2].getClassName() + ":"
					+ Thread.currentThread().getStackTrace()[2].getLineNumber() + ":" + txt);
			printWriter.close();
		}
	}

	public Logger log() {
		return LogManager.getLogger(Thread.currentThread().getStackTrace()[2].getClassName());
	}

}
