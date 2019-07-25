<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html>

<html lang="vi">
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <style>
                    .clear {
                    clear: both;
                    }
                    .button {
                    cursor: pointer;
                    border: none;
                    color: #ffffff;
                    text-align: center;
                    text-decoration: none;
                    display: inline-block;
                    font-size: 16px;
                    border-radius: 5px;
                    height: 40px;
                    border-width: 0px;
                    }
                    .tab {
                    overflow: hidden;
                    border: 1px solid #ccc;
                    background-color: #ffffff;
                    width: 50%;
                    margin: auto;
                    }
                    .tab button {
                    background-color: inherit;
                    float: left;
                    border: none;
                    outline: none;
                    cursor: pointer;
                    padding: 14px 16px;
                    transition: 0.3s;
                    width: 50%;
                    }
                    /* Change background color of buttons on hover */
                    .tab button:hover {
                    background-color: #ddd;
                    }

                    /* Create an active/current tablink class */
                    .tab button.active {
                    background-color: #ccc;
                    }

                    /* Style the tab content */
                    .tabcontent {
                    display: none;
                    padding: 6px 12px;
                    border: 1px solid #ccc;
                    border-top: none;
                    width: 50%;
                    margin: auto;
                    }

                    .form_input input {
                    width: 362px;
                    height: 30px;
                    border-radius: 4px;
                    padding: 10px 10px;
                    margin: 5px 0;
                    box-sizing: border-box;
                    }
            
                    div {
                    margin-left: 98px;
                    }

                    a {
                    text-decoration: none;
                    }

                    .tablinks {
                
                    }
                    .tab input[type=radio] {
                        background-color: inherit;
                        float: left;
                        border: none;
                        outline: none;
                        cursor: pointer;
                        padding: 14px 16px;
                        transition: 0.3s;
                        width: 50%;
                    }
                    /* Style the tab content */
                    .subcontent {
                        display: block;
                        padding: 6px 12px;
                        border: 1px solid #ccc;
                        border-top: none;
                        width: 100%;
                        margin: auto;
                    }
                </style>
                <script>
                    function openForm(evt, tabName) {
                        var i, tabcontent, tablinks;
                        document.getElementById('login').style.display = "block";
                        tabcontent = document.getElementsByClassName("tabcontent");
                        for (i = 0; i < tabcontent.length; i++) {
                            tabcontent[i].style.display = "none";
                        }
                        tablinks = document.getElementsByClassName("tablinks");
                        for (i = 0; i < tablinks.length; i++) {
                            tablinks[i].className = tablinks[i].className.replace(" active", "");
                        }
                        document.getElementById(tabName).style.display = "block";
                        evt.currentTarget.className += " active";
                    }
                    function openFormRegister(evt, tabName) {
                        var i, subcontent, sublinks;
                        subcontent = document.getElementsByClassName("subcontent");
                        for (i = 0; i < subcontent.length; i++) {
                            subcontent[i].style.display = "none";
                        }
                        sublinks = document.getElementsByClassName("sublinks");
                        for (i = 0; i < sublinks.length; i++) {
                            sublinks[i].className = sublinks[i].className.replace(" active", "");
                        }
                        document.getElementById(tabName).style.display = "block";
                        evt.currentTarget.className += " active";
                    }
                </script>
                <link/>
                <link href="DichVuCongWebService/static/fsp/css/font-awesome.min.css"/>
    </head>
    <body>
        <div>
            <div class="tab">
                <button class="tablinks active" onclick="openForm(event, 'login')">Đăng nhập</button>
                <button class="tablinks" onclick="openForm(event, 'register')">Đăng ký</button>
            </div>

            <div id="login" class="tabcontent">
                <div class="form_input">
                    <label style="margin-top: 11px; margin-right: 15px; width: 50px;">Email / SĐT </label>
                    <input id="txtLoginId" type="text" style="" placeholder="Nhập Email hoặc Số điện thoại"/>
                    <div>
                        <span id="msgLoginIdErr"></span>
                    </div>
                    <br/>
                    <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Mật khẩu </label>
                    <input id="txtPassword1" type="password" style="" placeholder="Mật khẩu từ 6 đến 32 ký tự"/>
                    <div>
                        <span id="msgPasswordErr"></span>
                    </div>
                </div>
                <div style="cursor: pointer; color: blue; width: 100%;
                        margin-left: 94px;
                        margin-top: 10px;">
                    <a href="#" style="">Quên mật khẩu? Nhấn vào đây</a>
                </div>
                <div style="margin-top: 60px; width: 100%">
                    <button class="button" style="height: 40px; width: 362px;margin-left: 94px;
                        background: #169BD5;" onClick="validate();">Đăng nhập</button>
                </div>
                <div style="margin-top: 30px;">
                    <button class="button" style="cursor: pointer; background: #153E7E;
                        margin-left: 144px; width: 250px;">
                        <i class="fa fa-facebook-f">
                            Login with Facebook
                    </button>

                </div>
                <div style="margin-top: 10px;">
                    <button class="button" style="cursor: pointer; background: #E41B17; 
                        margin-left: 144px; width: 250px;">
                        <i class="fa fa-google-plus">
                            Login with Google
                    </button>
                </div>
            </div>
            <div id="register" class="tabcontent">
                <form id="signup">
                    <div class="tab">
                        <input id="rdaPersonal" name="rdaPersonal" checked="true" type="radio" class="sublinks active" value="Cá nhân" onclick="openFormRegister(event, 'personal')"/>Cá nhân
                        <input id="rdaOrganization" type="radio" class="sublinks" value="Tổ chức" onclick="openFormRegister(event, 'organization')"/>Tổ chức
                    </div>
                    <div id="personal" class="subcontent">
                        <div id="personal_input" class="form_input">
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Username * </label>
                            <input id="txtUsername" type="text" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 15px; width: 50px;">Số điện thoại * </label>
                            <input id="txtPhone" type="text" style=""/>
                            <button id="btnRetrieveCode" style="">Nhận mã xác thực</button>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Mã xác thực * </label>
                            <input id="txtCode" type="text" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Họ và tên * </label>
                            <input id="txtFullname" type="text" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Mật khẩu * </label>
                            <input id="txtPassword" type="password" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Xác nhận mật khẩu * </label>
                            <input id="txtRePassword" type="password" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Email * </label>
                            <input id="txtEmail" type="text" style=""/>
                        </div>
                    </div>
                    <div id="organization" class="subcontent">
                        <div id="organization_input" class="form_input">
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Email * </label>
                            <input id="" type="text" style=""/>
                            <input id="" type="file" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 15px; width: 50px;">Mã xác thực * </label>
                            <input id="" type="text" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Tên tổ chức * </label>
                            <input id="" type="text" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Mật khẩu * </label>
                            <input id="" type="text" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Xác nhận mật khẩu * </label>
                            <input id="" type="text" style=""/>
                            <br/>
                            <label style="margin-top: 11px; margin-right: 33px; width: 50px;">Số điện thoại * </label>
                            <input id="" type="text" style=""/>
                        </div>
                    </div>
                    <div style="margin-top: 60px; width: 100%">
                        <button id="btnSignup" class="button" style="height: 40px; width: 200px;margin-left: 94px;
                            background: #169BD5;" onClick="">Đăng ký</button>
                    </div>
                </div>
            </form>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDB18ocC5Ydyc4k9wPHcqWu0XL-ToifH20"></script>
        <script>
            function validate() {
                var loginId = $('#txtLoginId').val();
                var password = $('#txtPassword').val();
                if(loginId == null || loginId == "") {
                    $('#msgLoginIdErr').text("Login id is required");
                    $('#msgLoginIdErr').css("display", "block");
                    $('#msgLoginIdErr').css('color', 'red');
                    return false;
                } else {
                    $('#msgLoginIdErr').text("");
                    $('#msgLoginIdErr').css("display", "none");
                }

                if(password == null || password == "") {
                    $('#msgPasswordErr').text("Password is required");
                    $('#msgPasswordErr').css("display", "block");
                    $('#msgPasswordErr').css('color', 'red');
                    return false;
                } else {
                    $('#msgPasswordErr').text("");
                    $('#msgPasswordErr').css("display", "none");
                }
                return true;
            }
            function login() {
                if(validate()) {
                    // todo
                }
            }
            $(function () {
                var result, obj;
                $('#btnSignup').on('click', function () {
                    obj = {
                        username: $('#txtUsername').val(),
                        phone: $('#txtPhone').val(),
                        code: $('#txtCode').val(),
                        fullname: $('#txtFullname').val(),
                        password: $('#txtPassword').val(),
                        rePassword: $('#txtRePassword').val(),
                        email: $('#txtEmail').val(),
                        personalOrOrganization: $('input[name=rdaPersonal]:checked').val()
                    };
                    $.ajax({
                        type: 'POST',
                        url: '/user/signup',
                        data: JSON.stringify(obj)
                    }).done(function (data) {
                        if (data.error_code == 0) {
                            // show create account success and forward to login
                            var modal = document.createElement('DIV');
                            modal.innerHTML = ('<div class="modal-dialog">' +
                                    '      <div class="modal-content">' +
                                    '        <div class="modal-header">' +
                                    '          <h4 class="modal-title green"> Success </h4>' +
                                    '        </div></div>' +
                                    '      </div>');
                            modal.setAttribute('class', 'modal fade in show');
                            $('#register').append(modal);
                        } else if (data.error_code == 1003) {
                            // alert user exist.
                            username.css({'border': '1px solid red', 'margin-bottom': '3px'});
                            username.parent().append('<span class="err red" style="float:left;margin-bottom:15px;">* Username is exist</span>');
                        } else {
                            // alert server error.
                            $('.register_false').replaceWith('<p class="red register_false"><strong>Register false - ' + data['error_message'] + '</strong></p>');
                        }
                    });
                })
            });
        </script>
    </body>
</html>

