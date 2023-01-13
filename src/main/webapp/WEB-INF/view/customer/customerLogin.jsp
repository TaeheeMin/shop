<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    
</head>
<body>
	<!-- ##### Header Area Start ##### -->
    <header class="header-area">
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="../../../inc/menu.jsp"></jsp:include>
		</div>
	</header>
	
	
    <!-- ##### Breadcumb Area Start ##### -->
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
        <div class="bradcumbContent">
            <p>See what’s new</p>
            <h2>Log in</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
    <!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Welcome Back</h3>
                        <!-- Login Form -->
                        <div class="login-form">
                            <form action="#" method="post">
                                <div class="form-group">
                                    <label for="exampleInputEmail1">Id</label>
                                    <input type="text" class="form-control" id="" aria-describedby="" placeholder="아이디를 입력하세요">
                                    <small id="" class="form-text text-muted"><i class="fa fa-lock mr-2"></i>모든 계정은 철저히 감시되고 있습니다</small>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1">Password</label>
                                    <input type="password" class="form-control" id="" placeholder="비밀번호를 입력하세요">
                                </div>
                                <button type="submit" class="btn oneMusic-btn mt-30">Login</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Login Area End ##### -->
	    <div>
			<jsp:include page="../../../inc/footer.jsp"></jsp:include>
		</div>
    <!-- ##### Footer Area Start ##### -->
    <footer class="footer-area">
    
    <!-- ##### All Javascript Script ##### -->
    <!-- jQuery-2.2.4 js -->
    <script src="${pageContext.request.contextPath}/resources/js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="${pageContext.request.contextPath}/resources/js/bootstrap/bootstrap.min.js"></script>
    <!-- All Plugins js -->
    <script src="${pageContext.request.contextPath}/resources/js/plugins/plugins.js"></script>
    <!-- Active js -->
    <script src="${pageContext.request.contextPath}/resources/js/active.js"></script>
		
    </footer>
    
	<h1>로그인</h1>
	<form action="${pageContext.request.contextPath}/LoginCustomer" method="post">
		<table>
			<tr>
				<th>아이디</th>
				<td><input type="text" name="customerId"></td>
			</tr>
			<tr>
				<th>비밀번호</th>
				<td><input type="password" name="customerPw"></td>
			</tr>
		</table>
		<div>
			<button type="submit">로그인</button>
		</div>		
	</form>
</body>
</html>