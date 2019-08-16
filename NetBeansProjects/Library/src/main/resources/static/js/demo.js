jQuery(document).ready(function ($) {
    
    var token = "";
    $('#demo_login').on('click', function (event) {
        
        var username = $("#demo_username").val();
        var password = $("#demo_password").val();
        if(!username){
            // alert("hello");
            $('#err_username').text("Username is not empty.");
            $('#err_password').text("");
        	
        }
        else if(!password){
            $('#err_password').text("Password is not empty.");
            $('#err_username').text("");
        	
        }
        else if(!(password.length >= 8)){
            $('#err_password').text("Password lenght must >= 8 charactors.");
            $('#err_username').text("");
            
        }
        else{
            $('#err_password').text("");
            $('#err_username').text("");
            var jData = {"username": username, "password": password};
            $('.loading').show();
            $.ajax({
                type: "POST",
                url: '/api/v1/login',
                data: JSON.stringify(jData),
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                success: function (data) {
                    if(data.user.role_id === 1){
                        $('.token_header').val(data.token);
                        location.href = "/admin/home";
                        
                    }
                    else if(data.user.role_id === 2){
                        location.href = "/home";
                    }
                    
                },
                failure: function (errMsg) {
                    alert("error");
                    console.log(errMsg);
                }
            });
        }
        

    });

    function checkEmail(email){
        return /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/.test(email);
    }

    $('#demo_register').on('click', function (event) {
        var username = $("#demo-username").val();
        var email = $("#demo-email").val();
        var password = $("#demo-password").val();
        var phone = $('#demo-phone').val();
        var confirm = $('#demo-confirm').val();
        if(!username){
            $("#err-username").text("Username is not empty.");
            $("#err-password").text("");
            $("#err-phone").text("");
            $("#err-email").text("");
            $("#err-confirm").text("");
        }
        else if(!password){
            $("#err-password").text("Password is not empty.");
            $("#err-username").text("");
            $("#err-phone").text("");
            $("#err-email").text("");
            $("#err-confirm").text("");
        }
        else if(password.length < 8){
            $("#err-password").text("Password lenght must >= 8 characters.");
            $("#err-username").text("");
            $("#err-phone").text("");
            $("#err-email").text("");
            $("#err-confirm").text("");
        }
        else if(!confirm){
            $("#err-confirm").text("Confirm Password is not empty.");
            $("#err-username").text("");
            $("#err-phone").text("");
            $("#err-email").text("");
            $("#err-password").text("");
        }
        else if(!(confirm === password)){
            $("#err-confirm").text("Confirm Password and Password are not match");
            $("#err-username").text("");
            $("#err-phone").text("");
            $("#err-email").text("");
            $("#err-password").text("");
        }
        else if(!phone){
            $("#err-phone").text("Phone number is not empty.");
            $("#err-username").text("");
            $("#err-password").text("");
            $("#err-email").text("");
            $("#err-confirm").text("");
        }
        else if(isNaN(phone) || phone.length < 10 || phone.length > 11){
            $("#err-phone").text("This is not phone number.");
            $("#err-username").text("");
            $("#err-password").text("");
            $("#err-email").text("");
            $("#err-confirm").text("");
        }
        else if(!email){
            $("#err-email").text("Email is not empty.");
            $("#err-username").text("");
            $("#err-password").text("");
            $("#err-phone").text("");
            $("#err-confirm").text("");
        }
        else if(!checkEmail(email)){
            $("#err-email").text("This is not email.");
            $("#err-username").text("");
            $("#err-password").text("");
            $("#err-phone").text("");
            $("#err-confirm").text("");
            
        }
        else{
            $("#err-email").text("");
            $("#err-username").text("");
            $("#err-password").text("");
            $("#err-phone").text("");
            $("#err-confirm").text("");

            if(!$('#check-register').is(':checked')){
                alert("I gree  is not checked !")
            }
            else{
                 var jData = {"username": username, "password": password, "email": email, "phone": phone};

                var content = Math.floor(Math.random() * 10000)+1000;
                var data = {"to": email, "subject": "Confirm Create Account", "content": content};
                
                $.ajax({
                    url: '/api/v1/sendSimpleEmail',
                    type: 'POST',
                    dataType: 'json',
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify(data),
                })
                .done(function(data) {
                    console.log(data);
                    var code = data.text;
                    var input = prompt("Enter code in your email:");
                    if(input === code){
                        $.ajax({
                            type: "POST",
                            url: '/api/v1/register',
                            data: JSON.stringify(jData),
                            contentType: "application/json; charset=utf-8",
                            dataType: "json",
                            success: function (data) {
                                location.href = "/login";
                                
                                // if (data.error_code === 0) {
                                //     $form_modal.removeClass('is-visible');
                                //     $(".modal-body").text("Đăng ký thành công. Vui lòng kiểm tra email để kích hoạt tài khoản.");
                                //     $("#myModal").modal('show');
                                // } else {
                                //     $(".modal-body").text("Tài khoản hoạc email đã được sử dụng.");
                                //     $("#myModal").modal('show');
                                // }
                            },
                            failure: function (errMsg) {
                                alert("Error !");
                                // $('.loading').hide();
                                // $(".modal-body").text("Không kết nối được tới máy chủ");
                                // $("#myModal").modal('show');
                            }
                        });

                    }
                    else{
                        alert("You need check code in your email !")
                    }
                })
                .fail(function() {
                    console.log("error");
                })
                .always(function() {
                    console.log("complete");
                });
            }
        }
        
    });

    
});
