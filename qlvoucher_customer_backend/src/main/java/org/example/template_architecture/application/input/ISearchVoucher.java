package org.example.template_architecture.application.input;

import org.example.template_architecture.application.dto.response.VoucherResponse;

import java.util.List;

public interface ISearchVoucher {
    List<VoucherResponse> execute(String keyword);
}
