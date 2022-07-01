<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- registerForm.html과 달리 registerForm.jsp는 위 두줄이 들어가야 한다. -->

<%@ page import="java.net.URLDecoder" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <style>
        * { box-sizing:border-box; }

        form {
            width:400px;
            height:600px;
            display : flex;
            flex-direction: column;
            align-items:center;
            position : absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%) ;
            border: 1px solid rgb(89,117,196);
            border-radius: 10px;
        }

        .input-field {
            width: 300px;
            height: 40px;
            border : 1px solid rgb(89,117,196);
            border-radius:5px;
            padding: 0 10px;
            margin-bottom: 10px;
        }
        label {
            width:300px;
            height:30px;
            margin-top :4px;
        }

        button {
            background-color: rgb(89,117,196);
            color : white;
            width:300px;
            height:50px;
            font-size: 17px;
            border : none;
            border-radius: 5px;
            margin : 20px 0 30px 0;
        }

        .title {
            font-size : 50px;
            margin: 40px 0 30px 0;
        }

        .msg {
            height: 30px;
            text-align:center;
            font-size:16px;
            color:red;
            margin-bottom: 20px;
        }
        .sns-chk {
            margin-top : 5px; 
        }
    </style>
    <title>Register</title>
</head>
<body>																									
				<!-- c:rul 이 하는 역할 -->
				<!-- 1. context root 자동 추가 -->			<!-- onsubmit은 이벤트 등록, this는 자기 자신 : <form>을 전송할 때 formCheck함수가 실행 하도록 등록. -->
				<!-- 2. session id 자동 추가 -->				<!-- onsubmit이 true일때만 전송. -->
   <form action="<c:url value="/register/save0"/>" method="POST" onsubmit="return formCheck(this)">
	    <div class="title">Register</div>
	    <div id="msg" class="msg">${URLDecoder.decode(param.msg, "utf-8")}</div> 
	    <label for="">아이디</label>
	    <input class="input-field" type="text" name="id" placeholder="8~12자리의 영대소문자와 숫자 조합" autofocus>
	    <label for="">비밀번호</label>
	    <input class="input-field" type="text" name="pwd" placeholder="8~12자리의 영대소문자와 숫자 조합">
	    <label for="">이름</label>
	    <input class="input-field" type="text" name="name" placeholder="홍길동">
	    <label for="">이메일</label>
	    <input class="input-field" type="text" name="email" placeholder="example@fastcampus.co.kr"> 
	    <label for="">생일</label>
	    <input class="input-field" type="text" name="birth" placeholder="2020/12/31">
	    <label for="">취미</label>
	    <input class="input-field" type="text" name="hobby">
	    <div class="sns-chk">
	        <label><input type="checkbox" name="sns" value="facebook"/>페이스북</label>
	        <label><input type="checkbox" name="sns" value="kakaotalk"/>카카오톡</label>
	        <label><input type="checkbox" name="sns" value="instagram"/>인스타그램</label>
	    </div>
	    <button>회원 가입</button>
   </form> 
   
   <script>
       function formCheck(frm) {
            var msg ='';

            if(frm.id.value.length<3) {
                setMessage('id의 길이는 3이상이어야 합니다.', frm.id);
                return false;
            }
            if(frm.pwd.value.length<3) {
                setMessage('pwd의 길이는 3이상이어야 합니다.', frm.pwd);
                return false;
            }

           return true;
       }

       function setMessage(msg, element){
    	   	// id가 msg인 태그의 자리에 msg출력
            document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
         	// ${msg}는 js6에서 제공하는 Template Literal이다.(EL이 아님)
            // Template Literal은 브라우저에서 돌아가고 EL은 서버에서 돌아간다. (서버가 우선임)
            // 서버에서 먼저 돌아가고 그것을 브라우저에서 받아서 js가 실행이 되는것이다.
            // 그래서 js에 ${msg}가 있으면 서버가 EL로 해석하기 때문에 여기서는 msg파라미터가 없기때문에 빈 문자열이 출력된다.
            // 이를 해결하기위해 EL로 한번 더 감싸주면 된다.
            if(element) {
                element.select(); // 값이 잘못 입력됬을때 그 값이 선택되도록 하는거
            }
       }
   </script>
</body>
</html>