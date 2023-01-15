<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<!-- inclue창 -->
    <header class="header-area">
        <!-- Navbar Area -->
        <div class="oneMusic-main-menu">
            <div class="classy-nav-container breakpoint-off">
                <div class="container">
                    <!-- Menu -->
                    <nav class="classy-navbar justify-content-between" id="oneMusicNav">

                        <!-- Nav brand -->
                        <a href="index.html" class="nav-brand"><img src="${pageContext.request.contextPath}/resources/img/core-img/logo.png" alt=""></a>

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
                                    <li><a href="${pageContext.request.contextPath}/Home2">Home</a></li>
                                    <li><a href="${pageContext.request.contextPath}/GoodsList">Albums</a></li>
                                    <li><a href="#">Pages</a>
                                        <ul class="dropdown">
                                            <li><a href="${pageContext.request.contextPath}/Home2">Home</a></li>
                                            <li><a href="${pageContext.request.contextPath}/GoodsList">Albums</a></li>
                                            <li><a href="${pageContext.request.contextPath}/QuestionListByCustomer">CustomerCenter</a></li>
                                            <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>
                                            <li><a href="contact.html">Contact</a></li>                                        
                                                                                  
                                              
                                            </li>
                                        </ul>
                                    </li>
                                    <li><a href="${pageContext.request.contextPath}/QuestionListByCustomer">CustomerCenter</a></li>
                                    <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>
                                    <li><a href="contact.html">Contact</a></li>
                                </ul>

 								<!-- 고객 Menu -->
 								<c:if test="${loginCustomer != null}">                
					          		 	<ul>
					          		 	<li><a href="#">MyPage</a>
	                                        <ul class="dropdown">
	                                            <li><a href="${pageContext.request.contextPath}/orders/ordersList">Orders</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/CustomerOne">MemberInformation</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/QuestionListByCustomer">CustomerCenter</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>	                                        
	                                            <li><a href="contact.html">Contact</a></li>                                            
	                                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>                                     
	                                        </ul>
                                    	</li>
                                    	</ul>	
					          	</c:if>	
					          	
 									
 									                             
                                   <!-- 관리자 Menu -->            
					           
					           	<c:if test="${loginEmp != null}">                
					          		 	<ul>
					          		 	<li><a href="#">EmployeePage</a>
	                                        <ul class="dropdown">
	                                            <li><a href="${pageContext.request.contextPath}/orders/ordersListByAdmin">Orders</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/AddEmp">AddEmployee</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/QuestionListByAdmin">CustomerCenter</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>
	                                            <li><a href="${pageContext.request.contextPath}/review/reviewList">Reviews</a></li>
	                                            <li><a href="contact.html">Contact</a></li>                                            
	                                            <li><a href="${pageContext.request.contextPath}/Logout">Logout</a></li>                                     
	                                        </ul>
                                    	</li>
                                    	</ul>	
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
                                        <a href="${pageContext.request.contextPath}/CartList"><p><span class="icon-shopping-cart"></span><span class="quantity">1</span></p></a> 
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