package org.example.template_architecture.application.mapper;

import org.example.template_architecture.domain.entity.VoucherUsage;
import org.example.template_architecture.infrastructure.persistence.VoucherUsageDbEntity;
import org.springframework.stereotype.Component;

@Component
public class VoucherUsageMapper {

    public VoucherUsageDbEntity toDbEntity(VoucherUsage domain) {
        if (domain == null) return null;
        VoucherUsageDbEntity entity = new VoucherUsageDbEntity();
        entity.setId(domain.getId());
        entity.setUserId(domain.getUserId());
        entity.setVoucherId(domain.getVoucherId());
        // usedAt thường để DB tự sinh hoặc gán lúc tạo
        return entity;
    }

    public VoucherUsage toDomain(VoucherUsageDbEntity entity) {
        if (entity == null) return null;
        return new VoucherUsage(
                entity.getId(),
                entity.getUserId(),
                entity.getVoucherId(),
                entity.getUsedAt()
        );
    }
}