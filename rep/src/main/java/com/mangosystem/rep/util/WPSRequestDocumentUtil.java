package com.mangosystem.rep.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class WPSRequestDocumentUtil {
	
	public WPSRequestDocumentUtil(){}
	
	public WPSRequestDocumentUtil (String requestUrl, String version) {
	
	}
	
	
	public static  String getReferenceUrl(Element el) {
		try {
			NodeList processOutputsNodeList = el.getElementsByTagName("wps:ProcessOutputs");
			NodeList outputNodeList = ((Element) processOutputsNodeList.item(0)).getElementsByTagName("wps:Output");
			Element referenceNode = (Element) ((Element) outputNodeList.item(0)).getElementsByTagName("wps:Reference").item(0);
			return referenceNode.getAttribute("href");
		} catch (NullPointerException e) {
			System.out.println("WPS Reference결과 주소를 가져오지 못했습니다. Null Pointer Exception \nResult El : " + WPSRequestDocumentUtil.parserDOMToString(el));
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * DOM(Element) XML을 String으로 변환
	 * 
	 * @param org
	 *            .w3c.dom.Element
	 * @return dom to string
	 */
	public static  String parserDOMToString(Element domElement) {

		String result = "";

		try {
			TransformerFactory transformerFactory = TransformerFactory
					.newInstance();

			StringWriter stringWriter = new StringWriter();

			Properties properties = new Properties();
			properties.setProperty(OutputKeys.INDENT, "yes");
			properties.setProperty(OutputKeys.ENCODING, "UTF-8");

			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperties(properties);
			transformer.transform(new DOMSource(domElement), new StreamResult(
					stringWriter));

			result = stringWriter.getBuffer().toString();

		} catch (TransformerException e) {
			e.printStackTrace();
		}

		return result;
	}
	
	public static String requestService(String serverURL, String requestParams) {

		StringBuffer sbResult = null;
		try {
			URL url = new URL(serverURL);
			URLConnection connection = url.openConnection();

			HttpURLConnection httpURLConnection = (HttpURLConnection) connection;
			httpURLConnection.setRequestMethod("POST");
			httpURLConnection.setRequestProperty("content-Type",
					"text/xml; charset=euc-kr");

			if (requestParams != null) {
				httpURLConnection.setRequestProperty("content-Length", ""
						+ requestParams.getBytes().length + "");
			}

			httpURLConnection.setDoOutput(true);
			httpURLConnection.setDoInput(true);

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
					httpURLConnection.getOutputStream(), "utf-8");

			if (requestParams != null) {
				outputStreamWriter.write(requestParams, 0,
						requestParams.length());
			}

			outputStreamWriter.flush();
			outputStreamWriter.close();

			// ##임시로 수정
			String encodingType = httpURLConnection.getContentType();
			if ("text/plain".equals(encodingType)) {
				encodingType = "euc-kr";
			} else {
				encodingType = "utf-8";
			}

			InputStreamReader inputStreamReader = new InputStreamReader(
					httpURLConnection.getInputStream(), encodingType);
			BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);
			String inputLine = null;

			sbResult = new StringBuffer();
			while ((inputLine = bufferedReader.readLine()) != null) {
				sbResult.append(inputLine);
			}
			bufferedReader.close();

		} catch (IOException e) {
			e.printStackTrace();
			return "";
		}
		return sbResult.toString();
	}
	

}
