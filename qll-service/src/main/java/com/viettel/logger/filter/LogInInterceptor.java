package com.viettel.logger.filter;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.cxf.interceptor.AbstractLoggingInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.jaxrs.model.ClassResourceInfo;
import org.apache.cxf.jaxrs.utils.HttpUtils;
import org.apache.cxf.jaxrs.utils.JAXRSUtils;
import org.apache.cxf.message.Message;
import org.apache.cxf.phase.AbstractPhaseInterceptor;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.transport.http.AbstractHTTPDestination;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.viettel.ktts2.common.UDate;

import viettel.passport.client.UserToken;

public class LogInInterceptor extends AbstractPhaseInterceptor<Message> implements VtLogInterceptor   {
	Logger loggerHieunang=Logger.getLogger("LogHieuNang");
	@Value("${APPLICATION_CODE}")
	private static String APP_CODE;
	
	public LogInInterceptor(){
        super(Phase.PRE_PROTOCOL);

	}
	public LogInInterceptor(String phase) {
		super(phase);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void handleMessage(Message message) throws Fault {
		// TODO Auto-generated method stub		
		HttpServletRequest request2 = (HttpServletRequest)
				message.get(AbstractHTTPDestination.HTTP_REQUEST);
		String loginName="";
		String remoteIp="";
		String requestUrI="";
		if(request2!=null){
			remoteIp=request2.getRemoteAddr();
			message.getExchange().put("REMOTE_IP", remoteIp);
			UserToken token= (UserToken)request2.getSession().getAttribute("vsaUserToken");
			if(token!=null){
				loginName=token.getUserName();
				message.getExchange().put("LOGIN_USER", loginName);
			}
			requestUrI=request2.getPathInfo();
			message.getExchange().put("REQUEST_URI", requestUrI);				
		}
		Date dateStart=Calendar.getInstance().getTime();
		message.getExchange().put(TIME_START, dateStart);		
		StringBuilder sb=new StringBuilder();
		sb.append("START_ACTION");
		sb.append("|");
		sb.append(APP_CODE);
		sb.append("|");
		sb.append(UDate.toLogDateFormat(dateStart));
		sb.append("|");
		sb.append(loginName);
		sb.append("|");
		sb.append(remoteIp);
		sb.append("|");
		sb.append(requestUrI);
		sb.append("||||");		
			loggerHieunang.info(sb.toString());
		
		
	}

	

}
