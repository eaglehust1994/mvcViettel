package com.viettel.asset.filter;

public final class CsrfToken {
	 private final String token;
	 private final String parameterName;
	 private final String headerName;
	 
	 public CsrfToken( String headerName,String parameterName,String token){
		 this.token=token;
		 this.parameterName=parameterName;
		 this.headerName=headerName;
	 }
		public String getToken() {			
			return token;
		}
		public String getParameterName() {
			return parameterName;
		}
		public String getHeaderName() {
			return headerName;
		}
}
