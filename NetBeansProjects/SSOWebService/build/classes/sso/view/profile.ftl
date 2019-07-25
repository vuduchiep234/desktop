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
            <header class="div-prifle">
                <div class="container">
                    <div id="thontin" class="col-xs-12">
                        <div id="username_left" class="col-xs-3">
                            <span id="username">Thông tin cá nhân</span>
                        </div>
                        <div id="username_center" class="col-xs-3">
                            <span class="label">Tên đăng nhập:</span> 
                            <span class="data">${tenDangNhap}</span>
                        </div>
                        <div id="username_right" class="col-xs-3">
                            <a class="data" href="/changpassword">Đổi mật khẩu</a>
                        </div>
                    </div>
                    <div class="col-xs-12">
                        <hr/>
                    </div>
                    <div id="thongtinchitiet" class="col-xs-12">
                        <div  class="col-xs-3 text-center">
                            <img class="avata-profile" src="${static_url}assets/img/avata.png"/>
                        </div>
                        <div class="col-xs-3">
                            <div class="text" id="hovaten">
                                <label class="label">Họ và tên</label>
                                <br>
                                    <span class="data">${hoVaTen}</span>
                            </div>

                            <div class="text" id="gioitinh">
                                <label class="label">Giới tính</label>
                                <br>
                                    <span class="data">${gioiTinh}</span>
                            </div>
                            <div class="text" id="thudientu">
                                <label class="label">Thư điện tử</label>
                                <br>
                                    <span class="data">${email}</span>
                            </div>
                            <div class="text" id="hokhauthuongtru">
                                <label class="label">Hộ khẩu thường trú</label>
                                <br>
                                    <span class="data">${hoKhauThuongTru}</span>
                            </div>
                        </div>

                        <div  class="col-xs-3">
                            <div class="text" id="ngaysinh">
                                <label class="label">Sinh ngày</label>
                                <br>
                                    <span class="data">${ngaySinh}</span>
                            </div>

                            <div class="text" id="quoctich">
                                <label class="label">Quốc tịch</label>
                                <br>
                                    <span class="data">${quocTich}</span>
                            </div>
                            <div class="text" id="sodienthoai">
                                <label class="label">Số điện thoại di động</label>
                                <br>
                                    <span class="data">${soDienThoai}</span>
                            </div>
                        </div>

                        <div  class="col-xs-3">
                            <div class="text" id="sogiaytotuythan">
                                <label class="label">Số giấy tờ tuỳ thân</label>
                                <br>
                                    <span class="data">${soGiayTo}</span>
                            </div>

                            <div class="text" id="coquancap">
                                <label class="label">Cơ quan cấp</label>
                                <br>
                                    <span class="data">${noiCapGiayTo}</span>
                            </div>
                            <div class="text" id="ngaycap">
                                <label class="label">Ngày cấp</label>
                                <br>
                                    <span class="data">${ngayCapGiayTo}</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="capnhat" class="container text-right">
                    <div class="col-xs-12">
                        <a  class="btn-prfile" href="/updateProfile">Cập nhật thông tin cá nhân</a>
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

