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
        
        <div class="container-fluid">
            <div class="row"  style="background-image: url('${static_url}assets/img/bg.png'); ">
                <!--<div class="col-md-3"></div>-->
                <div class="col-md-4 img-thumbnail" style="margin: 1% auto;">
                    
                    
                    <!-- <form style="width: 100%;"> -->
                        <div class="img-fluid" alt="Register" style=" background-image: url('${static_url}assets/img/register.jpg'); width: 100%; height: 110px; background-size: cover; text-align: center; color: white; margin-top: 2%;"> <strong style="padding-top: 2%; font-size: 20px;">Register </strong>
                        </div>
                        
                        <br>
                            <br>
                            
                            <div class="form-group">
                                <i class="fa fa-user"></i>
                                <label>Username</label>
                                
                                <input type="text" class="form-control fa fa-envelope" placeholder="Enter your username" name="" id="demo-username"/>
                                <span id="err-username" style="color: red;"></span>
                               
                            </div>
                            <div class="form-group">
                                <i class="fa fa-lock"></i>
                                <label>Password</label>
                                <input type="password" class="form-control" placeholder="Enter your password" name="" id="demo-password"/>
                                <span id="err-password" style="color: red;"></span>
                            </div>
                            <div class="form-group">
                                <i class="fa fa-phone"></i>
                                <label>Phone</label>
                                <input type="text" class="form-control" placeholder="Enter your phone number" name="" id="demo-phone"/>
                                <span id="err-phone" style="color: red;"></span>
                            </div>
                            <div class="form-group">
                                <i class=" fa fa-envelope"></i>
                                <label>Email</label>
                                <input type="email" class="form-control" placeholder="Enter your email" name="" id="demo-email"/>
                                <span id="err-email" style="color: red;"></span>
                            </div>
                            <div class="form-group">
                                <input type="checkbox"/>
                                <label>
                                    <strong>I agree !</strong>
                                </label>
                            </div>
                            
                            <div class="form-group">
                                <input class="btn btn-danger btn-block" type="submit" value="Register" id="demo_register"/>
                            </div>
                            <hr>
                                <div class="form-group" style="text-align: center;">
                                    <a href="/login" >
                                        <i class="fa fa-arrow-left"></i>
                                        <strong>Back to login</strong>
                                    </a>
                                </div>
                    <!-- </form> -->
                </div>
                <!--<div class="col-md-3"></div>-->
            </div>
        </div>
	
        <div>
            ${FOOTER}
        
        </div>	
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
        <script src="${static_url}assets/js/jquery-1.11.3.min.js"></script>

        <script src="${static_url}assets/js/main.js"></script>
        <script src="${static_url}assets/js/bootstrap.js"></script>
        <script src="${static_url}assets/js/demo.js"></script>
    </body>
</html>

