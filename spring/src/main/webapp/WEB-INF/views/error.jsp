<%@ page contentType="text/html;charset=utf-8" isErrorPage="true"%> <!-- isErrorPage="true" 써주면 m.addAttribute(“ex”, ex); 안 해줘도 jsp의 exception 객체를 이용하여 출력할 수 있다.  -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	<!-- isErrorPage의 디폴트 상태코드는 500번이다. -->
<html>
<head>
	<title>error.jsp</title>
</head>
<body>
	<h1>예외가 발생했습니다.</h1>
	발생한 예외(모델 사용) : ${ex}<br>
	예외 메시지(모델 사용) : ${ex.message}<br>
	<ol>
		<c:forEach items="${ex.stackTrace}" var="i">
			<li>${i.toString()}</li>
		</c:forEach>
	</ol>
	<p>-----------------------------</p>
	발생한 예외(기본객체 사용-exception) : ${pageContext.exception}<br>				<!-- 기본객체를 쓸데에는 문법적으로 pageContext. 을 붙여줘야한다. -->
	예외 메시지(기본객체 사용-exception) : ${pageContext.exception.message}<br>
	<ol>
		<c:forEach items="${pageContext.exception.stackTrace}" var="i">
			<li>${i.toString()}</li>
		</c:forEach>
	</ol>
</body>
</html>