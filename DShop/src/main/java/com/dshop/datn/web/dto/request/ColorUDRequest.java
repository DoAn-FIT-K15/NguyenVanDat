package com.dshop.datn.web.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class ColorUDRequest {
    private long id;

    private String value;

    private List<SizeUDRequest> sizes;

    private long productId;
}
