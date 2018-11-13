<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Error page</title>
<link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h4>${fn:escapeXml(requestScope['javax.servlet.error.message'])}</h4>
			
			<a href="logout"><h5>Nhấn vào link này để logout</h5></a>
		</div>
	</div>
</body>
</html>