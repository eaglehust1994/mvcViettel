package com.viettel.ktts2.schedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.Path;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.viettel.ktts2.common.UDate;

import net.shibboleth.utilities.java.support.xml.LoggingErrorHandler;


@Configuration
@Component
@EnableScheduling
@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
public class SpringScheduleExample {
	static Logger LOGGER = Logger.getLogger(SpringScheduleExample.class);
	Logger LOGGER_HIEU_NANG = Logger.getLogger("LogHieuNang");
	private SessionFactory sessionFactory;
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	protected Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	private static final String SQL_INSERT_LOG4J = "INSERT INTO log4j_KPI(action_time, class_name, method_name, user_name, ip_client_address, execute_time, application,NODE_SERVER,ACTION_TYPE)"

            + " VALUES(?, ?, ?, ?, ?, ?, ?,?,?)";
	
	private static final String SQL_CHECK_EXIST_LOG_4J="select 1 from log4j_KPI where action_time >= ? and rownum=1 and node_server =?";
	
	@Value("${log4j.filePath}")
	private String pathLog;
	@Value("${catalina.home}")
	private String catalinaHome;
	
	
	@Value("${NODE_SERVER}")
	private  String NODE_SERVER;

	
	static final String quote=Pattern.quote("|");
	
	private boolean hasInsertLog(Date date){
		Session session=currentSession();
		SQLQuery query=session.createSQLQuery(SQL_CHECK_EXIST_LOG_4J);
		query.setDate(0, date);
		query.setString(1, NODE_SERVER);
		if(query.uniqueResult()!=null){
			return true;
		}
		return false;
	}
		
	@Scheduled(cron=("${log4j.kpi.cronExpress}"))
	public void doSomething(){		
		try {
			Calendar curTime=Calendar.getInstance();
			curTime.add(Calendar.DATE, -1);
			LOGGER_HIEU_NANG.info("START_ACTION|DOC_LOGKPI");			
			//Kiem tra xem da chay trong ngay chua
			if(hasInsertLog(curTime.getTime())){
				LOGGER.warn("Da chay tien trinh quet log va insert vao database");
				return;
			}
			
			List<Log4jAnalystBo> lst=getActions(curTime.getTime());						 
			Session session=currentSession();
			SQLQuery query=session.createSQLQuery(SQL_INSERT_LOG4J);
			for(int i=0;i<lst.size();i++){
				query.setTimestamp(0, lst.get(i).actionTime);
				query.setString(1, lst.get(i).className);
				query.setString(2, lst.get(i).methodName);
				query.setString(3, lst.get(i).username);
				query.setString(4, lst.get(i).ipClientAddress);
				query.setLong(5, lst.get(i).excuteTime);
				query.setString(6, lst.get(i).application);
				query.setString(7, NODE_SERVER);
				query.setLong(8, lst.get(i).actionType);
				query.executeUpdate();
				if(i%100==0 && i>0){
					session.flush();
				}							
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Loi trong qua trinh doc log",e);
		}
		finally {
			LOGGER_HIEU_NANG.info("END_ACTION|DOC_LOGKPI");
		}
	}
	
	
	class Log4jAnalystBo {
		public Date actionTime;
		public String className;
		public String methodName;
		public String username;
		
		
		public String ipClientAddress;
		public Long excuteTime;
		public String application;
		public Long actionType;
	}
	
	private List<Log4jAnalystBo> getActions(Date logDate) throws FileNotFoundException, IOException {

       // String pathLog = ResourceBundle.getBundle("config").getString("log4j.filePath");
        //logFilePath của dữ liệu cũ
		String tomcatBase=System.getProperty(catalinaHome.trim());
        String logFilePath = tomcatBase+File.separator+"logs"+File.separator+pathLog + new SimpleDateFormat("yyyy-MM-dd").format(logDate);

        File fileLog = new File(logFilePath);
        
        List<Log4jAnalystBo> lstResurt=new ArrayList<>();
// 	Bắt đầu đọc
        if (fileLog.exists()) {

          try(BufferedReader br = new BufferedReader(new FileReader(fileLog))){

            String strLine;

            while ((strLine = br.readLine()) != null) {
                    if (!"".equals(strLine)) {
             		  try{             		   
                    	//Bat dau cat chuoi                    	                   
                    	String[] lstTemp=strLine.trim().split(quote);
                    	if(lstTemp.length<9){
                    		continue;
                    	}
                    	Log4jAnalystBo temp=new Log4jAnalystBo();
                    	if("START_ACTION".equals(lstTemp[0].trim())){
                    		temp.actionType=1l;//in
                    	}else if("END_ACTION".equals(lstTemp[0].trim())){
                    		temp.actionType=2l;
                    	}else if("ERROR_ACTION".equals(lstTemp[0].trim())){
                    		temp.actionType=3l;
                    	}else{
                    		throw new Exception("ACTION_TYPE is not valid");
                    	}
                    	if(lstTemp[1].length()>100){
                    		throw new Exception("APPLICATION vuot qua 100 ky tu");
                    	}
                    	temp.application=lstTemp[1];
                    	temp.actionTime=UDate.fromLogToDate(lstTemp[2].trim());
                    	if(lstTemp[3].length()>250){
                    		throw new Exception("USERNAME vuot qua 250 ky tu");
                    	}
                    	temp.username=lstTemp[3];
                    	if(lstTemp[4].length()>50){
                    		throw new Exception("ipClientAddress vuot qua 50 ky tu");
                    	}
                    	temp.ipClientAddress=lstTemp[4]; 
                    	if(lstTemp[6].length()>100){
                    		throw new Exception("methodName vuot qua 100 ky tu");
                    	}
                    	temp.methodName=lstTemp[6];
                    	if(lstTemp[7].length()>200){
                    		throw new Exception("className vuot qua 200 ky tu");
                    	}
                    	temp.className=lstTemp[7];
                    	
                    	
                    	temp.excuteTime=Long.parseLong(lstTemp[8].trim());
                    	
                    	lstResurt.add(temp);                    	
             		  }catch(Exception ex){
             			  LOGGER.error("Loi khi insert log kpi ban ghi:"+strLine,ex);
             		  }
                    }


            }

            //Close the input stream

           }

        } else {

            LOGGER.error("LogTimerTask: FILE LOG NOT EXISTS " + logFilePath);

        }

        return lstResurt;

    }
	
	
}
