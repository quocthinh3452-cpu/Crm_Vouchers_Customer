package org.example.template_architecture.infrastructure.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
//use jpa de truy van DB
@Repository
public interface VoucherJpaRepository extends JpaRepository<VoucherDbEntity, Long> {
    Optional<VoucherDbEntity> findByCode(String code);
    List<VoucherDbEntity> findByCodeContainingIgnoreCase(String code);
}