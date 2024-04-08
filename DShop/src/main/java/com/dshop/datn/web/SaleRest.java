package com.dshop.datn.web;

import com.dshop.datn.helper.ApiResponse;
import com.dshop.datn.services.SaleService;
import com.dshop.datn.web.dto.response.SaleResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sale")
public class SaleRest {
    private final SaleService saleService;

    public SaleRest(SaleService saleService) {
        this.saleService = saleService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getSale(@PathVariable(name = "id") Long id) {
        try {
            SaleResponse saleResponse = saleService.getSale(id);
            if (saleResponse != null) {
                return new ResponseEntity<>(ApiResponse.build(200, true, "thành công", saleResponse), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(ApiResponse.build(201, false, "thành công", null), HttpStatus.OK);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Lỗi " + e.getMessage());
        }
    }

//    @GetMapping("")
//    public ResponseEntity<?> getAll(@RequestParam(required = false) String keyword,
//                                    @RequestParam(value = "pageNo", defaultValue = "0") int pageNo,
//                                    @RequestParam(value = "pageSize", defaultValue = "20") int pageSize,
//                                    @RequestParam(value = "sortBy", defaultValue = "id") String sortBy) {
//        try {
//            List<SaleResponse> saleResponses = saleService.getAll(keyword, pageNo, pageSize, sortBy);
//            if (saleResponses != null) {
//                int total = saleResponses.size();
//                List<Object> data = new ArrayList<>(saleResponses);
//                return new ResponseEntity<>(ApiResponsePage.build(200, true, pageNo, pageSize, total, "Lấy danh sách thành công", data), HttpStatus.OK);
//            } else {
//                int total = 0;
//                return new ResponseEntity<>(ApiResponsePage.build(201, true, pageNo, pageSize, total, "Lấy danh sách thành công", null), HttpStatus.OK);
//            }
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Lỗi! " + e.getMessage());
//        }
//    }
}
