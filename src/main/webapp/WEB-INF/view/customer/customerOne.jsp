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
    <title>Recorder Music MyPage</title>
    
    <!-- Favicon -->
    <link rel="icon" href="${pageContext.request.contextPath}/resources/img/core-img/favicon.ico">

    <!-- Stylesheet -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
    <script>
    	$(document).ready(function(){
    		// 배송지 추가
    		$('#addressAddBtn').click(function(){
    			let postCode = $('#sample3_postcode');
				let searchAddress = $('#sample3_address'); // 우편번호
				console.log(searchAddress.val()); // 주소지
				let detailAddress = $('#sample3_detailAddress'); // 상세주소
				if(searchAddress.val() == '' || detailAddress.val() == '') {
					alert('주소를 입력해주세요.');
					return;
				}
				let addAddress = '('+postCode.val()+') '+searchAddress.val()+' '+detailAddress.val();
				console.log(addAddress);
				
				// 히든폼 address에 address value 기입하기
				$('input[name=address]').attr('value',addAddress);
				
				// addressAdd 컨트롤러 폼으로 보내기
				$('#addressAddForm').submit();
				
			});
    	});
    </script>
</head>
<body>
	<!-- ##### Header Area Start ##### -->
	<!-- 상단 메뉴 nav bar 구성 -->
	<jsp:include page="../../../inc/menu.jsp"></jsp:include>
	
	<!-- ##### Breadcumb Area Start ##### -->
    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
        <div class="bradcumbContent">
            <p>내 정보 조회 및 수정</p>
            <h2>My Account</h2>
        </div>
    </section>
    <!-- ##### Breadcumb Area End ##### -->
    
    <!-- ##### Login Area Start ##### -->
    <section class="login-area section-padding-100">
        <div class="container">
            <div class="row justify-content-center">
                <div class="col-12 col-lg-8">
                    <div class="login-content">
                        <h3>Customer Information</h3>
						
						<div class="mb-5">
							${customerOne.customerName}(${customerOne.customerId})님 어서오세요
						</div>
					    <div class="row">
					    	<div class="col">
					    		<div>
									보유 포인트 : ${customerOne.point}
								</div>
								<div class="mt-3">
									가입날짜 : ${customerOne.createdate}
								</div>
								
					    	</div>
					    	<div class="col text-right">
					    		<div>
									<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/CustomerModify">회원정보수정<i class="icon-settings"></i></a>
								</div>
								<div class="mt-3">
									<a class="btn btn-sm btn-outline-dark" href="${pageContext.request.contextPath}/CustomerRemove">회원탈퇴</a>
								</div>
					    	</div>
					    </div>          
						<hr>
						
						<div class="font-bold pb-3">
							주소목록
						</div>
						
						<c:forEach var="a" items="${list}">
							<div class="row">
								<div class="col-9 text-left">
									<div class="pb-3">
										${a.address}
									</div>
								</div>
									<div class="col-3 mb-3">
										<span><a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/AddressRemove?customerId=${a.customerId}&address=${a.address}">X</a></span>
									</div>
							</div>
						</c:forEach>
						<hr>
						
						<form id="addressAddForm" action="${pageContext.request.contextPath}/AddressAdd" method="post">
							<div class="font-bold pb-3">
								주소추가
							</div>
							
							<!-- 주소 3개초과로 추가할 시 문구출력 -->
							<c:if test="${addressMsg != null}">
							<span style="color:red">주소는 최대 3개까지 저장 가능합니다.</span>
							</c:if>
							
							<div>
							<!-- 카카오 다음 우편번호 서비스 JS API -->
								<div class="ml-4 text-left">
								<input class="mr-3 mb-1" type="text" id="sample3_postcode" placeholder="우편번호" readonly="readonly">
								<input class="ml-2 mb-1" type="button" onclick="sample3_execDaumPostcode()" value="우편번호 찾기"><br>
								<input class="mb-1" style="width:260px;" type="text" id="sample3_address" placeholder="주소"><br>
								<input class="mb-1" style="width:260px;" type="text" id="sample3_detailAddress" placeholder="상세주소">
								<input class="mb-1" style="width:140px;" type="text" id="sample3_extraAddress" placeholder="참고항목">
								</div>
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
								<input type="hidden" name="address" value="">
								<!-- <textarea rows="4" cols="70" name="address"></textarea> -->
							</div>
							<input type="hidden" name="customerId" value="${customerOne.customerId}">
							<div><button id="addressAddBtn" type="button" class="btn btn-sm btn-outline-dark mt-3">추가</button></div>
						</form>	
						<hr>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- ##### Login Area End ##### -->
    
	<!-- footer 하단 bar -->
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