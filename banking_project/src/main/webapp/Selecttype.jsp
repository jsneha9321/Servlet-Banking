<%@page import="dto.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Select Account Type</title>
</head>
<body>
<%Customer customer =(Customer)session.getAttribute("customer"); %>

<h1>hello <%=customer.getName()%></h1>
<h1>Select Type of Account</h1>
<form action="createbankaccount">
<input type="radio" name="banktype" value="saving" required="required"><b>Saving</b> <br>
<input type="radio" name="banktype" value="current"><b>Current</b><br><br>
<button type="reset">Cancel</button><button>Submit</button> 
</form>
</body>
</html>