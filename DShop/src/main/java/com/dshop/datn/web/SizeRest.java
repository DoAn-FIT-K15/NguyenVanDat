package com.dshop.datn.web;

import com.dshop.datn.helper.ApiResponse;
import com.dshop.datn.models.dtos.SizeDto;
import com.dshop.datn.services.SizeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/size")
public class SizeRest {
    private final SizeService sizeService;

    public SizeRest(SizeService sizeService) {
        this.sizeService = sizeService;
    }
    @GetMapping("")
    public ResponseEntity<?> getAllSizeValues() {
        try {
            List<SizeDto> size = sizeService.getAllValueSize();
            if (size != null) {
                return new ResponseEntity<>(ApiResponse.build(200, true, "thành công", size), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ApiResponse.build(201, false, "thất bại", null), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi " + e.getMessage());
        }
    }
}
