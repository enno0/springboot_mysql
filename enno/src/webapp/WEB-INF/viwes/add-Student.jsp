<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
  
<%@ page import="java.util.Date"%>

<%-- <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %> --%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Students</title>
        <link
	        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	        rel="stylesheet"
            integrity3="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	        crossorigin3="anonymous">

    </head>
    <body>
    <h1>Create a student</h1>
    <form:form action="/addStudent" method="POST" modelAttribute="student">
        <table>
            <tr>
                <td>Student Number</td>
                <td><form:input path="student_id"></td>
            </tr>
            <tr>
                <td>Student Name</td>
                <td><form:input path="name"></td>
            </tr>
            <tr>
                <td>Student City</td>
                <td><form:input path="city"></td>
            </tr>
            <tr>
                <td>Student Age</td>
                <td><form:input path="age"></td>
            </tr>
            <tr>
                <td><input type="submit" value="addStudent"></td>
            </tr>

</table> 
    
    </form:form>

    </body>
</html>