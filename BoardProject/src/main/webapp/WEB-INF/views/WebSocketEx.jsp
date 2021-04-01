<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅창</title>
</head>
<script src="./js/jquery-3.5.1.js"></script>
<script type="text/javascript"
	src="<c:url value="./js/sockjs-0.3.4.js"/>"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>


<script type="text/javascript">
	var ws;
	//	var userid = '${param.id}'; //파라미터로 넘겨서 설정할 (내) 아이디

	function connect() {

		//웹소켓 객체 생성하는 부분
		//핸들러 등록(연결 생성, 메시지 수신, 연결 종료)

		//url 연결할 서버의 경로
		ws = new WebSocket('ws://localhost:8081/ws');

		ws.onopen = function() {
			console.log('연결 생성');
			//			register();
		};

		ws.onmessage = function(e) {
			console.log('메시지 받음');
			var data = e.data;
			//alert("받은 메시지 : " + data);
			addMsg(data);
		};

		ws.onclose = function() {
			console.log('연결 끊김');
		};

		var socket = new SockJS('/users');
		stompClient = Stomp.over(socket);
		stompClient.connect({}, function(frame) {
			console.log('Connected: '+ frame);
			stompClient.subscribe('/topic/users', function(response) {
				console.log(response);
				showMessage(JSON.parse(response.body));
			});
		});

	}

	function addMsg(msg) { //원래 채팅 메시지에 방금 받은 메시지 더해서 설정하기
		console.log(msg);
		var data = JSON.parse(msg);
		var chat = $('#msgArea').val();
		chat = chat + "\n" + data.id + " : " + data.msg;
		$('#msgArea').val(chat);
	}

	function sendMsg() {
		//var msg = $("#chatMsg").val();
		//ws.send(userid + " : " + msg);
		
		console.log("sending");

		data = {
			'userId' : userId,
			'targetId' : targetId,
			'chatMsg' : $("#chatMsg").val()
		};

		var name = document.getElementById("name").value;
		
		stompClient.send("/WebSocketEx", {}, JSON.stringify({name : name}));
		showMessage(data);
		$("#chatMsg").val('');
		alertClosing('successMessage', 2000);
		

		var msg = {
			id : document.querySelector("#userId").value,
			msg : $("#chatMsg").val(),
			targetId : document.querySelector("#targetId").value
		};
		ws.send(JSON.stringify(msg));
	};
	

	//페이지가 로딩되면 connect 실행
	$(function() {
		connect();
		$('#btnSend').on("click", function() {
			sendMsg();
			$("#chatMsg").val("");
		})
	});
</script>

<body>
	<h1>채팅</h1>
	<textarea rows="5" cols="30" id="msgArea">
	</textarea>
	//채팅 내용 올라갈 화면
	<br> 메시지 :
	<input type="text" id="chatMsg"> //메시지 보내는 부분
	<br> 내 아이디 :
	<input type="text" id="userId"> // 채팅 내아이디 작성 부분
	<br> 상대방 아이디 :
	<input type="text" id="targetId"> // 채팅 상대 아이디 작성 부분
	<br>
	<input type="button" value="전송" id="btnSend">
</body>
</html>
