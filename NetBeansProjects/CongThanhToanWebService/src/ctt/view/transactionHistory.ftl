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
                                <link rel="stylesheet" href="${static_url}assets/css/tra-cuu-giao-dich.css" />
    </head>

    <body>

        <div id="my-page">
            ${header}
            ${menu}
        
            <div class="wrapper">

                <div class="container">

                    <div class="row">

                        <div class="page-tittle col-lg-12 col-sm-12">
                            <h2>Tra cứu giao dịch thanh toán</h2>
                        </div>

                        <div class="search-form col-lg-12 col-sm-12">
                            <form action="" method="">
                                <div class="input-group">
                                    <input list="browsers" type="text" class="form-control text-box-search"
                                           placeholder="Nhập mã yêu cầu" name="search">

                                        <datalist id="browsers">
                                            <option value="Internet Explorer">
                                                <option value="Firefox">
                                                    <option value="Chrome">
                                                        <option value="Opera">
                                                            <option value="Safari">
                                        </datalist>

                                        <div class="input-group-btn">
                                            <button class="btn btn-primary button-search" type="submit">
                                                <i class="fa fa-search"></i> Tra cứu
                                            </button>
                                        </div>
                                </div>
                            </form>
                        </div>

                        <div class="table-responsive col-lg-12 col-sm-12">
                            <table class="table table-responsive history-payment-table col-lg-12 col-xs-12 col-md-12 col-sm-12">
                                <thead>
                                    <tr>
                                        <th>Mã hóa đơn y/c thanh toán</th>
                                        <th>Mã hóa đơn đã thanh toán</th>
                                        <th>Mô tả</th>
                                        <th>Số tiền</th>
                                        <th>Đơn vị</th>
                                        <th>Cổng thanh toán</th>
                                        <th>Thời gian</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>#778022019003000066</td>
                                        <td>#778022019003000066</td>
                                        <td>
                                            <p class="bill-detail">Tiền điện tháng 5</p>
                                        </td>
                                        <td>
                                            <p class="price">950.000/ vnđ</p>
                                        </td>
                                        <td>
                                            <p class="bill-detail">DV #1</p>
                                        </td>
                                        <td>
                                            <img src="${static_url}assets/images/hompage/tra-cuu-giao-dich-2/vn-pay.png"
                                                 class="payment-images" />
                                        </td>
                                        <td>
                                            <p class="bill-detail">10/07/2019</p>
                                        </td>
                                        <td> 
                                            <button class="btn btn-danger btn-sm">Chưa thanh toán</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>#778022019003000066</td>
                                        <td>#778022019003000066</td>
                                        <td>
                                            <p class="bill-detail">Tiền điện tháng 5</p>
                                        </td>
                                        <td>
                                            <p class="price">950.000/ vnđ</p>
                                        </td>
                                        <td>
                                            <p class="bill-detail">DV #1</p>
                                        </td>
                                        <td>
                                            <img src="${static_url}assets/images/hompage/tra-cuu-giao-dich-2/vn-pay.png"
                                                 class="payment-images" />
                                        </td>
                                        <td>
                                            <p class="bill-detail">10/07/2019</p>
                                        </td>
                                        <td> 
                                            <button class="btn btn-danger btn-sm">Chưa thanh toán</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>#778022019003000066</td>
                                        <td>#778022019003000066</td>
                                        <td>
                                            <p class="bill-detail">Tiền điện tháng 5</p>
                                        </td>
                                        <td>
                                            <p class="price">950.000/ vnđ</p>
                                        </td>
                                        <td>
                                            <p class="bill-detail">DV #1</p>
                                        </td>
                                        <td>
                                            <img src="${static_url}assets/images/hompage/tra-cuu-giao-dich-2/vn-pay.png"
                                                 class="payment-images" />
                                        </td>
                                        <td>
                                            <p class="bill-detail">10/07/2019</p>
                                        </td>
                                        <td> 
                                            <button class="btn btn-danger btn-sm">Chưa thanh toán</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>#778022019003000066</td>
                                        <td>#778022019003000066</td>
                                        <td>
                                            <p class="bill-detail">Tiền điện tháng 5</p>
                                        </td>
                                        <td>
                                            <p class="price">950.000/ vnđ</p>
                                        </td>
                                        <td>
                                            <p class="bill-detail">DV #1</p>
                                        </td>
                                        <td>
                                            <img src="${static_url}assets/images/hompage/tra-cuu-giao-dich-2/vn-pay.png"
                                                 class="payment-images" />
                                        </td>
                                        <td>
                                            <p class="bill-detail">10/07/2019</p>
                                        </td>
                                        <td> 
                                            <button class="btn btn-success btn-sm">Đã thanh toán</button>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>#778022019003000066</td>
                                        <td>#778022019003000066</td>
                                        <td>
                                            <p class="bill-detail">Tiền điện tháng 5</p>
                                        </td>
                                        <td>
                                            <p class="price">950.000/ vnđ</p>
                                        </td>
                                        <td>
                                            <p class="bill-detail">DV #1</p>
                                        </td>
                                        <td>
                                            <img src="${static_url}assets/images/hompage/tra-cuu-giao-dich-2/vn-pay.png"
                                                 class="payment-images" />
                                        </td>
                                        <td>
                                            <p class="bill-detail">10/07/2019</p>
                                        </td>
                                        <td> 
                                            <button class="btn btn-success btn-sm">Đã thanh toán</button>
                                        </td>
                                    </tr>

                                </tbody>
                            </table>
                        </div>

                        <div class="col-lg-12 col-sm-12 col-xs-12">
                            <ul class="pagination pagination-sm pagination-page">
                                <li>
                                    <a href="#">
                                        <i class="fa fa-angle-left"></i>
                                    </a>
                                </li>
                                <li class="active">
                                    <a href="#">1</a>
                                </li>
                                <li>
                                    <a href="#">2</a>
                                </li>
                                <li>
                                    <a href="#">3</a>
                                </li>
                                <li>
                                    <a href="#">
                                        <i class="fa fa-angle-right"></i>
                                    </a>
                                </li>
                            </ul>
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

    </body>
</html>

