<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>request저장소에 있는거로 출력</h1>
	<h2>id=${param.id}</h2>
	<h2>pwd=${param.pwd}</h2>
	<h2>name=${param.name}</h2>
	<h2>email=${param.email}</h2>
	<h2>birth=${param.birth}</h2>
	<h2>sns=${paramValues.sns}</h2>
	<h2>sns=${paramValues.sns[0]}</h2>
	<h2>sns=${paramValues.sns[1]}</h2>
	<h2>sns=${paramValues.sns[2]}</h2>
	<b2><br>
	<h1>모델에 있는거로 출력 - 참조형 매개변수는 모델에 자동 추가됨.</h1>
	<h2>id=${user.id}</h2>
	<h2>pwd=${user.pwd}</h2>
	<h2>name=${user.name}</h2>
	<h2>email=${user.email}</h2>
	<h2>birth=${user.birth}</h2>
	<h2>hobby=${user.hobby[0]}</h2>
	<h2>hobby=${user.hobby[1]}</h2>
	<h2>sns=${user.sns[0]}</h2>
	<h2>sns=${user.sns[1]}</h2>
	<h2>sns=${user.sns[2]}</h2>
</body>
</html>