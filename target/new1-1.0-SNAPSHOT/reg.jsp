<%@ page import="java.sql.*" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="javax.sql.DataSource" %>
<%
String name = request.getParameter("name");
String email= request.getParameter("email");
try
{
Class.forName("com.mysql.cj.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hello","root","ShaonlyTatini@123");
Statement stmt= con.createStatement();
int i= stmt.executeUpdate("insert into regis(name,email)values('"+name+"','"+email+"')");
if(i>0)
{
out.print("Registetred");
}
else
{
out.print("Not Registered");
}
}
catch(Exception e)
{
System.out.println(e);
}
%>
