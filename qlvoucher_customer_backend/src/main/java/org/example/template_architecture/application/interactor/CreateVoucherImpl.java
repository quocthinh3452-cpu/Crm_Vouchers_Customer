package org.example.template_architecture.application.interactor;

import org.example.template_architecture.application.dto.request.VoucherRequest;
import org.example.template_architecture.application.dto.response.VoucherResponse;
import org.example.template_architecture.application.input.ICreateVoucher;
import org.example.template_architecture.domain.entity.Voucher;
import org.example.template_architecture.domain.repository.VoucherRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateVoucherImpl implements ICreateVoucher {

    private final VoucherRepository voucherRepository;

    // Inject Domain Repository vào Use Case
    public CreateVoucherImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    @Transactional
    public VoucherResponse execute(VoucherRequest request) {
        // 1. Kiểm tra nghiệp vụ (Check trùng code)
        if (voucherRepository.findByCode(request.getCode()).isPresent()) {
            // Lý tưởng nhất là ném ra một Custom Exception (VD: DuplicateVoucherCodeException)
            // Tạm thời dùng RuntimeException để bạn dễ hình dung luồng
            throw new RuntimeException("Code voucher đã tồn tại: " + request.getCode());
        }

        // 2. Map dữ liệu từ Request sang Domain Entity
        // Mặc định khi tạo mới, status là ACTIVE
        Voucher newVoucher = new Voucher(
                null, // ID tự tăng nên để null
                request.getCode(),
                request.getDiscountPercent(),
                request.getQuantity(),
                request.getExpiredDate(),
                "ACTIVE"
        );

        // 3. Lưu xuống database thông qua Repository
        Voucher savedVoucher = voucherRepository.save(newVoucher);

        // 4. Map kết quả sang Response DTO trả về cho client
        return new VoucherResponse(
                savedVoucher.getId(),
                savedVoucher.getCode(),
                savedVoucher.getDiscountPercent(),
                savedVoucher.getQuantity(),
                savedVoucher.getExpiredDate(),
                savedVoucher.getStatus()
        );
    }
}
