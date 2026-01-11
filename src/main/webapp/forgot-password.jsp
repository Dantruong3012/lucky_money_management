<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="components/head.jsp" />
    <title>Khôi Phục Mật Khẩu</title>
</head>
<body class="bg-cover page-forgot">

<div class="auth-card">
    <h2 class="auth-header">
        <i class="fa-solid fa-key me-2"></i> Khôi Phục
    </h2>

    <p class="text-dark fw-bold mb-4" style="text-shadow: 0 1px 2px rgba(255,255,255,0.8);">
        Nhập mã bảo mật bạn đã tạo khi đăng ký để lấy lại mật khẩu.
    </p>

    <form action="ForgotPasswordServlet" method="post">
        <div class="mb-3 text-start">
            <label class="fw-bold mb-1 ps-1">Tên tài khoản</label>
            <div class="input-group">
                <span class="input-group-text bg-white border-0 text-danger"><i class="fa-solid fa-user"></i></span>
                <input type="text" name="username" class="form-control" placeholder="Nhập username..." required>
            </div>
        </div>

        <div class="mb-4 text-start">
            <label class="fw-bold text-danger mb-1 ps-1">Mã bảo mật (Code)</label>
            <div class="input-group">
                <span class="input-group-text bg-white border-0 text-warning"><i class="fa-solid fa-shield-halved"></i></span>
                <input type="text" name="security_code" class="form-control border-warning" placeholder="Mã 6-7 ký tự..." required>
            </div>
        </div>

        <button type="submit" class="btn btn-tet w-100 shadow-lg">
            XÁC MINH & ĐỔI MẬT KHẨU
        </button>

        <div class="mt-4 border-top pt-3 border-secondary border-opacity-25">
            <a href="login.jsp" class="btn btn-light w-100 rounded-pill fw-bold text-secondary">
                <i class="fa-solid fa-arrow-left"></i> Quay lại Đăng Nhập
            </a>
        </div>
    </form>
</div>

<jsp:include page="components/script.jsp" />
</body>
</html>