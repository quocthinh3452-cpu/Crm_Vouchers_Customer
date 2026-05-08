package org.example.template_architecture.infrastructure.persistence;

import org.example.template_architecture.application.mapper.VoucherMapper;
import org.example.template_architecture.domain.entity.Voucher;
import org.example.template_architecture.domain.repository.VoucherRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class VoucherRepositoryImpl implements VoucherRepository {

    private final VoucherJpaRepository jpaRepository;
    private final VoucherMapper mapper;

    public VoucherRepositoryImpl(VoucherJpaRepository jpaRepository, VoucherMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public Voucher save(Voucher voucher) {
        VoucherDbEntity entity = mapper.toDbEntity(voucher);
        VoucherDbEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Voucher> findById(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }
    @Override
    public Optional<Voucher> findByCode(String code){
        return jpaRepository.findByCode(code).map(mapper::toDomain);
    }

    @Override
    public List<Voucher> findAll(){
        return jpaRepository.findAll().stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    public List<Voucher> searchByCode(String code) {
        // Gọi hàm findByCodeContainingIgnoreCase mà chúng ta đã định nghĩa trong VoucherJpaRepository
        return jpaRepository.findByCodeContainingIgnoreCase(code).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}