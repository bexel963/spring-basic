<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.fastcampus.spring.*" %>
<%
	// person은 lv(지역변수) 이다.
	// HTML 코드 안에서 lv를 EL로 출력하려면 lv가 기본객체(저장소)(pageContext, request, session, application)(scope)에 저장이 되어있어야 한다.
	_15_Person person = new _15_Person();
	request.setAttribute("person", person);
	request.setAttribute("name", "임재형");
	request.setAttribute("list", new java.util.ArrayList());
%>
<html>
<head>
	<title>EL</title>
</head>
<body>
	person.getCar().getColor() = <%=person.getCar().getColor()%> <br>
	person.getCar().getColor() = ${person.getCar().getColor()} <br>
	person.getCar().getColor() = ${person.car.color} <br>
	<br>
	name = <%=request.getAttribute("name")%> <br>
	name = ${requestScope.name} <br>
	name = ${name} <br>
	<br>
	<!-- 기본객체인 request는 지역변수이다. 지역변수를 EL로 나타낼 때는 pageContext(기본객체(저장소))를 앞에 붙여줘야한다. -->
	id=<%=request.getParameter("id")%> <br>
	id=${pageContext.request.getParameter("id")} <br>	<!-- EL은 null을 출력하지 않는다. -->
	id=${param.id} <br>
	<br>
	"1"+1 = ${"1"+1} <br>
	"1"+="1" = ${"1"+="1"} <br>
	"2">1 = ${"2">1} <br>   
	null = ${null}<br>
	null+1 = ${null+1} <br>
	null+null = ${null+null} <br>
	"" + null = ${""+null} <br>   
	""-1 = ${""-1} <br> 
	empty null=${empty null} <br>
	empty list=${empty list} <br>
	null==0 = ${null==0} <br>
	null eq 0 = ${null eq 0} <br>
	name == "남궁성"=${name=="남궁성"} <br>
	name != "남궁성"=${name!="남궁성"} <br>
	name eq "남궁성"=${name eq "남궁성"} <br>  
	name ne "남궁성"=${name ne "남궁성"} <br>  
	name.equals("남궁성")=${name.equals("남궁성")} <br> 
</body>
</html>