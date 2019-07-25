<style>

.avata-login {
    border-radius: 50%;
    max-width: 40px;
    max-height: 40px;
}
.dropdown-toggle{
    background: none !important;
    
    border: 0px !important;
}
.dropdown-menu a {
  padding: 0px 16px !important;
  text-decoration: none;
}


.has-sub:hover .dropdown-menu {
  display: block;
}
.has-sub .dropdown-menu a {
    padding: 10px !important;
}
</style>
<section id="nav-wrap">
            <nav class="navbar navbar-inverse navbar-expand-sm p-0" id="my-nav">
                <div class="container">
                    <!-- Collect the nav links, forms, and other content for toggling -->
                    <div class="collapse navbar-collapse no-padding" id="main-menu">
                        <ul class="nav navbar-nav menu-item ">
                            <li class="nav-item active">
                                <a class="nav-link" href="/">
                                    Trang chủ
                                </a>
                            </li>
                            <li class="nav-item dropdown has-sub">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                  Thanh toán phí thủ tục hành chính
                                </a>
                                <ul class="dropdown-menu p-0" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/payment/timKiem/hoaDon?loai=thuTucHanhChinh">Thanh toán thủ tục hành chính</a></li>
                                  <li><a class="dropdown-item" href="#">Danh sách đơn vị tham gia</a></li>
                                  <li><a class="dropdown-item" href="#">Các bước thực hiện</a></li>
                                </ul>
                            </li>
                            <li class="nav-item dropdown has-sub">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                   Thanh toán khác
                                </a>
                                <ul class="dropdown-menu p-0" aria-labelledby="navbarDropdown">
                                
                                  <li><a class="dropdown-item" href="/payment/timKiem/hoaDon?loai=viPhamHanhChinh">Vi phạm hành chính</a></li>
                                  <li><a class="dropdown-item" href="/payment/timKiem/hoaDon?loai=napTienDienThoai">Nạp tiền điện thoại</a></li>
                                  <li><a class="dropdown-item" href="/payment/timKiem/hoaDon?loai=tienDien">Điện</a></li>
                                  <li><a class="dropdown-item" href="/payment/timKiem/hoaDon?loai=tienNuoc">Nước</a></li>
                                  
                                </ul>
                            </li>
                            <li class="nav-item payment-history">
                                <a class="nav-link" href="/payment/history" >
                                   Tra cứu giao dịch
                                </a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="#" >
                                   Trợ giúp
                                </a>
                            </li>
                            <li class="nav-item ">
                                <a class="nav-link" href="#" >
                                   Liên hệ
                                </a>
                            </li>
                        </ul>

                    </div><!-- /.navbar-collapse -->
                </div><!-- /.container-fluid -->
            </nav>
        </section>