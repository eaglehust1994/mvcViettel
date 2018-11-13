package com.viettel.ktts2.common;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.ws.rs.core.MediaType;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.viettel.asset.business.CatAssetCodeBusiness;

public class URestService {
	static Logger LOGGER = LoggerFactory.getLogger(URestService.class);
	private static URestService instance;
	public static synchronized URestService getInstance(){
		if(instance==null){
			instance=new URestService();
		}
		return instance;
	}
	
	public String post(String link,Object o) throws Exception{
		 Client client = Client.create();
		 WebResource webResource = client
				   .resource(link);
//		 Gson input;
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonInString = mapper.writeValueAsString(o);
		 //webResource.setProperty("Content-Type", "application/json");
		 webResource.header("Content-Type", "application/json");
		 webResource.type(MediaType.APPLICATION_JSON);
		 LOGGER.info("ban tin gui sang link:"+link+", noi dung: "+jsonInString);
		 ClientResponse response = webResource.accept("application/json").header("Content-Type", "application/json;charset=UTF-8").type(MediaType.APPLICATION_JSON).post(ClientResponse.class,jsonInString);
		 if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
		}
		 
		 String output = response.getEntity(String.class);
		return output;
	}
	
	public  String postJson(String link, Object o) throws Exception{
		 URL url = new URL(link);
		 HttpURLConnection conn=null;
		 OutputStream os=null;
		
			
		 
		 try{
			 conn= (HttpURLConnection) url.openConnection();
		  conn.setDoOutput(true);
		  conn.setRequestMethod("POST");
		  conn.setRequestProperty("Content-Type", "application/json");
		  Gson input = new Gson();
		  os = conn.getOutputStream();
		  os.write(input.toJson(o).getBytes());
		  os.flush();
		  if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			  throw new RuntimeException("Failed : HTTP error code : "
					  + conn.getResponseCode());
		  }
		  StringBuilder sb=new StringBuilder();
		  BufferedReader br = new BufferedReader(new InputStreamReader(
				    (conn.getInputStream())));
		  String output;
		  while ((output = br.readLine()) != null) {
			   System.out.println(output);
			   sb.append(br);
		  }
		 
		 
		  return sb.toString();
		 }finally{
			 if(conn!=null){
				 conn.disconnect();
			 }
		 }
	}
}
