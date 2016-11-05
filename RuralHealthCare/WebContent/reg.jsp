<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration complete</title>
</head>
<body>
<%@ page import ="java.sql.*" %>
<%@ page import ="javax.sql.*" %>
<%
String user=request.getParameter("userid"); 
session.putValue("userid",user); 
String pwd=request.getParameter("pwd"); 
String fname=request.getParameter("fname"); 
String lname=request.getParameter("lname"); 
String address=request.getParameter("address"); 
String phone=request.getParameter("phone");
Class.forName("com.mysql.jdbc.Driver"); 
java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","7417899737"); 


Statement st = con.createStatement();

int i = st.executeUpdate("insert into user(userid, password, firstname, lastname, address, phone) values ('" + user + "','" + pwd + "','" + fname + "','" + lname + "','" + address + "','" + phone + "', CURDATE())");
if (i > 0) {
    session.setAttribute("userid", user);
    response.sendRedirect("index.html");
   out.print("Registration Successfull!"+"<a href='login.jsp'>Go to Login</a>");
} else {
    response.sendRedirect("index.");
}

%>
<a href ="Login.html">Login</a><br/><br/>
<a href="index.html">Home</a>

</body>
</html>