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

<body>
    <div id="my-page">
        <header id="my-header">
        ${HEADER}
	${MENU}
	</header>
            <header id="checkProfile" class="div-prifle">
                <div class="container">
                    <div id="thontin" class="col-xs-12">
                        <div id="username_left" class="col-xs-3">
                            <span id="username">Đồng bộ tài khoản</span>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <hr/>
                    </div>
                    <div id="thongtinchitiet" class="col-xs-12">
<!--                        <div  class="col-xs-3 text-center">
                            <img class="avata-profile" src="http://127.0.0.1:3001/assets/img/avata2.jpg"/>
                        </div>-->
                        <div class="col-xs-12">
                                <div class="text" >
                                    <label class="label">Số giấy tờ:</label>
                                    <br>
                                        <input id="soGiayTo" class="input-style" type="text"  place="Số giấy tờ"></input>
                                </div>
                                <div class="text" >
                                    <label class="label">Họ và tên:</label>
                                    <br>
                                        <input id="hoVaTen" class="input-style" type="text"  place="Họ và tên"></input>
                                </div>
                                <div class="text" >
                                    <label class="label">Ngày sinh:</label>
                                    <br>
                                        <input id="ngaySinh" class="input-style" type="text"  place="Ngày sinh"></input>
                                </div>
                                <div id="div-tenDangNhap" style="display:none;" class="text">
                                    <label class="label">Tên đăng nhập:</label>
                                    <br>
                                        <input id="tenDangNhap" class="input-style" type="text" ></input>
                                </div>
                                <div id="div-email" style="display:none;" class="text">
                                    <label class="label">Email:</label>
                                    <br>
                                        <input id="email" class="input-style" type="text" ></input>
                                </div>
                                <div id="div-tenMatKhau" style="display:none;" class="text">
                                    <label class="label">Mật khẩu:</label>
                                    <br>
                                        <input id="matKhau" class="input-style" type="password" ></input>
                                </div>
                        </div>
                    </div>
                </div>
                <div id="capnhat" class="container text-right">
                    <div class="col-xs-12">
                        <a class ="btn-prfile" id="btnSyncAccount" href="#">Tiếp tục</a>
                        <a class ="btn-prfile" style="display:none;"id="btnSyncProfile" href="#">Đồng bộ</a>
                    </div>
                </div>     
                  </header>
                  
                     
        </div>
  
	

    ${FOOTER}
        
</div>	
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${static_url}assets/js/jquery-1.11.3.min.js"></script>

<script src="${static_url}assets/js/main.js"></script>
<script src="${static_url}assets/js/bootstrap.js"></script>
    </body>
</html>

