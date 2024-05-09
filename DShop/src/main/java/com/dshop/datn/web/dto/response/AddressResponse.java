package com.dshop.datn.web.dto.response;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class AddressResponse {
    private long id;

    private String fullName;

    private String email;

    private String phone;

    private String addressDetail;

    private String province;

    private String district;

    private String wards;

    private Date createdDate;

    private Date modifiedDate;

    private int focus;

    private int status;
}
