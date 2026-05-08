package org.example.template_architecture.application.interactor;

import org.example.template_architecture.application.dto.request.UseVoucherRequest;
import org.example.template_architecture.application.input.IUseVoucher; // Nhớ tạo interface này nhé
import org.example.template_architecture.domain.entity.User;
import org.example.template_architecture.domain.entity.Voucher;
import org.example.template_architecture.domain.entity.VoucherUsage;
import org.example.template_architecture.domain.repository.UserRepository;
import org.example.template_architecture.domain.repository.VoucherRepository;
import org.example.template_architecture.domain.repository.VoucherUsageRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class UseVoucherImpl implements IUseVoucher {

    private final UserRepository userRepository;
    private final VoucherRepository voucherRepository;
    private final VoucherUsageRepository voucherUsageRepository;

    public UseVoucherImpl(UserRepository userRepository,
                          VoucherRepository voucherRepository,
                          VoucherUsageRepository voucherUsageRepository) {
        this.userRepository = userRepository;
        this.voucherRepository = voucherRepository;
        this.voucherUsageRepository = voucherUsageRepository;
    }

    @Override
    @Transactional // RẤT QUAN TRỌNG
    public String execute(UseVoucherRequest request) {
        // 1. Kiểm tra User có tồn tại không?
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy User!"));

        // 2. Kiểm tra Voucher có tồn tại không?
        Voucher voucher = voucherRepository.findById(request.getVoucherId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy Voucher!"));

        // 3. Kiểm tra các điều kiện (Business Rules)
        if (!"ACTIVE".equals(voucher.getStatus())) {
            throw new RuntimeException("Voucher này đang bị khóa (INACTIVE)!");
        }
        if (voucher.getQuantity() <= 0) {
            throw new RuntimeException("Voucher này đã hết lượt sử dụng!");
        }
        if (voucher.getExpiredDate().isBefore(LocalDate.now())) {
            throw new RuntimeException("Voucher này đã hết hạn!");
        }

        // 4. Thực thi nghiệp vụ: Giảm quantity của Voucher đi 1
        voucher.setQuantity(voucher.getQuantity() - 1);
        voucherRepository.save(voucher); // Cập nhật lại vào DB

        // 5. Lưu lịch sử sử dụng
        VoucherUsage usage = new VoucherUsage(null, user.getId(), voucher.getId(), LocalDateTime.now());
        voucherUsageRepository.save(usage);

        return "Sử dụng voucher thành công!";
    }
}