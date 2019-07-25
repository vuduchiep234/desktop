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
            <div class="row " style="padding: auto; background-image: url('${static_url}assets/img/b.jpg'); " >
                
                <div class="col-md-4 card img-thumbnail " style="margin: 1% auto;">
                    
                    
                    <!--<form style="width: 100%;" >-->
                        <div >
                            <div style="background-image: url('${static_url}assets/img/b.jpg'); width: 100%; height: 185px; background-size: cover; text-align: center; color: white;" class="row-fluid">
                                <!--<span id="username" >Login Form</span>  -->
                                <img class="img img-responsive img-circle center" src="${static_url}assets/img/login.gif" style="margin: 2% auto; width: 35%; height: 80%; padding-top: 1%;">
                                    <strong style="font-size:20px;">Login</strong>
                            </div>
                        </div>
                        <br>
                            <br>
                                <br>
                                <div class="form-group">
                                    <i class=" fa fa-user"></i>
                                    <label for="validationTooltip01">Username</label>
                                    <!--<label class="image-replace cd-email" for="signin-username"></label>-->
                                    <input type="text" class="form-control"  id="demo_username" placeholder="Enter your username" name=""/>
                                    <span id="err_username" style="color: red;"></span>
                                    
                                </div>
                                <div class="form-group">
                                    <i class="fa fa-lock"></i>
                                    <label>Password</label>
                                    <input type="password" class="form-control" placeholder="Enter your password" name="" id="demo_password"/>
                                    <span id="err_password" style="color: red;"></span>
                                    <!--<a href="#0" class="hide-password">-->
                                    <div class="form-group">
                                        <label style="float: right; padding-top: 4px; padding-left: 3px;">
                                            <a href="#" >
                                                <strong>Display</strong>
                                            </a>
                                        </label>
                                        <input type="checkbox" style="float: right;"/>
                                    </div>
                                    <!--</a>-->
                                </div>
                                <br>
                                    <div class="form-group">
                                        <input type="checkbox"/>
                                        <label>
                                            <strong>Remember me !</strong>
                                        </label>
                                    </div>
                                    <br>
                                        <div class="form-group">
                                            <input class="btn btn-danger btn-block" type="submit" value="Sign in" id="demo_login"/>
                                        </div>
                                        <hr>
                                            <div class="form-group">
                                                <a href="#">
                                                    <i class="ace-icon fa fa-arrow-left"></i>
                                                    <strong>I forgot my password</strong>
                                                </a>
                                                <a href="/register" style="float:right;">
                                                    <strong>I want to register</strong>
                                                    <i class="ace-icon fa fa-arrow-right"></i>
                                                </a>
                                            </div>
                        
                    <!--</form>-->
                    
                </div>
                
            </div>
        </div>
	
        <div>
            ${FOOTER}
        
        </div>	
        <!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
        <script src="${static_url}assets/js/jquery-1.11.3.min.js"></script>

        <script src="${static_url}assets/js/main.js"></script>
        <script src="${static_url}assets/js/demo.js"></script>
        <script src="${static_url}assets/js/bootstrap.js"></script>
    </body>
</html>

