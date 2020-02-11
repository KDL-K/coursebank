<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<style>
table {
  font-family: arial, sans-serif;
  border-collapse: collapse;
  width: 100%;
}

th {
  border: 1px solid black;
  text-align: center;
  padding: 8px;
}
td {
  border: 1px solid black;
  text-align: left;
  padding: 8px;
}

tr:nth-child(even) {
  background-color: #dddddd;
}
</style>
</head>
<body>
<table>
    <tr>
        <th><a href="add_instructor_page">Add instructor</a></th>
    </tr>
</table>
<br/><br/>
<table>
    <tr>
        <th>name</th>
        <th>surname</th>
        <th>email</th>
        <th>courses</th>
        <th>action</th>
    </tr>
    <c:forEach var="instructor" items="${instructors}">
        <tr>
            <td>${instructor.name}</td>
            <td>${instructor.surname}</td>
            <td>${instructor.email}</td>
            <td>
                <a href="instructor_course/${side}/${instructor.id}">courses</a>
            </td>
                            
            <td>
                <a href="instructor_edit_page/${instructor.id}">edit</a>
                <c:out value=" | "/>
                <a href="instructor_delete/${instructor.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br/>
<a href="${pageContext.request.contextPath}/">main</a>
</body>
</html>