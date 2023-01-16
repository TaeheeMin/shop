<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="vo.*"%>
<%@ page import="service.*"%>
	<!-- inclue창 -->
    <header class="header-area">
        <!-- Navbar Area -->
        <div class="oneMusic-main-menu">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Menu -->
                    <nav class="classy-navbar justify-content-between" id="oneMusicNav">

                        <!-- Nav brand -->
                        <a href="${pageContext.request.contextPath}/Home" class="nav-brand"><img src="${pageContext.request.contextPath}/resources/img/core-img/logo.png" alt=""></a>

                        <!-- Navbar Toggler -->
                        <div class="classy-navbar-toggler">
                            <span class="navbarToggler"><span></span><span></span><span></span></span>
                        </div>

                        <!-- Menu -->
                        <div class="classy-menu">

                            <!-- Close Button -->
                            <div class="classycloseIcon">
                                <div class="cross-wrap"><span class="top"></span><span class="bottom"></span></div>
                            </div>

                            <!-- Nav Start -->
                            <div class="classynav">
                                <ul>
                                	<li>${ttlCntCart}</li>
                                    <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                                    <li><a href="${pageContext.request.contextPath}/GoodsList">Albums</a></li>
                                    <li><a href="#">Pages</a>
                                        <ul class="dropdown">
                                            <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                                            <li><a href="${pageContext.request.contextPath}/GoodsList">Albums</a></li>
                                            <li><a href="${pageContext.request.contextPath}/QuestionListByCustomer">CustomerCenter</a></li>
                                            <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>
                                            <li><a href="contact.html">Contact</a></li>                                        
                                                                                  
                                              
                                            </li>
                                        </ul>
                                    <li><a href="${pageContext.request.contextPath}/QuestionListByCustomer">CustomerCenter</a></li>
                                    <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>

 								<!-- 고객 Menu -->
 								<c:if test="${loginCustomer != null}">                
					          		 	<ul>
					          		 	<li><a href="#">MyPage</a>
	                                        <ul class="dropdown">
	                                            <li><a href="${pageContext.request.contextPath}/orders/ordersList">Order Page</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/CustomerOne">Member Information</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/QuestionListByCustomer">Customer Center</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>	                                        
	                                            <li><a href="contact.html">Contact</a></li>                                            
	                                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>                                     
	                                        </ul>
                                    	</li>
                                    	</ul>
                                    	   	<div class="login-register-cart-button d-flex align-items-center">	
											<div class="cart-btn">
			                                	<a href="${pageContext.request.contextPath}/CartList"><p><span class="icon-shopping-cart"></span><span class="quantity">${ttlCntCart}</span></p></a> 
											</div>
								          	</div>	
					          	</c:if>	
					          	
 									
 									                             
                                   <!-- 관리자 Menu -->            
					           
					           	<c:if test="${loginEmp != null}">                
					          		 	<ul>
					          		 	<li><a href="#">EmployeePage</a>
	                                        <ul class="dropdown">
	                                            <li><a href="${pageContext.request.contextPath}/orders/ordersListByAdmin">Order Page</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/AddEmp">Add Employee</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/QuestionListByAdmin">Customer Center</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/review/reviewList">Reviews</a></li>
	                                            <li><a href="contact.html">Contact</a></li>                                            
	                                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>                                     
	                                        </ul>
                                    	</li>
                                    	</ul>
                                      	<div class="login-register-cart-button d-flex align-items-center">	
										<div class="cart-btn">
		                                	<a href="${pageContext.request.contextPath}/CartList"><p><span class="icon-shopping-cart"></span><span class="quantity">M</span></p></a> 
										</div>
							          	</div> 		
					          	</c:if>
					          
					           		
					           	<c:if test="${loginEmp == null && loginCustomer == null}"> 	
					           		<!-- Login/Register & Cart Button -->
                                	<div class="login-register-cart-button d-flex align-items-center">
                                    <!-- Login/Register -->
       		  
                                    <div class="login-register-btn mr-50">
                                        <a href="${pageContext.request.contextPath}/LoginCustomer" id="loginBtn">Login / Register</a>
                                    </div>

                                    <!-- Cart Button -->
                                    <div class="cart-btn">
                                        <a href="${pageContext.request.contextPath}/CartList">
                                        	<p><span class="icon-shopping-cart"></span><span class="quantity">${cart.size()}</span></p>
                                        </a> 
                                    </div>
                                </div>
					          </c:if>
           	
           					
                            </div>
                            <!-- Nav End -->

                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>