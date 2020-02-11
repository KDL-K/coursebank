<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Adding a course</title>
</head>
<body>
  <c:if test="${not empty message}">
    <p style="color:red"><c:out value="${message}" /></p>
  </c:if>
  <form:form action="add_course" modelAttribute="newCourse">
    Title: <form:input path="title"/><br/>

    <input type="submit" value="submit"/>
  </form:form>
  <br/>
<a href="${pageContext.request.contextPath}/">main</a>
  
</body>
</html>