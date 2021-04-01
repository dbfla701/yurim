<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<meta charset="UTF-8">
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">
<title>게시판 상세보기</title>

 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
     <link href="dashboard.css" rel="stylesheet">
    <link href="../../dist/css/bootstrap.min.css" rel="stylesheet">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <script src="../../assets/js/ie-emulation-modes-warning.js"></script>
</head>
<body>

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="#">PeapleMarket</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="#"> ${dto.username} (${dto.username})님 </a></li>
              <li class="dropdown">
                  <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">정렬순<span class="caret"></span></a>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="${pageContext.request.contextPath}/listPage?num=1&row=titleRow">제목순</a></li>
                    <li><a href="${pageContext.request.contextPath}/listPage?num=1&row=regdateRow">날짜 최신순</a></li>
                    <li><a href="${pageContext.request.contextPath}/listPage?num=1&row=seqRow">SEQ순</a></li></ul></li>
            <li><a href="#">MyPage</a></li>
            <li><a href="${pageContext.request.contextPath}/login.do">채팅하기</a></li>
            <li><a href="${pageContext.request.contextPath}/writeView.do">글등록</a></li>
          </ul>
          <form class="navbar-form navbar-right">
            <input type="text" class="form-control" placeholder="Search...">
          </form>
        </div>
      </div>
    </nav>

</head>


	<script type="text/javascript">
		$(document).ready(function(){
			var formObj = $("form[name='readForm']");
			
			// 수정 
			$(".update_btn").on("click", function(){
				formObj.attr("action", "${pageContext.request.contextPath}/updateView.do");
				formObj.attr("method", "get");
				formObj.submit();				
			})
			
			 // 삭제
         $(".delete_btn").on("click", function(){
            
            var deleteYN = confirm("삭제하시겠습니까?");
            if(deleteYN == true){
               
            formObj.attr("action", "/board/delete.do");
            formObj.attr("method", "post");
            formObj.submit();
            
            }
         })
			
			// 목록
			$(".list_btn").on("click", function(){
				
				location.href = "${pageContext.request.contextPath}/BoardMain.do";
			})
		})
	</script>


<body>
 <div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			
			<section id="container">
				<form name="readForm" role="form" method="post">
				<input type="hidden" id="seq" name="seq" value="${dto.seq}" />
				</form>
					<table>
						<tbody>
							<tr>
								<td>
									<label for="title">제목</label>  ${dto.title}
								</td>
							</tr>
							<tr>
								<td>
									<label for="content">내용</label>  ${dto.content}
								</td>
							</tr>
							<tr>
								<td>
									<label for="regdate">작성날짜</label>
									<fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd"/>					
								</td>
							</tr>
						</tbody>			
					</table>
			<hr />
			<div>
			download=${dto.download}
			<h1>파일 다운로드 테스트</h1>
			파일첨부 : <a href="download.do?id=${dto.download}">${dto.download}</a>
			</div>
			
            <div>
               <button type="submit" class="update_btn">수정</button>
					<button type="submit" class="delete_btn">삭제</button>
					<button type="submit" class="list_btn">목록</button>
            </div>
         </section>
         <hr />
      </div>
</body>
</html>

