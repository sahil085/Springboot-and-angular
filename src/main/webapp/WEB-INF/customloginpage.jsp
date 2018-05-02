<%@ taglib prefix="sec" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sahil
  Date: 27/4/18
  Time: 11:06 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

Login
<form action="/login" method="post">
    <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
        <font color="red">
            Your login attempt was not successful due to <br/><br/>
            <%--<c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}"/>.--%>
            <c:out value="Email Id Not Found"></c:out>
        </font>
    </c:if>
    <br/>
    <c:if test="${param.logout != null}">
        <div class="alert alert-success">
            <p>You have been logged out successfully.</p>
        </div>
    </c:if><br/>
   username : <input type="text" name="username"><br/>
    password : <input type="password" name="password"><br/>
    <input type="submit" value="submit">
</form>
<br/>
<hr>
Register
<form method="post" action="/register">

</form>

</body>
</html>
