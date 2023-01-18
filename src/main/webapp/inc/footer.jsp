<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!-- ##### Footer Area Start ##### -->
<footer class="footer-area">
    <div class="container">
        <div class="row d-flex flex-wrap align-items-center">
            <div class="col-12 col-md-6">
                <a href="${pageContext.request.contextPath}/Home"><img src="${pageContext.request.contextPath}/resources/img/core-img/logo.png" alt=""></a>
                <p class="copywrite-text"><a href="#"><!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. -->
	Copyright &copy;<script>document.write(new Date().getFullYear());</script> All rights reserved | This template is made with <i class="fa fa-heart-o" aria-hidden="true"></i> by <a href="https://colorlib.com" target="_blank">GDJ58</a>
	<!-- Link back to Colorlib can't be removed. Template is licensed under CC BY 3.0. --></p>
            </div>

            <div class="col-12 col-md-6">
                <div class="footer-nav">
                    <ul>
                        <li><a href="${pageContext.request.contextPath}/Home">Home</a></li>
                        <li><a href="${pageContext.request.contextPath}/GoodsList">Albums</a></li>
                        <li><a href="${pageContext.request.contextPath}/QuestionListByCustomer">CustomerCenter</a></li>
                        <li><a href="${pageContext.request.contextPath}/NoticeList">News</a></li>
                        <li><a href="${pageContext.request.contextPath}/Contact">Contact</a></li>
                        <li class="ml-2 text-secondary">
                    		live  : ${currentCnt}<br>
                    		Today : ${todayCount}<br>
                    		Total : ${totalCount}
                    	</li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</footer>
