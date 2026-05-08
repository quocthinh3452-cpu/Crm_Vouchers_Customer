package org.example.template_architecture.application.interactor;

import org.example.template_architecture.application.dto.request.VoucherRequest;
import org.example.template_architecture.application.dto.response.VoucherResponse;
import org.example.template_architecture.application.input.IUpdateVoucher;
import org.example.template_architecture.domain.entity.Voucher;
import org.example.template_architecture.domain.repository.VoucherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UpdateVoucherImpl implements IUpdateVoucher {

    private final VoucherRepository voucherRepository;

    public UpdateVoucherImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    @Transactional
    public VoucherResponse execute(Long id, VoucherRequest request) {
        // 1. Kiểm tra Voucher có tồn tại không
        Voucher existingVoucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Voucher với ID: " + id));

        // 2. Kiểm tra trùng Code (nếu User thay đổi Code khác với ban đầu)
        if (!existingVoucher.getCode().equals(request.getCode())) {
            Optional<Voucher> checkDuplicate = voucherRepository.findByCode(request.getCode());
            if (checkDuplicate.isPresent()) {
                throw new RuntimeException("Code voucher đã tồn tại: " + request.getCode());
            }
        }

        // 3. Cập nhật thông tin trên Domain Model (Cần bổ sung các Setter trong class Voucher)
        existingVoucher.setCode(request.getCode());
        existingVoucher.setDiscountPercent(request.getDiscountPercent());
        existingVoucher.setQuantity(request.getQuantity());
        existingVoucher.setExpiredDate(request.getExpiredDate());

        // 4. Lưu lại
        Voucher updatedVoucher = voucherRepository.save(existingVoucher);

        // 5. Trả về kết quả
        return new VoucherResponse(
                updatedVoucher.getId(),
                updatedVoucher.getCode(),
                updatedVoucher.getDiscountPercent(),
                updatedVoucher.getQuantity(),
                updatedVoucher.getExpiredDate(),
                updatedVoucher.getStatus()
        );
    }
}
