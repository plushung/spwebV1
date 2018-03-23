<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body> 
	<c:if test="${spituser!=null }">
		<ul>
			<li>${spituser.getId() }</li>
			<li>${spituser.getUsername() }</li>
			<li>${spituser.getType() }</li>
		</ul>
	</c:if>
	<sf:form action="/spweb/login.do" commandName="spittle">
	<table>
	<tr><td>name:	</td><td><sf:input path="username"/><br/></td></tr>
	<tr><td>pass:	</td><td><input type="password" name="password"><br/></td></tr>
	<tr><td>qq:		</td><td><sf:input path="QQ"/><br/></td></tr>
	<tr><td>sex:	</td><td>男<sf:radiobutton path="sex" value="man"/> 女<sf:radiobutton path="sex" value="woman"/></td></tr>
	<tr><td><input type="submit" value="login"></tr>
	</table>
	</sf:form>
</body>
</html>