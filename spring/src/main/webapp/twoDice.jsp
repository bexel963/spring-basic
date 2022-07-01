<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import = "java.util.Random" %>

<!-- WebServlet으로 매핑 안 해줘도 jsp파일은 자동으로 URL에 매핑된다. -->

<%-- 클래스 영역 - 서블릿클래스의 내부로 들어감(인스턴스멤버, 클래스멤버 들어가는자리)--%>
<%!
	int getRandomInt(int range){
		return new Random().nextInt(range)+1;
	}
%>

<%-- 메서드 영역 - service()의 내부로 들어감(지역멤버 들어가는 자리) --%>
<%
	int idx1 = getRandomInt(6);
	int idx2 = getRandomInt(6);
%>

<html>
<head>
	<title>towDice.jsp</title>
</head>
<body>
	<img src="resources/img/dice<%=idx1%>.jpg">
	<img src="resources/img/dice<%=idx2%>.jpg">
</body>
</html>