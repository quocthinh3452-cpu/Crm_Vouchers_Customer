package org.example.template_architecture.application.interactor;

import org.example.template_architecture.application.input.IDeleteVoucher;
import org.example.template_architecture.domain.entity.Voucher;
import org.example.template_architecture.domain.repository.VoucherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeleteVoucherImpl implements IDeleteVoucher {

    private final VoucherRepository voucherRepository;

    public DeleteVoucherImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    @Transactional
    public void execute(Long id) {
        // Kiểm tra xem có tồn tại không trước khi xóa
        Voucher existingVoucher = voucherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Voucher với ID: " + id));

        voucherRepository.deleteById(id);
    }
}
