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

<body >
    <div id="my-page">
        <header id="my-header">
        ${HEADER}
	${MENU}
	</header>
            <header class="div-prifle">
                <div class="container">
                    <div id="thontin" class="col-xs-12">
                        <div id="username_left" class="col-xs-3">
                            <span id="username">Cập nhật lại mật khẩu</span>
                        </div>
                        <div id="username_center" class="col-xs-3">
                            <span class="label">Tên đăng nhập:</span> 
                            <span class="data">${tenDangNhap}</span>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <hr/>
                    </div>
                    <div id="thongtinchitiet" class="col-xs-12">
                        <div  class="col-xs-3 text-center">
                            <img class="avata-profile" src="${static_url}assets/img/avata.png"/>
                        </div>
                        <div class="col-xs-9">
                                <div class="text" >
                                    <label class="label">Mật khẩu mới:</label>
                                    <br>
                                        <input id="newPassword" class="input-style" type="password"  place="Họ và tên"></input>
                                </div>
                                <div class="text" >
                                    <label class="label">Nhập lại mật khẩu mới:</label>
                                    <br>
                                        <input id="reNewPassword" class="input-style" type="password"  place="Họ và tên"></input>
                                </div>
                        </div>
                    </div>
                </div>
                <div id="capnhat" class="container text-right">
                    <div class="col-xs-12">
                        <a class ="btn-prfile" id="btnForgetPassword" href="#">Cập nhật</a>
                    </div>
                </div>        
        </div>
    </header>
	

    ${FOOTER}
        
</div>	
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${static_url}assets/js/jquery-1.11.3.min.js"></script>

<script src="${static_url}assets/js/main.js"></script>
<script src="${static_url}assets/js/bootstrap.js"></script>
    </body>
</html>

