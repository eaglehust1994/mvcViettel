package com.viettel.asset.exception;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.apache.commons.httpclient.HttpStatus;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.viettel.asset.rest.AbstractRsService;
import com.viettel.ktts2.common.BusinessException;
import com.viettel.ktts2.common.ResponseMessage;

@Provider
@Component
public class BusinessExceptionMapper extends AbstractRsService implements ExceptionMapper<Exception> {

	private Logger LOGGER =Logger.getLogger("LogLoi");;
	@Override
	public Response toResponse(Exception exception) {
		Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
//		exception.printStackTrace();
		ResponseMessage rm;
		LOGGER.error(exception.getMessage(),exception);
		if (exception instanceof BusinessException){
			httpStatus = Response.Status.BAD_REQUEST;	
			rm=new ResponseMessage((BusinessException)exception);
//		ResponseMessage rm=;
//			return Response.status(Response.Status.OK).entity(new ResponseMessage((BusinessException)exception)).type(MediaType.APPLICATION_JSON).build(); 
		}else{
			rm = new ResponseMessage(httpStatus);
		}
		return Response.status(httpStatus)
                .entity(rm)
                .type(MediaType.APPLICATION_JSON).build();
	}

}
