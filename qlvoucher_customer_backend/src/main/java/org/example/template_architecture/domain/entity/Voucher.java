package org.example.template_architecture.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class Voucher {
    private Long id;
    private String code;
    private int discountPercent;
    private int quantity;
    private LocalDate expiredDate;
    private String status;


    public Voucher() {}

    public Voucher(Long id, String code, int discountPercent, int quantity, LocalDate expiredDate, String status) {
        this.id = id;
        this.code = code;
        this.discountPercent = discountPercent;
        this.quantity = quantity;
        this.expiredDate = expiredDate;
        this.status = status;
    }


    public boolean canBeUsed() {
        return "ACTIVE".equals(this.status)
                && this.quantity > 0
                && this.expiredDate.isAfter(LocalDate.now());
    }

    public void decreaseQuantity() {
        if (this.quantity > 0) {
            this.quantity--;
        }
    }
}