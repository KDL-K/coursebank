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
        <th><!-- <a href="add_course_page">Add course</a>-->
           <c:if test="${side eq 'side_instructor'}"><form action="${pageContext.request.contextPath}/add_course_page" method="get">
              <input type="hidden" value="${instructorId}">
              <input type="submit" value="Add course">
           </form></c:if>
        </th>
    </tr>
</table>
<table>
    <tr>
        <th>title</th>
        <th>students</th>
        <c:if test="${side eq 'side_instructor'}"><th>action</th></c:if>
    </tr>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td>${course.title}</td>
            <td>
                <a href="${pageContext.request.contextPath}/course_students/${course.id}">students</a>
            </td>
            <c:if test="${side eq 'side_instructor'}"><td>
                <a href="${pageContext.request.contextPath}/course_delete/${course.id}">delete</a>
            </td></c:if>
            
        </tr>
    </c:forEach>
</table>
<br/>
<a href="${pageContext.request.contextPath}/">main</a>
</body>
</html>