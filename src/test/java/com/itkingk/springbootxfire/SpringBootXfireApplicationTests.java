package com.itkingk.springbootxfire;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.xfire.client.Client;
import org.codehaus.xfire.transport.http.HttpTransport;
import org.junit.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;


public class SpringBootXfireApplicationTests {

	@Test
	public void contextLoads() {
		insertRwlxData();
	}

	public static Object[] getWebService(String url, String action, Object[] objarr) {
		Object[] results = null;
		try {
			Client client = new Client(new URL(url));
			client.setProperty("mtom-enable", "true");
			client.setProperty(HttpTransport.CHUNKING_ENABLED, "true");
			results = client.invoke(action, objarr);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return results;
	}

	public static String insertRwlxData() {
		String res = "";
		try {

			String bdzid = "FF000000-0000-0000-1000-000000000400-08416";
			String fwdz = "127.0.0.1:8080";
			//传给接口的参数
			//传给接口的参数
			Map<String, Object> m=new TreeMap<String, Object>();
			m.put("stationId",bdzid);
			String paramJson = new ObjectMapper().writeValueAsString(m);
			String url ="http://"+fwdz+"/webservice/robotApiService?wsdl";
			//获取接口返回的参数
			Object[] result = getWebService(url,"syncTaskType",new Object[]{paramJson});
			System.out.println(result[0].toString());
			res = "同步成功";
		} catch (Exception e) {
			res = "同步失败";
			e.printStackTrace();
		}
		return res;
	}
}
