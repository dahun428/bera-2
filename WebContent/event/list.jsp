<%@page import="com.br.utils.NumberUtil"%>
<%@page import="com.br.vo.Pagenation"%>
<%@page import="com.br.dto.EventDto"%>
<%@page import="java.util.List"%>
<%@page import="com.br.service.EventService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%

	int pageNo = NumberUtil.stringToInt(request.getParameter("pageNo"), 1);
	
	EventService eventService = new EventService();
	int totalRows = eventService.getProgressEventListThumbnailTypeTotalRows();
	int rowsPerPage = 10;
	int pagesPerBlock = 5;
	
	Pagenation pagenation = new Pagenation(rowsPerPage, pagesPerBlock, pageNo, totalRows);
	List<EventDto> events = eventService.getProgressEventListThumbnailType(pagenation.getBeginIndex(), pagenation.getEndIndex());

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
						<h4 class="display-4 text-center">EVENT</h4>
						<div class="title text-center">
							<h5>현재 진행중인 이벤트 입니다.</h5>
						<hr />
							<p>베스킨라빈스 회원들에게 드리는 특별한 혜택을 만나보세요</p>
						</div>
						<div class="row">
							<div class="col-12">
								<div class="row">
					<%
						if(!events.isEmpty()){
					%>
							<%
								for(EventDto event : events){
							%>
							<div class="col-3">
								<div class="card list-box">
								<!-- event image -->
									<div class="card-img">
										<a href="detail.jsp?no=<%=event.getNo()%>">
											<img style="max-height: 280px;" src="../image/EVENT/<%=event.getImagePath() %>" alt="" class="card-img-top"/>
										</a>
									</div>
										<div class="card-body">
										<!--  event title -->
											<h5 class="card-title color-pink"><%=event.getTitle() %></h5>
											<p class="card-text"><%=event.getContent() %></p>
										<!--  event date -->	
										<p class="text-info">
										<span><%=event.getStartDate() %></span>~
										<span><%=event.getEndDate() %></span>
										</p>
										</div>
								</div>
															
							</div>
							<%
								}
							%>	
						</div>
						<!-- pagenation -->
						<div>
							<ul class="pagination justify-content-center">
							<%
								if(pagenation.getPageNo() > 1){
							%>
							<li class="page-item">
								<a href="list.jsp?pageNo=<%=pagenation.getPageNo() - 1 %>" class="page-link">이전</a>
							</li>
							<%
								}
							%>
							<%
								for(int num = pagenation.getBeginPage(); num <= pagenation.getEndPage(); num++){
							%>
							<li class="page-item">
								<a href="list.jsp?pageNo=<%=num %>" class="page-link <%=num == pageNo ? "active" : "" %>"><%=num %></a>
							</li>
							<%
								}
							%>
							<%
								if(pagenation.getPageNo() < pagenation.getTotalPages()){
							%>
							<li class="page-item">
								<a href="list.jsp?pageNo=<%=pagenation.getPageNo() + 1 %>" class="page-link">다음</a>
							</li>
							<%
								}
							%>
							</ul>
							<!-- pagenation -->
						</div>
					<%
						} else {
					%>	
							<div class="row">
								<div class="col-12">
									<p class="display-4">
										현재 진행중인 이벤트가 없습니다.
									</p>
								</div>
							</div>
					<%
						}
					%>
							</div>
						</div>
					</div>
					<div class="col-12 text-right">
						<a href="eventmanager.jsp" class="btn btn-primary">관리자페이지로 이동</a>
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