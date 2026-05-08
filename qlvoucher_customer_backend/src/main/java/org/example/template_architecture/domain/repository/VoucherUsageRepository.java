package org.example.template_architecture.domain.repository;

import org.example.template_architecture.domain.entity.VoucherUsage;
import java.util.List;

public interface VoucherUsageRepository {
    VoucherUsage save(VoucherUsage voucherUsage);
    List<VoucherUsage> findByUserId(Long userId);
    List<VoucherUsage> findAll();
}