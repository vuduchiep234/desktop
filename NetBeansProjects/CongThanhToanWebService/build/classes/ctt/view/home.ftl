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
                    <link href="${static_url}assets/css/cong_thanh_toan.css" rel="stylesheet">
                        <link rel="stylesheet" type="text/css" href="sliderengine/amazingslider-1.css">
                            <link href="${static_url}assets/css/header.css" rel="stylesheet">-->
                                            
                            <link rel="stylesheet" href="${static_url}assets/css/bootstrap.min.css" />

                            <link rel="stylesheet" href="${static_url}assets/fonts/roboto.css"/>
                            <link rel="stylesheet" href="${static_url}assets/fonts/roboto-condensed.css"/>
    
                            <link rel="stylesheet" href="${static_url}assets/css/style.css" />
                            <link rel="stylesheet" href="${static_url}assets/css/font-awesome.min.css" />
                            <link rel="stylesheet" href="${static_url}assets/css/hompage.css" />
    </head>

    <body>
        
        <div id="my-page">
            ${header}
            ${menu}
        
            <div class="main-content">

                <div class="slide-show">
                    <div class="container">

                        <div id="myCarousel" class="carousel slide" data-ride="carousel">

                            <!-- Indicators -->
                            <ol class="pagination-slide-show carousel-indicators">
                                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                                <li data-target="#myCarousel" data-slide-to="1"></li>
                                <li data-target="#myCarousel" data-slide-to="2"></li>
                            </ol>

                            <!-- Wrapper for slides -->
                            <div class="carousel-inner">

                                <div class="item active col-lg-12 col-sm-12 col-xs-12">
                                    <div class="col-sm-5 col-xs-5 col-md-5 col-lg-5 text-slide-show">
                                        <div class="col-12 box-center">
                                            <h3>Cổng thanh toán</h3>
                                            <p class="title-slide-show">Dịch Vụ Tp Hồ Chí Minh</p>
                                            <div class="hr row"></div>
                                            <p class="content-slide-show">Giải pháp toàn diện về tiện ích, bảo mật và quản
                                                lý thông tin người dùng</p>
                                        </div>
                                    </div>

                                    <div class="slide-show-images col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <img src="assets/images/hompage/slide-show/img-slide-show.png" alt="null">
                                    </div>
                                </div>

                                <div class="item col-lg-12 col-sm-12 col-xs-12">
                                    <div class="col-sm-5 col-xs-5 col-md-5 col-lg-5 text-slide-show">
                                        <div class="col-12 box-center">
                                            <h3>Cổng thanh toán</h3>
                                            <p class="title-slide-show">Dịch Vụ Tp Hồ Chí Minh</p>
                                            <div class="hr row"></div>
                                            <p class="content-slide-show">Giải pháp toàn diện về tiện ích, bảo mật và quản
                                                lý thông tin người dùng</p>
                                        </div>
                                    </div>

                                    <div class="slide-show-images col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <img src="assets/images/hompage/slide-show/img-slide-show.png" alt="null">
                                    </div>
                                </div>

                                <div class="item col-lg-12 col-sm-12 col-xs-12">
                                    <div class="col-sm-5 col-xs-5 col-md-5 col-lg-5 text-slide-show">
                                        <div class="col-12 box-center">
                                            <h3>Cổng thanh toán</h3>
                                            <p class="title-slide-show">Dịch Vụ Tp Hồ Chí Minh</p>
                                            <div class="hr row"></div>
                                            <p class="content-slide-show">Giải pháp toàn diện về tiện ích, bảo mật và quản
                                                lý thông tin người dùng</p>
                                        </div>
                                    </div>

                                    <div class="slide-show-images col-sm-7 col-xs-7 col-md-7 col-lg-7">
                                        <img src="assets/images/hompage/slide-show/img-slide-show.png" alt="null">
                                    </div>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>


                <div class="procedure">
                    <div class="container">

                        <div class="left-procedure col-sm-5 col-xs-12 col-md-5 col-lg-5">
                            <h4>Quy trình thanh toán lệ phí thủ tục hành chính.</h4>
                            <div class="text-procedure">
                                <p>Thủ tục hành chính về chi trả các khoản chi phí liên quan.</p>
                                <p>Các bước và thực hiện theo hướng dẫn.</p>
                            </div>
                            <button type="button" class="button-procedure form-control" id="paymentAdministrative">Thanh toán thủ tục hành
                                chính
                                <i class="fa fa-angle-right"></i> 
                            </button>
                        </div>

                        <div class="right-procedure col-sm-7 col-xs-12 col-md-7 col-lg-7">
                            <img src="assets/images/hompage/procedure/procedure.png" class="procedure-image" />
                        </div>

                    </div>
                </div>

            </div>
            
            ${footer}
        
        
        </div>
        <!--        <script src="${static_url}assets/js/jquery-3.3.1.min.js"></script>
       <script src="${static_url}assets/js/bootstrap.min.js"></script>
        Insert to your webpage before the </head> 
       <script src="sliderengine/amazingslider.js"></script>
       <link rel="stylesheet" type="text/css" href="sliderengine/amazingslider-1.css">
       <script src="sliderengine/initslider-1.js"></script>
        End of head section HTML codes -->
         
        <script src="${static_url}assets/js/jquery-1.12.1.min.js"></script>
        <script src="${static_url}assets/js/bootstrap.min.js"></script>
       
    
    </body>
</html>

