<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>

<html lang="vi">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!-- Tell the browser to be responsive to screen width -->
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <title>Cổng thanh toán HCM</title>
        <link href="${static_url}assets/css/bootstrap.min.css" rel="stylesheet">
        <link href="${static_url}assets/css/nhap_ma_so.css" rel="stylesheet">
        <link href="${static_url}assets/css/header.css" rel="stylesheet">
    </head>

    <body>

        <header id="my-header">
            ${header}
            ${menu}
        </header>
        <div class="main-content" style="min-height:100px!important;">
            <div class="container pt-5">
                <div class="text-center">
                    <div class="row">
                        <div class="col-md-6 offset-md-3 mt-2">
                            <div class="form-group input-form">
                                <input id="input-search" class="form-control mb-3 border-0" type="text" placeholder="Nhập thông tin cần tìm">
                                
                                <button id="btnSearch" type="button" class="btn mt-5">Tra cứu</button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <footer class="p-4 pl-md-6">
            <p class="">SỞ THÔNG TIN VÀ TRUYỀN THÔNG TPHCM</p>
            <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif">
                <i class="fas fa-home mr-1"></i> Địa chỉ: 59 Lý Tự Trọng, P.Bến Nghé, Quận 1, TPHCM</p>
            <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif">
                <i class="fas fa-phone-square mr-1"></i> Điện thoại: (028) 3520.2727 - (028)3520.2323</p>
            <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif">
                <i class="fas fa-fax mr-1"></i> Fax: (028)3520.2424 - (028)3930.9498</p>
            <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif">
                <i class="fas fa-envelope mr-1"></i> Email: stttt@tphcm.gov.vn</p>
        </footer>

        <script src="${static_url}assets/js/jquery-3.3.1.min.js"></script>
        <script src="${static_url}assets/js/bootstrap.min.js"></script>
        <script src="${static_url}assets/js/payment_history.js"></script>


    </body>
</html>

