package org.example.template_architecture.presentation.controller;

import jakarta.validation.Valid;
import org.example.template_architecture.application.dto.request.UseVoucherRequest;
import org.example.template_architecture.application.input.IUseVoucher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/voucher-usages")
@CrossOrigin(origins = "*")
public class VoucherUsageController {

    private final IUseVoucher useVoucher;

    public VoucherUsageController(IUseVoucher useVoucher) {
        this.useVoucher = useVoucher;
    }

    @PostMapping
    public ResponseEntity<Map<String, String>> useVoucher(@Valid @RequestBody UseVoucherRequest request) {
        String result = useVoucher.execute(request);

        // Trả về JSON thông báo thành công
        Map<String, String> response = new HashMap<>();
        response.put("message", result);
        return ResponseEntity.ok(response);
    }
}