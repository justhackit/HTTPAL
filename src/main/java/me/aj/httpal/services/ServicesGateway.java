package me.aj.httpal.services;

import java.io.IOException;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import me.aj.httpal.KafkaUtils.KafkaUtil;
import me.aj.httpal.httputils.MyHttpClientPoolUtil;
import me.aj.httpal.vo.HTTPAssemblyLineConstants;
import me.aj.httpal.vo.MyHTTPServletRequest;

@Controller
public class ServicesGateway {

	private static Logger _logger = Logger.getLogger(ServicesGateway.class.getName());


	@RequestMapping(value = "/api/{apiName}", method = RequestMethod.POST)
	public @ResponseBody String proxyPost(@PathVariable("apiName") String apiName,
			HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
		String requestPayload = "";
		try {
			requestPayload = httpServletRequest.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		MyHTTPServletRequest req = MyHTTPServletRequest.buildTheVo(httpServletRequest, requestPayload, apiName);
		String responseBody="";
		if(apiName.startsWith("get")){
			responseBody = beARealProxy(req, requestPayload, apiName);
		}else{
			KafkaUtil.publishHTTPRequest(req);
			String transId = req.getHeaders().get(HTTPAssemblyLineConstants.HTTPALINE_TRANS_ID);
			responseBody= "You can track with transaction id: " + transId;	
		}
		return responseBody;
		
	}
	
	private String beARealProxy(MyHTTPServletRequest httpRequest,String payLoad,String serviceName){
		
		MyHttpClientPoolUtil httpClient = new MyHttpClientPoolUtil();
		HttpResponse response = httpClient.executeService(httpRequest);
		HttpEntity r_entity = response.getEntity();
		String responseStr="";
		try {
			responseStr=EntityUtils.toString(r_entity).toString().trim();
			/*BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			
			String line = "";
			while ((line = rd.readLine()) != null) {
			    result.append(line);
			}
			System.out.println("From normal block:\n"+result.toString());*/
			return responseStr;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Exception from beARealProxy:\n");
			e.printStackTrace();
			return "Received this exception : "+e.getMessage();
			
		}
		return responseStr;
	}

}
