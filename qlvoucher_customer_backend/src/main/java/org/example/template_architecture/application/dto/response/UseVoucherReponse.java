package org.example.template_architecture.application.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UseVoucherReponse {
    private Long id;
    private Long userId;
    private Long voucherId;
    private LocalDateTime usedAt;

    public UseVoucherReponse() {
    }

    public UseVoucherReponse(Long id, Long userId, Long voucherId, LocalDateTime usedAt) {
        this.id = id;
        this.userId = userId;
        this.voucherId = voucherId;
        this.usedAt = usedAt;
    }
}
