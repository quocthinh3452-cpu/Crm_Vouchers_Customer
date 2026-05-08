package org.example.template_architecture.domain.repository;

import org.example.template_architecture.domain.entity.Voucher;
import java.util.List;
import java.util.Optional;

public interface VoucherRepository {
    Voucher save(Voucher voucher);
    Optional<Voucher> findById(Long id);
    Optional<Voucher> findByCode(String code);
    List<Voucher> findAll();
    List<Voucher> searchByCode(String code);
    void deleteById(Long id);
}
