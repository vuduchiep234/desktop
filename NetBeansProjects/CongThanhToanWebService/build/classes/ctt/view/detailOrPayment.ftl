<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>

<html lang="vi">

    <head>
        <meta charset="UTF-8"/>
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Phiếu xác nhận</title>
        <link href="${static_url}assets/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="${static_url}assets/css/phieu_xac_nhan.css" rel="stylesheet"/>
        <link href="https://pay.vnpay.vn/lib/vnpay/vnpay.css" rel="stylesheet" />
                                        <link href="${static_url}assets/css/header.css" rel="stylesheet">
        <!--<link href="${static_url}assets/css/popup.css" rel="stylesheet">-->
    </head>

    <body>
        <header id="my-header">
            ${header}
            ${menu}
        </header>
        <div class="main-content">
            <div class="container">
                <div class="document-parent">
                    <h2>Thông tin thanh toán</h2>
                    <hr>
                        <div class="detail">
                            <div class="row">
                                <div class="col-md-2 font-weight-bold">Tên người nộp:</div>
                                <div class="col-md-10 value">${tenNguoiNop}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-2 font-weight-bold ">Số CMND:</div>
                                <div class="col-md-10 value">${soCMND}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-2 font-weight-bold ">Địa chỉ:</div>
                                <div class="col-md-10 value">${diaChi}</div>
                            </div>
                            <div class="row">
                                <div class="col-md-2 font-weight-bold">Số tiền:</div>
                                <div class="col-md-10 amount">
                                    <h3 class="font-weight-bolder">${soTienView}/ vnd</h3>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-md-2 font-weight-bold label">Trạng thái:</div>
                                <div class="col-md-10">
                                    <div id="txtStatusThanhToan" class="paid-status">Đã thanh toán</div>
                                </div>
                            </div>
                        </div>
                </div>
                <button id="btnPayment" type="button" class="btn btn-done mt-4">Hoàn thành</button>
            </div>
            <footer class="p-4 pl-md-6">
                    <p class="">SỞ THÔNG TIN VÀ TRUYỀN THÔNG TPHCM</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-home mr-1"></i> Địa chỉ: 59 Lý Tự Trọng, P.Bến Nghé, Quận 1, TPHCM</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-phone-square mr-1"></i> Điện thoại: (028) 3520.2727 - (028)3520.2323</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-fax mr-1"></i> Fax: (028)3520.2424 - (028)3930.9498</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-envelope mr-1"></i> Email: stttt@tphcm.gov.vn</p>
            </footer>
        </div>
        
        <div class="modal" id="myModal" role="dialog">
            <div class="modal-dialog modal-xl">
    
                <!-- Modal content-->
                <div class="modal-content">
                    <!--                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <h4 class="modal-title">Modal Header</h4>
                    </div>-->
                    <div class="modal-body no-padding">
                        <iframe id="iframePayment" width="100%" height="550px" src=""></iframe>
                    </div>
                    <!--                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    </div>-->
                </div>
      
            </div>
        </div>
        
        <script
            src="https://code.jquery.com/jquery-3.3.1.js"
            integrity="sha256-2Kok7MbOyxpgUVvAk/HJ2jigOSYS2auK4Pfzbm7uH60="
            crossorigin="anonymous">
        </script>
        <script src="https://pay.vnpay.vn/lib/vnpay/vnpay.min.js"></script>
        <script src="${static_url}assets/js/jquery-3.3.1.min.js"></script>
        <script src="${static_url}assets/js/bootstrap.min.js"></script>
        <script>
            
            $('.btn-try-again').click(function () {
            $('#modal-failed').modal('hide');
            });
            $('.btn-ok').click(function () {
            $('#modal-success').modal('hide');
            });
            
            $(function () {
            var result, obj;
            
            var btnPayment = document.getElementById("btnPayment");
            var txtStatusThanhToan = document.getElementById("txtStatusThanhToan");
            
            if(${trangThai} == 0){
            btnPayment.innerHTML ='Thanh toán'
            txtStatusThanhToan.innerHTML ='Chưa thanh toán'
            txtStatusThanhToan.setAttribute('class', 'unpaid-status');
            }else{
            btnPayment.innerHTML ='Hoàn thành'
            txtStatusThanhToan.innerHTML ='Đã thanh toán'
            }
                
            $('#btnPayment').on('click', function(event){
            //example using post method
            
            if(${trangThai} == 1){
                window.location.href ="https://pay.tphcm.gov.vn/";
                return false;
            }
            
            var lbSotien = ${soTien};
            var lbMaHoaDon = '${maHoaDon}';
            var lbTenNguoiNop = '${tenNguoiNop}';
            obj = {
            maHoaDon: lbMaHoaDon,
            soTien: lbSotien,
            tenNguoiNop: lbTenNguoiNop
            };
            $.ajax({
            type: 'POST',
            url: '/payment/detailPayment/payment',
            data: JSON.stringify(obj)
            }).done(function (data) {
            if (data.error_code == 'SUCCESSFUL') {
            // show create account success and forward to login
                if (window.vnpay) {
            
                window.location.replace(data.data);
                vnpay.open({width: 1250, height: 600, url: data.data});
                } else {
            alert('a');
                location.href = data.data;
                }
            <!--document.getElementById("iframePayment").src = data.data-->
            <!--$('#myModal').modal("show");-->
            <!--document.body.innerHTML = ('<iframe width="100%" height="100%" src=" '+ data.data + '"></iframe> ');-->
            } else if (data.error_code == 1003) {
            } 
            });
            return false;
            })
            });
        </script>
    
    </body>
</html>

