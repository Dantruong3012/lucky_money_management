document.addEventListener("DOMContentLoaded", function() {
    const body = document.querySelector("body");

    function createPetal() {
        const petal = document.createElement("div");
        petal.classList.add("petal");

        // Random Hoa Mai (Vàng) hoặc Đào (Hồng)
        if (Math.random() > 0.5) {
            petal.classList.add("apricot");
        } else {
            petal.classList.add("peach");
        }

        // Vị trí xuất phát ngẫu nhiên
        petal.style.left = Math.random() * 100 + "vw";

        // Tốc độ rơi: 4s đến 9s
        const duration = Math.random() * 5 + 4;
        petal.style.animationDuration = duration + "s";

        // Kích thước ngẫu nhiên: 10px đến 20px
        const size = Math.random() * 10 + 10;
        petal.style.width = size + "px";
        petal.style.height = size + "px";

        body.appendChild(petal);

        // Xóa hoa ngay khi nó rơi xong để giải phóng bộ nhớ (tránh lag)
        setTimeout(() => {
            petal.remove();
        }, duration * 1000);
    }

    // Tạo hoa mỗi 400ms (giảm tần suất một chút để mượt hơn)
    setInterval(createPetal, 400);
});