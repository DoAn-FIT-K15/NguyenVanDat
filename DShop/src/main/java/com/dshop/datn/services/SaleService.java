package com.dshop.datn.services;

import com.dshop.datn.web.dto.request.SaleRequest;
import com.dshop.datn.web.dto.response.SaleResponse;
import org.springframework.data.util.Pair;

import java.util.List;

public interface SaleService {
    SaleResponse getSale(Long id);

    SaleResponse getSaleByName(String name);

    SaleResponse create(SaleRequest saleRequest);

    SaleResponse update(long id, SaleRequest saleRequest);

    Pair<List<SaleResponse>, Integer> getAll(String keyword, Integer isActive, int pageNo, int pageSize, String sortBy, boolean desc);

    void delete(long id);

    String addProductsToSale(Long id, List<Long> productIds);

    String removeProductsFromSale(Long id, List<Long> productIds);

    String hideSale(Long id);

    String showSale(Long id);

}
