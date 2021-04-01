<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<title>seq순 조회</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

</head>
<body>

<div id="container">
		<h1>seq순 조회</h1>
		
		<div class="btn-group">
			<button type="button" class="btn btn-primary" onclick="javascript:location.href='${pageContext.request.contextPath}/regdateRow.do'">
			날짜 최신순
			</button>
			 <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
      <span class="caret"></span>
    </button>
    <ul class="dropdown-menu" role="menu">
      <li><a href="${pageContext.request.contextPath}/titleRow.do">제목순</a></li>
      <li><a href="${pageContext.request.contextPath}/seqRow.do">SEQ 순</a></li>
    </ul>
  </div>
</div>
<div>
			<button type="button" onclick="javascript:location.href='${pageContext.request.contextPath}/writeView.do'">
			글 등록
			</button>
		</div>
		
		
		<table class="table table-striped">
			
			<thead><tr>
				<th>번호</th>
				<th>제목</th>
				<th>날짜</th>				
			</tr></thead>
			<tbody>
				<c:forEach items="${listsss}" var="dto">
					<tr>
						<td><a href="${pageContext.request.contextPath}/BoardDetail.do?seq=${dto.seq}"><c:out value="${dto.seq}" /></a></td>
								<td><c:out value="${dto.title}" /></td>
								
								<td><fmt:formatDate value="${dto.regdate}" pattern="yyyy-MM-dd"/></td>		
					</tr>
				</c:forEach>
			</tbody>
		</table>
		
			
		<div>
			<c:forEach begin="1" end="${pageNum}" var="num" step="1">
				<span>
				
					<a href="${pageContext.request.contextPath}/listPage?num=${num}">${num}</a>
				</span>
			
			</c:forEach>
		</div>

</body>
</html>