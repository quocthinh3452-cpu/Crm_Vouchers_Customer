# 🎟️ Hệ Thống Quản Lý Voucher

## 🛠 Công Nghệ Sử Dụng

### Backend (BE)
![Java](https://img.shields.io/badge/Java_17-ED8B00?style=for-the-badge&logo=java&logoColor=white) ![Spring Boot](https://img.shields.io/badge/Spring_Boot_3-6DB33F?style=for-the-badge&logo=spring&logoColor=white)

*   **Clean Architecture & DDD:** Chia tách rõ ràng các lớp Domain, Application, Infrastructure và Presentation.
*   **Spring Data JPA & MySQL:** Quản lý thực thể và tương tác cơ sở dữ liệu.
*   **Jakarta Validation:** Kiểm soát dữ liệu đầu vào.

### Frontend (FE)
![React](https://img.shields.io/badge/React_JS-20232A?style=for-the-badge&logo=react&logoColor=61DAFB) ![Vite](https://img.shields.io/badge/Vite-B73BFE?style=for-the-badge&logo=vite&logoColor=FFD62E)

*   **React JS & Vite:** Framework hiện đại cho giao diện người dùng.
*   **Axios:** Gọi API từ Backend.
*   **Bootstrap 5 & Bootstrap Icons:** Thiết kế giao diện Dashboard chuyên nghiệp và responsive.

---

## 🏗 Cấu Trúc Database
Hệ thống sử dụng 3 bảng chính với các ràng buộc chặt chẽ:

*   **Users:** Lưu trữ thông tin khách hàng (Họ tên, Email, SĐT).
*   **Vouchers:** Quản lý mã giảm giá, phần trăm chiết khấu, số lượng và ngày hết hạn.
*   **Voucher_usages:** Lưu trữ lịch sử khi khách hàng sử dụng mã giảm giá.

---

## 🚀 Hướng Dẫn Cài Đặt & Chạy Dự Án

### 1. Chuẩn bị Backend (Spring Boot)

**Cấu hình Database:**
*   Mở MySQL (XAMPP/WAMP) và tạo database tên là `voucher_management`.
*   Chạy các lệnh SQL tạo bảng đã cung cấp để khởi tạo cấu trúc.

**Cấu hình kết nối:**
*   Mở file `src/main/resources/application.properties`.
*   Cập nhật `spring.datasource.username` và `password` theo máy của bạn.

**Chạy dự án:**
*   Mở project bằng IntelliJ IDEA hoặc Eclipse.
*   Chạy file `TemplateArchitectureApplication.java`.
*   Backend sẽ chạy tại: `http://localhost:8080`.

### 2. Chuẩn bị Frontend (React)

**Cài đặt thư viện:**
*   Mở terminal tại thư mục dự án frontend.
*   Chạy lệnh: `npm install` để tải các package.

**Cài đặt Icons:**
*   Chạy lệnh: `npm install bootstrap-icons`.

**Chạy ứng dụng:**
*   Chạy lệnh: `npm run dev`.
*   Truy cập giao diện tại: `http://localhost:5173`.

---

## 💡 Chức năng Chính

*   **Quản lý Voucher:** Thêm mới, cập nhật, xóa, tìm kiếm mã code, và theo dõi trạng thái hoạt động.
  
<img width="1920" height="1080" alt="Ảnh chụp màn hình 2026-05-08 195720" src="https://github.com/user-attachments/assets/f4c43f7d-d473-40de-9a23-58fa85328d18" />

*   **Quản lý User:** Lưu trữ danh sách khách hàng, kiểm tra trùng lặp email.
  
 <img width="1920" height="1080" alt="Ảnh chụp màn hình 2026-05-08 195656" src="https://github.com/user-attachments/assets/481a6b25-f22e-49b1-a341-eb1a73dd5df7" />
 
*   **Giao dịch Voucher:** Cho phép chọn User và Voucher từ danh sách để thực hiện giảm giá. Hệ thống tự động trừ số lượng và kiểm tra hạn dùng.
  
 <img width="1920" height="1080" alt="Ảnh chụp màn hình 2026-05-08 195620" src="https://github.com/user-attachments/assets/489f720e-caed-4434-ba2f-23c2c2c70438" />
