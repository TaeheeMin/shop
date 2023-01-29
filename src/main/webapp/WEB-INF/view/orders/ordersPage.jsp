<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
				<script>
			$(document).ready(function() {
				// 배송지 선택
				$('#addressBtn').click(function(){
					if($('.addressCode:checked').length == 0) {
						alert('배송지를 선택하세요.');
						return;
					} 
					
					console.log($('.addressCode:checked').val());
					let checkedAddress = $('.addressCode:checked');
					console.log("선택한 주소 : "+checkedAddress.val());
					
					let addressName = "#address"+checkedAddress.val();
					console.log(addressName);
					let addressFinal = $(addressName);
					console.log(addressFinal.val());

					// 선택한 radio addressCode 대입하기
					$('input[name=addressCode]').attr('value',checkedAddress.val());
					// addressCode에 일치하는 address 대입하기
					$('input[name=address]').attr('value',addressFinal.val());
				});
				
				// 배송지 추가
				$('#addressAddBtn').click(function(){
					let postCode = $('#sample3_postcode'); // 우편번호
					let searchAddress = $('#sample3_address'); // 주소지
					console.log(searchAddress.val()); // 상세주소
					let detailAddress = $('#sample3_detailAddress');
					if(searchAddress.val() == '' || detailAddress.val() == '') {
						alert('주소를 입력해주세요.');
						return;
					}
					let addAddress = '('+postCode.val()+') '+searchAddress.val()+' '+detailAddress.val();
					console.log(addAddress);
					
					// 히든폼 addressAdd에 address value 기입하기
					$('input[name=addressAdd]').attr('value',addAddress);
					
					// addressAdd 컨트롤러 폼으로 보내기
					$('#addressAddForm').submit();
				});
				
				// 주문관련
			   let total = $('#totalPrice').val(); // 주문금액 
			   let orderPriceCount = ${fn:length(cartList)}; // 상품 개수 
			   let orderPriceList = document.querySelectorAll('.orderPrice'); // 상품별 가격
			   let cartOrderPriceList = document.querySelectorAll('.cartOrderPrice'); // 상품별 원래 가격
			   let customerPoint = $('#point').val(); // 고객 포인트
			   $('#usePoint').attr('readonly', false);
			   // 포인트 일부사용
			   $('#usePoint').blur(function() {
				   let usePoint = $('#usePoint').val(); // 사용 포인트
				   if(usePoint != 0) {
					   let sharePoint = Math.round(usePoint / orderPriceCount) // 상품별 사용 포인트
					   //console.log('나머지 : ' + usePoint % orderPriceCount);
					   console.log('sharePoint : ' + sharePoint);
					   for(let i = 0; i<orderPriceCount; i++) { // 상품 개수만큼 반복
							let orderPrice = $(orderPriceList[i]).val()-sharePoint; // 상품별 가격에 사용 포인트 차감
							//console.log(orderPrice)
						   $(orderPriceList[i]).val(orderPrice);// 가격수정
					   }
					   $('#totalPrice').attr('value', total-$('#usePoint').val()); // 주문금액 - 사용포인트 
					   $('#point').attr('value',${loginCustomer.point}-$('#usePoint').val()) // 잔여 포인트
					   $('#sharePoint').attr('value', sharePoint);
				   } else{ // 원래 값으로 변경
					   for(let i = 0; i<orderPriceCount; i++) {
							let cartOrderPrice = $(cartOrderPriceList[i]).val();
							// console.log(cartOrderPrice)
						   $(orderPriceList[i]).val(cartOrderPrice);
					   }
					   $('#totalPrice').attr('value', total);
					   $('#point').attr('value',customerPoint);
					   $('#sharePoint').attr('value', 0);
				   }
				});
			   
			   // 포인트 전액사용
			   $('#pointAll').change(function() {
				   let sharePoint = Math.floor(customerPoint / orderPriceCount) // 상품별 사용 포인트
				   if($("#pointAll").is(":checked")){ // 전액사용
					   $('#usePoint').val('');
					   $('#usePoint').attr('readonly', true); // 전액 사용시 포인트 이부 사용 불가
					   if(total-${loginCustomer.point} < 0){ // 주문 금액 < 잔여포인트
						   for(let i = 0; i<orderPriceCount; i++) { // 상품 개수만큼 반복
							   $(orderPriceList[i]).val(0);// 가격수정
						   }
						   $('#totalPrice').attr('value', 0); // 주문금액 0
						   $('#point').attr('value',${loginCustomer.point}-total) // 잔여포인트 계산
						   $('#sharePoint').attr('value', sharePoint);
					   }else{ // 주문금액 > 포인트 
						   let totalPrice = total-${loginCustomer.point};
						   $('#totalPrice').attr('value', totalPrice); // 주문금액
						   for(let i = 0; i<orderPriceCount; i++) {
							   $(orderPriceList[i]).val(Math.round(totalPrice/orderPriceCount));
						   }
						   $('#point').attr('value', 0) // 잔여포인트 0
						   $('#sharePoint').attr('value', sharePoint);
					   }
				   
			        } else { // 전액 사용 X	
			        	$('#usePoint').attr('readonly', false);
			        	for(let i = 0; i<orderPriceCount; i++) {
							let cartOrderPrice = $(cartOrderPriceList[i]).val();
							// console.log(cartOrderPrice)
						   $(orderPriceList[i]).val(cartOrderPrice);
					   }
					   $('#totalPrice').attr('value', total);
					   $('#point').attr('value',customerPoint);
					   $('#sharePoint').attr('value', 0);
			        }
				});
			   
				// 배송지 미선택으로 주문요청시 return
				$('#orderBtn').click(function(){
					if($('#myAddress').val() == ''){
						alert('배송지를 입력하세요.');
						return;
					}
					$('#orderForm').submit();
				});
				
			});
		</script>
	</head>
	<body>
		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/menu.jsp"></jsp:include>
		</div>
		
		<!-- 주소추가 최대3개가능 초과추가요청시, alert출력 -->
		<c:if test="${maxAddress != null}">
			<script>alert('주소는 최대 3개까지 저장 가능합니다.')</script>
		</c:if>
		
    	<!-- ##### Breadcumb Area Start ##### -->
	    <section class="breadcumb-area bg-img bg-overlay" style="background-image: url(${pageContext.request.contextPath}/resources/img/bg-img/breadcumb3.jpg);">
	        <div class="bradcumbContent">
	            
	            <h2>Order</h2>
	        </div>
	    </section>
	    <!-- ##### Breadcumb Area End ##### -->
	    
	    <!-- ##### Album Catagory Area Start ##### -->
	    <section class="album-catagory section-padding-100-0">
    		
    		
	        <div class="container">
	         <div class="col-12">	        
					
			<form id="orderForm" method="post" action="${pageContext.request.contextPath}/orders/ordersAdd">
			<!-- 주문할 상품 목록 -->			
			<table class = "table w-100 rounded" style="table-layout: auto; width: 100%; table-layout: fixed;">
				<tr>
					<td>앨범이름</td>
					<td>앨범</td>
					<td>가격</td>
					<td>주문수량</td>
					<td>주문가격</td>
				</tr>
				<c:forEach var="c" items="${cartList}">
					<tr>
						<td>${c.goodsTitle}</td>
						<td><img src="${pageContext.request.contextPath}/goodsimg/${c.filename}" width="200" height="200"></td>
						<td>${c.goodsPrice}</td>
						<td>${c.cartQuantity }</td>
						<td>
							${c.cartQuantity*c.goodsPrice}
							<input type="hidden" id="orderPrice" class="orderPrice" name="orderPrice" value="${c.cartQuantity*c.goodsPrice}" readonly="readonly">
							<input type="hidden" name="goodsCode" value="${c.goodsCode}">
							<input type="hidden" name="cartQuantity" value="${c.cartQuantity}" >
							<input type="hidden" id="cartOrderPrice" class="cartOrderPrice" value="${c.cartQuantity*c.goodsPrice}" >
						</td>
					</tr>
				</c:forEach>
			</table>		
					
			<!-- 고객 정보 -->
		    <section class="contact-area section-padding-0-100">
		        <div class="container">
		            <div class="row">
		                <div class="col-12">
		                    <!-- Contact Form Area -->
		                    <div class="contact-form-area">                       
		                            <div class="row">
		                                <div class="col-md-6 col-lg-3">
		                                    <div class="form-group">
		                                        <input type="text" name="customerId" value="${loginCustomer.customerId}" readonly="readonly" class="form-control">
		                                    </div>
		                                </div>
		                                <div class="col-md-6 col-lg-3">
		                                    <div class="form-group">
		                                        <input type="text" name="customerName" value="${loginCustomer.customerName}" readonly="readonly" class="form-control">
		                                    </div>
		                                </div>
		                                <div class="col-lg-3">
		                                    <div class="form-group">
		                                        <input type="text" name="customerPhone" value="${loginCustomer.customerPhone}" readonly="readonly" class="form-control">
		                                    </div>
		                                </div>
		                                 <div class="col-lg-3">
		                                    <div class="form-group">
		                                        <input type="text" name="point" id="point" value="${loginCustomer.point}" readonly="readonly" class="form-control">
		                                    </div>
		                                </div>                                     
		                                <div class="col-12">
		                                    <div class="form-group">
		                                    	<input type="hidden" name="addressCode" value="" readonly="readonly">
		                                    	<input type="text" id="myAddress" name="address" value="" readonly="readonly" class="form-control">										
		                                    </div>
		                                </div>
		                                
		                                <!-- 배송지 선택 모달-->
		                                <!-- Trigger the modal with a button -->
										<button type="button" class="ml-3 btn btn-secondary btn-sm" data-toggle="modal" data-target="#myModal">배송지 선택</button>
		                                
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
															<span>
																<input type="radio" name="addressCode" class="addressCode" value="${a.addressCode}">
																<input type="hidden" id="address${a.addressCode}" value="${a.address}">
															</span>
																${a.address}
															<span style="float:right"><a class="btn btn-sm btn-outline-danger" href="${pageContext.request.contextPath}/AddressRemoveOrder?customerId=${a.customerId}&address=${a.address}"><i class="icon-trash"></i></a></span>
														</p>
													</c:forEach>
										      </div>
										      <hr>
										      <div class="modal-header">
										        <h4 class="modal-title">배송지 추가</h4>
										      </div>
										      <!-- 주소추가 -->
											<!-- 카카오 다음 우편번호 서비스 JS API -->
											<div class="ml-4">
												<input class="mr-3 mb-1" type="text" id="sample3_postcode" placeholder="우편번호">
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
										      <div class="modal-footer">
										      	<button type="button" id="addressAddBtn" class="btn btn-default" data-dismiss="modal">추가</button>
										      	<button type="button" id="addressBtn" class="btn btn-default" data-dismiss="modal">선택</button>
										        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
										      </div>
										    </div>
										  </div>
										</div>
		                            </div>                       
		                    </div>
		                </div>
		            </div>
		        </div>
		    </section>			
			<br>
			<br>			
			<!-- 총 주문가격 -->
			<c:set var = "total" value = "0" />
			<c:forEach var="result" items="${cartList}">			
				<c:set var= "total" value="${total + result.goodsPrice*result.cartQuantity}"/>
			</c:forEach>
			<div class="col-md-6 col-lg-3">
				total price: <input type="text" name="totalPrice" id="totalPrice" value="${total}" class="form-control" readonly="readonly">
			</div>
			<div class="col-md-6 col-lg-3">
				point : <input type="text" name="usePoint" id="usePoint" class="form-control" readonly="" value="">
			</div>
			<div class="col-md-6 col-lg-3">
				<p>전액사용 <input type="checkbox" id="pointAll" name="pointAll" value=""></p>
			</div>
			<input type="hidden" id="sharePoint" name="sharePoint" value="">
			<div class="col-12 text-center wow fadeInUp" data-wow-delay="500ms">
            	<button class="btn oneMusic-btn mt-30" type="button" id="orderBtn">결제<i class="fa fa-angle-double-right"></i></button>
            </div> 	
		</form>		
		
		<!-- 주소추가 히든폼 -->
		<form id="addressAddForm" action="${pageContext.request.contextPath}/AddressAddByOrder" method="post">
			<input type="hidden" name="addressAdd" value="">
			<input type="hidden" name="customerId" value="${loginCustomer.customerId}">
		</form>
			        
		</div>
		</div>
	    </section>
	    <!-- ##### Album Catagory Area End ##### -->

		<!-- 메뉴 partial jsp 구성 -->
		<div>
			<jsp:include page="/inc/footer.jsp"></jsp:include>
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