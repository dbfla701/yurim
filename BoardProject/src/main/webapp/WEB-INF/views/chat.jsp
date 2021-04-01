<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Simple Chat</title>
</head>
<script src="./js/jquery-3.5.1.js"></script>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
<body>
	<div>
		<button type="button" onclick="openSocket();">대화방 참여</button>
		<button type="button" onclick="closeSocket();">대회방 나가기</button>
		<br />
		<br />
		<br /> 메세지 입력 :
		<input type="text" id="sender" value="${sessionScope.id}" style="display: none;">
		<input type="text" id="reciever" placeholder="받는사람">
		<input type="text" id="messageinput">
		
		<button type="button" onclick="javascript:send();">메세지 전송</button>
		<button type="button" onclick="javascript:clearText();">대화내용
			지우기</button>
	</div>
	<!-- Server responses get written here -->
	<div id="messages"></div>

	<!-- websocket javascript -->
	<script type="text/javascript">
		var ws;
		var messages = document.getElementById("messages");
		function openSocket() {
			if (ws !== undefined && ws.readyState !== WebSocket.CLOSED) {
				writeResponse("WebSocket is already opened.");
				return;
			}
			//웹소켓 객체 만드는 코드
			ws = new WebSocket("ws://localhost:8081/echo.do");
			ws.onopen = function(event) {
				if (event.data === undefined) {
					return;
				}
				writeResponse(event.data);
			};
			ws.onmessage = function(event) {
				console.log('writeResponse');
				console.log(event.data)
				writeResponse(event.data);
			};
			ws.onclose = function(event) {
				writeResponse("대화 종료");
			}
		}
		
		function send() {
			// var text=document.getElementById("messageinput").value+","+document.getElementById("sender").value;
			var text = document.getElementById("messageinput").value + ","
					+ document.getElementById("sender").value;


			var reciever = document.getElementById("reciever").value;
	            if (text.match("/")) {
	                if (text.match(("/" + $('#reciever').val()))) {
	                    var temp = text.replace("/" + $('#reciever').val(), "(귓속말) :").split(":");
	                    if (temp[1].trim() == "") {
		                    
	                    } else {
	                        $('#messageinput').html($('#messageinput').html() +
	    	                        "<p class='whisper'>"
	                            + reciever + text.replace("/" + $('#reciever').val(), "(귓속말) :") + "</p>");
	                    }
	                } 
	            } 
// 	            else {
// 	                if (text.match("!")) {
// 	                    $('#messageinput').html($('#messageinput').html()
// 	                        + "<p class='sendText'><b class='impress'>" + sender + " : " + text + "</b></p>");
// 	                } else {
// 	                    $('#messageinput').html($('#messageinput').html()
// 	                        + "<p class='sendText'>" + sender + " : " + text + "</p>");
// 	                }
// 	            }
				ws.send(text);
				text = "";
	            
	        }
		
		function closeSocket() {
			ws.close();
		}
		
		function writeResponse(text) {
			messages.innerHTML += "<br/>" + text;
		}
		
		function clearText() {
			console.log(messages.parentNode);
			messages.parentNode.removeChild(messages)
		}

		
	</script>
</body>
</html>

