package com.dshop.datn.web.admin;

import com.dshop.datn.helper.ApiResponse;
import com.dshop.datn.services.ProductImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/productImage")
public class AProductImageRest {
    private final ProductImageService productImageService;

    public AProductImageRest(ProductImageService productImageService) {
        this.productImageService = productImageService;
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") long id) {
        try {
            productImageService.delete(id);
            return new ResponseEntity<>(ApiResponse.build(200, true, "thành công", "Xóa ảnh sản phẩm thành công!"), HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Xóa ảnh sản phẩm không thành công! Lỗi " + e.getMessage());
        }
    }
}
