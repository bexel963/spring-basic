<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!-- core Library가 가지고 있는 tag들을  쓰려면 이 한줄 넣어줘야함. -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- fmt Library(형식화라이브러리)가 가지고 있는 tag들을 쓰려면 이 한줄 넣어줘야함. -->
<html>
<head>
	<title>JSTL</title>
</head>
<body>
	<!-- EL이 lv를 사용하려면 lv이 기본 객체저장소에 저장 되어있어햐 한다. -->
	<c:set var="to" value="10" scope="page"/> <!-- scope="page"가 생략 되어있는것이다.(pageContext 안에 저장이됨) 다른 저장소에 저장해도 됨. -->
	<c:set var="arr" value="10,20,30,40,50,60,70"/>
	<c:forEach var="i" begin="1" end="${to}">
		${i}
	</c:forEach>
	<br>
	<c:if test="${not empty arr}">									<!-- arr가 비어있지 않으면 -->
		<c:forEach var="elem" items="${arr}" varStatus="status">	<!-- varStatus="status"는 count(1부터시작)와 index(0부터시작)를 가지고 있다. -->
			${status.count}. arr[${status.index}]=${elem}<br>		<!-- 배열 arr의 요소들을 하나씩 꺼내어 elem에 저장하고 저장된 elem의 값을 출력한다. -->
		</c:forEach>
	</c:if>	
	<br>
	<c:if test="${param.msg != null}">
		msg=${param.msg}				  <!-- <p>태그 넣어서 태스트 -->
		msg=<c:out value="${param.msg}"/> <!-- c:out 은 URL에 입력한 요청에 tag가 들어있어도 tag로 해석을 안 한다.(문자로 해석함.) (script공격 방어할때 쓴다.) -->
	</c:if>					
	<br>
	<c:if test="${param.msg == null}">메시지가 없습니다.<br></c:if>
	<c:set var="age" value="${param.age}"/>
	<p>나이는 ${param.age}세 입니다.</p>
	<br>
	<!-- if/else 문 -->							
	<c:choose>
		<c:when test="${age >= 19}">성인 입니다.</c:when>
		<c:when test="${0 <= age && age < 19}">성인이 아닙니다.</c:when>
		<c:otherwise>값이 유효하지 않습니다.</c:otherwise>
	</c:choose>
	<br><br>
	<c:set var="now" value="<%=new java.util.Date()%>"/>
	Server time is <fmt:formatDate value="${now}" type="both" pattern="yyyy/MM/dd HH:mm:ss"/>
	<br>
</body>
</html>