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
				String position = "event";
			%>
			<%@ include file="/common/navi.jsp"%>
		</div>
		<div class="body">
			<div class="container">
				<div class="content-header text-center">
					<h1 class="display-4">EVENT</h1>
					<p>
						<strong>이벤트 등록 페이지 입니다.</strong>
					</p>
				</div>
				<!-- event form start -->
				<div class="custom-content-box">
					<form action="eventregister.jsp"  enctype="multipart/form-data" method="post" id="event-form">
						<table class="table">
							<thead>
								<tr>
									<td class="text-center" colspan="4">EVENT</td>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td colspan="4"><input type="text" class="form-control"
										placeholder="제목을 입력해주세요" name="title" maxlength="50" /></td>
								</tr>
								<tr>
									<td>
										<span>이벤트 시작일</span>
									</td>
									<td>
										<div class="form-group">
											<select name="startYear" id="" class="form-control">
												<option value="">연도 선택</option>
					<%
						for(int i = 2019; i <= 2050; i++){
							int number = i;
					%>						
							<option value="<%=number%>"><%=number %>년</option>
					<%
						}
					%>						
											</select>
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="startMonth" id="" class="form-control">
												<option value="">월 선택</option>
					<%
						for(int i = 1; i <= 12; i++){
							int number = i;
					%>						
							<option value="<%=number%>"><%=number %>월</option>
					<%
						}
					%>													
											</select>
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="startDay" id="" class="form-control">
												<option value="">일자 선택</option>
					<%
						for(int i = 1; i <= 31; i++){
							int number = i;
					%>						
							<option value="<%=number%>"><%=number %>일</option>
					<%
						}
					%>						
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td>
										<span>이벤트 종료일</span>
									</td>
									<td>
										<div class="form-group">
											<select name="endYear" id="" class="form-control">
												<option value="">연도 선택</option>
					<%
						for(int i = 2019; i <= 2050; i++){
							int number = i;
					%>						
							<option value="<%=number%>"><%=number %>년</option>
					<%
						}
					%>								
											</select>
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="endMonth" id="" class="form-control">
												<option value="">월 선택</option>
					<%
						for(int i = 1; i <= 12; i++){
							int number = i;
					%>						
							<option value="<%=number%>"><%=number %>월</option>
					<%
						}
					%>								
											</select>
										</div>
									</td>
									<td>
										<div class="form-group">
											<select name="endDay" id="" class="form-control">
												<option value="">일자 선택</option>
					<%
						for(int i = 1; i <= 31; i++){
							int number = i;
					%>						
							<option class="text-center" value="<%=number%>"><%=number %>일</option>
					<%
						}
					%>								
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<div>
											<input type="radio" name="event-manager" id="event-manager-event" checked="checked"/><span>이벤트 등록</span>
											<input type="radio" name="event-manager" id="event-manager-banner" /><span>배너 등록</span>
										</div>
									</td>
								</tr>
								<tr id="event-thumbnail">
									<td colspan="4">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												id="event-thumbnail-checkbox" value="thumbnail" name="thumbnail"> <label
												class="form-check-label" for="event-thumbnail-checkbox">썸네일
												이미지 추가</label>
										</div>
									</td>
								</tr>
								<tr id="event-content">
									<td colspan="4">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												id="event-content-checkbox" value="content" name="content"> <label
												class="form-check-label" for="event-content-checkbox">컨텐츠
												이미지 추가</label>
										</div>
									</td>
								</tr>
								<tr id="event-banner">
									<td colspan="4">
										<div class="form-check form-check-inline">
											<input class="form-check-input" type="checkbox"
												id="event-banner-checkbox" value="banner" name="banner" > <label
												class="form-check-label" for="event-banner-checkbox">배너
												이미지 추가</label>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4"><textarea class="form-control"
											placeholder="내용을 입력해주세요" name="content" maxlength="2048"
											style="height: 100px;" /></textarea></td>
								</tr>
							</tbody>
						</table>
						<div class="form-btn text-right">
							<button onclick="history.go(-1);" type="button"
								class="btn btn-secondary">돌아가기</button>
							<button type="submit" class="btn btn-primary">작성완료</button>
						</div>
					</form>
				</div>
			</div>
			<!-- event form end -->
		</div>
		<div class="footer">
			<%@ include file="/common/footer.jsp"%>
		</div>
	</div>
</body>
</html>