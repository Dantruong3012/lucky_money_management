<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <jsp:include page="components/head.jsp" />
  <title>Sổ Lì Xì</title>
</head>
<body class="bg-cover page-history">

<div class="unified-glass-container">
  <jsp:include page="components/sidebar.jsp" />

  <div class="glass-content">
    <div class="d-flex justify-content-between align-items-center mb-4">
      <h2 class="text-white fw-bold text-uppercase" style="text-shadow: 0 2px 4px rgba(0,0,0,0.3);">
        <i class="fa-solid fa-list-ul me-2"></i> Nhật Ký Lì Xì
      </h2>
      <a href="add-transaction.jsp" class="btn btn-tet">
        <i class="fa-solid fa-plus me-2"></i> Ghi Mới
      </a>
    </div>

    <div class="glass-table">
      <table class="table table-hover align-middle mb-0" style="background: transparent;">
        <thead class="table-danger">
        <tr>
          <th>Người</th>
          <th>Mối quan hệ</th>
          <th>Số Tiền</th>
          <th>Lời Chúc</th>
          <th>Xóa</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${transactionList}" var="t">
          <tr style="background: transparent;">
            <td class="fw-bold">${t.giverName}</td>

            <td>
              <c:set var="badgeClass" value="bg-secondary"/> <c:if test="${t.relationship.contains('Gia đình')}">
              <c:set var="badgeClass" value="bg-success"/>
            </c:if>
              <c:if test="${t.relationship.contains('Công ty') or t.relationship.contains('Đồng nghiệp')}">
                <c:set var="badgeClass" value="bg-primary"/>
              </c:if>
              <c:if test="${t.relationship.contains('Người yêu') or t.relationship.contains('Vợ chồng')}">
                <c:set var="badgeClass" value="bg-danger"/>
              </c:if>
              <c:if test="${t.relationship.contains('Bạn bè')}">
                <c:set var="badgeClass" value="bg-info"/>
              </c:if>
              <c:if test="${t.relationship.contains('Hẹn hò') or t.relationship.contains('Mua sắm') or t.relationship.contains('Ăn uống')}">
                <c:set var="badgeClass" value="bg-warning"/>
              </c:if>

              <span class="badge ${badgeClass}">
                  ${t.relationship}
              </span>
            </td>
            <c:choose>
              <c:when test="${t.type == 'INCOME'}">
                <td class="fw-bold text-success">
                  + <fmt:formatNumber value="${t.amount}" pattern="#,###"/> đ
                </td>
              </c:when>
              <c:otherwise>
                <td class="fw-bold text-danger">
                  - <fmt:formatNumber value="${t.amount}" pattern="#,###"/> đ
                </td>
              </c:otherwise>
            </c:choose>

            <td class="fst-italic text-muted small">${t.wish}</td>

            <td class="text-center align-middle">
              <a href="delete-servelet?id=${t.id}"
                 class="btn btn-sm btn-outline-danger border-0"
                 onclick="return confirm('Xóa thật nhé?');">

                <i class="fa fa-trash"></i>

              </a>
            </td>
          </tr>
        </c:forEach>

        <c:if test="${empty transactionList}">
          <tr>
            <td colspan="5" class="text-center py-4 text-muted">
              Chưa có giao dịch nào. Hãy thêm mới nhé!
            </td>
          </tr>
        </c:if>

        </tbody>
      </table>
    </div>

  </div>
</div>

<jsp:include page="components/script.jsp" />
</body>
</html>