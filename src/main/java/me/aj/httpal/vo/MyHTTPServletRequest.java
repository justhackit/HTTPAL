package me.aj.httpal.vo;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

public class MyHTTPServletRequest {
	private String method;
	private String service;
	private HashMap<String, String> headers;
	private String payLoad;

	public MyHTTPServletRequest() {
	}
	
	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	public String getPayLoad() {
		return payLoad;
	}

	public void setPayLoad(String payLoad) {
		this.payLoad = payLoad;
	}

	public static MyHTTPServletRequest buildTheVo(HttpServletRequest httpServletRequest, String payLoad,String serviceName) {
		MyHTTPServletRequest toRet = new MyHTTPServletRequest();
		toRet.setMethod(httpServletRequest.getMethod());
		if(serviceName != null && serviceName.length() > 0){
			toRet.setService(serviceName);
		}else{
			toRet.setService(httpServletRequest.getRequestURL().toString());	
		}
		
		Enumeration<String> headerNames = httpServletRequest.getHeaderNames();
		HashMap<String, String> hdrMap = new HashMap<String, String>();
		if (headerNames != null) {
			while (headerNames.hasMoreElements()) {
				String hdrName = headerNames.nextElement();
				String hdrVal = httpServletRequest.getHeader(hdrName);
				hdrMap.put(hdrName, hdrVal);
			}
			//HTTPALine unique id
			hdrMap.put(HTTPAssemblyLineConstants.HTTPALINE_TRANS_ID, UUID.randomUUID().toString());
			toRet.setHeaders(hdrMap);
		}
		if (payLoad != null) {
			toRet.setPayLoad(payLoad);
		}
		return toRet;
	}

	@Override
	public String toString() {
		return "MyHTTPServletRequest [method=" + method + ", serviceName=" + service + ", headers=" + headers + ", payLoad="
				+ payLoad + "]";
	}
	
	

}
