package org.example.template_architecture.application.dto.request;

import jakarta.validation.constraints.NotNull;

public class UseVoucherRequest {
    @NotNull(message = "User ID không được để trống")
    private Long userId;

    @NotNull(message = "Voucher ID không được để trống")
    private Long voucherId;

    // Getters and Setters
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public Long getVoucherId() { return voucherId; }
    public void setVoucherId(Long voucherId) { this.voucherId = voucherId; }
}