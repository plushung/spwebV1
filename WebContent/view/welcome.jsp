<%@page import="java.util.Iterator"
import="java.util.ArrayList"
import="spit.Spittle"
import="java.awt.List"
import="org.springframework.beans.factory.annotation.Autowired"
import="sqltest.sqlt"
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
<%
Spittle sp=(Spittle)session.getAttribute("spl");
%>

	<div align="center">
			<font face="华文彩云" size="14" color="blue">
			welcome to Spweb ${spituser.getId() }</font>
		</div>
		<br/>
	<div align="center">
			<span><a href="/spweb/html/secon.html">register</a></span>
			| <span><a href="/spweb/view/#">login</a></span>
			| <span><a href="/spweb/login">login</a></span>
			| <span><a href="/spweb/REST/spit.xml">REST spit <blink>123</blink></a></span>
			| <span><a href="/spweb/movie/movielist">movielist</a></span>
			| <span><a href="#">navigate</a></span>
			| <span><a href="#">navigate</a></span>
			| <span><a href="/spweb">homepage</a></span>
		</div>
		<br/>
	<div align="left">
	<c:if test="${spituser.getType()==1 }">
	<div align="center">
		<a href="/spweb/user/${spituser.getId()}/getuserlist">获取用户列表</a>
	</div>
	</c:if>
	<c:if test="${spituser!=null }">
		<ul>
			<li><c:out value="${spituser.getId()}"></c:out></li><li>
			<a href="/spweb/user/list/${spituser.getId()}/pagelist">link</a>
		</ul>
	</c:if>
	<c:if test="${pagelist!=null }">
	<a>list</a>
	<c:forEach var="lisu" items="${pagelist }">
		<ul>
			<li><c:out value="${lisu.getPageId()}"></c:out></li>
		</ul>
	</c:forEach>	
	</c:if>
	
	<c:if test="${userlist!=null }">
	<div align="center"><!-- 用户列表 -->
	<table>
	<tr><th>name</th><th>id</th><th>type</th><th>sex</th><th>qq</th></tr>
		<c:forEach var="lis" items="${userlist}">
		<tr><td><c:out value="${lis.getUsername()}"></c:out></td>
		<td><c:out value="${lis.getId()}"></c:out></td>
		<td><c:out value="${lis.getSex()}"></c:out></td>
		<td><c:out value="${lis.getSex()}"></c:out></td>
		<td><c:out value="${lis.getQQ()}"></c:out></td>
		</tr>
		</c:forEach>	
	</table>
	</div>
	</c:if>
	
	<c:if test="${movielist!=null }">
	<c:set var="n" value="1"></c:set>
	<div align="center"><!-- 电影列表 -->
	<table>
	<tr><th>movie list</th></tr>
		<c:forEach var="lis" items="${movielist}">
		<tr><td id="movie"><a href="/spweb/download/${lis}">${n}:${lis }</a></td>
		<c:set var="n" value="${n+1}"></c:set>
		</tr>
		</c:forEach>	
	</table>
	</div>
	</c:if>
<%-- 	<embed src="/spweb/download/${movielist.get(1)}" controller="true" autoplay="false">
 --%>	</div>
<style>
<!--
table{
border: double;
}
td {
	border:double;
	border-bottom:silver;
	border-left:silver;
	border-right:silver;
	width: 100px;
	
}
#movie{
	border:double;
	border-bottom:silver;
	border-left:silver;
	border-right:silver;
	width: 700px;
}
tr {
	border:double;
}

-->
</style>
</body>
</html>