package com.dshop.datn.services;

import com.dshop.datn.web.dto.request.AddressRequest;
import com.dshop.datn.web.dto.response.AddressResponse;

import java.util.List;

public interface AddressService {
    AddressResponse createAddress(AddressRequest addressRequest);
    AddressResponse updateAddress(long addressId, AddressRequest addressRequest);
    void deleteAddress(long addressId);
    List<AddressResponse> getAddressByUser(long userId);
}
