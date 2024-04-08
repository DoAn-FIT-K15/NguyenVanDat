package com.dshop.datn.web.dto.request;

import lombok.Data;

@Data
public class OrderItemRequest {
    private int quantity;
    private int sellPrice;
    private String productName;
    private String valueColor;
    private String valueSize;
}
