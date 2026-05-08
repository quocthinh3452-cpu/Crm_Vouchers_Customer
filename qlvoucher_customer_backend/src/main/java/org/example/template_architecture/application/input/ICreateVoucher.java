package org.example.template_architecture.application.input;

import org.example.template_architecture.application.dto.request.VoucherRequest;
import org.example.template_architecture.application.dto.response.VoucherResponse;

public interface ICreateVoucher {
    VoucherResponse execute(VoucherRequest request);
}
