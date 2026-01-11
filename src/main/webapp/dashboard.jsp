<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="vi">
<head>
  <jsp:include page="components/head.jsp" />
  <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
  <title>Tổng Quan 2026</title>
</head>
<body class="bg-cover page-dashboard">

<div class="unified-glass-container">

  <jsp:include page="components/sidebar.jsp" />

  <div class="glass-content">

    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="text-white fw-bold text-uppercase" style="text-shadow: 0 2px 4px rgba(0,0,0,0.3);">
        <i class="fa-solid fa-home me-2"></i> Tổng Quan
      </h2>
      <div class="bg-white bg-opacity-75 rounded-pill px-4 py-2 text-danger fw-bold shadow">
        Chào, ${displayName}! 🧧
      </div>
    </div>

    <div class="row g-4">
      <div class="col-md-4">
        <div class="stat-glass-card">
          <div class="stat-title text-success">
            <i class="fa-solid fa-arrow-trend-up me-2"></i>Tổng Thu
          </div>
          <div class="stat-value text-success">
            +<fmt:formatNumber value="${totalIncome}" pattern="#,###"/>
            <span class="currency-unit">đ</span>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="stat-glass-card">
          <div class="stat-title text-danger">
            <i class="fa-solid fa-arrow-trend-down me-2"></i>Tổng Chi
          </div>
          <div class="stat-value text-danger">
            -<fmt:formatNumber value="${totalExpense}" pattern="#,###"/>
            <span class="currency-unit">đ</span>
          </div>
        </div>
      </div>

      <div class="col-md-4">
        <div class="stat-glass-card">
          <div class="stat-title text-primary">
            <i class="fa-solid fa-wallet me-2"></i>Số Dư Hiện Tại
          </div>
          <div class="stat-value text-primary">
            <fmt:formatNumber value="${balances}" pattern="#,###"/>
            <span class="currency-unit">đ</span>
          </div>
        </div>
      </div>
    </div>

    <div class="row mt-4">
      <div class="col-12">
        <div class="stat-glass-card chart-card-container p-3" style="height: auto; align-items: stretch;">

          <div class="d-flex justify-content-between align-items-center mb-2 px-2">
            <h5 class="fw-bold shadow-sm" style="color: #333; text-shadow: 1px 1px 0 rgba(255,255,255,0.5);">
              <i class="fa-solid fa-chart-line me-2"></i>Tiền Mừng Tuổi (10 ngày gần nhất)
            </h5>
          </div>

          <div style="position: relative; height: 350px; width: 100%;">
            <canvas id="financeChart"></canvas>
          </div>

        </div>
      </div>
    </div>

    <div class="footer-tet">
      <h4>🎉 Chúc Mừng Năm Mới Xuân Bính Ngọ 2026 🎉</h4>
    </div>

  </div>
</div>

<jsp:include page="components/script.jsp" />

<script>
  const ctx = document.getElementById('financeChart').getContext('2d');

  // Gradient màu giữ nguyên
  let gradientIncome = ctx.createLinearGradient(0, 0, 0, 400);
  gradientIncome.addColorStop(0, 'rgba(25, 135, 84, 0.4)');
  gradientIncome.addColorStop(1, 'rgba(25, 135, 84, 0.0)');

  let gradientBalance = ctx.createLinearGradient(0, 0, 0, 400);
  gradientBalance.addColorStop(0, 'rgba(13, 110, 253, 0.3)');
  gradientBalance.addColorStop(1, 'rgba(13, 110, 253, 0.0)');

  const myChart = new Chart(ctx, {
    type: 'line',
    data: {
      labels: [<%= request.getAttribute("chartLabels") %>],
      datasets: [
        // ... (Phần data datasets giữ nguyên) ...
        {
          label: 'Số Dư Tích Lũy',
          data: [<%= request.getAttribute("chartBalance") %>],
          borderColor: '#0d6efd', backgroundColor: gradientBalance,
          borderWidth: 3, tension: 0.4, fill: true, pointRadius: 4, pointHoverRadius: 6
        },
        {
          label: 'Thu Nhập',
          data: [<%= request.getAttribute("chartIncome") %>],
          borderColor: '#198754', backgroundColor: gradientIncome,
          borderWidth: 2, tension: 0.3, fill: true, pointRadius: 3
        },
        {
          label: 'Chi Tiêu',
          data: [<%= request.getAttribute("chartExpense") %>],
          borderColor: '#dc3545', borderWidth: 2, borderDash: [5, 5],
          tension: 0.3, fill: false, pointRadius: 3
        }
      ]
    },
    options: {
      responsive: true, maintainAspectRatio: false,
      interaction: { mode: 'index', intersect: false },
      plugins: {
        legend: {
          position: 'top',
          labels: {
            // [SỬA 3] Đổi màu chữ chú thích sang màu xám đậm
            color: '#333',
            font: { size: 12, family: "'Nunito', sans-serif", weight: 'bold' }
          }
        },
        tooltip: {
          backgroundColor: 'rgba(0, 0, 0, 0.8)', titleColor: '#fff', bodyColor: '#fff',
          borderColor: 'rgba(255, 255, 255, 0.2)', borderWidth: 1
        }
      },
      scales: {
        x: {
          // [SỬA 3] Đổi màu chữ trục X sang xám đậm
          ticks: { color: '#555', font: { weight: 'bold'} },
          grid: { display: false }
        },
        y: {
          // [SỬA 3] Đổi màu chữ trục Y và lưới sang màu tối
          ticks: { color: '#555', font: { weight: 'bold'} },
          grid: { color: 'rgba(0, 0, 0, 0.1)' }, // Lưới màu đen mờ
          beginAtZero: true
        }
      }
    }
  });
</script>

</body>
</html>