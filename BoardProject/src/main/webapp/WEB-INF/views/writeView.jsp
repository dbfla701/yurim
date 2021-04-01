<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

 <!-- <script type="text/javascript">
      $(document).ready(function(){
         var formObj = $("form[name='writeForm']");
         $(".write_btn").on("click", function(){
            if(fn_valiChk()){
               return false;
            }
            formObj.attr("action", "/write.do");
            formObj.attr("method", "post");
            formObj.submit();
         });
      })
      function fn_valiChk(){
         var regForm = $("form[name='writeForm'] .chk").length;
         for(var i = 0; i<regForm; i++){
            if($(".chk").eq(i).val() == "" || $(".chk").eq(i).val() == null){
               alert($(".chk").eq(i).attr("title"));
               return true;
            }
         }
      }
   </script> -->
   
<body>

<div id="root">
			<header>
				<h1> 게시판</h1>
			</header>
			<hr />
			 
			<nav>
			  홈 - 글 작성
			</nav>
			<hr />
			
			<section id="container">
				<form name="writeForm" method="post" action="${pageContext.request.contextPath}/write.do" enctype="multipart/form-data">
					<table>
						<tbody>
							<tr>
								<td>
									<label for="title">제목</label><input type="text" id="title" name="title" />
								</td>
							</tr>	
							<tr>
								<td>
									<label for="content">내용</label><textarea id="content" name="content" ></textarea>
								</td>
							</tr>
							<tr>
								<td>
								<input type="file" name="file"/>
								</td>
							</tr>
								<tr>
								<td>						
									<button type="submit">작성</button>
								</td>
							</tr>
						</tbody>			
					</table>
				</form>
			</section>
			<hr />
		</div>

</body>
</html>