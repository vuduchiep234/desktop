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
                                <link href="${static_url}assets/css/cong_thanh_toan.css" rel="stylesheet">
                                <link href="${static_url}assets/css/header.css" rel="stylesheet">
    </head>

    <body>
         <header id="my-header">
            ${header}
            ${menu}
        </header>
        <div class="container main-content mt-5">
            <div class="row">
        
            
                <div class="error-page">
                    <h2 class="headline text-red">404</h2>

                    <div class="error-content">
                        <h3>
                            <i class="fa fa-warning text-red"></i> Oops! Không tìm thấy page.</h3>

                        <p>
                            Chúng tôi không thể tìm thấy page.
                            Trong khi đó, bạn có thể <a href="/"> trở về trang chủ
                        </p>
         
                    </div>
                </div>

       
            </div>
        </div>
        <footer class="p-4 mt-5">
            <p class="text-center">Phát triển bởi FPT</p>
        </footer>
        <!-- /.login-box -->
        <!-- Modal -->
        <script src="${static_url}assets/js/main.js"></script>
        <script src="${static_url}assets/js/jquery.min.js"></script>
        <script src="${static_url}assets/js/bootstrap.min.js"></script>
    </body>
</html>

