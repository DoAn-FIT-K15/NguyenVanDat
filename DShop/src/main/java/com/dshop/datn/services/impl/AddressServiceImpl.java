package com.dshop.datn.services.impl;

import com.dshop.datn.repositories.AddressRepository;
import com.dshop.datn.web.dto.request.AddressRequest;
import com.dshop.datn.web.dto.response.AddressResponse;
import com.dshop.datn.mapper.AddressMapper;
import com.dshop.datn.models.Address;
import com.dshop.datn.services.AddressService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;
    public AddressServiceImpl(AddressRepository addressRepository, AddressMapper addressMapper) {
        this.addressRepository = addressRepository;
        this.addressMapper = addressMapper;
    }

    @Override
    @Transactional
    public AddressResponse createAddress(AddressRequest addressRequest) {
        Address address = addressMapper.mapToModel(addressRequest);
        address.setStatus(1);
        address.setFocus(0);
        Date currentDate = new Date();
        address.setCreatedDate(currentDate);
        address.setModifiedDate(currentDate);
        addressRepository.save(address);
        return addressMapper.mapToResponse(address);
    }

    @Override
    public AddressResponse updateAddress(long addressId, AddressRequest addressRequest) {
        Address address = addressRepository.findById(addressId).orElseThrow();
        addressMapper.updateModel(address, addressRequest);
        Date currentDate = new Date();
        address.setModifiedDate(currentDate);
        addressRepository.save(address);
        return addressMapper.mapToResponse(address);
    }
    @Override
    public void deleteAddress(long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow();
        addressRepository.delete(address);
    }

    @Override
    public List<AddressResponse> getAddressByUser(long userId) {
        List<Address> addresses = addressRepository.findByUserId(userId);
        return addresses.stream()
                .map(addressMapper::mapToResponse)
                .toList();
    }
}
