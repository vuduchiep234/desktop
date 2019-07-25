<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>

<html lang="vi">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Cổng xác thực tập trung T.P Hồ Chí Minh</title>
    <link rel="icon" href="${static_url}assets/img/favicon.png" type="image/png" sizes="32x32">
    <link href="${static_url}assets/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="${static_url}assets/css/bootstrap.css" rel="stylesheet">
  <link href="${static_url}assets/css/bootstrap-grid.css" rel="stylesheet">
  <link href="${static_url}assets/css/style.css" rel="stylesheet">
      <link href="${static_url}assets/css/responsive.css" rel="stylesheet">
  <link href="${static_url}assets/css/jquery.mmenu.all.css" rel="stylesheet">
  <script src="${static_url}assets/js/modernizr.min.js" type="text/javascript"></script>
 
</head>

<body class="body-home">
    <div id="my-page">
        <header id="my-header">
        ${HEADER}
	${MENU}
	</header>
	
		<div class="container">
			<div class="row">
			<content class="dvc-content">
				<div class="dvc-textgt">
					<p class="title-gt">Giới Thiệu </p>
				<p class="gioithieu">Giải pháp toàn diện về tiện ích, bảo mật và quản lý thông tin người dùng tập trung ... </p>
				</div>
				</content>
			</div>
		</div>
                <div class="carousel-container">
  <div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
      <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner">
      <div class="item active">
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="#" class="thumbnail icon-slide dangky-icon" title="Đăng ký tài khoản trên hệ thống" aria-label="Đăng ký tài khoản trên hệ thống" data-toggle="modal" data-target=""></a>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="#" class="thumbnail icon-slide dangnhap-icon" title="Đăng nhập hệ thống xác thực tập trung" aria-label="Đăng nhập hệ thống xác thực tập trung" data-toggle="modal" data-target=""></a>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="/syncAccount" class="thumbnail icon-slide dongbotaikhoan-icon" title="" aria-label="Đồng bộ tài khoản về cổng đăng nhập dùng chung." ></a>
        </div>
        
      </div>

      <div class="item">
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="/profile" class="thumbnail icon-slide hosocuatoi-icon" title="" aria-label="Hồ sơ cá nhân." ></a>
        </div>
         <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="/updateProfile" class="thumbnail icon-slide capnhat-hoso-icon" title="" aria-label="Cập nhật thông tin cá nhân"></a>
        </div>
        <div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
            <a href="#" class="thumbnail icon-slide quenmatkhau-icon" title="" aria-label="Quên mật khẩu?" data-toggle="modal" data-target="#myModalHorizontal"></a>
        </div>
      </div>
    
      
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left"></span>
      <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right"></span>
      <span class="sr-only">Next</span>
    </a>
  </div>
</div>

	${FOOTER}
        <div class="cd-user-modal"> <!-- this is the entire modal form, including the background -->
		<div class="cd-user-modal-container"> <!-- this is the container wrapper -->
			<ul class="cd-switcher">
				<li><a href="#0">Đăng nhập</a></li>
				<li><a href="#0">Đăng ký</a></li>
			</ul>

			<div id="cd-login"> <!-- log in form -->
				<form  class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-email" for="signin-username">Tên đăng nhập</label>
						<input id="signin-username" class="full-width has-padding has-border"  type="text" placeholder="Tên đăng nhập">
						<span class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-password" for="signin-password">Mật khẩu</label>
						<input class="full-width has-padding has-border" id="signin-password" type="password"  placeholder="Mật khẩu">
						<a href="#0" class="hide-password">Hiện</a>
						<span class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input type="checkbox" id="remember-me" checked>
						<label for="remember-me">Ghi nhớ</label>
					</p>

					<p class="fieldset">
						<input id="btn-login" class="full-width" type="submit" value="Đăng nhập">
					</p>
				</form>
				
				<p class="cd-form-bottom-message"><a href="#0">Quên mật khẩu?</a></p>
				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div> <!-- cd-login -->

			<div id="cd-signup"> <!-- sign up form -->
				<form class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-username" for="signup-username">Tên đăng nhập</label>
						<input class="full-width has-padding has-border" id="signup-username" type="text" placeholder="Tên đăng nhập">
						<span class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<label class="image-replace cd-email" for="signup-email">E-mail</label>
						<input class="full-width has-padding has-border" id="signup-email" type="email" placeholder="E-mail">
						<span class="cd-error-message">Error message here!</span>
					</p>
                                        
                                        <p class="fieldset">
						<label class="image-replace cd-email" for="signup-email">Số điện thoại</label>
						<input class="full-width has-padding has-border" id="signup-phone" type="number" placeholder="Số điện thoại">
						<span class="cd-error-message">Error message here!</span>
					</p>
                                        
					<p class="fieldset">
						<label class="image-replace cd-password" for="signup-password">Mật khẩu</label>
						<input class="full-width has-padding has-border" id="signup-password" type="text"  placeholder="Mật khẩu">
						<a href="#0" class="hide-password">Ẩn</a>
						<span class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input type="checkbox" id="accept-terms">
						<label for="accept-terms">Tôi đồng ý với <a href="#0">Điều khoản</a></label>
					</p>

					<p class="fieldset">
						<input id="btn-singup" class="full-width has-padding" type="submit" value="Tạo tài khoản">
					</p>
				</form>

				<!-- <a href="#0" class="cd-close-form">Close</a> -->
			</div> <!-- cd-signup -->

			<div id="cd-reset-password"> <!-- reset password form -->
				<p class="cd-form-message" style="    font-size: 18px;">Quên mật khẩu? Hãy điền địa chỉ email của bạn. Bạn sẽ nhận được một liên kết để tạo một mật khẩu mới.</p>

				<form class="cd-form">
					<p class="fieldset">
						<label class="image-replace cd-email" for="reset-email">E-mail</label>
						<input class="full-width has-padding has-border" id="reset-email" type="email" placeholder="E-mail">
						<span class="cd-error-message">Error message here!</span>
					</p>

					<p class="fieldset">
						<input class="full-width has-padding" type="submit" value="Reset password">
					</p>
				</form>

				<p class="cd-form-bottom-message"><a href="#0">Quay lại đăng nhập</a></p>
			</div> <!-- cd-reset-password -->
			<a href="#0" class="cd-close-form">Đóng</a>
		</div> <!-- cd-user-modal-container -->
	</div> <!-- cd-user-modal -->
        
</div>	
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${static_url}assets/js/jquery-1.11.3.min.js"></script>
 <script src="${static_url}assets/js/jquery.mmenu.all.min.js" type="text/javascript"></script>
<script src="${static_url}assets/js/main.js"></script>
<script src="${static_url}assets/js/bootstrap.js"></script>
</body>
</html>

