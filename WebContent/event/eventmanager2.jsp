<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.br.dto.EventDto"%>
<%@page import="java.util.List"%>
<%@page import="com.br.service.EventService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	EventService eventService = new EventService();
	List<EventDto> progressEvent = eventService.getAllEventProgressThumbnailType();
	List<EventDto> endEvent = eventService.getAllEventTerminatedThumbnailType();
	List<EventDto> progressBanner = eventService.getAllEventProgressBannerType();
	List<EventDto> endBanner = eventService.getAllEventTerminatedBannerType();
	
	System.out.println("progressEvent : " + progressEvent.toString());
%>
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
				<div class="row">
					<div class="col-12 custom-box">
						<h4 class="display-4 text-center">EVENT MANAGER</h4>
						<div class="title text-center">
							<h5>이벤트 관리자 페이지 입니다.</h5>
							<hr />
						</div>
						<div class="row content-header">
							<div class="col-12">
								<div class="title">
									<h4>진행중인 이벤트</h4>
									<hr />
									<div class="row">
								<!-- progress event -->
								<%
									if(!progressEvent.isEmpty()){
										for(EventDto event : progressEvent){
											
								%>
										<div class="col-3">
											<div class="card">
												<!-- event image -->
												<div class="card-img">
													 <img
														style="max-height: 280px;"
														src=<%=StringUtils.isEmpty(event.getImagePath()) ? "../image/no_detail_img.jpg" : "../image/EVENT/"+event.getImagePath() %> alt=""
														class="card-img-top img-thumbnail" />
												</div>
												<div class="card-body">
													<!--  event title -->
													<h5 class="card-title"><%=event.getTitle() %></h5>
													<!--  event date -->
													<p>
														<span><%=event.getStartDate() %></span>~ <span><%=event.getEndDate() %></span>
													</p>
												</div>
											</div>
											<div>
												<a href="terminateregister.jsp?no=<%=event.getNo() %>" class="btn btn-secondary">종료</a>
												<a href="modifyform.jsp?no=<%=event.getNo() %>" class="btn btn-primary">수정</a>
											</div>
										</div>
								<%
										}
									}
								%>
										
									<!-- progress event -->
									</div>
								</div>
							</div>
							<div class="col-12">
								<div class="title">
									<h4>진행중인 배너</h4>
									<hr />
									<div class="row">
								<!-- progress banner -->
								<%
									if(!progressBanner.isEmpty()){
										for(EventDto event : progressBanner){
								%>
										<div class="col-3">
											<div class="card">
												<!-- event image -->
												<div class="card-img">
													 <img
														style="max-height: 280px;"
														src=<%=StringUtils.isEmpty(event.getImagePath()) ? "../image/no_detail_img.jpg" : "../image/EVENT/"+event.getImagePath() %> alt=""
														class="card-img-top img-thumbnail" />
												</div>
												<div class="card-body">
													<!--  event title -->
													<h5 class="card-title"><%=event.getTitle() %></h5>
													<!--  event date -->
													<p>
														<span><%=event.getStartDate() %></span>~ <span><%=event.getEndDate() %></span>
													</p>
												</div>
											</div>
											<div>
												<a href="terminateregister.jsp?no=<%=event.getNo() %>" class="btn btn-secondary">종료</a>
												<a href="modifyform.jsp?no=<%=event.getNo() %>" class="btn btn-primary">수정</a>
											</div>
										</div>
								<%
										}
									}
								%>
									</div>
								<!-- progress banner -->
								</div>
							</div>
							<div class="col-12">
								<div class="title">
									<h4>종료된 이벤트</h4>
									<hr />
									<div class="row">
									<!-- terminated event -->
								<%
									if(!endEvent.isEmpty()){
										for(EventDto event : endEvent){
								%>
										<div class="col-3">
											<div class="card">
												<!-- event image -->
												<div class="card-img">
													 <img
														style="max-height: 280px;"
														src=<%=StringUtils.isEmpty(event.getImagePath()) ? "../image/no_detail_img.jpg" : "../image/EVENT/"+event.getImagePath() %> alt=""
														class="card-img-top img-thumbnail" />
												</div>
												<div class="card-body">
													<!--  event title -->
													<h5 class="card-title"><%=event.getTitle() %></h5>
													<!--  event date -->
													<p>
														<span><%=event.getStartDate() %></span>~ <span><%=event.getEndDate() %></span>
													</p>
												</div>
											</div>
											<div>
												<a href="startregister.jsp?no=<%=event.getNo() %>" class="btn btn-info">시작</a>
												<a href="modifyform.jsp?no=<%=event.getNo() %>" class="btn btn-primary">수정</a>
											</div>
										</div>
								<%
										}
									}
								%>
									<!-- terminated event -->
									</div>
								</div>
							</div>
							<div class="col-12">
								<div class="title">
									<h4>종료된 배너</h4>
									<hr />
									<div class="row">
								<!-- terminated banner -->	
								<%
									if(!endBanner.isEmpty()){
										for(EventDto event : endBanner){
								%>
										<div class="col-3">
											<div class="card">
												<!-- event image -->
												<div class="card-img">
													 <img
														style="max-height: 280px;"
														src=<%=StringUtils.isEmpty(event.getImagePath()) ? "../image/no_detail_img.jpg" : "../image/EVENT/"+event.getImagePath() %> alt=""
														class="card-img-top img-thumbnail" />
												</div>
												<div class="card-body">
													<!--  event title -->
													<h5 class="card-title"><%=event.getTitle() %></h5>
													<!--  event date -->
													<p>
														<span><%=event.getStartDate() %></span>~ <span><%=event.getEndDate() %></span>
													</p>
												</div>
											</div>
											<div>
												<a href="startregister.jsp?no=<%=event.getNo() %>" class="btn btn-info">시작</a>
												<a href="modifyform.jsp?no=<%=event.getNo() %>" class="btn btn-primary">수정</a>
											</div>
										</div>
								<%
										}
									}
								%>
									<!-- terminated banner -->	
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-12 text-right">
						<a href="eventform.jsp" class="btn btn-primary">글쓰기</a>
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