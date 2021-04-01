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
<title>게시판</title>

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
    
<!-- <div  class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> -->
 <h1 class="page-header"></h1>
  <div class="table-responsive">
		<table class="table table-striped">
			
			<thead><tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>				
			</tr></thead>
			<tbody>
				<c:forEach items="${list}" var="dto">
					<tr>
						<td><a href="${pageContext.request.contextPath}/BoardDetail.do?seq=${dto.seq}"><c:out value="${dto.seq}" /></a></td>
								<td><c:out value="${dto.title}" /></td>
								<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd"/></td>		
					</tr>
								
				</c:forEach>
			</tbody>
		</table></div>
		
		<!-- 페이징 시작 -->
		<div>
		<c:if test="${prev}">
 <span>[ <a href="${pageContext.request.contextPath}/listPage?num=${startPageNum - 1}&row=${row}">이전</a> ]</span>
</c:if>

<c:forEach begin="${startPageNum}" end="${endPageNum}" var="num">
  <span>
  
   <c:if test="${select != num}">
   <a href="${pageContext.request.contextPath}/listPage?num=${num}&row=${row}">${num}</a> 
    </c:if>
    
    <c:if test="${select == num}">
   <b>${num}</b>
  </c:if>
 </span>
</c:forEach>

<c:if test="${next}">
 <span>[ <a href="${pageContext.request.contextPath}/listPage?num=${endPageNum + 1}&row=${row}">다음</a> ]</span>
</c:if>
			<%-- <c:forEach begin="1" end="${pageNum}" var="num" step="1">
				<span>
				
					<a href="${pageContext.request.contextPath}/listPage?num=${num}">${num}</a>
				</span>
			
			</c:forEach> --%>
		</div>
</body>
</html>

