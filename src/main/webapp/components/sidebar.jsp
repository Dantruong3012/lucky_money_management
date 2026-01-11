<%@ page contentType="text/html; charset=UTF-8" %>
<div class="glass-sidebar">
    <div class="sidebar-brand">
        <i class="fa-solid fa-horse-head fa-lg me-2"></i> TẾT 2026
    </div>

    <div class="py-4"> <ul class="nav flex-column">
        <li class="nav-item">
            <a class="nav-link ${pageContext.request.requestURI.contains('dashboard') ? 'active' : ''}"
               href="${pageContext.request.contextPath}/dashboard">
                <i class="fa-solid fa-chart-pie me-3"></i> Tổng Quan
            </a>
        </li>
        <li class="nav-item">
            <a class="nav-link ${pageContext.request.requestURI.contains('history-servelet') ? 'active' : ''}"
               href="${pageContext.request.contextPath}/history-servelet">
                <i class="fa-solid fa-book-open me-3"></i> Sổ Lì Xì
            </a>
        </li>

        <li class="nav-item mt-5 pt-3 mx-3 border-top border-light border-opacity-25">
            <a class="nav-link text-warning" href="${pageContext.request.contextPath}/logout">
                <i class="fa-solid fa-power-off me-3"></i> Đăng Xuất
            </a>
        </li>
    </ul>
    </div>
</div>