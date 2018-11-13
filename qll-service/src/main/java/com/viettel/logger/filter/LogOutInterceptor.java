package com.viettel.logger.filter;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.message.Exchange;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.ktts2.common.UDate;
import com.viettel.ktts2.common.UString;

import viettel.passport.client.UserToken;

public class LogOutInterceptor extends AbstractPhaseInterceptor<Message> implements VtLogInterceptor   {
	Logger loggerHieunang=Logger.getLogger("LogHieuNang");
	Logger loggerLoi=Logger.getLogger("LogLoi");
	@Value("${APPLICATION_CODE}")
	private static String APP_CODE;
	
	public LogOutInterceptor(){
        super(Phase.POST_PROTOCOL);

	}
	public LogOutInterceptor(String phase) {
		super(phase);
		// TODO Auto-generated constructor stub
	}

	
	    
	    @Context
	    HttpServletRequest request;
	@Override
	public void handleMessage(Message message) throws Fault {
		// TODO Auto-generated method stub
		
		
		Exchange exchange=message.getExchange();
		
		Date dateStart=(Date)exchange.get(TIME_START);	
		if(dateStart==null){
			return;
		}
		HttpServletResponse response=(HttpServletResponse)message.get(AbstractHTTPDestination.HTTP_RESPONSE);
		
		String loginName=exchange.get("LOGIN_USER")==null?"":(String)exchange.get("LOGIN_USER");
		String remoteIp=exchange.get("REMOTE_IP")==null?"":(String)exchange.get("REMOTE_IP");
		String requestUri=exchange.get("REQUEST_URI")==null?"":(String)exchange.get("REQUEST_URI");
		String methodname=exchange.get(RESOURCE_OPERATION_NAME)==null?"":(String)exchange.get(RESOURCE_OPERATION_NAME);
		Object serviceClass=exchange.get(RESOURCE_OBJECT_NAME);
		String classNamee=serviceClass!=null?serviceClass.getClass().getName():"";
		Long duration=new Date().getTime()-dateStart.getTime();
		StringBuilder sb=new StringBuilder();
		
		
		if(response.getStatus()!=200 && response.getStatus()!=489 &&response.getStatus()!=499 ){
			loggerLoi.error(sb.toString());
			sb.append("ERROR_ACTION");
		}else{			
			sb.append("END_ACTION");
		}
		sb.append("|");
		sb.append(APP_CODE);
		sb.append("|");
		sb.append(UDate.toLogDateFormat(dateStart));
		sb.append("|");
		sb.append(loginName);
		sb.append("|");
		sb.append(remoteIp);
		sb.append("|");
		sb.append(requestUri);
		sb.append("|");
		sb.append(methodname);
		sb.append("|");
		sb.append(classNamee);
		sb.append("|");
		sb.append(duration);
		sb.append("|");
		loggerHieunang.info(sb.toString());
		
		
		//System.out.println("dateStart: "+dateStart.toString()+", date End:"+new Date().toString());
	}

	

}
