<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
   	
   	<!-- Title -->
	<title>Recoder Musice - World Best Music</title>
	
	<!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
</head>
<body>
	<!-- 메뉴 partial jsp 구성 -->
	<div>
		<jsp:include page="../../../inc/menu.jsp"></jsp:include>
	</div>
	
   	<!-- ##### Breadcumb Area Start ##### -->
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
        <div class="bradcumbContent">
            
            <h2>Order</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
	<h3>${loginCustomer.customerId}님의 배송지목록</h3>
	
	<!-- Trigger the modal with a button -->
<button type="button" class="btn btn-secondary btn-sm" data-toggle="modal" data-target="#myModal">배송지 선택</button>

<!-- Modal -->
<div id="myModal" class="modal fade" role="dialog">
  <div class="modal-dialog">

    <!-- Modal content-->
    <div class="modal-content">
      <div class="modal-header">
        <h4 class="modal-title">배송지 목록</h4>
      </div>
      <div class="modal-body">
        	<c:forEach var="a" items="${list}">
				<p>
					<span><input type="radio" name="addressCode" class="addressCode" value="${a.addressCode}"></span>
					${a.address}
					<span><a href="${pageContext.request.contextPath}/AddressRemoveOrder?customerId=${a.customerId}&address=${a.address}">삭제</a></span>
				</p>
			</c:forEach>
      </div>
      <div class="modal-footer">
      	<button type="button" class="btn btn-default" data-dismiss="modal">선택</button>
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>

  </div>
</div>
	
	<!-- 카카오 다음 우편번호 서비스 JS API -->
<input type="text" id="sample3_postcode" placeholder="우편번호">
<input type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기"><br>
<input type="text" id="sample3_address" placeholder="주소"><br>
<input type="text" id="sample3_detailAddress" placeholder="상세주소">
<input type="text" id="sample3_extraAddress" placeholder="참고항목">

<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 0;position:relative">
<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

    function sample3_execDaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var addr = ''; // 주소 변수
                var extraAddr = ''; // 참고항목 변수

                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                    addr = data.roadAddress;
                } else { // 사용자가 지번 주소를 선택했을 경우(J)
                    addr = data.jibunAddress;
                }

                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
                if(data.userSelectedType === 'R'){
                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있고, 공동주택일 경우 추가한다.
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                    if(extraAddr !== ''){
                        extraAddr = ' (' + extraAddr + ')';
                    }
                    // 조합된 참고항목을 해당 필드에 넣는다.
                    document.getElementById("sample3_extraAddress").value = extraAddr;
                
                } else {
                    document.getElementById("sample3_extraAddress").value = '';
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('sample3_postcode').value = data.zonecode;
                document.getElementById("sample3_address").value = addr;
                // 커서를 상세주소 필드로 이동한다.
                document.getElementById("sample3_detailAddress").focus();

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>

	<!-- 주소추가 -->
	<form action="${pageContext.request.contextPath}/AddressAddByOrder" method="post">
		<textarea rows="4" cols="40" name="address"></textarea>
		<input type="hidden" name="customerId" value="${loginCustomer.customerId}">
		<div>
			<button type="submit">배송지 추가</button>
		</div>
	</form>
	<hr>
	
	<!-- 주소목록 -->
	<h5>배송지 목록</h5>
	<form action="${pageContext.request.contextPath}/orders/ordersAdd" method="get">
		<c:forEach var="a" items="${list}">
			<p>
				<span><input type="radio" name="addressCode" class="addressCode" value="${a.addressCode}"></span>
				${a.address}
				<span><a href="${pageContext.request.contextPath}/AddressRemoveOrder?customerId=${a.customerId}&address=${a.address}">삭제</a></span>
			</p>
		</c:forEach>
		<!-- <input type="hidden" name="cartList" value="${cartList}"> -->
		<button type="submit">배송지 선택</button>
	</form>
	
	<!-- 메뉴 partial jsp 구성 -->
	<div>
		<jsp:include page="../../../inc/footer.jsp"></jsp:include>
	</div>
		
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
	
</body>
</html>