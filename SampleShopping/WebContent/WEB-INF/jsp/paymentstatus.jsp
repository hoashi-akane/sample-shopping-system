<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="java.util.*" %>
    
<!DOCTYPE html>
<html>
<head>
<% 
String message = (String)request.getAttribute("message");
%>
<meta charset="UTF-8">
<title>SampleShopping</title>
</head>
<body>
<%= message %>
</body>
</html>