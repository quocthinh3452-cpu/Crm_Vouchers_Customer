package org.example.template_architecture.application.dto.response;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class VoucherResponse {
    private Long id;
    private String code;
    private int discountPercent;
    private int quantity;
    private LocalDate expiredDate;
    private String status;

    // Constructors
    public VoucherResponse() {}

    public VoucherResponse(Long id, String code, int discountPercent, int quantity, LocalDate expiredDate, String status) {
        this.id = id;
        this.code = code;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.expiredDate = expiredDate;
        this.status = status;
    }
}
