package org.example.template_architecture.infrastructure.persistence;

import org.example.template_architecture.application.mapper.VoucherUsageMapper;
import org.example.template_architecture.domain.entity.VoucherUsage;
import org.example.template_architecture.domain.repository.VoucherUsageRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class VoucherUsageRepositoryImpl implements VoucherUsageRepository {

    private final VoucherUsageJpaRepository jpaRepository;
    private final VoucherUsageMapper mapper;

    public VoucherUsageRepositoryImpl(VoucherUsageJpaRepository jpaRepository, VoucherUsageMapper mapper) {
        this.jpaRepository = jpaRepository;
        this.mapper = mapper;
    }

    @Override
    public VoucherUsage save(VoucherUsage voucherUsage) {
        VoucherUsageDbEntity entity = mapper.toDbEntity(voucherUsage);
        VoucherUsageDbEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public List<VoucherUsage> findByUserId(Long userId) {
        return jpaRepository.findByUserId(userId).stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<VoucherUsage> findAll() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }
}