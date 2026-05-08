package org.example.template_architecture.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VoucherUsageJpaRepository extends JpaRepository<VoucherUsageDbEntity, Long> {
    // Tìm lịch sử sử dụng theo ID người dùng
    List<VoucherUsageDbEntity> findByUserId(Long userId);
}