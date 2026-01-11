<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <jsp:include page="components/head.jsp" />
</head>
<body class="bg-cover page-register">

<div class="auth-card">
  <h2 class="auth-header">Đăng Ký Tài Khoản</h2>

  <%
    String error = (String) request.getAttribute("error");
    String alert = (String) request.getAttribute("alert");
  %>
  <% if (error != null) { %>
  <div class="alert alert-danger text-center mb-3 py-2 small fw-bold" role="alert">
    <i class="fa-solid fa-triangle-exclamation"></i> <%= error %>
  </div>
  <% } %>
  <% if (alert != null) { %>
  <div class="alert alert-success text-center mb-3 py-2 small fw-bold" role="alert">
    <i class="fa-solid fa-check-circle"></i> <%= alert %>
  </div>
  <% } %>
  <form action="register" method="post">
    <div class="mb-3 text-start">
      <label class="fw-bold mb-1">Tên đăng nhập</label>
      <input type="text" name="username" class="form-control"
             value="${oldUserName}" placeholder="Nhập tên..." required>
    </div>

    <div class="mb-3 text-start">
      <label class="fw-bold mb-1">Tên hiển thị</label>
      <input type="text" name="displayname" class="form-control"
             value="${oldDisplayName}" placeholder="Nhập tên hiển thị..." required>
    </div>

    <div class="mb-3 text-start">
      <label class="fw-bold mb-1">Mật khẩu</label>
      <input type="password" name="password" class="form-control" placeholder="Nhập mật khẩu..." required>
    </div>

    <div class="mb-3 text-start">
      <label class="fw-bold mb-1">Nhập lại mật khẩu</label>
      <input type="password" name="confirm_password" class="form-control" placeholder="Xác nhận..." required>
    </div>

    <div class="mb-4 text-start">
      <label class="text-danger fw-bold small">★ Mã bảo mật (6-7 ký tự)</label>
      <input type="text" name="security_code" class="form-control border-warning"
             value="${oldSecurityCode}" placeholder="Để khôi phục MK..." minlength="6" maxlength="7" required>
    </div>

    <button type="submit" class="btn btn-tet w-100">ĐĂNG KÝ NGAY</button>

    <div class="mt-3">
      <a href="login.jsp" class="text-decoration-none text-secondary">
        <i class="fa-solid fa-arrow-left"></i> Quay lại Đăng Nhập
      </a>
    </div>
  </form>
</div>

<jsp:include page="components/script.jsp" />
</body>
</html>