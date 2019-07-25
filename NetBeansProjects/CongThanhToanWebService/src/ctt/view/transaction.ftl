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
                            <!--                            <link href="${static_url}assets/css/bootstrap.min.css" rel="stylesheet">
                        <link href="${static_url}assets/css/nhap_ma_so.css" rel="stylesheet">
                            <link href="${static_url}assets/css/header.css" rel="stylesheet">-->
                                    
                            <link rel="stylesheet" href="${static_url}assets/css/bootstrap.min.css">
    
                                <!--                                <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet">
                                <link href="https://fonts.googleapis.com/css?family=Roboto+Condensed:300,700" rel="stylesheet">-->
                                <link rel="stylesheet" href="${static_url}assets/css/bootstrap.min.css" />

                                <link rel="stylesheet" href="${static_url}assets/fonts/roboto.css"/>
                                <link rel="stylesheet" href="${static_url}assets/fonts/roboto-condensed.css"/>
    
                                <link rel="stylesheet" href="${static_url}assets/css/style.css" />
                                <link rel="stylesheet" href="${static_url}assets/css/font-awesome.min.css" />
                                <!--                                <link rel="stylesheet" href="${static_url}assets/css/hompage.css" />
                                <link rel="stylesheet" href="${static_url}assets/css/font-awesome.min.css">
                                <link rel="stylesheet" href="${static_url}assets/css/style.css">-->
                                <link rel="stylesheet" href="${static_url}assets/css/new-styles-1.css" />
    </head>

    <body>

        <div id="my-page">
            ${header}
            ${menu}
        
            <div class="wrapper-information">

                <div class="container">

                    <div class="row">


                        <div class="col-lg-3 col-sm-2 col-md-3 col-xs-0"></div>

                        <div class="row-layout col-lg-6 col-md-6 col-sm-8 col-xs-12">

                            <div class="top-layout text-layout col-xs-7 col-lg-7">
                                <h2>TRA CỨU</h2>
                                <p>Giao dịch thanh toán</p>
                            </div>
                            <div class="top-layout image-layout col-lg-5 col-xs-5">
                                <img src="${static_url}assets/images/hompage/tra-cuu-giao-dich/5-textbox-tra-cuu.png" />
                            </div>


                            <div class="col-lg-12 col-md-12 col-xs-12">
                                <form method="" action="">
                                    <div class="form-group">

                                        <input list="browsers" name="browser" class="select-box form-control" placeholder="Nhập mã yêu cầu" />
                                        <datalist id="browsers">
                                            <option value="Internet Explorer">
                                                <option value="Firefox">
                                                    <option value="Chrome">
                                                        <option value="Opera">
                                                            <option value="Safari">
                                        </datalist>

                                    </div>

                                    <div class="form-group">
                                        <button type="button" class="search-button form-control" id="transaction">
                                            <i class="fa fa-search"></i> Tìm kiếm </button>
                                    </div>
                                </form>
                            </div>

                        </div>

                    </div>

                </div>

            </div>

            ${footer}
        </div>
        <!--        <script src="${static_url}assets/js/jquery-3.3.1.min.js"></script>
        <script src="${static_url}assets/js/bootstrap.min.js"></script>-->
        <script src="${static_url}assets/js/payment_history.js"></script>
        
        <script src="${static_url}assets/js/jquery-1.12.1.min.js"></script>
        <script src="${static_url}assets/js/bootstrap.min.js"></script>
        <script src="${static_url}assets/js/demo.js"></script>

    </body>
</html>

