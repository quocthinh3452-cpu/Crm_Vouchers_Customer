package org.example.template_architecture.application.mapper;

import org.example.template_architecture.domain.entity.Voucher;
import org.example.template_architecture.infrastructure.persistence.VoucherDbEntity;
import org.springframework.stereotype.Component;

@Component
public class VoucherMapper {

    public VoucherDbEntity toDbEntity(Voucher domain) {
        if (domain == null) return null;
        VoucherDbEntity entity = new VoucherDbEntity();
        entity.setId(domain.getId());
        entity.setCode(domain.getCode());
        entity.setDiscountPercent(domain.getDiscountPercent());
        entity.setQuantity(domain.getQuantity());
        entity.setExpiredDate(domain.getExpiredDate());
        entity.setStatus(domain.getStatus());
        return entity;
    }

    public Voucher toDomain(VoucherDbEntity entity) {
        if (entity == null) return null;
        return new Voucher(
                entity.getId(),
                entity.getCode(),
                entity.getDiscountPercent(),
                entity.getQuantity(),
                entity.getExpiredDate(),
                entity.getStatus()
        );
    }
}