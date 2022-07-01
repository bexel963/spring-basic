<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="loginOutLink" value="${sessionScope.id==null ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${sessionScope.id==null ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/> 
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/022cf171a0.js" crossorigin="anonymous"></script>
<link href="https://fonts.googleapis.com/css2?family=Source+Sans+Pro:wght@200&display=swap" rel="stylesheet">   
<title>Insert title here</title>
	<style>    
	* { 
	    box-sizing: border-box; 
	    margin : 0;
	    padding: 0;
	}
	
	a { 
		text-decoration: none;  
		color: black;
	}
	
	.nav {
	    list-style-type: none;
	    height: 48px;
	    width: 100%;
	    background-color: #30426E;
	    display: flex;
	}
	
	.nav > li {
	    color: lightgray;
	    height : 100%;
	    width:90px;
	    display:flex;
	    align-items: center;
	}
	
	.nav > li > a {
	    color: lightgray;
	    margin:auto;
	    padding: 10px;
	    font-size:20px;
	    align-items: center;
	}
	
	.nav > li > a:hover {
	    color :white;
	    border-bottom: 3px solid rgb(209, 209, 209);
	}
	
	#logo {
		color:white;
	    font-size: 18px;
	    padding-left:40px; 
	    margin-right:auto;
	    display: flex;
	}
		.category-title{
			width: 100%;
			height: 60px;
			background: #f7f7f7;
            border-bottom: 1px solid #eee;
            padding-top: 7px;
            margin-top: 100px;
        }
        .category-title .text{
            width: 1170px;
            font-size: 30px;
            color: #555555;
            margin: 0 auto;
        }
        .title{
            color: black;
        }
        .container{
            width: 1170px;
            padding: 50px 50px;  
        }
        .table{
            text-align: center;
            font-size: 11px;
            width: 900px;
            border-bottom: solid 2px #dee2e6;
            margin: 0 auto;
        }
        .board{
            width: 900px;
            margin: 0 auto;
        }
        .btn{
            float: right;
            margin-right: 70px;
        }
        .search-box{
            padding-left: 70px;
            margin-bottom: 30px;
        }
        .page-item{
        	padding: 0 10px;
        }
        .link{
        	cursor: pointer;
        }
        .paging-active {
	      background-color: rgb(216, 216, 216);
	      border-radius: 5px;
	      color: rgb(24, 24, 24);
    	}
    </style>
</head>
<body>
	<div id="menu">
		<ul class="nav">
		    <li id="logo">fastcampus</li>
		    <li><a href="<c:url value='/'/>">Home</a></li>
		    <li><a href="<c:url value='/board/list'/>">Board</a></li>
		    <li><a href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>    
		    <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
		    <li><a href=""><i class="fas fa-search small"></i></a></li>
		</ul> 
	</div>
	<div class="category-title">
		<div class="text">게시판</div>
	</div>
	<div class="container">
		<div class="search-box"><br>
			<form class="input-group mb-3" action="<c:url value='/board/list'/>" method="get">
				<div class="input-group-append">
					<select class="form-control" name="option">       
						<option value="A" ${ph.sc.option=='A' || ph.sc.option=='' ? "selected" : "" }>제목+내용</option>
						<option value="T" ${ph.sc.option=='T' ? "selected" : ""}>제목</option>
						<option value="W" ${ph.sc.option=='W' ? "selected" : "" }>작성자</option>
					</select>
				</div>
				<input type="text" class="form-control" name="keyword" placeholder="Search" value = ${ph.sc.keyword}>
				<div class="input-group-append">
                	<button class="btn btn-success" type="submit">Go</button>  
            	</div>
        	</form>
    	</div>
    	<table class="table">
	        <thead>
		        <tr>
		            <th width="50">NO</th>
		            <th width="500">제목</th>
		            <th width="100">글쓴이</th>
		            <th width="90">날짜</th>
		            <th width="50">조회</th>
		            <th width="50">추천</th>    
		        </tr>
	        </thead>
	        <tbody>
	        <c:forEach var="board" items="${list}">
	            <tr>
	                <td>${board.bno}</td>
	                <td class="link"><a href="<c:url value="/board/detail${ph.sc.queryString}&bno=${board.bno}"/>"><c:out value='${board.title}'/></a></td>
	                <td>${board.writer}</td>
	                <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.reg_date}"/></td>
	                <td>${board.view_cnt}</td>
	                <td>5</td>
	            </tr>
            </c:forEach>
	        </tbody>
    	</table><br>
	    <ul class="pagination justify-content-center">
			<c:if test="${ph.showPrev}">
            	<a href="<c:url value="/board/list${ph.sc.getQueryString(ph.beginPage-1)}"/>"><li class="page-item">이전</li></a>
            </c:if>
            <c:forEach var="i" begin="${ph.beginPage}" end="${ph.endPage}">
            	<a class="${i==ph.sc.page ? 'paging-active' : ''}" href="<c:url value="/board/list${ph.sc.getQueryString(i)}"/>"><li class="page-item">${i}</li></a>
            </c:forEach>
            <c:if test="${ph.showNext}">
           		<a href="<c:url value="/board/list${ph.sc.getQueryString(ph.endPage+1)}"/>"><li class="page-item">다음</li></a>
           	</c:if>
	    </ul>
	    <a href="<c:url value='/board/write'/>"><button type="button" class="write-btn btn btn-success">글쓰기</button></a>
	    <input type="hidden" name="user" value="${sessionScope.id}">
	</div>
	
	<script>
		$('.write-btn').click(function(){
			var user = $('input[name=user]').val();
			if(user == ''){
				alert('로그인 후 이용하세요.');
				return true;
			}
		})
		let msg = "${msg}";	// get방식이라 모델에 있는게 파라미터로 전달이 된다.
		if(msg=="DEL_OK") alert("성공적으로 삭제되었습니다.");
		if(msg=="DEL_ERR") alert("삭제에 실패했습니다.");
		if(msg=="WRT_OK") alert("게시글이 등록되었습니다.");
		if(msg=="AMEND_OK") alert("게시글이 수정되었습니다.");
	</script>
</body>
</html>