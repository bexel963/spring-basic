<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="https://kit.fontawesome.com/022cf171a0.js" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <style>
        *{
            padding: 0;
            margin: 0;
        }
        ul, li{
            list-style: none;
        }
        button{
            background-color: white;
        }
        .container{
            width: 900px;
            margin: 50px auto;
            font-size: 12px;
            font-family: '돋움',Dotum,Helvetica,'Apple SD Gothic Neo',sans-serif;
        }
        .board_title{
            border-top: 2px solid #ccc;
            color: #333333;
            font-weight: bold;
            padding: 20px 5px;
        }
        .board_title .title{
            float: left;
        }
        .board_title .date{
            float: right;
        }
        .board_title::after{
            content: '';
            display: block;
            clear: both;
        }
        .board_info{
            border-top: 2px solid #ccc;
            padding: 10px 10px;
            background-color: #eee;
        }
        .board_info ul{
            display: flex;
            justify-content: flex-end;
        }
        .board_info ul li:nth-of-type(1){
            margin-right: auto;
            margin-left: 0;
            font-weight: bold;
        }
        .board_info ul li{
            margin-left: 30px;
        }
        .board_info ul span{
            margin-left: 5px;
            font-weight: bold;
        }
        .content{
            padding: 30px 20px;
            line-height: 30px;
        }
        .btn_box{
            display: flex;
            justify-content: center;
            margin-top: 60px;
        }
        .btn_box i{
            margin-right: 10px;
        }
        .btn_box button{
            margin: 0 10px;
        }
        .comment_title{
            margin-top: 40px;
            font-size: 14px;
        }
        .comment_title i{
        	margin-right: 10px;
        	color: #EE7B00;
        }
        .comment_content{
            background-color: #FFF9F2;
            margin-top: 5px;
        }
        .comment_content .writer{
            padding: 10px 0;
        }
        .comment_content span:nth-of-type(1){
            margin-right: 10px;
        }
        .comment_content .writer :nth-of-type(1){
            font-weight: bold;
        }
        .comment_content .content{
            border-bottom: 1px solid #ccc;
            margin-bottom: 20px;
        }
        .comment_write{
            display: flex;
            margin-top: 10px;
        }
        .comment_write textarea{
            flex-basis: 90%;
        }
        .comment_write button{
            flex-basis: 10%;
        }
        button{
            padding: 10px;
            margin: 0 10px;
            cursor: pointer;
            font-size: 15px;
        }
        .like{
            color: #EE7B00;
            border: 2px solid #EE7B00;
            border-radius: 0.2em;
        }
        .hate{
            color: #aaaaaa;
            border: 2px solid #aaaaaa;
            border-radius: 0.2em;
        }
        .check{
            color: #EE7B00;
            font-size: 15px;
            margin-right: 10px;
        }
        .btn_group{
            margin-top: 40px;
            display: flex;
            justify-content: center;
        }
        .btn_group button{
            border: 2px solid #ccc;
            border-radius: 0.2em;
            padding: 10px 20px;
            font-weight: bold;
        }
        .btn_group button:hover{
            border: 2px solid #EE7B00;
            color: #EE7B00;
        }
        textarea{
            padding: 10px;
        }
    </style>
</head>
<body>
	    <div class="container">
		<form action="" id="form">
	        <div class="board_title">
	        	<input type="hidden" name="bno" value="${dto.bno}">
	            <div class="title"><span><c:out value='${dto.title}'/></span></div>
	            <div class="date"><span><fmt:formatDate pattern="yyyy-MM-dd" value="${dto.reg_date}"/></span></div>
	        </div>
	        <div class="board_info">
	            <ul>
	                <li>작성자 : ${dto.writer}</li>
	                <li>url</li>
	                <li>조회</li><span>${dto.view_cnt}</span>
	                <li>추천</li><span>3</span>
	                <li>댓글</li><span>${dto.comment_cnt}</span>
	                <li>댓글보기</li>
	            </ul>
	        </div>
	        <div class="content" style="font-size: 14px;">
	            <span>
	                ${dto.content}
	            </span>
	        </div>
	        <div class="btn_box">
	            <button class="like"><i class="fas fa-thumbs-up"></i>추천<span> 1</span></button>
	            <button class="hate"><i class="fas fa-thumbs-down"></i>비추천<span> 2</span></button>
	        </div>
	        <div class="comment_title">
	            <i class="fas fa-comment"></i><span>댓글</span>
	            <span style="font-weight: bold;">${dto.comment_cnt}</span>
	        </div>
	        <c:forEach var="comment" items="${clist}">
	        <div class="comment_content">
	            <div class="writer">
	                <span>${comment.commenter}</span>
	                <span><fmt:formatDate pattern="yyyy-MM-dd" value="${comment.reg_date}"/></span>
	            </div>
	            <div class="content">
	                <span>
	                    ${comment.comment}
	                </span>
	            </div>
	        </div>
	        </c:forEach>
	        <div style="font-weight: bold;"><i class="fas fa-check check"></i><span>댓글 쓰기</span></div>
	        <div class="comment_write">
	            <textarea name="" id="" cols="30" rows="10"></textarea>
	            <button>등록</button>
	        </div>
    	</form>
    		<c:set var="idcon1" value="${sessionScope.id}"/>
    		<c:set var="idcon2" value="${dto.writer}"/>
	        <div class="btn_group">
	            <button id="writeBtn">글쓰기</button>
	            <c:if test="${sessionScope.id == dto.writer}">
		            <button id="amendBtn">수정</button>
		            <button id="removeBtn">삭제</button>
	            </c:if>
	            <button id="listBtn">목록</button>
	        </div>
	    </div>
    <script>
    	$(document).ready(function(){
    		$('#listBtn').on("click", function(){
    			
    			location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}";
    		});
			$('#writeBtn').on("click", function(){
    			
    			location.href = "<c:url value='/board/write'/>";
    		});
			$('#amendBtn').on("click", function(){
    			
    			location.href = "<c:url value='/board/amend'/>?bno=${dto.bno}";
    		});
			$('#removeBtn').on("click", function(){
    			if(!confirm("정말로 삭제하시겠습니까?")) return;
    			let form = $('form');
    			form.attr("action", "<c:url value='/board/remove'/>?page=${page}&pageSize=${pageSize}");
    			form.attr("method", "post");
    			form.submit();
    		});
    	})
    </script>
</body>
</html>