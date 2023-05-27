<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="utf-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="apple-touch-icon" sizes="76x76" href="../resources/img/apple-icon.png">
  <link rel="icon" type="image/png" href="../resources/img/favicon.png">
  <title>
    대덕인재개발원 CRUD 연습
  </title>
  <!--     Fonts and icons     -->
  <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700" />
  <!-- Nucleo Icons -->
  <link href="../resources/css/nucleo-icons.css" rel="stylesheet" />
  <link href="../resources/css/nucleo-svg.css" rel="stylesheet" />
  <!-- Font Awesome Icons -->
  <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>
  <!-- Material Icons -->
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">
  <!-- CSS Files -->
  <link id="pagestyle" href="../resources/css/material-dashboard.css?v=3.0.4" rel="stylesheet" />
</head>
<body class="">
  <c:if test="${not empty message}">
  	<script type="text/javascript">
  		alert('${message }');
  	</script>
  </c:if>
  <main class="main-content  mt-0">
    <section>
      <div class="page-header min-vh-100">
        <div class="container">
          <div class="row">
            <div class="col-6 d-lg-flex d-none h-100 my-auto pe-0 position-absolute top-0 start-0 text-center justify-content-center flex-column">
              <div class="position-relative bg-gradient-info h-100 m-3 px-7 border-radius-lg d-flex flex-column justify-content-center" style="background-image: url('../resources/img/illustrations/illustration-lock.jpg'); background-size: cover;">
              </div>
            </div>
            <div class="col-xl-4 col-lg-5 col-md-7 d-flex flex-column ms-auto me-auto ms-lg-auto me-lg-5">
              <div class="card card-plain">
                <div class="card-header">
                  <h4 class="font-weight-bolder">회원가입</h4>
                  <p class="mb-0">회원등록 후, 저희 서비스와 함께해요!</p>
                </div>
                <div class="card-body">
                  <form action="/login/join"  method="post" role="form" id="joinForm">
                    <div class="input-group input-group-outline mb-3">
                      <label class="form-label"></label>
                      <input type="text" class="form-control" id="memId" name="memId" placeholder="아이디">
                    </div>
                    <div class="input-group input-group-outline mb-3">
                      <label class="form-label" id="labelPw"></label>
                      <input type="password" class="form-control" id="memPw" name="memPw" placeholder="비밀번호">
                    </div>
                    <div class="input-group input-group-outline mb-3">
                      <label class="form-label" id="labelPw2"></label>
                      <input type="password" class="form-control" id="memPw2" name="memPw2" placeholder="비밀번호 재입력">
                    </div>
					<div class="input-group input-group-outline mb-3">
                      <label class="form-label" id="labelName"></label>
                      <input type="text" class="form-control" id="memName" name="memName" placeholder="이름">
                    </div>
					<div class="form-check mb-3">
					  <input class="form-check-input" type="radio" name="memGender" id="memGender" value="M" checked>
					  <label class="custom-control-label" for="customRadio1">남자</label>
					  <input class="form-check-input" type="radio" name="memGender" id="memGender" value="F">
					  <label class="custom-control-label" for="customRadio1">여자</label>
					</div>
					<div class="input-group input-group-outline mb-3">
                      <label class="form-label" id="labelPhone"></label>
                      <input type="text" class="form-control" id="memPhone" name="memPhone" placeholder="핸드폰번호">
                    </div>
					<div class="input-group input-group-outline mb-3">
                      <label class="form-label" id="labelEmail"></label>
                      <input type="text" class="form-control" id="memEmail" name="memEmail" placeholder="이메일">
                    </div>
                    <div class="form-check form-switch">
					  <input class="form-check-input" type="checkbox" id="memAgree"  name="memAgree" value="Y">
					  <label class="form-check-label" for="flexSwitchCheckDefault">개인정보 동의</label>
					  <p class="mb-2 text-sm mx-auto">
						개인정보 동의  
						<a href="../pages/sign-in.html" class="text-primary text-gradient font-weight-bold">
						상세보기
						</a>	
					  </p>
					</div>
                    <div class="text-center">
                      <button id="joinBtn" type="button" class="btn btn-lg bg-gradient-primary btn-lg w-100 mt-4 mb-0">가입하기</button>
                    </div>
                  </form>
                </div>
                <div class="card-footer text-center pt-0 px-lg-2 px-1">
                  <p class="mb-2 text-sm mx-auto">
                    우리 서비스 회원이세요?
                    <a href="login" class="text-primary text-gradient font-weight-bold">로그인</a>
                  </p>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </main>
  <!--   Core JS Files   -->
  <script src="../resources/js/core/bootstrap.min.js"></script>
  <!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script type="text/javascript">


  $(function(){
	  var joinBtn = $("#joinBtn");
	  var memId = $("#memId");
	  var memPw = $("#memPw");
	  var memPw2 = $("#memPw2");
	  var memName = $("#memName");
	  var memGender = $("#memGender");
	  var memPhone = $("#memPhone");
	  var memEmail = $("#memEmail");
	  var memAgree = $("#memAgree");
	  
	  memId.on("keydown",function(){
		$("#labelId").html("");	
	  })
	  
	  memPw.on("keydown",function(){
		$("#labelPw").html("");		
	  })
	  
	  memPw2.on("keydown",function(){
		$("#labelPw2").html("");		
	  })
	  
	  memName.on("keydown",function(){
		$("#labelName").html("");		
	  })
	  
	  memPhone.on("keydown",function(){
		$("#labelPhone").html("");		
	  })
	  
	  memEmail.on("keydown",function(){
		$("#labelEmail").html("");		
	  })
	  
	  
	  joinBtn.on('click', function(){
		  var memId = $("#memId").val();
		  var memPw = $("#memPw").val();
		  var memPw2 = $("#memPw2").val();
		  var memName = $("#memName").val();
		  var memGender = $("#memGender").val();
		  var memPhone = $("#memPhone").val();
		  var memEmail = $("#memEmail").val();
		  var memAgree = $("#memAgree");
		  
		  if(memId.trim() == ""){
			  alert("아이디를 입력해주세요!");
			  return false;
		  }
		  
		  if(memPw.trim() == ""){
			  alert("비밀번호를 입력해주세요!");
			  return false;
		  }
		  
		  if(memPw2.trim() == ""){
			  alert("비밀번호를 한 번더 입력하세요!");
			  return false;
		  }
		  
		  if(memPw.trim() != memPw2.trim()){
			  alert("비밀번호가 일치하지 않습니다.");
			  return false;
		  }
		  
		  if(memName.trim() == ""){
			  alert("이름을 입력해주세요!");
			  return false;
		  }
		  
		  if(memPhone.trim() == ""){
			  alert("핸드폰번호를 입력해주세요!");
			  return false;
		  }
		  
		  if(memEmail.trim() == ""){
			  alert("이메일을 입력해주세요!");
			  return false;
		  }
			
		  if(memAgree.is(':checked') == false){
			  alert("개인정보 동의를 해주세요!")
			  return false;
		  }
		  

		  $("#joinForm").submit();
		  
	  })
  })
  </script>
  </body>

</html>