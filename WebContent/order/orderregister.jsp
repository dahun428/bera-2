<%@page import="com.br.vo.PointUse"%>
<%@page import="com.br.vo.User"%>
<%@page import="com.br.dao.UserDao"%>
<%@page import="com.br.dao.ProductDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.br.vo.Product"%>
<%@page import="com.br.utils.NumberUtil"%>
<%@page import="com.br.vo.Order"%>
<%@page import="com.br.dao.OrderDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String[] productNo = request.getParameterValues("productNo");
	String[] amount = request.getParameterValues("product-amount");
	
	// 사용 포인트
	int usingPoint = NumberUtil.stringToInt(request.getParameter("using-point"));
	// 총 구매가격
	int payment = NumberUtil.stringToInt(request.getParameter("total-payment")) - usingPoint;
	//총 적립포인트
	int point = 0;
	// 구매한 상품번호들
	int[] productNoList = new int[productNo.length];
	for (int i = 0; i < productNoList.length; i++) {
		productNoList[i] = NumberUtil.stringToInt(productNo[i]);
	}
	// 상품별 총 구매개수
	int[] amountList = new int[amount.length];
	for (int i = 0; i < amountList.length; i++) {
		amountList[i] = NumberUtil.stringToInt(amount[i]);
	}
	
	OrderDao orderDao = new OrderDao();
	ProductDao productDao = new ProductDao();
	UserDao userDao = new UserDao();
	
	/* -- 상품 결제  -- */
	Order order = new Order();
	order.setUserId(loginedUserId);
	order.setUsingPoint(usingPoint);
	order.setPayment(payment);
	
	List<Product> products = new ArrayList<Product>();
	for(int i=0; i<productNoList.length; i++) {
		Product product = productDao.getProductByProductNo(productNoList[i]);
		product.setAmount(amountList[i]);
		products.add(product);
		
		point += product.getPoint() * amountList[i];
	}
	order.setProducts(products);
	
	orderDao.insertOrder(order);
	/* ----------- */
	
	/* -- 사용 포인트 차감 / 포인트 적립 -- */
	userDao.changePoint(loginedUserId, usingPoint, "minus");
	userDao.changePoint(loginedUserId, point, "plus");
	/* ---------------- */
	
	/* -- 포인트 사용내역 추가 -- */
	PointUse pointUse = new PointUse();
	Product product = productDao.getProductByProductNo(productNoList[0]);
	pointUse.setUserId(loginedUserId);
	pointUse.setPoint(usingPoint);
	if(productNoList.length != 1) {
		pointUse.setReason(product.getName()+ "외 " + (productNoList.length-1) + "개");
	} else {
		pointUse.setReason(product.getName());
	}
	
	userDao.addPointUse(pointUse);
	/* ------------------ */
	
	response.sendRedirect("complete.jsp");
	
%>