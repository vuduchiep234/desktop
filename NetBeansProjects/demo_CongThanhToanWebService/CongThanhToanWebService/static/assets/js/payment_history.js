$(function () {
    var documentHeight = window.screen.availHeight;
    var headerHight = $('header').height();
    var footerHight = $('footer').height();
    var minHeight = documentHeight - (footerHight + headerHight);
    $('.main-content').prop('style', 'min-height:' + minHeight + 'px');
    $('.nav.menu-item .active').removeClass('active');
    $('.payment-history').addClass('active');
    $('#btnSearch').click(function () {
        transaction_history.getByCondition();
    });

});

var transaction_history = {
    url: '/payment/transaction/history',
    getByCondition: function () {
        $('#smNotFound').remove();
        var valueSearch = $('#input-search').val();
        
        if (valueSearch.trim().length === 0 ) {
            $('<p id="smNotFound" class="mb-2" style="color: #d60f0f !important;font-size:12px;">Nhập thông tin để tra cứu</p>').insertBefore('#txt-id');
            return;
        }
        var params = '?valueSearch=' + valueSearch;
        $.ajax({
            url: transaction_history.url + params,
            type: 'GET'
        }).done(function (respData) {
            if (respData.error_code === 'SUCCESSFUL') {
                if(respData.data.length > 0){
                    var data = respData.data[0];
                    var time = data.payDate;
//                    var date = new Date(data.payDate*1000);
                    var year = time.toString().substring(0,4);
                    var month = time.toString().substring(5,6);
                    var day = time.toString().substring(7,8);
                    var hours = time.toString().substring(9,10);
                    var minutes = time.toString().substring(11,12);
                    var seconds = time.toString().substring(0,3);

                    
                    var formattedTime = year+"-"+month+"-"+day+" " +hours + ':' + minutes.substr(-2) + ':' + seconds.substr(-2);
                    var content = '<div class="document-parent">' +
                            '<h2>Thông tin giao dịch</h2>' +
                            '<hr style="border: 2px solid #c4c4c4; width: 25%;">' +
                            '<div class="detail">' +
                            '<div class="row" style="margin-bottom:1.3rem;">' +
                            '<div class="col-md-3 font-weight-bold">Mã số hóa đơn:</div>' +
                            '<div class="col-md-9 " style="font-family:Roboto-Regular">' + data.orderPayId + '</div>' +
                            '</div>' +
                            '<div class="row" style="margin-bottom:1.3rem;">' +
                            '<div class="col-md-3 font-weight-bold">Số tiền:</div>' +
                            '<div class="col-md-9" style="font-family:Roboto-Regular">' +
                            data.amount +
                            '</div>' +
                            '</div>' +
                            '<div class="row" style="margin-bottom:1.3rem;">' +
                            '<div class="col-md-3 font-weight-bold">Cổng thanh toán:</div>' +
                            '<div class="col-md-9 " style="font-family:Roboto-Regular">' + data.payCenter + '</div>' +
                            '</div>' +
                            '<div class="row" style="margin-bottom:1.3rem;">' +
                            '<div class="col-md-3 font-weight-bold ">Mã giao dịch:</div>' +
                            '<div class="col-md-9 " style="font-family:Roboto-Regular">' + data.payTransactionNo + '</div>' +
                            '</div>' +
                            '<div class="row" style="margin-bottom:1.3rem;">' +
                            '<div class="col-md-3 font-weight-bold ">Mã yêu cầu:</div>' +
                            '<div class="col-md-9 " style="font-family:Roboto-Regular">' + data.requestCode + '</div>' +
                            '</div>' +
                            '<div class="row" style="margin-bottom:1.3rem;">' +
                            '<div class="col-md-3 font-weight-bold label">Thông tin thanh toán:</div>' +
                            '<div class="col-md-9" style="font-family:Roboto-Regular" >' +
                            data.orderInfo +
                            '</div>' +
                            '</div>' +
                            '<div class="row" style="margin-bottom:1.3rem;">' +
                            '<div class="col-md-3 font-weight-bold ">Thời gian giao dịch:</div>' +
                            '<div class="col-md-9 " style="font-family:Roboto-Regular">' +formattedTime + '</div>' +
                            '</div>' +
                            '</div>' +
                            '</div>' +
                            '<button type="button" class="btn btn-secondary btn-back-to-form mt-4 mb-5">Quay lại</button>';
                    $('.main-content .container').html(content);
                    $('.btn-back-to-form').click(function () {
                        window.location = '/payment/history';
                    });
                    
                }else{
                    $('<p id="smNotFound" class="mb-2" style="color: #d60f0f !important;font-size:12px;">Không tim thấy thông tin</p>').insertBefore('#input-search');
                }
                
            } else {
                $('<p id="smNotFound" class="mb-2" style="color: #d60f0f !important;font-size:12px;">' + respData.error_message + '</p>').insertBefore('#input-search');
            }
        });

    }
};
