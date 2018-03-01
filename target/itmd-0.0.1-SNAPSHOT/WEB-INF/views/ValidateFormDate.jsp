<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>ValidateFormData</title>
</head>
<body>
<%
String emailAddress=request.getParameter("emailAddress");
String SSN=request.getParameter("socialsecuritynumber");
String streetAdress=request.getParameter("streetAddress");
String City=request.getParameter("city");
String State=request.getParameter("state");
String zipCode=request.getParameter("zipcode");
String cardNumber=request.getParameter("customerName");

out.println("Customer Name:"+cardNumber+"\n");
out.println("SSN:"+SSN+"\n");
out.println("streetAdress:"+streetAdress+"\n");
out.println("City:"+City+"\n");
out.println("zipCode:"+zipCode+"\n"); 



%>

</body>
</html>