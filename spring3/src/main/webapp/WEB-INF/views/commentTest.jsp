<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
    <h2>댓글 리스트 가져오기</h2>
    <button id="sendBtn" type="button">SEND</button>
    <h2>Data From Server :</h2>
    <div id="commentList"></div>
    <h2>댓글 등록하기</h2>
   	comment : <input type="text" name="comment">
   	<button id="regisBtn">등록</button>
   	<button id="modBtn">수정</button>
    <script>
    
    	let bno = 2332;
    	let showList = function(bno){
    		$.ajax({
                type:'GET',      
                url: '/spring3/comments?bno='+bno,  
//              dataType : 'text', 						// 전송받을 데이터의 타입, 생략하면 기본값은 json이다.
//              data : JSON.stringify(person),  		// 서버로 전송할 데이터. stringify()로 직렬화 필요. - dataType이 json이면 이거 필요 없다.
                success : function(result){
                	$('#commentList').html(toHtml(result));		// result는 서버가 전송한 데이터
                },
                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
            }); 
    	}
    
        $(document).ready(function(){
        	
           	showList(bno);
           	
            $("#sendBtn").click(function(){
            	alert('클릭');           
            });
            
            $('#regisBtn').click(function(){
            	
            	let comment = $("input[name=comment]").val();
            	
            	if(comment.trim() == ''){
            		alert('댓글을 입력해 주세요');
            		$("input[name=comment]").focus()
            		return;
            	}
            	$.ajax({
                    type:'POST',       // 요청 메서드
                    url: '/spring3/comments?bno='+bno,  // 요청 URI
                    headers : { "content-type": "application/json"}, // 요청 헤더
                    data : JSON.stringify({bno:bno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                    success : function(result){
						alert(result);
						showList(bno);
                    },
                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                }); 
            });
            
			$('#modBtn').click(function(){
            	
            	let comment = $("input[name=comment]").val();
            	let cno = $(this).attr("data-cno");
            	
            	if(comment.trim() == ''){
            		alert('댓글을 입력해 주세요');
            		$("input[name=comment]").focus()
            		return;
            	}
            	$.ajax({
                    type:'PATCH',       // 요청 메서드
                    url: '/spring3/comments/'+cno,  // 요청 URI
                    headers : { "content-type": "application/json"}, // 요청 헤더
                    data : JSON.stringify({cno:cno, comment:comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                    success : function(result){
						alert(result);
						showList(bno);
                    },
                    error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
                }); 
            });
			
//          $(".delBtn").click(function(){
			// ajax는 비동기라서 요청해 놓고 result를 받아오기 전에 다음 코드들이 실행이 된다.
			// result를 받아와야 .delBtn이 생기는건데 그 전에 jsp의 모든 코드들이 실행되는 것이다.
			// 위 코드처럼 작성하면 result의 정보로 구성된 html 코드에 있는 태그들에는 이벤트가 걸리지 않는다. 
			// 그래서 아래코드처럼 작성해야한다.
			// 아래 코드가 동적으로 생성되는 코드에 이벤트를 거는 방법이다.
			$("#commentList").on("click", ".delBtn", function(){	// ''#commentList 안에 .delBtn을 클릭하면~' 이라는 의미임
//             	alert("delBtn clicked");
				
				let cno = $(this).parent().attr("data-cno");
				
				$.ajax({
	                type:'DELETE',      
	                url: '/spring3/comments/'+cno+'?bno='+bno,  
	                success : function(result){
	                	alert(result);
	                	showList(bno);
	                },
	                error   : function(){ alert("error") } // 에러가 발생했을 때, 호출될 함수
	            }); 			
            })
            $("#commentList").on("click", ".modBtn", function(){
            	
            	let cno = $(this).parent().attr("data-cno");
            	let comment = $(this).parent().find('.comment').text();
				$("input[name=comment]").val(comment);
				$("#modBtn").attr("data-cno", cno);
				$("input[name=comment]").focus()
            })
            
        });
        
        let toHtml = function(comments){
        	let tmp = "<ul>";
        	
        	comments.forEach(function(comment){
        		tmp += '<li data-cno=' + comment.cno
        		tmp += ' data-pcno=' + comment.pcno
        		tmp += ' data-bno=' + comment.bno + '>' 
        		tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
        		tmp += ' comment=<span class="comment">' + comment.comment + '</span>'
        		tmp += ' up_date=' + comment.up_date
        		tmp += ' <button class="delBtn">삭제</button>'
        		tmp += ' <button class="modBtn">수정</button>'
        		tmp += '</li>'
        	})
        	
        	return tmp + "</ul>";
        }
        
        
        
        
        
        
        
        
        
        
        
        
    </script>
</body>
</html>