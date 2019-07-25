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
      <link href="${static_url}assets/css/bootstrap-datetimepicker.css" rel="stylesheet">
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
                            <span id="username">Thay đổi thông tin</span>
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
                        <div class="col-xs-9">
                            <div class="col-xs-6">
                                <div class="text" >
                                    <label class="label">Họ và tên:</label>
                                    <br>
                                        <input id="hoVaTen" class="input-style" type="text" value="${hoVaTen}" place="Họ và tên"></input>
                                </div>

                                
                                <div class="text">
                                    <label class="label">Ngày sinh:</label>
                                    <br>
                                        <div class='input-group date' id='datetimepicker1'>
                                            <input type='text' class="input-style" value="${ngaySinh}"/>
                                            <span class="input-group-addon" style="border:0px; background:none;font-size:18px"><span style="top: -5px;" class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                </div>
                                <div class="text" >
                                    <label class="label">Dân tộc:</label>
                                    <br>
                                        <input id="danToc" class="input-style" type="text"  value="${danToc}"></input>
                                </div>
                                <div class="text" >
                                    <label class="label">Quốc tịch:</label>
                                    <br>
                                      <input id="quocTich" class="input-style" type="text"  value="${quocTich}"></input>
                                </div>
                                <div class="text">
                                    <label class="label">Loại giấy tờ:</label>
                                    <br>
                                        <select id="loaiGiayTo" class="mdb-select md-form input-style">
                                        <option value="" disabled selected>${loaiGiayTo}</option>
                                        <option value="1">Chứng minh nhân dân</option>
                                        <option value="2">Hộ chiếu</option>
                                      </select>
                                </div>
                                <div class="text">
                                    <label class="label">Số giấy tờ:</label>
                                    <br>
                                        <input id="soGiayTo" class="input-style" type="text" value="${soGiayTo}"></input>
                                </div>
                                <div class="text">
                                    <label class="label">Ngày cấp:</label>
                                    <br>
                                       <div class='input-group date' id='datetimepicker2'>
                                            <input type='text' class="input-style" value="${ngayCapGiayTo}"/>
                                            <span class="input-group-addon" style="border:0px; background:none;font-size:18px"><span style="top: -5px;" class="glyphicon glyphicon-calendar"></span>
                                            </span>
                                        </div>
                                </div>
                                <div class="text">
                                    <label class="label">Nơi cấp:</label>
                                    <br>
                                        <input id="noiCap" class="input-style" type="text" value="${noiCapGiayTo}"></input>
                                </div> 
                            </div>

                            <div  class="col-xs-6" style="margin-bottom:100px;">
                                <div class="text" >
                                    <label class="label">Giới tính:</label>
                                    <br>
                                        <select id="gioiTinh" class="mdb-select md-form input-style">
                                        <option value="" disabled selected>${gioiTinh}</option>
                                        <option value="1">Nam</option>
                                        <option value="2">Nữ</option>
                                      </select>
                                </div>
                                <div class="text">
                                    <label class="label">Email:</label>
                                    <br>
                                        <input id="email" class="input-style" type="text" place="Ngày Sinh" value="${email}"></input>
                                </div>

                                <div class="text">
                                    <label class="label">Số điện thoại:</label>
                                    <br>
                                        <input id="soDienThoai" class="input-style" type="text" value="${soDienThoai}"></input>
                                </div>
                                <div class="text">
                                    <label class="label">Số nhà:</label>
                                    <br>
                                        <input id="soNha" class="input-style" type="text" place="Số điện thoại" value="${soNha}"></input>
                                </div>
                                <div class="text">
                                    <label class="label">Tên đường:</label>
                                    <br>
                                        <input id="tenDuong" class="input-style" type="text" place="Số giấy tờ tùy thân" value="${tenDuong}"></input>
                                </div>

                                <div class="text">
                                    <label class="label">Tên phường:</label>
                                    <br>
                                        <input id="tenPhuong" class="input-style" type="text" place="Nơi cấp giấy tờ" value="${tenPhuong}"></input>
                                </div>
                                <div class="text">
                                    <label class="label">Tên huyện:</label>
                                    <br>
                                        <input id="tenHuyen" class="input-style" type="text" value="${tenHuyen}"></input>
                                </div>
                                <div class="text">
                                    <label class="label">Địa chỉ thường trú:</label>
                                    <br>
                                        <textarea id="diaChiDangKy" rows="4" class="textarea-style" type="text" value="${diaChiDangKy}"></textarea>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="capnhat" class="container text-right">
                    <div class="col-xs-12">
                        <a class ="btn-prfile" id="btnUpdateProfile" href="#">Cập nhật</a>
                    </div>
                </div>        
        </div>
    </header>
	

    ${FOOTER}
        
</div>	
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="${static_url}assets/js/jquery-1.11.3.min.js"></script>
<script src="${static_url}assets/js/jquery.mmenu.all.min.js"></script>
<script src="${static_url}assets/js/main.js"></script>
<script src="${static_url}assets/js/bootstrap.js"></script>
<script src="${static_url}assets/js/moment-with-locales.js"></script>
<script src="${static_url}assets/js/bootstrap-datetimepicker.js"></script>
    </body>
</html>

