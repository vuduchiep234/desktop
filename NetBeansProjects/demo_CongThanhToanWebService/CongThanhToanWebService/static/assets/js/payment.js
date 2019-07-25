$(function () {
    var result, obj;

    var smNotFound = document.getElementById("smNotFound");

//    $('#btnPayVNPay').on('click', function (event) {
//        //example using post method
//        window.location.replace("http://localhost:2005/vnpay/payment");
//
//        return fale;
//    });
    var url = new URL(window.location.href);
    var maHoSo = url.searchParams.get("maHoSo");
    
    if(maHoSo !== 'null' && maHoSo !== null){
        $("#txtMaHoaDon").val(maHoSo);
        searchOrder();
    }
    
    
    $('#btnSearch').on('click', function (event) {
        searchOrder();
        //example using post method
        
    });

    $('#btnBack').on('click', function (event) {
        //example using post method
        window.location = '/';

        return false;
    });
//    $('#btnSearch').click();
});

function searchOrder(){
    $(".loading").show();
        var maHoaDon = $("#txtMaHoaDon").val();
//        maHoaDon = '38.98.H29-210519-0013';
        obj = {
            maHoaDon: maHoaDon
        };
        $.ajax({
            type: 'POST',
            url: '/payment/timKiem/order',
            data: JSON.stringify(obj)
        }).done(function (respData) {
            $(".loading").hide();
            console.log(respData);
            if (respData.error_code === "SUCCESSFUL") {
                respData = respData.data;
                $('.main-content .container > .text-center').hide();
                var content =
                        '<div class="document-parent">' +
                        '<h2>Thông tin thanh toán</h2>' +
                        '<hr>' +
                        '<div class="detail">' +
                        '   <div class="row">' +
                        '       <div class="col-md-4">' +
                        '           <div class="row">' +
                        '               <div class="col-md-12 font-weight-bold">Tên người nộp:</div>' +
                        '               <div class="col-md-12 value">' + respData.tenNguoiNop + '</div>' +
                        '           </div>' +
                        '           <div class="row">' +
                        '               <div class="col-md-12 font-weight-bold ">Số CMND:</div>' +
                        '               <div class="col-md-12 value">' + respData.soCMND + '</div>' +
                        '           </div>' +
                        '           <div class="row">' +
                        '               <div class="col-md-12 font-weight-bold ">Địa chỉ:</div>' +
                        '               <div class="col-md-12 value">' + respData.diaChi + '</div>' +
                        '           </div>' +
                        '       </div>' +
                        '       <div class="col-md-8">' +
                        '           <div class="table-responsive">' +
                        '               <table class="table table-hover table-striped">' +
                        '                   <thead style="background:#466f98;color:#fff;">' +
                        '                       <tr>' +
                        '                           <th style="min-width:75px;">Số tiền</th>' +
                        '                           <th style="min-width:145px;">Trạng thái</th>' +
                        '                           <th>Mô tả</th>' +
                        '                           <th style="min-width:115px;">#</th>' +
                        '                       </tr>' +
                        '                   </thead>' +
                        '                   <tbody>';
                var item;
                for (var i = 0; i < respData.danhSachThanhToan.length; i++) {
                    item = respData.danhSachThanhToan[i];
                    var btnThanhToan = "<td></td>";
                    if(item.trangThai === 0){
                        btnThanhToan = '<td><button type="button" class="btn btn-pay btn-sm btn-info" data-order-id="' + item.orderId + '" data-partnerCode="' + item.partnerCode + '" data-dichvu-id="' + respData.dichvuId + '">Thanh toán</button></td>';
                    }
                    content += '<tr>' +
                            '<td>' + item.soTien + '</td>' +
                            '<td><div class="status ' + (item.trangThai === 0 ? 'unpaid text-danger">Chưa thanh toán' : 'paid text-success">Đã thanh toán') + '</div></td>' +
                            '<td>' + item.moTa + '</td>' +
                            btnThanhToan +
                            '</tr>';
                }
                content += '                </tbody>' +
                        '               </table>' +
                        '               <span class="notifi-register" style="font-size: 14px;font-style: italic;color: red;"></span>'+
                        '           </div>' +
                        '       </div>' +
                        '   </div>' + // end row
                        '</div>' + // end detail
                        '</div>' + // end document-parent
                        '<button type="button" class="btn btn-done mt-4">Hoàn thành</button>';
                $('#search-result').html(content);

                // event
                $('.btn-done').click(function () {
                    $('.main-content .container > .text-center').show();
                    $('#search-result').empty();
                });
                $('.btn-pay').click(function () {
                    payHandle($(this));
                });

            } else {
                smNotFound.setAttribute('style', 'display: block;color: #d60f0f !important');
            }

        });
        return false;
}
function payHandle(jbtn) {
    var isPaid = jbtn.parents('tr').find('.status').prop('class').includes('paid');
    if (!isPaid) {
        return;
    }
    var url = new URL(window.location.href);
    var lbSotien = jbtn.parents('tr').find('td:first-child').text();
    var dichvuId = jbtn.attr('data-dichvu-id');
    var orderInfo = jbtn.parents('tr').find('td:nth-child(3)').text();
    var orderId = jbtn.attr("data-order-id");
    var partnerCode = jbtn.attr("data-partnerCode");
    var returnUrl = url.searchParams.get("returnUrl");
    var postData = {
        'soTien': Number(lbSotien),
        'dichvuId': Number(dichvuId),
        'orderInfo': orderInfo,
        'orderId': orderId,
        'partnerCode': partnerCode,
        'returnUrl':returnUrl
    };
    $(".loading").show();
    $.ajax({
        type: 'POST',
        url: '/payment/urlredirect/get',
        data: JSON.stringify(postData)
    }).done(function (data) {
        $(".loading").hide();
        if (data.error_code === 'SUCCESSFUL') {
                location.href = data.data;
        } else {
            $(".table-responsive").remove(".notifi-register");
            $(".notifi-register").html('<span class"notifi-register" style="font-size: 14px;font-style: italic;color: red;">Tài khoản chưa đăng ký với kênh thanh toán.</span>');
        }
    });
}