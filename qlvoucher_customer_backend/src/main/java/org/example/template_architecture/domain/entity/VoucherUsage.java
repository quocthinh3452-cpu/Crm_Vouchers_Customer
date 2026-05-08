package org.example.template_architecture.domain.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class VoucherUsage {
    private Long id;
    private Long userId;
    private Long voucherId;
    private LocalDateTime usedAt;

    public VoucherUsage() {}

    public VoucherUsage(Long id, Long userId, Long voucherId, LocalDateTime usedAt) {
        this.id = id;
        this.userId = userId;
        this.voucherId = voucherId;
        this.usedAt = usedAt;
    }
}