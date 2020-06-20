<%@page import="com.br.service.EventService"%>
<%@page import="com.br.dto.EventDto"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.br.utils.NumberUtil"%>
<%@page import="java.util.Date"%>
<%@page import="com.br.utils.FileUtil"%>
<%@page import="java.util.Map"%>
<%@page import="com.br.utils.BRStatics"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
request.setCharacterEncoding("utf-8");




String directory = "C:\\Projects\\JSP\\bera-master\\WebContent\\image\\EVENT";

//파일 유틸에서 업로드 기능 실행
Map<String, String> fileMap = FileUtil.MultifileUploadExecute(request, directory);

String thumbnailImagePath = fileMap.get("fileRealNamethumbnail");
String contentImagePath = fileMap.get("fileRealNamecontent");
String bannerImagePath = fileMap.get("fileRealNamebanner");

//맵으로 변환된 파일 이름, 파라미터 이름
//getparameter 대신 fileMap 에 있는 값을 가져온다.
String title = fileMap.get("title");
String content = fileMap.get("content");
System.out.println("title : " + title);
System.out.println("content : " + content);

// 월, 일에 0 붙이기
String startYear = fileMap.get("startYear");
String startMonth = String.format("%02d",NumberUtil.stringToInt(fileMap.get("startMonth")));
String startDay = String.format("%02d",NumberUtil.stringToInt(fileMap.get("startDay")));
String endYear = fileMap.get("endYear");
String endMonth = String.format("%02d",NumberUtil.stringToInt(fileMap.get("endMonth")));
String endDay = String.format("%02d",NumberUtil.stringToInt(fileMap.get("endDay")));

//string to Date 
StringBuffer startDateBuffer = new StringBuffer();
String startDateStr = startDateBuffer.append(startYear).append(startMonth).append(startDay).toString();
SimpleDateFormat df = new SimpleDateFormat("yyyyMd");
Date startDate = df.parse(startDateStr);
System.out.println("startDate : " + startDate);


StringBuffer endDateBuffer = new StringBuffer();
String endDateStr = endDateBuffer.append(endYear).append(endMonth).append(endDay).toString();
Date endDate = df.parse(endDateStr);
System.out.println("endDate : " + endDate );

//객체에 넣기
//imagePath 값은 service 에서 다시 재정의 하여 넣는다.
EventDto event = new EventDto();
event.setTitle(title);
event.setContent(content);
event.setStartDate(startDate);
event.setEndDate(endDate);
event.setContentImagePath(contentImagePath);
event.setThumbnailImagePath(thumbnailImagePath);
event.setBannerImagePath(bannerImagePath);
boolean isBanner = false;
if(thumbnailImagePath != null || contentImagePath != null){
	isBanner = true;
}
event.setBanner(isBanner);
//DB에넣기
EventService eventService = new EventService();
eventService.insertEvent(event);

response.sendRedirect("list.jsp");




%>