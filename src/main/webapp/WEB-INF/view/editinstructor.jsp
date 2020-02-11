<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %> 
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"  %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Adding an instructor</title>
</head>
<body>
  <c:if test="${not empty message}">
    <p style="color:red"><c:out value="${message}" /></p>
  </c:if>
  
  <form:form action="${pageContext.request.contextPath}/instructor_edit" modelAttribute="instructor" method="POST">
          <form:hidden path="id" value="${instructor.id}"/><br/>
    Name: <form:input path="name" value="${instructor.name}"/><br/>
    Surname: <form:input path="surname" value="${instructor.surname}"/><br/>
    Email: <form:input path="email" value="${instructor.email}"/><br/>
    Hobby: <form:input path="instructorDetail.hobby" value="${instructor.instructorDetail.hobby}"/><br/>
    <input type="submit" value="submit"/>
  </form:form>
  <br/>
<a href="${pageContext.request.contextPath}/">main</a>
  
</body>
</html>