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
                            <link rel="stylesheet" href="${static_url}assets/css/new-styles-1.css" />
    </head>

    <body>
        
        <div id="my-page">
            ${header}
            ${menu}
        
            <div class="main-content">
                <div class="wrapper-step1">
                    <div class="container">



                        <div class="right-layout-step1 col-xs-12 col-sm-5">
                            <h3>THANH TOÁN</h3>
                            <p>Phí thủ tục hành chính.</p>
                            <p>
                                <img class="payment-image"
                                     src="${static_url}assets//images//hompage/step-1/1_step1-nhap-ma-so-op1.png" />
                            </p>
                        </div>
                        <div class="left-layout-step1 col-xs-12 col-sm-6">

                            <img class="enter-number" src="${static_url}assets/images/hompage/step-1/1_step1-nhap-ma-so-op2.png" />
                            <p class="notify">Hãy nhập chính xác mã số rồi ấn xác nhận để tiếp tục</p>
                            <!--<form>-->
                                <div class="input-number form-group">
                                    <input type="text" class="form-control" id="test" placeholder="Nhập mã số hợp đồng">
                                </div>
                                <div class="form-group submit-buttton">
                                    <button type="submit" class="form-control" id="submit">Xác nhận</button>
                                </div>
                            <!--</form>-->

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
        <script src="${static_url}assets/js/demo.js"></script>
       
    
    </body>
</html>

