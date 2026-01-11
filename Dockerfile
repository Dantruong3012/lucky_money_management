# --- Giai đoạn 1: Build Code (Dùng Gradle để tạo ra file .war) ---
FROM gradle:jdk21 AS build
WORKDIR /app
COPY . .
# Cấp quyền và chạy lệnh build (bỏ qua test để chạy cho nhanh)
RUN chmod +x gradlew
RUN ./gradlew clean build -x test --no-daemon

# --- Giai đoạn 2: Chạy ứng dụng (Dùng Tomcat để chạy file .war vừa tạo) ---
FROM tomcat:10.1-jdk21
RUN rm -rf /usr/local/tomcat/webapps/*

# Copy kết quả từ Giai đoạn 1 (build) sang Giai đoạn 2
COPY --from=build /app/build/libs/*.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 8080
CMD ["catalina.sh", "run"]