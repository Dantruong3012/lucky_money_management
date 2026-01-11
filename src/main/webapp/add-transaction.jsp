<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <jsp:include page="components/head.jsp" />
    <title>Nhập Lì Xì Mới</title>
</head>
<body class="bg-cover page-add">

<div class="auth-card" style="width: 500px; max-width: 95%;">

    <h3 class="auth-header mb-4">
        <i class="fa-solid fa-envelope-circle-check"></i> Nhập Lì Xì
    </h3>

    <%-- ================= KHU VỰC HIỂN THỊ ALERT (THÊM MỚI) ================= --%>
    <%
        // 1. Lấy thông báo lỗi (từ req)
        String errorMsg = (String) request.getAttribute("errorMessage");

        // 2. Lấy thông báo thành công (từ Session)
        String successMsg = (String) session.getAttribute("successMessage");

        // 3. Lấy lại dữ liệu cũ (nếu có lỗi)
        String oldGiverName = (String) request.getAttribute("oldGiverName");
        String oldAmount = (String) request.getAttribute("oldAmount");
        String oldWish = (String) request.getAttribute("oldWish");
    %>

    <%-- Hiển thị lỗi --%>
    <% if (errorMsg != null) { %>
    <div class="alert alert-danger text-center fw-bold">
        <i class="fa-solid fa-triangle-exclamation"></i> <%= errorMsg %>
    </div>
    <% } %>

    <%-- Hiển thị thành công & Xóa ngay khỏi session --%>
    <% if (successMsg != null) { %>
    <div class="alert alert-success text-center fw-bold">
        <i class="fa-solid fa-check-circle"></i> <%= successMsg %>
    </div>
    <% session.removeAttribute("successMessage"); // Xóa để F5 không hiện lại %>
    <% } %>
    <%-- ================= KẾT THÚC ALERT ================= --%>


    <form action="TransactionServlet" method="post">

        <label class="fw-bold mb-2 text-dark w-100 text-start">Bạn đang thực hiện:</label>
        <div class="type-selector-group">
            <input type="radio" name="type" id="type-income" value="INCOME" class="btn-check-hidden" checked>
            <label for="type-income" class="type-option">
                <i class="fa-solid fa-hand-holding-dollar text-success"></i>
                <span>NHẬN LÌ XÌ</span>
            </label>

            <input type="radio" name="type" id="type-expense" value="EXPENSE" class="btn-check-hidden">
            <label for="type-expense" class="type-option">
                <i class="fa-solid fa-envelope-open-text text-warning"></i>
                <span>GỬI LÌ XÌ</span>
            </label>
        </div>

        <div class="mb-3 text-start">
            <label class="fw-bold mb-1 text-dark">Người lì xì / Được lì xì:</label>
            <div class="input-group">
                <span class="input-group-text bg-white text-danger"><i class="fa-solid fa-user"></i></span>
                <input type="text" name="giverName"
                       value="<%= (oldGiverName != null) ? oldGiverName : "" %>"
                       class="form-control" placeholder="VD: Bác Hùng, Cô Tư..." required>
            </div>
        </div>

        <div class="mb-3 text-start">
            <label class="fw-bold mb-1 text-dark">Mối quan hệ:</label>
            <div class="input-group">
                <span class="input-group-text bg-white text-warning"><i class="fa-solid fa-users"></i></span>
                <select name="relationship" class="form-select form-control">
                    <option value="Người thân">Người thân</option>
                    <option value="Bạn bè">Bạn bè</option>
                    <option value="Đồng nghiệp">Đồng nghiệp</option>
                    <option value="Khác">Khác</option>
                </select>
            </div>
        </div>

        <div class="mb-3 text-start">
            <label class="fw-bold mb-1 text-dark">Số tiền (VNĐ):</label>
            <div class="input-group">
                <span class="input-group-text bg-white text-success"><i class="fa-solid fa-money-bill-1-wave"></i></span>
                <input type="number" name="amount"
                       value="<%= (oldAmount != null) ? oldAmount : "" %>"
                       class="form-control fw-bold text-danger"
                       style="font-size: 1.1rem;" placeholder="VD: 500000"
                       min="1000" step="1000" required>
            </div>
            <div class="form-text text-end fst-italic">Tối thiểu: 1.000 đ</div>
        </div>

        <div class="mb-4 text-start">
            <label class="fw-bold mb-1 text-dark">Lời chúc:</label>
            <textarea name="wish" class="form-control" rows="2" placeholder="Nhập lời chúc..."><%= (oldWish != null) ? oldWish : "" %></textarea>
        </div>

        <button type="submit" class="btn btn-tet w-100 py-2 mb-4 shadow fw-bold fs-5">
            <i class="fa-solid fa-floppy-disk me-2"></i> LƯU VÀO SỔ
        </button>

        <div class="d-flex justify-content-between align-items-center border-top border-secondary border-opacity-25 pt-3">
            <a href="dashboard" class="btn btn-light rounded-pill shadow-sm text-secondary fw-bold px-3">
                <i class="fa-solid fa-arrow-left"></i> Trang Chủ
            </a>
            <a href="history-servelet" class="btn btn-warning rounded-pill shadow-sm text-dark fw-bold px-3">
                Sổ Lì Xì <i class="fa-solid fa-arrow-right"></i>
            </a>
        </div>
    </form>
</div>

<jsp:include page="components/script.jsp" />
</body>
</html>