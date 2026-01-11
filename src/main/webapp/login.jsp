<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <jsp:include page="components/head.jsp" />
</head>
<body class="bg-cover page-login">

<div class="auth-card">
  <h2 class="auth-header">Đăng Nhập Xuân</h2>
  <p class="text-muted mb-4">Chào mừng năm mới 2026!</p>

  <%
    String errorMessage = (String) request.getAttribute("errorMessage");
    String oldUsername = (String) request.getAttribute("oldUsername"); // Lấy lại tên cũ
  %>

  <% if (errorMessage != null) { %>
  <div class="alert alert-danger text-center mb-3 py-2 small fw-bold" role="alert">
    <i class="fa-solid fa-triangle-exclamation"></i> <%= errorMessage %>
  </div>
  <% } %>

  <form action="${pageContext.request.contextPath}/login-servelet" method="post">

    <div class="mb-3 text-start">
      <label class="fw-bold mb-1">Tài khoản</label>
      <input type="text" name="username"
             value="<%= (oldUsername != null) ? oldUsername : "" %>"
             class="form-control" placeholder="Username" required>
    </div>

    <div class="mb-3 text-start">
      <label class="fw-bold mb-1">Mật khẩu</label>
      <input type="password" name="password" class="form-control" placeholder="Password" required>
    </div>

    <button type="submit" class="btn btn-tet w-100 mt-2">MỞ CỬA ĐÓN LỘC</button>

    <div class="mt-3 d-flex justify-content-between small">
      <a href="register.jsp" class="text-danger fw-bold text-decoration-none">Đăng Ký</a>
      <a href="forgot-password.jsp" class="text-secondary text-decoration-none">Quên MK?</a>
    </div>
  </form>
</div>

<jsp:include page="components/script.jsp" />
</body>
</html>