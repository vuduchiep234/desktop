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
        
        <div class="container">
            <div class="row" style="margin-top: 100px; margin: 0 auto;" >
                <!--<div class="col-md-3"></div>-->
                <div class="col-md-5">
                    
                    
                    <form style="width: 100%;">
                        <div id="username">
                            <span id="username"  class="full-width has-padding">Register</span>
                        </div>
                        <br>
                            <!--<h3>Register</h3>-->
                            <div class="form-group">
                                <div class="row">
                                    <div class="col">
                                        <label >Username</label>
                                        <input type="text" class="form-control" placeholder="Enter username" required="true" name="" for="signup-username"/>
                                    </div>
                                    <div class="col">
                                        <label>Phone</label>
                                        <input type="text" class="form-control" placeholder="Enter phone number" required="true" name="" />
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label>Email</label>
                                <input type="email" class="form-control" placeholder="Enter your email" required="true" name=""/>
                            </div>
                            <div class="form-group">
                                <label>Password</label>
                                <input type="password" class="form-control" placeholder="Enter your password" required="true" name=""/>
                            </div>
                            <div class="form-group">
                                <input type="checkbox"/>
                                <label><strong>Remember me !</strong></label>
                            </div>
<!--                            <p class="fieldset cd-form form-group">
                                <input id="btn-singup" class="full-width has-padding" type="submit" value="Tạo tài khoản">
                            </p>-->
                            <div class="form-group">
                                <input class="btn btn-danger " type="submit" value="Register" />
                            </div>
                    </form>
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
    </body>
</html>

