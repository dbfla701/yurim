<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<meta property="og:type" content="website">
	<meta property="og:title" content="[네이버: 로그인]">
	<meta property="og:description" content="안전한 로그인을 위해 주소창의 URL과 자물쇠 마크를 확인하세요!">
	<meta property="og:image" content="https://ssl.pstatic.net/sstatic/search/common/og_v3.png">
	<meta property="og:image:type" content="image/png">
	<meta property="og:image:width" content="1200">
	<meta property="og:image:height" content="1200">		
	<title>네이버 : 로그인</title>
	<link rel="stylesheet" type="text/css" href="https://nid.naver.com/login/css/global/desktop/w_20210201.css?20201006">
</head>
<body class="chrome">
<div id="wrap">
    <div id="u_skip">
        <a href="#content" id="u_skip_anchor"><span>본문으로 바로가기</span></a>
    </div>
	<!-- header -->
	<div id="header">
		<h1><a href="https://www.naver.com" class="sp h_logo" id="log.naver"><span class="blind">PeapleMarket</span></a></h1>
		<div class="lang">
			<select id="locale_switch" name="locale_switch" title="언어선택" class="sel">
				<option value="ko_KR" >한국어</option>
				<option value="en_US" >English</option>
				<option value="zh-Hans_CN" >中文(简体)</option>
				<option value="zh-Hant_TW" >中文(台灣)</option>
			</select>
		</div>
	</div>
	<div id="container">
		<!-- content -->
		<div id="content">
			<div class="title" aria-live="assertive">
				<p></p>
			</div>
				<form id="frmNIDLogin" name="frmNIDLogin" target="_top" AUTOCOMPLETE="off" action="${pageContext.request.contextPath}/listPage?num=1&row=seqRow" method="POST">
				<input type="hidden" id="localechange" name="localechange" value="">
				<input type="hidden" name="dynamicKey" id="dynamicKey" value="">
				<input type="hidden" name="encpw" id="encpw" value="">
<input type="hidden" name="enctp" id="enctp" value="1">
<input type="hidden" name="svctype" id="svctype" value="1">
<input type="hidden" name="smart_LEVEL" id="smart_LEVEL" value="1">
<input type="hidden" name="bvsd" id="bvsd" value="">
<input type="hidden" name="encnm" id="encnm" value="">
<input type="hidden" name="locale" id="locale" value="ko_KR">
<input type="hidden" name="url" id="url" value="https://www.naver.com">

				<fieldset class="login_form">
					<legend class="blind">로그인</legend>
					
					<div class="id_area">
						<div class="input_row" id="id_area">
							<span class="input_box">
								<label for="id" id="label_id_area" class="lbl">아이디</label>
								<input type="text" id="id" name="username" accesskey="L" placeholder="아이디" class="int" maxlength="41" value="">
							</span>
						</div>
						<div class="error" id="err_empty_id" style="display:none" aria-live="assertive"></div>
					</div>
					
					<div class="pw_area">
						<div class="input_row" id="pw_area">
						
							<span class="input_box">
								<label for="pw" id="label_pw_area"  class="lbl">비밀번호</label>
								<input type="password" id="pw" name="password" placeholder="비밀번호" class="int" maxlength="16">
							</span>
						</div>
					</div>

					<input type="submit" title="로그인" alt="로그인" value="로그인" class="btn_global" id="log.login">
					<div class="check_info">
						<div class="pc_check">
							<span class="ip_check">
								<a href="/login/ext/help_ip3.html" target="_blank" id="ipguide" title="" >
									<span class="ip_txt01">IP</span>
									<span class="ip_txt02">보안</span>								
								</a>
								<span class="ip_ch">
									<input type="checkbox" id="ip_on" value="off" class=""/>
									<label for="ip_on" id="label_ip_on" class="security ">OFF</label>
								</span>
							</span>
						</div>
					</div>
				</fieldset>
			</form>
			</div></div></div>
			
</body>
</html>
