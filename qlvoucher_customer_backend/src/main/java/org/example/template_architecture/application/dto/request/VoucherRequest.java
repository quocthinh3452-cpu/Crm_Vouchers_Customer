package org.example.template_architecture.application.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class VoucherRequest {

    @NotBlank(message = "Code voucher không được để trống")
    private String code;

    @NotNull(message = "Discount percent không được để trống")
    @Min(value = 1, message = "Discount percent phải từ 1 đến 100")
    @Max(value = 100, message = "Discount percent phải từ 1 đến 100")
    private Integer discountPercent;

    @NotNull(message = "Quantity không được để trống")
    @Min(value = 0, message = "Quantity phải lớn hơn hoặc bằng 0")
    private Integer quantity;

    @NotNull(message = "Ngày hết hạn không được để trống")
    @Future(message = "Expired date phải lớn hơn ngày hiện tại")
    private LocalDate expiredDate;

    // Getters and Setters
    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }
    public Integer getDiscountPercent() { return discountPercent; }
    public void setDiscountPercent(Integer discountPercent) { this.discountPercent = discountPercent; }
    public Integer getQuantity() { return quantity; }
    public void setQuantity(Integer quantity) { this.quantity = quantity; }
    public LocalDate getExpiredDate() { return expiredDate; }
    public void setExpiredDate(LocalDate expiredDate) { this.expiredDate = expiredDate; }
}