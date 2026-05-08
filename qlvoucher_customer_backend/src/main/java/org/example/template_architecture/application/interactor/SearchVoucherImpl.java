package org.example.template_architecture.application.interactor;

import org.example.template_architecture.application.dto.response.VoucherResponse;
import org.example.template_architecture.application.input.ISearchVoucher;
import org.example.template_architecture.domain.repository.VoucherRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchVoucherImpl implements ISearchVoucher {

    private final VoucherRepository voucherRepository;

    public SearchVoucherImpl(VoucherRepository voucherRepository) {
        this.voucherRepository = voucherRepository;
    }

    @Override
    public List<VoucherResponse> execute(String code) {
        return voucherRepository.searchByCode(code).stream()
                .map(voucher -> new VoucherResponse(
                        voucher.getId(),
                        voucher.getCode(),
                        voucher.getDiscountPercent(),
                        voucher.getQuantity(),
                        voucher.getExpiredDate(),
                        voucher.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
