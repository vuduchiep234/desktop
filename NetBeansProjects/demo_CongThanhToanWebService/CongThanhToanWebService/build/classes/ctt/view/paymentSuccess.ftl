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
                            <title>Kết quả thanh toán</title>
                            <link href="${static_url}assets/css/bootstrap.min.css" rel="stylesheet">
                                <link href="${static_url}assets/css/popup.css" rel="stylesheet">
                                    <link href="${static_url}assets/css/header.css" rel="stylesheet">
</head>

    <body>
        <header id="my-header">
            ${header}
            ${menu}
        </header>
        <div class="main-content">
            <div class="container">
                <div class="document-parent text-center" style="border: 0px solid !important;padding:120px">
                    <img class="icon-status-success" src="${static_url}assets/img/icon-success.png">
                        <h5 class="payment-label font-weight-bolder mt-5">Thanh Toán</h5>
                        <h3 class="payment-status-success font-weight-bold">THÀNH CÔNG</h3>
                        <button id="btnBack" type="button" class="btn btn-ok">OK</button>
                </div>

            </div>
            <footer class="p-4 pl-md-6">
                    <p class="">SỞ THÔNG TIN VÀ TRUYỀN THÔNG TPHCM</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-home mr-1"></i> Địa chỉ: 59 Lý Tự Trọng, P.Bến Nghé, Quận 1, TPHCM</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-phone-square mr-1"></i> Điện thoại: (028) 3520.2727 - (028)3520.2323</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-fax mr-1"></i> Fax: (028)3520.2424 - (028)3930.9498</p>
                    <p class="mb-2" style="font:85% Arial, Helvetica, Verdana, sans-serif"><i class="fas fa-envelope mr-1"></i> Email: stttt@tphcm.gov.vn</p>
        </footer>
        </div>
        <script src="${static_url}assets/js/jquery-3.3.1.min.js"></script>
        <script src="${static_url}assets/js/bootstrap.min.js"></script>
        <script>
            $(function () {
            var result, obj;
                
            $('#btnBack').on('click', function(event){
            //example using post method
            window.location.replace("https://pay.tphcm.gov.vn/");
            
            return false;
            })
            });
        </script>
    
    </body>
</html>

