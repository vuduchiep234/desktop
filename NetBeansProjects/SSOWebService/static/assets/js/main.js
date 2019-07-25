jQuery(document).ready(function ($) {
    $(function () {
        var bindDatePicker = function () {
            $(".date").datetimepicker({
                format: 'DD/MM/YYYY',
                icons: {
                    time: "fa fa-clock-o",
                    date: "fa fa-calendar",
                    up: "fa fa-arrow-up",
                    down: "fa fa-arrow-down"
                }
            }).find('input:first').on("blur", function () {
                // check if the date is correct. We can accept dd-mm-yyyy and yyyy-mm-dd.
                // update the format if it's yyyy-mm-dd
                var date = parseDate($(this).val());

//                if (!isValidDate(date)) {
//                    //create date based on momentjs (we have that)
//                    date = moment().format('DD/MM/YYYY');
//                }

                $(this).val(date);
            });
        }

        var isValidDate = function (value, format) {
            format = format || false;
            // lets parse the date to the best of our knowledge
            if (format) {
                value = parseDate(value);
            }

            var timestamp = Date.parse(value);

            return isNaN(timestamp) == false;
        }

        var parseDate = function (value) {
            var m = value.match(/^(\d{1,2})(\/|-)?(\d{1,2})(\/|-)?(\d{4})$/);
            if (m)
//                value =("00" + m[1]).slice(-2)+ '/' + ("00" + m[3]).slice(-2) + '/' +  m[5] ;
            console.log(m[0]);
            return m[0];
        }
        bindDatePicker();
    });
    // nav-mobile
    $(function () {

        $('nav#menu').mmenu({
            extensions: ['fx-menu-slide', 'shadow-page', 'shadow-panels', 'listview-large', 'pagedim-white'],
            iconPanels: true,
            counters: true,
            keyboardNavigation: {
                enable: true,
                enhance: true
            },
        }, {
            searchfield: {
                clear: true
            }
        });
    });


//    var url = location.protocol + "//" + window.location.hostname + "/hcm-sso/user/";
    var url = location.protocol + "//" + window.location.hostname + ":3001/user/";
    var $form_modal = $('.cd-user-modal'),
            $form_login = $form_modal.find('#cd-login'),
            $form_signup = $form_modal.find('#cd-signup'),
            $form_forgot_password = $form_modal.find('#cd-reset-password'),
            $form_modal_tab = $('.cd-switcher'),
            $tab_login = $form_modal_tab.children('li').eq(0).children('a'),
            $tab_signup = $form_modal_tab.children('li').eq(1).children('a'),
            $forgot_password_link = $form_login.find('.cd-form-bottom-message a'),
            $back_to_login_link = $form_forgot_password.find('.cd-form-bottom-message a'),
            $main_nav = $('.main-nav');

    //open modal
    $('.dangky-icon').on('click', function (event) {
        $form_modal.addClass('is-visible');
        $main_nav.children('ul').removeClass('is-visible');
        signup_selected();
    });
    $('.dangnhap-icon').on('click', function (event) {
        $form_modal.addClass('is-visible');
        $main_nav.children('ul').removeClass('is-visible');
        login_selected();
    });
    $('.quenmatkhau-icon').on('click', function (event) {
        $form_modal.addClass('is-visible');
        $tab_login.addClass('selected');
        $main_nav.children('ul').removeClass('is-visible');
        forgot_password_selected();
    });

    $('.cd-signin').on('click', function (event) {

        if ($(event.target).is($main_nav)) {
            // on mobile open the submenu
            $(this).children('ul').toggleClass('is-visible');
        } else {
            // on mobile close submenu
            $main_nav.children('ul').removeClass('is-visible');
            //show modal layer
            $form_modal.addClass('is-visible');
            //show the selected form
            ($(event.target).is('.cd-signup')) ? signup_selected() : login_selected();
        }

    });

    //close modal
    $('.cd-user-modal').on('click', function (event) {
        if ($(event.target).is($form_modal) || $(event.target).is('.cd-close-form')) {
            $form_modal.removeClass('is-visible');
        }
    });
    //close modal when clicking the esc keyboard button
    $(document).keyup(function (event) {
        if (event.which == '27') {
            $form_modal.removeClass('is-visible');
        }
    });

    //switch from a tab to another
    $form_modal_tab.on('click', function (event) {
        event.preventDefault();
        ($(event.target).is($tab_login)) ? login_selected() : signup_selected();
    });

    //hide or show password
    $('.hide-password').on('click', function () {
        var $this = $(this),
                $password_field = $this.prev('input');

        ('password' === $password_field.attr('type')) ? $password_field.attr('type', 'text') : $password_field.attr('type', 'password');
        ('Ân' === $this.text()) ? $this.text('Hiện') : $this.text('Ân');
        //focus and move cursor to the end of input field
        $password_field.putCursorAtEnd();
    });

    //show forgot-password form 
    $forgot_password_link.on('click', function (event) {
        event.preventDefault();
        forgot_password_selected();
    });

    //back to login from the forgot-password form
    $back_to_login_link.on('click', function (event) {
        event.preventDefault();
        login_selected();
    });

    function login_selected() {
        $form_login.addClass('is-selected');
        $form_signup.removeClass('is-selected');
        $form_forgot_password.removeClass('is-selected');
        $tab_login.addClass('selected');
        $tab_signup.removeClass('selected');
    }

    function signup_selected() {
        $form_login.removeClass('is-selected');
        $form_signup.addClass('is-selected');
        $form_forgot_password.removeClass('is-selected');
        $tab_login.removeClass('selected');
        $tab_signup.addClass('selected');
    }

    function forgot_password_selected() {
        $form_login.removeClass('is-selected');
        $form_signup.removeClass('is-selected');
        $form_forgot_password.addClass('is-selected');
    }

    //REMOVE THIS - it's just to show error messages 
    $form_login.find('input[type="submit"]').on('click', function (event) {
        event.preventDefault();
        $form_login.find('input[type="email"]').toggleClass('has-error').next('span').toggleClass('is-visible');
    });
    $form_signup.find('input[type="submit"]').on('click', function (event) {
        event.preventDefault();
        $form_signup.find('input[type="email"]').toggleClass('has-error').next('span').toggleClass('is-visible');
    });


    //IE9 placeholder fallback
    //credits http://www.hagenburger.net/BLOG/HTML5-Input-Placeholder-Fix-With-jQuery.html
    if (!Modernizr.input.placeholder) {
        $('[placeholder]').focus(function () {
            var input = $(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
            }
        }).blur(function () {
            var input = $(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.val(input.attr('placeholder'));
            }
        }).blur();
        $('[placeholder]').parents('form').submit(function () {
            $(this).find('[placeholder]').each(function () {
                var input = $(this);
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            })
        });
    }

    $('#btn-login').on('click', function (event) {
        var username = $("#signin-username").val();
        var password = $("#signin-password").val();
        var jData = {"username": username, "password": password};
        $('.loading').show();
        $.ajax({
            type: "POST",
            url: url + "login",
            data: JSON.stringify(jData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                $('.loading').hide();
                if (data.error_code === 0) {
                    $form_modal.removeClass('is-visible');
                    $(".modal-body").text("Đăng nhập thành công");
                    $("#myModal").modal('show');
                    location.reload();
                } else if (data.error_code === 99) {
                    $(".modal-body").text("Tài khoản chưa kích hoạt. Vui lòng vào email kích hoạt tài khoản.");
                    $("#myModal").modal('show');
                } else {
                    $(".modal-body").text("Đăng nhập thất bại");
                    $("#myModal").modal('show');
                }

            },
            failure: function (errMsg) {
                $('.loading').hide();
                $(".modal-body").text("Không kết nối được tới máy chủ");
                $("#myModal").modal('show');
            }
        });

    });
    $('#btn-singup').on('click', function (event) {
        var username = $("#signup-username").val();
        var email = $("#signup-email").val();
        var password = $("#signup-password").val();
        var phone = $('#signup-phone').val();
        var jData = {"username": username, "password": password, "email": email, "phone": phone};
        $('.loading').show();
        $.ajax({
            type: "POST",
            url: url + "signup",
            data: JSON.stringify(jData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                $('.loading').hide();
                if (data.error_code === 0) {
                    $form_modal.removeClass('is-visible');
                    $(".modal-body").text("Đăng ký thành công. Vui lòng kiểm tra email để kích hoạt tài khoản.");
                    $("#myModal").modal('show');
                } else {
                    $(".modal-body").text("Tài khoản hoạc email đã được sử dụng.");
                    $("#myModal").modal('show');
                }
            },
            failure: function (errMsg) {
                $('.loading').hide();
                $(".modal-body").text("Không kết nối được tới máy chủ");
                $("#myModal").modal('show');
            }
        });

    });

    //update profile 
    $("#btnUpdateProfile").on('click', function (event) {
        $('.loading').show();
        var hoVaTen = $("#hoVaTen").val();
        var ngaySinh = $("#ngaySinh").val();
        var danToc = $("#danToc").val();
        var quocTich = $("#quocTich").text();
        var gioiTinh = $("#gioiTinh option:selected").val();
        var email = $("#email").val();
        var soDienThoai = $("#soDienThoai").val();
        var soNha = $("#soNha").val();
        var loaiGiayTo = $("#loaiGiayTo").val();
        var soGiayTo = $("#soGiayTo").val();
        var tenDuong = $("#tenDuong").val();
        var tenPhuong = $("#tenPhuong").val();
        var tenHuyen = $("#tenHuyen").val();
        var ngayCap = $("#ngayCap").val();
        var noiCap = $("#noiCap").val();
        var diaChiDangKy = $.trim($("#diaChiDangKy").val());

        if (loaiGiayTo == "Hộ chiếu") {
            loaiGiayTo = 2;
        } else {
            loaiGiayTo = 1;
        }
        var jData = {"hoVaTen": hoVaTen, "ngaySinh": ngaySinh, "danToc": danToc, "quocTich": quocTich, "gioiTinh": gioiTinh, "email": email, "soDienThoai": soDienThoai
            , "soNha": soNha, "loaiGiayTo": loaiGiayTo, "soGiayTo": soGiayTo, "tenDuong": tenDuong, "tenPhuong": tenPhuong, "tenHuyen": tenHuyen, "ngayCapGiayTo": ngayCap, "noiCapGiayTo": noiCap, "hoKhauThuongTru": diaChiDangKy};
        console.log(jData);
        $.ajax({
            type: "POST",
            url: url + "updateProfile",
            data: JSON.stringify(jData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                $('.loading').hide();
                if (data.error_code === 0) {
                    $form_modal.removeClass('is-visible');
                    $(".modal-body").text("Cập nhật thành công");
                    $("#myModal").modal('show');
                } else {
                    $(".modal-body").text("Cập nhật thất bại");
                    $("#myModal").modal('show');
                }
            },
            failure: function (errMsg) {
                $('.loading').hide();
                $(".modal-body").text("Không kết nối được tới máy chủ");
                $("#myModal").modal('show');
            }
        });

    });
    $("#btnChangePassword").on('click', function (event) {
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPassword").val();
        var reNewPassword = $("#reNewPassword").val();
        if (newPassword !== reNewPassword) {
            $(".modal-body").text("Xác nhận mật khẩu cũ không trùng khớp!");
            $("#myModal").modal('show');
        }
        $('.loading').show();
        var jData = {"oldPassword": oldPassword, "newPassword": newPassword};
        $.ajax({
            type: "POST",
            url: url + "changePassword",
            data: JSON.stringify(jData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                $('.loading').hide();
                if (data.error_code === 0) {
                    $(".modal-body").text("Cập nhật mật khẩu thành công");
                    $("#myModal").modal('show');
                    location.href = "/profile";
                } else {
                    $(".modal-body").text("Cập nhật mật khẩu thất bại");
                    $("#myModal").modal('show');
                }
            },
            failure: function (errMsg) {
                $('.loading').hide();
                $(".modal-body").text("Không kết nối được tới máy chủ");
                $("#myModal").modal('show');
            }
        });
    });
    $("#btnSyncAccount").on('click', function (event) {
        var soGiayTo = $("#soGiayTo").val();
        var hoVaTen = $("#hoVaTen").val();
        var ngaySinh = $("#ngaySinh").val();
        if (soGiayTo == "" || hoVaTen == "" || ngaySinh == "") {
            $(".modal-body").text("Bạn vui lòng nhập đầy đủ thông tin!");
            $("#myModal").modal('show');
            return false;
        }
        $('.loading').show();
        var jData = {"soGiayTo": soGiayTo, "hoVaTen": hoVaTen, "ngaySinh": ngaySinh};
        $.ajax({
            type: "POST",
            url: url + "checkSyncAccount",
            data: JSON.stringify(jData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                $('.loading').hide();
                if (data.error_code === 0) {
                    $("#soGiayTo").prop("disabled", true);
                    $("#ngaySinh").attr("disabled", true);
                    $("#hoVaTen").attr("disabled", true);
                    $("#btnSyncAccount").hide();
                    $("#btnSyncProfile").show();
                    $("#div-tenDangNhap").show();
                    $("#div-tenMatKhau").show();
                    $("#tenDangNhap").focus();
                    return false;
                } else if (data.error_code === 2) {
                    $(".modal-body").text("Tài khoản đã được đồng bộ");
                    $("#myModal").modal('show');
                    return false;
                } else if (data.error_code === 3) {
                    //chua co email
                    $("#soGiayTo").prop("disabled", true);
                    $("#ngaySinh").attr("disabled", true);
                    $("#hoVaTen").attr("disabled", true);
                    $("#btnSyncAccount").hide();
                    $("#btnSyncProfile").show();
                    $("#div-tenDangNhap").show();
                    $("#div-email").show();
                    $("#div-tenMatKhau").show();
                    $("#tenDangNhap").focus();
                    return false;
                    return false;
                } else {
                    $(".modal-body").text("Thông tin không chính xác hoạc chưa tồn tài. bạn vui lòng kiếm tra lại!");
                    $("#myModal").modal('show');
                    return false;
                }
            },
            failure: function (errMsg) {
                $('.loading').hide();
                $(".modal-body").text("Không kết nối được tới máy chủ");
                $("#myModal").modal('show');
            }
        });
    });
    $("#btnSyncProfile").on('click', function (event) {
        var soGiayTo = $("#soGiayTo").val();
        var hoVaTen = $("#hoVaTen").val();
        var ngaySinh = $("#ngaySinh").val();
        var tenDangNhap = $("#tenDangNhap").val();
        var matKhau = $("#matKhau").val();
        var email = $("#email").val();
        if (soGiayTo == "" || hoVaTen == "" || ngaySinh == "") {
            $(".modal-body").text("Bạn vui lòng nhập đầy đủ thông tin!");
            $("#myModal").modal('show');
            return false;
        }
        $('.loading').show();
        var jData = {"soGiayTo": soGiayTo, "hoVaTen": hoVaTen, "ngaySinh": ngaySinh, "tenDangNhap": tenDangNhap, "matKhau": matKhau, "email": email};
        $.ajax({
            type: "POST",
            url: url + "syncAccount",
            data: JSON.stringify(jData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                $('.loading').hide();
                if (data.error_code === 0) {
                    $(".modal-body").text("Hệ thống đã gửi email kích hoạt tài khoản về email. Vui lòng đăng nhập email để kích hoạt tài khoản!");
                    $("#myModal").modal('show');

                } else {
                    $(".modal-body").text("Tên đăng nhập đã được sử dụng. bạn vui lòng thử với tên khác!");
                    $("#myModal").modal('show');
                    return false;
                }
            },
            failure: function (errMsg) {
                $('.loading').hide();
                $(".modal-body").text("Không kết nối được tới máy chủ");
                $("#myModal").modal('show');
            }
        });
    });

});


//credits https://css-tricks.com/snippets/jquery/move-cursor-to-end-of-textarea-or-input/
jQuery.fn.putCursorAtEnd = function () {
    return this.each(function () {
        // If this function exists...
        if (this.setSelectionRange) {
            // ... then use it (Doesn't work in IE)
            // Double the length because Opera is inconsistent about whether a carriage return is one character or two. Sigh.
            var len = $(this).val().length * 2;
            this.setSelectionRange(len, len);
        } else {
            // ... otherwise replace the contents with itself
            // (Doesn't work in Google Chrome)
            $(this).val($(this).val());
        }
    });
};