<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<style>
.error {color:red}
</style>
<meta charset="utf-8">
<title>Adding an instructor</title>
</head>
<body>
  <c:if test="${not empty message}">
    <p style="color:red"><c:out value="${message}" /></p>
  </c:if>
  <form:form action="add_instructor" modelAttribute="newInstructor">
    Name: <form:input path="name"/>
    <form:errors path="name" cssClass="error"/><br/>
    Surname: <form:input path="surname"/>
    <form:errors path="surname" cssClass="error"/><br/>
    Email: <form:input path="email"/>
    <form:errors path="email" cssClass="error"/><br/>
    Hobby: <form:input path="instructorDetail.hobby"/><br/>
    <input type="submit" value="submit"/>
  </form:form>
  <br/>
<a href="${pageContext.request.contextPath}/">main</a>
  
</body>
</html>