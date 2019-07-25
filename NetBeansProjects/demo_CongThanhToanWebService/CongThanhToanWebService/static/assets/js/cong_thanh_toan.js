$(function () {
    $('#btnLePhiXuLyThuTucHanhChinh').hover(function () {
        $('#btnLePhiXuLyThuTucHanhChinh img').prop('src', '/assets/img/icon_le_phi.png');
    }, function () {
        $('#btnLePhiXuLyThuTucHanhChinh img').prop('src', '/assets/img/icon_le_phi_blue.png');
    });
    $('#btnTienDien').hover(function () {
        $('#btnTienDien img').prop('src', '/assets/img/icon_tien_dien.png');
    }, function () {
        $('#btnTienDien img').prop('src', '/assets/img/icon_tien_dien_blue.png');
    });
    $('#btnTienNuoc').hover(function () {
        $('#btnTienNuoc img').prop('src', '/assets/img/icon_tien_nuoc.png');
    }, function () {
        $('#btnTienNuoc img').prop('src', '/assets/img/icon_tien_nuoc_blue.png');
    });
    $('#btnViPhamHanhChinh').hover(function () {
        $('#btnViPhamHanhChinh img').prop('src', '/assets/img/icon_tien_vi_pham_hanh_chinh.png');
    }, function () {
        $('#btnViPhamHanhChinh img').prop('src', '/assets/img/icon_tien_vi_pham_hanh_chinh_blue.png');
    });
    $('#btnNapTienDienThoai').hover(function () {
        $('#btnNapTienDienThoai img').prop('src', '/assets/img/icon_nap_tien_dt.png');
    }, function () {
        $('#btnNapTienDienThoai img').prop('src', '/assets/img/icon_nap_tien_dt_blue.png');
    });

});