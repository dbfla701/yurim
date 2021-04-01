<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta charset="UTF-8">
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>updateView</title>
</head>
	<script type="text/javascript">
			$(document).ready(function() {

				$(".cancel_btn").on("click", function() {
					event.preventDefault();
					location.href = "${pageContext.request.contextPath}/BoardMain.do";
				})
			})
			
			 $(document).ready(function(){
         var formObj = $("form[name='updateForm']");
         
         $(".cancel_btn").on("click", function(){
            event.preventDefault();
            location.href = "${pageContext.request.contextPath}/BoardMain.do";
         })
         
         $(".update_btn").on("click", function(){
            if(fn_valiChk()){
               return false;
            }
            formObj.attr("action", "${pageContext.request.contextPath}/update.do");
            formObj.attr("method", "post");
            formObj.submit();
         })
      })
         
      function fn_valiChk(){
         var updateForm = $("form[name='updateForm'] .chk").length;
         for(var i = 0; i<updateForm; i++){
            if($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null){
               alert($(".chk").eq(i).attr("title"));
               return true;
            }
         }
      }
		</script>
<body>

	<div id="root">
		<header>
			<h1>게시판</h1>
		</header>
	
		<hr />

		<nav>홈 - 글 작성</nav>
		<hr />

		<section id="container">
			<form name="updateForm" role="form" method="post" action="${pageContext.request.contextPath}/update.do">
				<input type="hidden" id="seq" name="seq" value="${update.seq}" readonly="readonly"/>
		
			<table>
				<tbody>
					<tr>
						<td><label for="title">순서</label>
						<input type="text" id="title" name="title" value="${update.seq}" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td><label for="title">제목</label>
						<textarea id="title" name="title"><c:out
									value="${update.title}" /></textarea></td>
					</tr>
					<tr>
						<td><label for="content">내용</label>
						<input type="text" id="content" name="content" value="${update.content}" /></td>
					</tr>
					<tr>
						<td><label for="regdate">작성날짜</label> <fmt:formatDate
								value="${update.regdate}" pattern="yyyy-MM-dd" /></td>
					</tr>
				</tbody>
			</table>
			<div>
				<button type="submit" class="update_btn">저장</button>
				<button type="submit" class="cancel_btn">취소</button>
			</div>
				</form>
		</section>
		<hr />
	</div>

</body>
</html>