<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<style>
</style>
<body>
	<div class="wrapper">
		<div class="header">
			<%@ include file="/common/header.jsp"%>
		</div>
		<div class="navi">
			<%
				String position = "user";
			%>
			<%@ include file="/common/navi.jsp"%>
		</div>
		<div class="body">
		<%
			String error = request.getParameter("error");
		%>
		<%
			if("fail".equals(error)) {
				out.println("<script>alert('현재 로그인 된 정보와 일치하지 않습니다.');</script>");
			}
		%>
			<div class="container">
				<div class="content-header text-center">
					<h1 class="display-4">MY PAGE</h1>
					<p>
						<strong>회원님을 위한 베스킨라빈스의 다양한 정보</strong>
					</p>
				</div>
				<div class="custom-content-box">
					<hr />
					<%
						String myPagePosition = "deletePage";
					%>
					<!-- mypage-nav start -->
					<%@ include file="mypage-navi.jsp"%>
					<!-- mypage-nav end -->
					<!-- mypage-orderlist content start -->
					<!-- 서버에서 주문정보 불러와서 테이블 형태로 화면에 나타내기 -->
					<div class="row content-header text-center">
						<div class="col-12">
							<h2>회원탈퇴 페이지 입니다.</h2>
						</div>
					</div>
					<div class="row content-header">
						<div class="col-12">
							<h4>회원탈퇴</h4>
							<hr />
							<!-- 회원 탈퇴 폼 만들기 -->
							<div class="custom-box-wrap">
								<div class="custom-box">
									<form action="delete.jsp" method="post">
										<div class="row">
											<div class="col-8">
												<div class="form-group">
													<input type="text" name="id" class="form-control"
														placeholder="아이디를 입력하세요" />
												</div>
												<div class="form-group">
													<input type="password" name="pwd" class="form-control"
														placeholder="비밀번호를 입력하세요" />
												</div>
											</div>
											<div class="col-4">
												<button class="out-btn">탈퇴하기</button>
											</div>
										</div>
									</form>
								</div>
							</div>
							<!-- 회원 탈퇴 폼 만들기 end -->
						</div>
					</div>
				</div>
			</div>

		</div>
		<div class="footer">
			<%@ include file="/common/footer.jsp"%>
		</div>
	</div>
</body>
</html>