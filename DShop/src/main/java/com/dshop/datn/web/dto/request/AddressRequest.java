package com.dshop.datn.web.dto.request;

import lombok.Data;

@Data
public class AddressRequest {
    private String fullName;

    private String phone;

    private String addressDetail;

    private String province;

    private String district;

    private String wards;

    private Long userId;
}
