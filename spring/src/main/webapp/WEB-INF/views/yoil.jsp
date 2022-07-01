<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<P> 모델로부터 받은 값 사용(개별 파라미터) 		 	 -> ${year}년 ${month}월 ${day}일은 ${yoil}요일 입니다.</P>
	<P> request객체에 저장된 값 사용(개별 파라미터) 	 -> ${param.year}년 ${param.month}월 ${param.day}일은 ${yoil}요일 입니다.</P>
	<p> 모델로부터 받은 값 사용(객체 파라미터) 			 -> ${myDate.year}년 ${myDate.month}월 ${myDate.day}일은 ${yoil}요일 입니다.</p> <!-- ${date.year}하면 date객체의 getter가 호출된다. -->
	<p> request객체에 저장된 값 사용(기존방식으로 출력) 	 -> year = <%=request.getParameter("year") %></p>
	<p> request객체에 저장된 값 사용(EL로 출력) 		 -> year = ${param.year}</p>
</body>
</html>
