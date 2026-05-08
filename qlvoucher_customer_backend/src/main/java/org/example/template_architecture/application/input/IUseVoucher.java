package org.example.template_architecture.application.input;

import org.example.template_architecture.application.dto.request.UseVoucherRequest;

public interface IUseVoucher {
    String execute(UseVoucherRequest request);
}