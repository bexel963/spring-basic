<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
    <style>
        .container{
            margin: 50px auto;
            width: 900px;
            padding: 30px;
        }
		.category-title{
			width: 100%;
			height: 60px;
			background: #f7f7f7;
            border-bottom: 1px solid #eee;
            padding-top: 7px;
            margin-bottom: 20px;
		}
		.category-title .text{
			width: 1170px;
			font-size: 30px;
			color: #555555;
			margin: 0 auto;
		}
        .btn{
            float: right;
            margin-left: 10px;
        }
        .btn{
            margin: 20px 1px 20px 0;
        }
        .after::after{
			clear: both;
			content: '';
			display: block;
		}
    </style>
</head>
<body>
    <div class="container">
        <div class="category-title">
            <div class="text">게시글 등록</div>
        </div>
        <div class="body after">
            <form action="<c:url value='write'/>" method="post">	
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" value="${dto.title}">
                </div>
                <div class="form-group">
                    <label for="writer">작성자</label>
                    <input type="text" class="form-control" id="writer" name="writer" readonly value="${sessionScope.id}">
                </div>
                <div class="form-group">
                    <label for="content">내용</label>
                    <textarea style="display:none;" class="form-control"  id="content" name="content"></textarea>
                </div>
                <div id="summernote" class="content-box"></div>
                <button type="submit" class="btn btn-success">등록</button>
            </form>
            <a href="<c:url value='list'/>">
            	<button type="button" class="btn btn-success">목록</button><br><br>
            </a>
        </div>
    </div>
	<script>
        $('#summernote').summernote({
            placeholder: '내용 입력',
            tabsize: 2,
            height: 300
        });
        $('form').submit(function(){
            var content = $('#summernote').summernote('code');
            $('textarea[name=content]').val(content);
        })
    </script>
</body>
</html>