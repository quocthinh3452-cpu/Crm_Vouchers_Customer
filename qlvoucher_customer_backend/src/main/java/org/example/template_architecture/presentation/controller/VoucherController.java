package org.example.template_architecture.presentation.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import jakarta.validation.Valid;
import org.example.template_architecture.application.dto.request.VoucherRequest;
import org.example.template_architecture.application.dto.response.VoucherResponse;
import org.example.template_architecture.application.input.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vouchers")
@CrossOrigin(origins = "*")// mở khóa cros và cho phép tất cả các nguồn gọi api
public class VoucherController {

    // Tiêm (Inject) các Use Case thông qua Interface
    private final ICreateVoucher createVoucher;
    private final IUpdateVoucher updateVoucher;
    private final IDeleteVoucher deleteVoucher;
    private final IGetAllVouchers getAllVouchers;
    private final ISearchVoucher searchVoucher;

    public VoucherController(
            ICreateVoucher createVoucher,
            IUpdateVoucher updateVoucher,
            IDeleteVoucher deleteVoucher,
            IGetAllVouchers getAllVouchers,
            ISearchVoucher searchVoucher) {
        this.createVoucher = createVoucher;
        this.updateVoucher = updateVoucher;
        this.deleteVoucher = deleteVoucher;
        this.getAllVouchers = getAllVouchers;
        this.searchVoucher = searchVoucher;
    }

    // 1. Lấy danh sách voucher
    @GetMapping
    public ResponseEntity<List<VoucherResponse>> getAll() {
        return ResponseEntity.ok(getAllVouchers.execute());
    }

    // 2. Tìm kiếm voucher theo code (VD: /vouchers/search?code=ABC)
    @GetMapping("/search")
    public ResponseEntity<List<VoucherResponse>> search(@RequestParam String code) {
        return ResponseEntity.ok(searchVoucher.execute(code));
    }

    // 3. Tạo voucher mới (Nhớ thêm @Valid để kích hoạt validation trong DTO)
    @PostMapping
    public ResponseEntity<VoucherResponse> create(@Valid @RequestBody VoucherRequest request) {
        VoucherResponse response = createVoucher.execute(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 4. Cập nhật voucher
    @PutMapping("/{id}")
    public ResponseEntity<VoucherResponse> update(@PathVariable Long id, @Valid @RequestBody VoucherRequest request) {
        return ResponseEntity.ok(updateVoucher.execute(id, request));
    }

    // 5. Xóa voucher
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteVoucher.execute(id);
        return ResponseEntity.noContent().build();
    }
}
